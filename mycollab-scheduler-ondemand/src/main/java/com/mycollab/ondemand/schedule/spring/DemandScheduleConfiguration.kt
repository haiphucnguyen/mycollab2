package com.mycollab.ondemand.schedule.spring

import com.mycollab.ondemand.schedule.jobs.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.JobDetailFactoryBean

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Configuration
class DemandScheduleConfiguration {

    @Bean
    fun sendCountUserLoginByDateJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(SendingCountUserLoginByDateJob::class.java)
        return bean
    }

    @Bean
    fun sendCountLiveInstancesByDateJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(CountLiveInstancesJob::class.java)
        return bean
    }

    @Bean
    fun removeObsoleteAccountsJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(DeleteObsoleteAccountJob::class.java)
        return bean
    }

    @Bean
    fun removeObsoleteUsersJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(DeleteObsoleteUsersJob::class.java)
        return bean
    }

    @Bean
    fun removeObsoleteLiveInstancesJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(DeleteObsoleteLiveInstancesJob::class.java)
        return bean
    }

    @Bean
    fun sendOneWeekFollowupDownloadedUsersJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(FollowupDownloadedUsersAfterOneWeekJob::class.java)
        return bean
    }

    @Bean
    fun sendOneWeekFollowupSignupUsersJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(FollowupSignupUserAfterOneWeekJob::class.java)
        return bean
    }

    @Bean
    fun sendingCountUserLoginByDateTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(sendCountUserLoginByDateJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun sendingCountLiveInstancesByDateTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(sendCountLiveInstancesByDateJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun deleteObsoleteAccountsTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(removeObsoleteAccountsJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun deleteObsoleteUsersTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(removeObsoleteUsersJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun deleteObsoleteLiveInstancesTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(removeObsoleteLiveInstancesJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun sendOneWeekFollowupDownloadedUsersTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(sendOneWeekFollowupDownloadedUsersJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun sendOneWeekFollowupSignupUsersTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(sendOneWeekFollowupSignupUsersJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }

    @Bean
    fun sendAccountBillingRequestEmailJob(): JobDetailFactoryBean {
        val bean = JobDetailFactoryBean()
        bean.setDurability(true)
        bean.setJobClass(BillingSendingNotificationJob::class.java)
        return bean
    }

    @Bean
    fun sendAccountBillingEmailTrigger(): CronTriggerFactoryBean {
        val bean = CronTriggerFactoryBean()
        bean.setJobDetail(sendAccountBillingRequestEmailJob().`object`!!)
        bean.setCronExpression("0 0 0 * * ?")
        return bean
    }
}
