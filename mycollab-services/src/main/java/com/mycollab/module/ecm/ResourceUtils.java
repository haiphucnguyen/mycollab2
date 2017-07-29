package com.mycollab.module.ecm;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.module.ecm.domain.ExternalContent;
import com.mycollab.module.ecm.domain.ExternalDrive;
import com.mycollab.module.ecm.domain.ExternalFolder;
import com.mycollab.module.ecm.domain.Resource;
import com.mycollab.module.ecm.service.DropboxResourceService;
import com.mycollab.module.ecm.service.ExternalResourceService;
import com.mycollab.spring.AppContextUtil;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Utility class of processing MyCollab resources.
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ResourceUtils {

    /**
     * @param resourceType
     * @return
     */
    public static ExternalResourceService getExternalResourceService(ResourceType resourceType) {
        if (ResourceType.Dropbox == resourceType) {
            return AppContextUtil.getSpringBean(DropboxResourceService.class);
        } else {
            throw new MyCollabException("Current support only dropbox resource service");
        }
    }

    /**
     * @param resource
     * @return
     */
    public static ExternalDrive getExternalDrive(Resource resource) {
        if (resource instanceof ExternalFolder) {
            return ((ExternalFolder) resource).getExternalDrive();
        } else if (resource instanceof ExternalContent) {
            return ((ExternalContent) resource).getExternalDrive();
        }
        return null;
    }

    /**
     * @param resource
     * @return
     */
    public static ResourceType getType(Resource resource) {
        if (!resource.isExternalResource()) {
            return ResourceType.MyCollab;
        } else {
            try {
                String storageName = (String) PropertyUtils.getProperty(resource, "storageName");
                if (StorageNames.DROPBOX.equals(storageName)) {
                    return ResourceType.Dropbox;
                } else {
                    throw new Exception("Current support only dropbox resource service");
                }
            } catch (Exception e) {
                throw new MyCollabException("Can not define sotrage name of bean " + BeanUtility.printBeanObj(resource));
            }
        }
    }
}
