package com.mycollab.ondemand.module.billing.service;

import com.fastspring.FastSpring;
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
        return new FastSpring("mycollab", "haiphucnguyen@gmail.com", "8ADellm064OP");
    }
}
