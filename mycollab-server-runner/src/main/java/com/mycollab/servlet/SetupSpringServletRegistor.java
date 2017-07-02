package com.mycollab.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Profile("setup")
@Configuration
public class SetupSpringServletRegistor {
    @Bean
    public ServletRegistrationBean assetServlet() {
        return new ServletRegistrationBean(new AssetHttpServletRequestHandler(), "/assets/*");
    }

    @Bean
    public ServletRegistrationBean databaseValidateServlet(){
        return new ServletRegistrationBean(new DatabaseValidateServlet(), "/validate");
    }

    @Bean
    public ServletRegistrationBean emailValidateServlet() {
        return new ServletRegistrationBean(new EmailValidationServlet(), "/emailValidate");
    }

    @Bean
    public ServletRegistrationBean installationSerlvet() {
        return new ServletRegistrationBean(new InstallationServlet(), "/install");
    }

    public ServletRegistrationBean setupServlet(){
        return new ServletRegistrationBean(new SetupServlet(), "");
    }
}
