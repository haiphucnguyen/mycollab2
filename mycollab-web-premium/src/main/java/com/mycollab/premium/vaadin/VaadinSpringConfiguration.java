package com.mycollab.premium.vaadin;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Configuration
public class VaadinSpringConfiguration {
    @Bean
    public ServletRegistrationBean exampleServletBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new MyCollabServlet(), "/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
