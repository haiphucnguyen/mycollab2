package com.mycollab.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Component
@Profile({"production"})
@ConfigurationProperties(prefix = "server")
public class ServerConfiguration {

    public static final String STORAGE_FILE = "file";

    public static final String STORAGE_S3 = "s3";

    private String storageSystem = STORAGE_FILE;

    private Integer port = 8080;

    private String apiUrl;

    private String pull_method;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl(String path) {
        return String.format("%s%s", apiUrl, path);
    }

    public String getResourceDownloadUrl() {
        return String.format("http://%s:%d/file/", SiteConfiguration.getServerAddress(), port);
    }

    public String getCdnUrl() {
        return String.format("http://%s:%d/assets/", SiteConfiguration.getServerAddress(), port);
    }

    public String getAppUrl() {
        return String.format("http://%s:%d/", SiteConfiguration.getServerAddress(), port);
    }

    public String getStorageSystem() {
        return storageSystem;
    }

    public void setStorageSystem(String storageSystem) {
        this.storageSystem = storageSystem;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPull_method() {
        return pull_method;
    }

    public void setPull_method(String pull_method) {
        this.pull_method = pull_method;
    }

    public boolean isPush() {
        return !"pull".equalsIgnoreCase(pull_method);
    }
}
