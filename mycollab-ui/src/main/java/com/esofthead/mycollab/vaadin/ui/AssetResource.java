package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.configuration.MyCollabAssets;
import com.vaadin.server.ExternalResource;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
public class AssetResource extends ExternalResource {

    public AssetResource(String resourceId) {
        super(MyCollabAssets.newAssetLink(resourceId));
    }
}
