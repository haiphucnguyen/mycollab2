package com.mycollab.vaadin.spring;

import com.mycollab.vaadin.MyCollabServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Configuration
@Profile("production")
public class VaadinSpringConfiguration {
    @Bean
    public ServletRegistrationBean mainServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new MyCollabServlet(), "/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
