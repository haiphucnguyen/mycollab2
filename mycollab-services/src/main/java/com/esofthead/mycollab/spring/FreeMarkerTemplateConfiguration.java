package com.esofthead.mycollab.spring;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@Service
public class FreeMarkerTemplateConfiguration extends FreeMarkerConfigurationFactoryBean {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        Configuration configuration = getObject();
        configuration.setIncompatibleImprovements(Configuration.VERSION_2_3_24);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass().getClassLoader(), ""));
    }

}
