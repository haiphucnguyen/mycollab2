package com.mycollab.ondemand.module.billing.service

import com.fastspring.FastSpring
import org.springframework.beans.factory.config.AbstractFactoryBean
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
class FastSpringFactoryBean : AbstractFactoryBean<FastSpring>() {
    override fun getObjectType(): Class<*> = FastSpring::class.java

    override fun isSingleton(): Boolean = false

    @Throws(Exception::class)
    override fun createInstance(): FastSpring = FastSpring("mycollab", "haiphucnguyen@gmail.com", "8ADellm064OP")
}
