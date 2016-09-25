package com.mycollab.premium.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author MyCollab Ltd
 * @since 5.4.0
 */
@Configuration
@ComponentScan(basePackages = {
        "com.mycollab.premium.license.service",
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
