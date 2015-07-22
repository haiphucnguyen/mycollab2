package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.oauth.service.MyCollabOauthServiceFactory;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.ui.UI;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component
public class OauthBrowserWindowOpenerUtil {

    public static BrowserWindowOpener createDropboxOauthInstance() {
        OAuthService service = MyCollabOauthServiceFactory.getDropboxService();
        return newOauthBrowser(service);
    }

    private static BrowserWindowOpener newOauthBrowser(OAuthService service) {
        final String state = UUID.randomUUID().toString();
        final String authUrl = service.getAuthorizationUrl(null) + "&state=" + state;

        return new BrowserWindowOpener(authUrl) {
            @Override
            public void attach() {
                super.attach();
                UI ui = UI.getCurrent();
                LocalCacheManager.getCache("tempCache").put(state, ui);
            }

            @Override
            public void detach() {
                super.detach();
            }
        };
    }
}
