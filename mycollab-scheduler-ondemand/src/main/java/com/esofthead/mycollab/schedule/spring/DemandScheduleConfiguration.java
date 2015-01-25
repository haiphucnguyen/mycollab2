package com.esofthead.mycollab.schedule.spring;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.schedule.AutowiringSpringBeanJobFactory;
import com.esofthead.mycollab.schedule.QuartzScheduleProperties;
import com.esofthead.mycollab.schedule.email.user.impl.BillingSendingNotificationJobs;
import com.esofthead.mycollab.schedule.jobs.SendingCountUserLoginByDateJob;
import com.esofthead.mycollab.spring.DataSourceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Configuration
public class DemandScheduleConfiguration {
    @Bean
    public JobDetailFactoryBean sendCountUserLoginByDateJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SendingCountUserLoginByDateJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean public CronTriggerFactoryBean sendingCountUserLoginByDateTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendCountUserLoginByDateJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean JobDetailFactoryBean sendAccountBillingEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(BillingSendingNotificationJobs.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean public CronTriggerFactoryBean sendAccountBillingEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendAccountBillingEmailJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean public SchedulerFactoryBean quartzSchedulerDemand() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        if (DeploymentMode.site == SiteConfiguration.getDeploymentMode()) {
            bean.setDataSource(new DataSourceConfiguration().dataSource());
        }

        bean.setQuartzProperties(new QuartzScheduleProperties());
        bean.setJobFactory(new AutowiringSpringBeanJobFactory());
        bean.setConfigLocation(new ClassPathResource("quartz.properties"));
        bean.setOverwriteExistingJobs(true);
        bean.setAutoStartup(true);
        bean.setApplicationContextSchedulerContextKey("applicationContextSchedulerContextKey");

        bean.setTriggers(sendingCountUserLoginByDateTrigger().getObject(), sendAccountBillingEmailTrigger().getObject());
        return bean;
    }
}
