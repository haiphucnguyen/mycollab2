package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@Theme(MyCollabVersion.THEME_VERSION)
@Widgetset("com.esofthead.mycollab.widgetset.MyCollabWidgetSet")
public class OAuthUI extends UI {
    private OAuthCallbackRequestHandler callbackRequestHandler;

    @Override
    protected void init(VaadinRequest request) {
        Page.getCurrent().setLocation("");
        callbackRequestHandler = new OAuthCallbackRequestHandler();
        getSession().addRequestHandler(callbackRequestHandler);
    }
}
