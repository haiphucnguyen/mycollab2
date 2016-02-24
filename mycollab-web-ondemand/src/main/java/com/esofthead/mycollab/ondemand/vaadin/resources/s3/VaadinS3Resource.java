package com.esofthead.mycollab.ondemand.vaadin.resources.s3;

import com.esofthead.mycollab.vaadin.resources.VaadinResource;
import com.vaadin.server.Resource;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class VaadinS3Resource extends VaadinResource {

    @Override
    public Resource getStreamResource(String documentPath) {
        return new S3StreamDownloadResource(documentPath);
    }
}
