package com.mycollab.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Component
@ConfigurationProperties(prefix = "server")
public class ServerConfiguration {

    public static final String STORAGE_FILE = "file";

    public static final String STORAGE_S3 = "s3";

    private Integer port;

    private String address;

    private String url;

    private String storageSystem = STORAGE_FILE;

    private String cdnUrl;

    private String downloadUrl;

    private String apiUrl;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCdnUrl() {
        return cdnUrl;
    }

    public void setCdnUrl(String cdnUrl) {
        this.cdnUrl = cdnUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl(String path) {
        return String.format("%s%s", apiUrl, path);
    }

    public String getStorageSystem() {
        return storageSystem;
    }

    public void setStorageSystem(String storageSystem) {
        this.storageSystem = storageSystem;
    }
}
