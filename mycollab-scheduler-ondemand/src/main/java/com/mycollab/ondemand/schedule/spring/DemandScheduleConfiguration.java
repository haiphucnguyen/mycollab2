package com.mycollab.ondemand.schedule.spring;

import com.mycollab.ondemand.schedule.jobs.BillingSendingNotificationJob;
import com.mycollab.ondemand.schedule.jobs.*;
import com.mycollab.schedule.AutowiringSpringBeanJobFactory;
import com.mycollab.schedule.QuartzScheduleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

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
        return bean;
    }

    @Bean
    public JobDetailFactoryBean sendCountLiveInstancesByDateJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(CountLiveInstancesJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean removeObsoleteAccountsJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DeleteObsoleteAccountJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean removeObsoleteUsersJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DeleteObsoleteUsersJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean removeObsoleteLiveInstancesJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DeleteObsoleteLiveInstancesJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean sendOneWeekFollowupDownloadedUsersJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(FollowupDownloadedUsersAfterOneWeekJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean sendOneWeekFollowupSignupUsersJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(FollowupSignupUserAfterOneWeekJob.class);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendingCountUserLoginByDateTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendCountUserLoginByDateJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendingCountLiveInstancesByDateTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendCountLiveInstancesByDateJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean deleteObsoleteAccountsTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(removeObsoleteAccountsJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean deleteObsoleteUsersTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(removeObsoleteUsersJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean deleteObsoleteLiveInstancesTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(removeObsoleteLiveInstancesJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendOneWeekFollowupDownloadedUsersTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendOneWeekFollowupDownloadedUsersJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendOneWeekFollowupSignupUsersTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendOneWeekFollowupSignupUsersJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public JobDetailFactoryBean sendAccountBillingRequestEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(BillingSendingNotificationJob.class);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendAccountBillingEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendAccountBillingRequestEmailJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Autowired
    private ApplicationContext applicationContext;

    public DemandScheduleConfiguration() {
        super();
    }

    @Bean
    public SchedulerFactoryBean quartzSchedulerDemand() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
//        if (DeploymentMode.site == SiteConfiguration.getDeploymentMode()) {
//            bean.setDataSource(new DataSourceConfiguration().dataSource());
//        }

        bean.setQuartzProperties(new QuartzScheduleProperties());
        AutowiringSpringBeanJobFactory factory = new AutowiringSpringBeanJobFactory();
        factory.setApplicationContext(applicationContext);
        bean.setJobFactory(factory);
        bean.setOverwriteExistingJobs(true);
        bean.setAutoStartup(true);
        bean.setApplicationContextSchedulerContextKey("onDemandScheduleContext");

        bean.setTriggers(sendingCountUserLoginByDateTrigger().getObject(),
                sendingCountLiveInstancesByDateTrigger().getObject(),
                deleteObsoleteAccountsTrigger().getObject(),
                deleteObsoleteUsersTrigger().getObject(),
                sendAccountBillingEmailTrigger().getObject(),
                deleteObsoleteLiveInstancesTrigger().getObject(),
                sendOneWeekFollowupDownloadedUsersTrigger().getObject(),
                sendOneWeekFollowupSignupUsersTrigger().getObject());
        return bean;
    }
}
