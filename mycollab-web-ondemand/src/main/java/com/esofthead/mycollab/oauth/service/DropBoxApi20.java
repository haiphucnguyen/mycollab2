package com.esofthead.mycollab.oauth.service;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class DropBoxApi20 extends DefaultApi20 {
    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.dropbox.com/1/oauth2/token?grant_type=authorization_code";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        return String.format("https://www.dropbox.com/1/oauth2/authorize?client_id=%s&response_type=code&redirect_uri=%s", oAuthConfig.getApiKey(), oAuthConfig.getCallback());
    }

    @Override
    public Verb getAccessTokenVerb(){
        return Verb.POST;
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new JsonTokenExtractor();
    }
}
