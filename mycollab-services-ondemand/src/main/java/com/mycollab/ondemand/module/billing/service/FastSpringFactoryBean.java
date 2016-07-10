package com.mycollab.ondemand.module.billing.service;

import com.mycollab.billing.fastspring.FastSpring;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
public class FastSpringFactoryBean extends AbstractFactoryBean<FastSpring> {
    @Override
    public Class<?> getObjectType() {
        return FastSpring.class;
    }

    @Override
    protected FastSpring createInstance() throws Exception {
        return new FastSpring("mycollab", "linhduong@esofthead.com", "24pIlObiL14A");
    }
}
