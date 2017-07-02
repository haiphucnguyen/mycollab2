package com.mycollab.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Configuration
public class GenericSpringServletRegistor {
    @Bean
    public ServletRegistrationBean errorServlet() {
        return new ServletRegistrationBean(new AppExceptionServletHandler(), "/error");
    }

    @Bean
    public ServletRegistrationBean tooltipServlet(){
        return new ServletRegistrationBean(new TooltipGeneratorServletRequestHandler(), "/tooltip/*");
    }

    @Bean
    public ServletRegistrationBean upgradeServlet() {
        return new ServletRegistrationBean(new UpgradeServlet(), "/upgrade");
    }

    @Bean
    public ServletRegistrationBean upgradeStatus() {
        return new ServletRegistrationBean(new UpgradeStatusServlet(), "/upgrade_status");
    }
}
