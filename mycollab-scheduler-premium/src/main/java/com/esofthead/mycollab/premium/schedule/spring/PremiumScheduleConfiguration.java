package com.esofthead.mycollab.premium.schedule.spring;

import com.esofthead.mycollab.premium.schedule.jobs.CheckUpdateJob;
import com.esofthead.mycollab.schedule.AutowiringSpringBeanJobFactory;
import com.esofthead.mycollab.schedule.QuartzScheduleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@Configuration
public class PremiumScheduleConfiguration {
    @Bean
    public JobDetailFactoryBean checkUpdateJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(CheckUpdateJob.class);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean checkUpdateJobTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(checkUpdateJob().getObject());
        bean.setCronExpression("0 0 8 * * ?");
        return bean;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SchedulerFactoryBean quartzSchedulerCommunity() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        bean.setQuartzProperties(new QuartzScheduleProperties());
        bean.setOverwriteExistingJobs(true);
        AutowiringSpringBeanJobFactory factory = new AutowiringSpringBeanJobFactory();
        factory.setApplicationContext(applicationContext);
        bean.setJobFactory(factory);
        bean.setApplicationContextSchedulerContextKey("communityScheduler");

        bean.setTriggers(checkUpdateJobTrigger().getObject());
        return bean;
    }
}
