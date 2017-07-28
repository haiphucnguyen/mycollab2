package com.mycollab.premium.configuration;

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
        "com.mycollab.license.service",
        "com.mycollab.premium.module.billing.service",
        "com.mycollab.premium.module.ecm.service",
        "com.mycollab.premium.module.file.service",
        "com.mycollab.premium.module.user.service",
        "com.mycollab.premium.schedule.jobs",
        "com.mycollab.premium.schedule.spring",
        "com.mycollab.premium.vaadin.ui.service",
        "com.mycollab.pro.cache.aspect",
        "com.mycollab.pro.common.service",
        "com.mycollab.pro.module.project.service",
        "com.mycollab.pro.module.user.service",
        "com.mycollab.pro.module.project.view.service"
})
public class SpringConfiguration {
}
