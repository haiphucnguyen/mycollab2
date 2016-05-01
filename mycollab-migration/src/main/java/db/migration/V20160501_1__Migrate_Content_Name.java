package db.migration;

import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class V20160501_1__Migrate_Content_Name implements SpringJdbcMigration {
    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ResourceService resourceService = ApplicationContextUtil.getSpringBean(ResourceService.class);
                    if (resourceService != null) {
                        List<Resource> resources = resourceService.getResources("");
                        migrateContentNames(resourceService, resources);
                        break;
                    } else {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            }
        }).start();
    }

    private void migrateContentNames(ResourceService resourceService, List<Resource> resources) {
        for (Resource resource : resources) {
            String oldPath = resource.getPath();
            int lastIndex = oldPath.lastIndexOf('/');
            if (lastIndex > -1) {
                String fileName = oldPath.substring(lastIndex + 1);
                fileName = FileUtils.escape(fileName);
                String newPath = oldPath.substring(0, lastIndex+1) + fileName;
                if (!oldPath.equals(newPath)) {
                    resourceService.rename(oldPath, newPath, "");
                    resource.setPath(newPath);
                }
            }
            List<Resource> subResources = resourceService.getResources(resource.getPath());
            migrateContentNames(resourceService, subResources);
        }
    }
}
