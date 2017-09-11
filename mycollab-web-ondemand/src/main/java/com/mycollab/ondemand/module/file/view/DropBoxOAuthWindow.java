package com.mycollab.ondemand.module.file.view;

import com.mycollab.module.ecm.StorageNames;
import com.vaadin.server.BrowserWindowOpener;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public final class DropBoxOAuthWindow extends DefaultCloudDriveOAuthWindow {
    private static final long serialVersionUID = 1L;

    @Override
    protected BrowserWindowOpener oauthWindowOpener() {
        return OauthBrowserWindowOpenerUtil.createDropboxOauthInstance();
    }

    @Override
    protected String getStorageName() {
        return StorageNames.INSTANCE.getDROPBOX();
    }

    @Override
    protected String windowTitle() {
        return "Connect Dropbox Drive";
    }
}
