package com.mycollab.ondemand.module.file.view;

import com.mycollab.pro.module.file.view.OauthWindowFactory;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@ViewComponent
public class OauthWindowFactoryImpl implements OauthWindowFactory {
    @Override
    public Window newDropBoxAuthWindow() {
        return new DropBoxOAuthWindow();
    }
}
