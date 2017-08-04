package com.mycollab.spring;

import com.mycollab.core.MyCollabException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * Static spring application context to retrieve spring bean without in servlet
 * context
 *
 * @author MyCollab Ltd
 * @since 1.0.0
 */
@Component("appContextUtil")
@Profile({"production", "test"})
public class AppContextUtil implements ApplicationContextAware {
    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        ctx = appContext;
    }

    public static <T> T getSpringBean(String name, Class<T> classType) {
        try {
            return ctx.getBean(name, classType);
        } catch (Exception e) {
            throw new MyCollabException("Can not find service " + name);
        }
    }

    public static Validator getValidator() {
        return getSpringBean("validator", LocalValidatorFactoryBean.class);
    }

    public static <T> T getSpringBean(Class<T> classType) {
        try {
            return ctx.getBean(classType);
        } catch (Exception e) {
            throw new MyCollabException("Can not find service " + classType, e);
        }
    }
}
