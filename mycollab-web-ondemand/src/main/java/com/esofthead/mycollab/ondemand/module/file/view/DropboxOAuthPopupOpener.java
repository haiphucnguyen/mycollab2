package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.oauth.OAuthPopupOpener;
import com.esofthead.mycollab.oauth.service.DropBoxApi20;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class DropboxOAuthPopupOpener extends OAuthPopupOpener {
    public DropboxOAuthPopupOpener() {
        super(DropBoxApi20.class, "y43ga49m30dfu02", "rheskqqb6f8fo6a");
    }
}
