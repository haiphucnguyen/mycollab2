package com.esofthead.mycollab.vaadin.resources;

import com.esofthead.mycollab.configuration.Storage;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.resources.file.VaadinFileResource;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class VaadinResourceFactory {
    private static final String S3_CLS = "com.esofthead.mycollab.vaadin.resources.s3.VaadinS3Resource";

    private static VaadinResourceFactory _instance = new VaadinResourceFactory();
    private VaadinResource vaadinResource;

    private VaadinResourceFactory() {
        Storage storage = StorageFactory.getInstance();
        if (storage.isFileStorage()) {
            vaadinResource = new VaadinFileResource();
        } else if (storage.isS3Storage()) {
            try {
                Class<VaadinResource> cls = (Class<VaadinResource>) Class.forName(S3_CLS);
                vaadinResource = cls.newInstance();
            } catch (Exception e) {
                throw new MyCollabException("Exception when load s3 resource file", e);
            }
        } else {
            throw new MyCollabException("Do not support storage system setting. Accept file or s3 only");
        }
    }


    public static VaadinResource getInstance() {
        return _instance.vaadinResource;
    }
}
