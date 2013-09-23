package db.migration;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.esofthead.mycollab.configuration.FileStorageConfiguration;
import com.esofthead.mycollab.configuration.S3StorageConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.MimeTypesUtil;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_57__Adjust_Repo_Structure_2 implements SpringJdbcMigration {
	private static Logger log = LoggerFactory
			.getLogger(V1_57__Adjust_Repo_Structure_2.class);
	private ContentJcrDao contentJcrDao;

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		RawContentService rawContentService = ApplicationContextUtil
				.getSpringBean(RawContentService.class);
		contentJcrDao = ApplicationContextUtil
				.getSpringBean(ContentJcrDao.class);

		log.debug("Content service {} in mode ", rawContentService,
				SiteConfiguration.getDeploymentMode());

		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			migrateS3AccountFiles(jdbcTemplate);
			migrateS3AvatarFiles();

		} else {
			File baseFolder = FileStorageConfiguration.baseContentFolder;
			migrateAccountFiles(jdbcTemplate, baseFolder);
		}

	}

	private void migrateS3AccountFiles(JdbcTemplate jdbcTemplate) {
		S3StorageConfiguration storageConfiguration = (S3StorageConfiguration) SiteConfiguration
				.getStorageConfiguration();
		AmazonS3 s3client = storageConfiguration.newS3Client();
		ObjectListing listObjects = s3client.listObjects(
				storageConfiguration.getBucket(), "1");
		Long totalSize = 0L;

		for (S3ObjectSummary objectSummary : listObjects.getObjectSummaries()) {
			Content content = new Content();
			content.setMimeType(MimeTypesUtil.detectMimeType(objectSummary
					.getKey()));
			content.setSize(objectSummary.getSize());
			content.setPath(objectSummary.getKey());
			content.setContentPath(objectSummary.getKey());
			log.debug("Save content {}", BeanUtility.printBeanObj(content));
			contentJcrDao.saveContent(content, "");

			totalSize += objectSummary.getSize();
		}
		String insertSql = "INSERT INTO `m_ecm_driveinfo` (`sAccountId`, `usedVolume`) VALUES ('%d', '%d')";
		insertSql = String.format(insertSql, 1, totalSize);
		jdbcTemplate.execute(insertSql);
	}

	private void migrateS3AvatarFiles() {
		S3StorageConfiguration storageConfiguration = (S3StorageConfiguration) SiteConfiguration
				.getStorageConfiguration();
		AmazonS3 s3client = storageConfiguration.newS3Client();
		ObjectListing listObjects = s3client.listObjects(
				storageConfiguration.getBucket(), "avatar");

		for (S3ObjectSummary objectSummary : listObjects.getObjectSummaries()) {
			Content content = new Content();
			content.setMimeType(MimeTypesUtil.detectMimeType(objectSummary
					.getKey()));
			content.setSize(objectSummary.getSize());
			content.setPath(objectSummary.getKey());
			content.setContentPath(objectSummary.getKey());
			log.debug("Save content {}", BeanUtility.printBeanObj(content));
			contentJcrDao.saveContent(content, "");
		}
	}

	private void migrateAccountFiles(JdbcTemplate jdbcTemplate, File baseFolder) {
		if (baseFolder.exists()) {
			if (baseFolder.isDirectory()) {
				File[] listFiles = baseFolder.listFiles();
				for (File subfile : listFiles) {
					int accountId = 0;
					try {
						accountId = Integer.parseInt(subfile.getName());
					} catch (Exception e) {
						migrateFilesStorage(baseFolder, subfile);
						continue;
					}
					migrateFilesStorage(baseFolder, subfile);
					long accountSize = FileUtils.sizeOfDirectory(subfile);
					String insertSql = "INSERT INTO `m_ecm_driveinfo` (`sAccountId`, `usedVolume`) VALUES ('%d', '%d')";
					insertSql = String
							.format(insertSql, accountId, accountSize);
					jdbcTemplate.execute(insertSql);
				}
			}
		}
	}

	private void migrateFilesStorage(File rootFolder, File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				Folder folder = new Folder();
				String folderPath = file.getAbsolutePath().substring(
						rootFolder.getAbsolutePath().length() + 1);
				folder.setPath(folderPath);
				contentJcrDao.createFolder(folder, "");
				File[] listFiles = file.listFiles();
				for (File subfile : listFiles) {
					migrateFilesStorage(rootFolder, subfile);
				}
			} else {
				Content content = new Content();
				content.setMimeType(MimeTypesUtil.detectMimeType(file
						.getAbsolutePath()));
				content.setSize(file.length());
				String path = file.getAbsolutePath().substring(
						rootFolder.getAbsolutePath().length() + 1);
				content.setPath(path);
				content.setContentPath(path);
				contentJcrDao.saveContent(content, "");
				log.debug("Save content {}", BeanUtility.printBeanObj(content));
			}
		}
	}
}
