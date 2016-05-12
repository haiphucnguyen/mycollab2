package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.cache.service.CacheService;
import com.esofthead.mycollab.oauth.service.MyCollabOauthServiceFactory;
import com.esofthead.mycollab.spring.AppContextUtil;
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
                CacheService cacheService = AppContextUtil.getSpringBean(CacheService.class);
                cacheService.putValue("tempCache", state, ui);
            }

            @Override
            public void detach() {
                super.detach();
            }
        };
    }
}
