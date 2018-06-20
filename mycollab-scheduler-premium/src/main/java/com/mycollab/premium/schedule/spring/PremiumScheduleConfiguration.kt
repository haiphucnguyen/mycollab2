package com.mycollab.premium.schedule.spring

import com.mycollab.premium.schedule.jobs.CheckUpdateJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@Configuration
@Profile("program")
open class PremiumScheduleConfiguration {

    @Bean
    open fun checkUpdateJobBean(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(CheckUpdateJob::class.java)
        return bean
    }

    @Bean
    open fun checkUpdateJobTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(checkUpdateJobBean().`object`)
        bean.setCronExpression("0 0 8 * * ?")
        return bean
    }
}
