package com.mycollab.premium.schedule.spring;

import com.mycollab.premium.schedule.jobs.CheckUpdateJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import javax.sql.DataSource;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@Configuration
@Profile("production")
public class PremiumScheduleConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JobDetailFactoryBean checkUpdateJobBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setDurability(true);
        bean.setJobClass(CheckUpdateJob.class);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean checkUpdateJobTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(checkUpdateJobBean().getObject());
        bean.setCronExpression("0 0 8 * * ?");
        return bean;
    }
}
