package com.mycollab.web.configuration

import com.mycollab.web.AppServlet
import com.vaadin.spring.annotation.EnableVaadin
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.vaadin.spring.events.annotation.EnableEventBus

@Configuration
@EnableVaadin
@EnableEventBus
class VaadinSpringConfiguration {

    @Bean("mainServlet")
    fun mainServlet(): ServletRegistrationBean<*> {
        val bean = ServletRegistrationBean(AppServlet(), "/*")
        bean.setLoadOnStartup(1)
        return bean
    }
}
