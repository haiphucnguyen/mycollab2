package com.mycollab.test.spring;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;

@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.mycollab.**.service", "com.mycollab.**.spring",
        "com.mycollab.**.jobs", "com.mycollab.**.aspect", "com.mycollab.**.esb",
        "com.mycollab.**.configuration"},
        excludeFilters = {@ComponentScan.Filter(classes = {Controller.class})})
public class RootConfiguration {
}
