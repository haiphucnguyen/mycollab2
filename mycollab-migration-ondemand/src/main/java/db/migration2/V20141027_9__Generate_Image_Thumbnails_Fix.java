package db.migration2;

import com.mycollab.core.utils.ImageUtil;
import com.mycollab.core.utils.MimeTypesUtil;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.ecm.domain.Content;
import com.mycollab.module.ecm.domain.Folder;
import com.mycollab.module.ecm.domain.Resource;
import com.mycollab.module.ecm.service.ContentJcrDao;
import com.mycollab.module.ecm.service.ResourceService;
import com.mycollab.module.file.service.RawContentService;
import com.mycollab.spring.AppContextUtil;
import org.apache.commons.collections.CollectionUtils;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class V20141027_9__Generate_Image_Thumbnails_Fix implements
        SpringJdbcMigration {

    private static final Logger LOG = LoggerFactory
            .getLogger(V20141027_9__Generate_Image_Thumbnails_Fix.class);

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        List<Map<String, Object>> accounts = jdbcTemplate
                .queryForList("SELECT id FROM s_account ");
        for (Map<String, Object> row : accounts) {
            Integer accountId = (Integer) row.get("id");
            generateImageThumbnail(accountId, accountId + "");
        }
    }

    private void generateImageThumbnail(Integer accountId, String path) {
        ResourceService resourceService = AppContextUtil
                .getSpringBean(ResourceService.class);

        RawContentService rawContentService = AppContextUtil
                .getSpringBean(RawContentService.class);

        ContentJcrDao contentJcrDao = AppContextUtil
                .getSpringBean(ContentJcrDao.class);

        List<Resource> resources = resourceService.getResources(path);
        if (CollectionUtils.isNotEmpty(resources)) {
            for (Resource resource : resources) {
                if (resource instanceof Content) {
                    Content content = (Content) resource;
                    String mimeType = MimeTypesUtil.detectMimeType(content
                            .getPath());
                    LOG.info("Check mimetype " + mimeType + " of content "
                            + content.getPath() + "--" + content.getThumbnail()
                            + ".");
                    if (MimeTypesUtil.isImage(mimeType)) {
                        try {
                            BufferedImage image = ImageUtil.generateImageThumbnail(resourceService
                                    .getContentStream(resource.getPath()));
                            String thumbnailPath = String.format(".thumbnail/%d/%s.%s", accountId,
                                    StringUtils.generateSoftUniqueId(), "png");
                            content.setThumbnail(thumbnailPath);
                            contentJcrDao.saveContent(content, "");

                            File tmpFile = File.createTempFile("tmp", "png");
                            ImageIO.write(image, "png", new FileOutputStream(
                                    tmpFile));

                            rawContentService.saveContent(thumbnailPath,
                                    new FileInputStream(tmpFile));
                        } catch (Exception e) {
                            LOG.error(
                                    "Generate thumbnal is failed "
                                            + resource.getPath(), e);
                        }
                    }
                } else if (resource instanceof Folder) {
                    generateImageThumbnail(accountId, resource.getPath());
                }
            }
        }
    }
}