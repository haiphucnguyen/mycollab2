package com.mycollab.test.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Configuration
@EnableAutoConfiguration(exclude = {FlywayAutoConfiguration.class, FreeMarkerAutoConfiguration.class})
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.mycollab.**.service", "com.mycollab.**.spring",
        "com.mycollab.**.jobs", "com.mycollab.**.aspect", "com.mycollab.**.esb",
        "com.mycollab.**.configuration"},
        excludeFilters = {@ComponentScan.Filter(classes = {Controller.class})})
@Profile("test")
public class RootConfiguration {
}
