package com.mycollab.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@Configuration
@ComponentScan(basePackages = "com.mycollab.rest.server.resource",
        includeFilters = {@ComponentScan.Filter(classes = {RestController.class})})
@Profile("production")
public class WebConfiguration extends WebMvcConfigurationSupport {
}
