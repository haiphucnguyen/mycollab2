package com.mycollab.module.ecm.service.impl;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.ecm.ResourceType;
import com.mycollab.module.ecm.ResourceUtils;
import com.mycollab.module.ecm.domain.Content;
import com.mycollab.module.ecm.domain.Folder;
import com.mycollab.module.ecm.domain.Resource;
import com.mycollab.module.ecm.service.ExternalResourceService;
import com.mycollab.module.ecm.service.ResourceMover;
import com.mycollab.module.ecm.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ResourceMoverImpl implements ResourceMover {

    @Autowired
    private ResourceService resourceService;

    private void moveResourceInDifferentStorage(Resource srcRes, Resource destRes, String userMove, Integer sAccountId) {
        String destMovePath = destRes.getPath() + "/" + srcRes.getName();

        if (srcRes instanceof Folder) {
            Folder createdFolder;
            List<Resource> lstRes;

            if (ResourceUtils.INSTANCE.getType(destRes) != ResourceType.MyCollab) {
                ExternalResourceService destService = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(destRes));
                createdFolder = destService.createNewFolder(ResourceUtils.INSTANCE.getExternalDrive(destRes), destMovePath);
            } else {
                createdFolder = resourceService.createNewFolder(destRes.getPath(), srcRes.getName(), "", userMove);
            }

            if (ResourceUtils.INSTANCE.getType(srcRes) != ResourceType.MyCollab) {
                ExternalResourceService srcService = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(srcRes));
                lstRes = srcService.getResources(ResourceUtils.INSTANCE.getExternalDrive(srcRes), srcRes.getPath());
            } else {
                lstRes = resourceService.getResources(srcRes.getPath());
            }

            for (Resource res : lstRes) {
                if (res instanceof Folder) {
                    moveResourceInDifferentStorage(res, createdFolder, userMove, sAccountId);
                } else {
                    copyFile((Content) res, createdFolder, userMove, sAccountId);
                }
            }
        } else {
            copyFile((Content) srcRes, destRes, userMove, sAccountId);
        }
    }

    private void copyFile(Content srcRes, Resource destRes, String userMove, Integer sAccountId) {
        // get input stream of download
        String destMovePath = destRes.getPath() + "/" + srcRes.getName();
        String srcPath = srcRes.getPath();

        InputStream in;
        ExternalResourceService srcService;
        if (ResourceUtils.INSTANCE.getType(srcRes) != ResourceType.MyCollab) {
            srcService = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(srcRes));
            in = srcService.download(ResourceUtils.INSTANCE.getExternalDrive(srcRes), srcRes.getPath());
        } else {
            in = resourceService.getContentStream(srcPath);
        }

        // update path of srcRes -------------------------
        srcRes.setPath(destMovePath);

        // ------------------------------------------------
        if (ResourceUtils.INSTANCE.getType(destRes) != ResourceType.MyCollab) {
            ExternalResourceService destService = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(destRes));
            destService.saveContent(ResourceUtils.INSTANCE.getExternalDrive(destRes), srcRes, in);
        } else {
            resourceService.saveContent(srcRes, userMove, in, sAccountId);
        }
    }

    private boolean checkIsTheSameAccountInStorage(Resource srcRes, Resource destRes) {
        return ResourceUtils.INSTANCE.getType(srcRes) == ResourceUtils.INSTANCE.getType(destRes) &&
                ResourceUtils.INSTANCE.getExternalDrive(srcRes).getAccesstoken().equals(ResourceUtils.INSTANCE.getExternalDrive(destRes).getAccesstoken());
    }

    private boolean isDuplicateFileName(Resource srcRes, Resource destRes) {
        if (ResourceUtils.INSTANCE.getType(destRes) == ResourceType.MyCollab) {
            List<Resource> lstRes = resourceService.getResources(destRes.getPath());
            for (Resource res : lstRes) {
                if (srcRes.getName().equals(res.getName()))
                    return true;
            }
        } else {
            ExternalResourceService service = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(destRes));
            List<Resource> lstRes = service.getResources(ResourceUtils.INSTANCE.getExternalDrive(destRes), destRes.getPath());
            for (Resource res : lstRes) {
                if (srcRes.getName().equals(res.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * destRes must be instanceof Folder
     */
    @Override
    public void moveResource(Resource srcRes, Resource destRes, String userMove, Integer sAccountId) {
        ResourceType srcType = ResourceUtils.INSTANCE.getType(srcRes);
        ResourceType destType = ResourceUtils.INSTANCE.getType(destRes);

        if (destRes instanceof Content)
            throw new MyCollabException("You cant move somethings to content path.That is impossible.");
        if (isDuplicateFileName(srcRes, destRes)) {
            throw new MyCollabException("Please check duplicate file, before move");
        }

        if (srcType == ResourceType.MyCollab && destType == ResourceType.MyCollab) {
            resourceService.moveResource(srcRes.getPath(), destRes.getPath(), userMove);
        } else if (srcType == destType && srcType != ResourceType.MyCollab) {
            if (checkIsTheSameAccountInStorage(srcRes, destRes)) {
                ExternalResourceService service = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(srcRes));
                service.move(ResourceUtils.INSTANCE.getExternalDrive(srcRes), srcRes.getPath(),
                        destRes.getPath() + "/" + srcRes.getName());
            } else {
                moveResourceInDifferentStorage(srcRes, destRes, userMove, sAccountId);
                // delete src resource
                ExternalResourceService srcService = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(srcRes));
                srcService.deleteResource(ResourceUtils.INSTANCE.getExternalDrive(srcRes), srcRes.getPath());
            }
        } else {
            moveResourceInDifferentStorage(srcRes, destRes, userMove, sAccountId);

            if (ResourceUtils.INSTANCE.getType(srcRes) != ResourceType.MyCollab) {
                ExternalResourceService srcService = ResourceUtils.INSTANCE.getExternalResourceService(ResourceUtils.INSTANCE.getType(srcRes));
                srcService.deleteResource(ResourceUtils.INSTANCE.getExternalDrive(srcRes), srcRes.getPath());
            } else {
                resourceService.removeResource(srcRes.getPath(), userMove, true, sAccountId);
            }
        }
    }
}
