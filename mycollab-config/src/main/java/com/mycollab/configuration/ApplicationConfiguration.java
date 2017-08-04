package com.mycollab.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Component
@Profile({"production", "test"})
@ConfigurationProperties(prefix = "app")
public class ApplicationConfiguration {

    private String description;

    private String facebookUrl;

    private String twitterUrl;

    private String googleUrl;

    private String linkedinUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getGoogleUrl() {
        return googleUrl;
    }

    public void setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public Map<String, String> defaultUrls() {
        Map<String, String> defaultUrls = new HashMap<>();
        defaultUrls.put("facebook_url", facebookUrl);
        defaultUrls.put("google_url", googleUrl);
        defaultUrls.put("linkedin_url", linkedinUrl);
        defaultUrls.put("twitter_url", twitterUrl);
        return defaultUrls;
    }
}
