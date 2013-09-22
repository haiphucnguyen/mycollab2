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
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.MimeTypesUtil;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.module.file.service.impl.AmazonRawContentServiceImpl;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V1_56__Adjust_Repo_Structure implements SpringJdbcMigration {
	private static Logger log = LoggerFactory
			.getLogger(V1_56__Adjust_Repo_Structure.class);
	private ContentJcrDao contentJcrDao;

	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		RawContentService rawContentService = ApplicationContextUtil
				.getSpringBean(RawContentService.class);
		contentJcrDao = ApplicationContextUtil
				.getSpringBean(ContentJcrDao.class);

		if (rawContentService instanceof AmazonRawContentServiceImpl) {
			S3StorageConfiguration storageConfiguration = (S3StorageConfiguration) SiteConfiguration
					.getStorageConfiguration();
			AmazonS3 s3client = storageConfiguration.newS3Client();
			ObjectListing listObjects = s3client.listObjects(
					storageConfiguration.getBucket(), "/");
			for (S3ObjectSummary objectSummary : listObjects
					.getObjectSummaries()) {
				Content content = new Content();
				content.setMimeType(MimeTypesUtil.detectMimeType(objectSummary
						.getKey()));
				content.setSize(objectSummary.getSize());
				content.setPath(objectSummary.getKey());
				log.debug("Save content {}", BeanUtility.printBeanObj(content));
				contentJcrDao.saveContent(content, "");
			}

		} else {
			File baseFolder = FileStorageConfiguration.baseContentFolder;
			migrateAccountFiles(jdbcTemplate, baseFolder);
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
						e.printStackTrace();
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
				content.setPath(file.getAbsolutePath().substring(
						rootFolder.getAbsolutePath().length() + 1));
				contentJcrDao.saveContent(content, "");
			}
		}
	}

}
