package com.mycollab.ondemand.module.ecm.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.*;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.module.ecm.StorageNames;
import com.mycollab.module.ecm.domain.*;
import com.mycollab.module.ecm.service.DropboxResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.*;

@Service
public class DropboxResourceServiceImpl implements DropboxResourceService {
    private static final Logger LOG = LoggerFactory.getLogger(DropboxResourceServiceImpl.class);

    @Override
    public List<Resource> getResources(ExternalDrive drive, String path) {
        List<Resource> resources = new ArrayList<>();
        try {
            DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
            DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
            DbxEntry.WithChildren children = client.getMetadataWithChildren(path);
            if (CollectionUtils.isNotEmpty(children.children)) {
                for (DbxEntry entry : children.children) {
                    if (entry.isFile()) {
                        DbxEntry.File file = entry.asFile();
                        ExternalContent resource = new ExternalContent(file.path);
                        resource.setStorageName(StorageNames.INSTANCE.getDROPBOX());
                        resource.setExternalDrive(drive);
                        Date lastModifiedDate = file.lastModified;
                        Calendar createdDate = new GregorianCalendar();
                        createdDate.setTime(lastModifiedDate);
                        resource.setSize(file.numBytes);
                        resource.setCreated(createdDate);
                        if (file.mightHaveThumbnail) {
                            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                            client.getThumbnail(new DbxThumbnailSize("s", 38, 38), DbxThumbnailFormat.PNG, file.path,
                                    null, outStream);
                            resource.setThumbnailBytes(outStream.toByteArray());
                        }
                        resources.add(resource);
                    } else if (entry.isFolder()) {
                        DbxEntry.Folder folder = entry.asFolder();
                        ExternalFolder resource = new ExternalFolder(folder.path);
                        resource.setStorageName(StorageNames.INSTANCE.getDROPBOX());
                        resource.setExternalDrive(drive);
                        resources.add(resource);
                    } else {
                        LOG.error("Do not support dropbox resource except file or folder");
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error when get dropbox resource", e);
            throw new UserInvalidInputException(
                    "Error when retrieving dropbox files. The most possible issue is can not connect to dropbox server");
        }
        Collections.sort(resources);
        return resources;
    }

    @Override
    public List<ExternalFolder> getSubFolders(ExternalDrive drive, String path) {
        List<ExternalFolder> subFolders = new ArrayList<ExternalFolder>();

        try {
            DbxRequestConfig requestConfig = new DbxRequestConfig(
                    "MyCollab/1.0", null);
            DbxClientV1 client = new DbxClientV1(requestConfig,
                    drive.getAccesstoken());
            DbxEntry.WithChildren children = client.getMetadataWithChildren(path);
            if (CollectionUtils.isNotEmpty(children.children)) {
                for (DbxEntry entry : children.children) {
                    if (entry.isFolder()) {
                        ExternalFolder resource = new ExternalFolder(entry.path);
                        resource.setStorageName(StorageNames.INSTANCE.getDROPBOX());
                        resource.setExternalDrive(drive);
                        subFolders.add(resource);
                    }

                }
            }
        } catch (Exception e) {
            LOG.error("Error when get dropbox resource", e);
        }

        return subFolders;
    }

    @Override
    public Resource getCurrentResourceByPath(ExternalDrive drive, String path) {
        try {
            DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
            DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
            Resource res = null;

            DbxEntry entry = client.getMetadata(path);
            if (entry == null) {
                return null;
            }

            if (entry.isFile()) {
                ExternalContent resource = new ExternalContent(entry.path);
                resource.setStorageName(StorageNames.INSTANCE.getDROPBOX());
                resource.setExternalDrive(drive);
                Date lastModifiedDate = ((DbxEntry.File) entry).lastModified;
                Calendar createdDate = new GregorianCalendar();
                createdDate.setTime(lastModifiedDate);
                resource.setSize(((DbxEntry.File) entry).numBytes);
                resource.setCreated(createdDate);
                res = resource;
            } else if (entry.isFolder()) {
                ExternalFolder resource = new ExternalFolder(entry.path);
                resource.setStorageName(StorageNames.INSTANCE.getDROPBOX());
                resource.setExternalDrive(drive);
                res = resource;
            }
            return res;
        } catch (DbxException e) {
            LOG.error("Error when get dropbox resource", e);
            throw new UserInvalidInputException(e);
        }

    }

    @Override
    public Folder getParentResourceFolder(ExternalDrive drive, String childPath) {
        String folderPath = childPath.substring(0, childPath.lastIndexOf("/"));
        if (folderPath.length() == 0)
            folderPath = "/";
        return (Folder) this.getCurrentResourceByPath(drive, folderPath);
    }

    @Override
    public Folder createNewFolder(ExternalDrive drive, String path) {
        DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
        DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
        try {
            client.createFolder(path);
            return (Folder) this.getCurrentResourceByPath(drive, path);
        } catch (DbxException e) {
            LOG.error("Error when createdFolder dropbox resource", e);
        }
        return null;
    }

    @Override
    public void saveContent(ExternalDrive drive, Content content, InputStream in) {
        DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
        DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
        try {
            client.uploadFile(content.getPath(), DbxWriteMode.add(), -1, in);
        } catch (Exception e) {
            LOG.error("Error when upload file to Dropbox", e);
        }
    }

    @Override
    public void rename(ExternalDrive drive, String oldPath, String newPath) {
        DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
        DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
        try {
            client.copy(oldPath, newPath);
            client.delete(oldPath);
        } catch (DbxException e) {
            LOG.error("Error when rename dropbox resource", e);
        }
    }

    @Override
    public void deleteResource(ExternalDrive drive, String path) {
        DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
        DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
        try {
            client.delete(path);
        } catch (DbxException e) {
            LOG.error("Error when Delete dropbox resource", e);
        }
    }

    @Override
    public InputStream download(ExternalDrive drive, final String path) {
        DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
        final DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
        PipedInputStream in = new PipedInputStream();
        try {
            final PipedOutputStream out = new PipedOutputStream(in);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        client.getFile(path, null, out);
                        out.close();
                    } catch (Exception e) {
                        LOG.error("Error when get File from dropbox", e);
                    }
                }
            }).start();
        } catch (Exception e) {
            throw new MyCollabException(
                    "Error when get inputStream from dropbox file", e);
        }
        return in;
    }

    /**
     * Only support move in Dropbox local, not implement move from Dropbox
     * to MyCollab or against. Must implement it later
     */
    @Override
    public void move(ExternalDrive drive, String fromPath, String toPath) {
        DbxRequestConfig requestConfig = new DbxRequestConfig("MyCollab/1.0", null);
        DbxClientV1 client = new DbxClientV1(requestConfig, drive.getAccesstoken());
        try {
            client.move(fromPath, toPath);
        } catch (DbxException e) {
            LOG.error("Error when move dropbox resource", e);
        }
    }
}
