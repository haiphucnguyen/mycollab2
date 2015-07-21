package com.esofthead.mycollab.oauth.service;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.oauth.OAuthService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class MyCollabOauthServiceFactory {
    private static Map<Class<? extends Api>, OAuthService> serviceMap = new ConcurrentHashMap<>();

    static {
        OAuthService dropBoxService = new ServiceBuilder().provider(DropBoxApi20.class).apiKey("y43ga49m30dfu02")
                .apiSecret("rheskqqb6f8fo6a")
                .callback(SiteConfiguration.getDropboxCallbackUrl()).build();
        serviceMap.put(DropBoxApi20.class, dropBoxService);
    }

    public static OAuthService getDropboxService() {
        return serviceMap.get(DropBoxApi20.class);
    }
}
