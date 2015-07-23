package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.core.UnsupportedFeatureException;
import com.vaadin.ui.Window;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component
public class OauthWindowFactoryImpl implements OauthWindowFactory {
    @Override
    public Window newDropBoxAuthWindow() {
        throw new UnsupportedFeatureException("This feature is not supported in this edition");
    }
}
