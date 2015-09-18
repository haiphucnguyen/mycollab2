package com.esofthead.mycollab.schedule.spring;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.schedule.AutowiringSpringBeanJobFactory;
import com.esofthead.mycollab.schedule.QuartzScheduleProperties;
import com.esofthead.mycollab.schedule.jobs.CheckUpdateJob;
import com.esofthead.mycollab.spring.DataSourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Configuration
public class CommunityScheduleConfiguration {
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
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SchedulerFactoryBean quartzSchedulerCommunity() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        if (DeploymentMode.site == SiteConfiguration.getDeploymentMode()) {
            bean.setDataSource(new DataSourceConfiguration().dataSource());
        }

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
