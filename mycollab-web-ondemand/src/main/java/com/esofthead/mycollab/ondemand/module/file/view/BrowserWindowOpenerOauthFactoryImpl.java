package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.module.file.view.BrowserWindowOpenerOauthFactory;
import com.esofthead.mycollab.oauth.service.MyCollabOauthServiceFactory;
import com.vaadin.server.BrowserWindowOpener;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component
public class BrowserWindowOpenerOauthFactoryImpl implements BrowserWindowOpenerOauthFactory {
    @Override
    public BrowserWindowOpener createDropboxOauthInstance() {
        OAuthService service = MyCollabOauthServiceFactory.getDropboxService();
        String authUrl = service.getAuthorizationUrl(null);
        return new BrowserWindowOpener(authUrl);
    }
}
