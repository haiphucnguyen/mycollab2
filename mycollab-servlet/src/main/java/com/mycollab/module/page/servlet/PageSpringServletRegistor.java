package com.mycollab.module.page.servlet;

import com.mycollab.module.file.servlet.AssetHandler;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Configuration
public class PageSpringServletRegistor {
    @Bean
    public ServletRegistrationBean fileUploadServlet() {
        return new ServletRegistrationBean(new FileUploadServlet(), "/page/upload");
    }
}
