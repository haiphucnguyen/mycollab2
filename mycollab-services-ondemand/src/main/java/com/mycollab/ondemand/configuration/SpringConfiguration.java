package com.mycollab.ondemand.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author MyCollab Ltd
 * @since 5.4.0
 */
@Configuration
@Profile("production")
@ComponentScan(basePackages = {
        "com.mycollab.monitoring.spring",
        "com.mycollab.pro.cache.aspect",
        "com.mycollab.pro.common.service",
        "com.mycollab.pro.jgroups.service",
        "com.mycollab.pro.module.project.service",
        "com.mycollab.pro.module.user.service",
        "com.mycollab.pro.module.project.view.service",
        "com.mycollab.ondemand.aspect",
        "com.mycollab.ondemand.module.billing.esb",
        "com.mycollab.ondemand.module.billing.service",
        "com.mycollab.ondemand.common.service",
        "com.mycollab.ondemand.module.ecm.service",
        "com.mycollab.ondemand.module.file.service",
        "com.mycollab.ondemand.module.support.service",
        "com.mycollab.ondemand.module.user.esb",
        "com.mycollab.ondemand.schedule.spring",
        "com.mycollab.ondemand.schedule.jobs",
        "com.mycollab.ondemand.vaadin.ui.service"
})
public class SpringConfiguration {

}
