package com.esofthead.mycollab.ondemand.module.file.view;

import com.vaadin.server.BrowserWindowOpener;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class OAuthPopupOpener extends BrowserWindowOpener {
    public OAuthPopupOpener() {
        super(OAuthUI.class);
    }

    @Override
    public void attach() {
        super.attach();
    }

    @Override
    public void detach() {
        super.detach();
    }
}
