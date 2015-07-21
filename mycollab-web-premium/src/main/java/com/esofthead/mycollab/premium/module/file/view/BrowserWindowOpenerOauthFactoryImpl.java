package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.core.UnsupportedFeatureException;
import com.esofthead.mycollab.module.file.view.BrowserWindowOpenerOauthFactory;
import com.vaadin.server.BrowserWindowOpener;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component
public class BrowserWindowOpenerOauthFactoryImpl implements BrowserWindowOpenerOauthFactory {
    @Override
    public BrowserWindowOpener createDropboxOauthInstance() {
        throw new UnsupportedFeatureException("This feature is not supported in this edition");
    }
}
