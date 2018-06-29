package com.mycollab.premium.schedule.spring

import com.mycollab.premium.schedule.jobs.CheckUpdateJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@Configuration
class PremiumScheduleConfiguration {

    @Bean
    fun checkUpdateJobBean(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(CheckUpdateJob::class.java)
        return bean
    }

    @Bean
    fun checkUpdateJobTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(checkUpdateJobBean().`object`!!)
        bean.setCronExpression("0 0 8 * * ?")
        return bean
    }
}
