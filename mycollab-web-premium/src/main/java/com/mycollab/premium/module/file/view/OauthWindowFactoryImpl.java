package com.mycollab.premium.module.file.view;

import com.mycollab.core.UnsupportedFeatureException;
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
        throw new UnsupportedFeatureException("This feature is not supported in this edition");
    }
}
