package com.mycollab.ondemand.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author MyCollab Ltd
 * @since 5.4.0
 */
@Configuration
@ComponentScan(basePackages = "com.mycollab.rest.server.resource",
        includeFilters = {@ComponentScan.Filter(classes = {RestController.class})})
@EnableWebMvc
@Profile("production")
public class RestConfiguration {
}
