package com.esofthead.mycollab.spring;

import freemarker.cache.ClassTemplateLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@Configuration
public class FreeMarkerTemplateConfiguration {
    @Bean
    public FreeMarkerConfigurationFactoryBean freemarkerTemplate() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("");
        bean.setDefaultEncoding("UTF-8");
        bean.setPreTemplateLoaders(new ClassTemplateLoader(getClass().getClassLoader(), ""));
        return bean;
    }
}
