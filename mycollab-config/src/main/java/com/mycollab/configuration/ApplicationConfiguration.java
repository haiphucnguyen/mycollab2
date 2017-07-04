package com.mycollab.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfiguration {
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
