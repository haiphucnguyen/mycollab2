package com.mycollab.schedule.spring;

import com.mycollab.module.project.schedule.email.service.OverdueProjectTicketsNotificationJob;
import com.mycollab.schedule.AutowiringSpringBeanJobFactory;
import com.mycollab.schedule.QuartzScheduleProperties;
import com.mycollab.schedule.jobs.CleanupTimeTrackingCacheDataJob;
import com.mycollab.schedule.jobs.CrmSendingRelayEmailNotificationJob;
import com.mycollab.schedule.jobs.LiveInstanceMonitorJob;
import com.mycollab.schedule.jobs.ProjectSendingRelayEmailNotificationJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Configuration
@Profile("production")
public class DefaultScheduleConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JobDetailFactoryBean cleanTimelineTrackingCacheJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(CleanupTimeTrackingCacheDataJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean projectSendRelayNotificationEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(ProjectSendingRelayEmailNotificationJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean projectOverdueAssignmentsNotificationEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(OverdueProjectTicketsNotificationJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean crmSendRelayNotificationEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(CrmSendingRelayEmailNotificationJob.class);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean liveInstanceMonitorJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(LiveInstanceMonitorJob.class);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean projectSendRelayNotificationEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(projectSendRelayNotificationEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean projectOverdueAssignmentsNotificationEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(projectOverdueAssignmentsNotificationEmailJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean crmSendRelayNotificationEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(crmSendRelayNotificationEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean cleanUpTimelineCacheDataTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(cleanTimelineTrackingCacheJob().getObject());
        bean.setCronExpression("0 0 0 * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean liveInstanceMonitorTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(liveInstanceMonitorJob().getObject());
        bean.setCronExpression("0 0 6 * * ?");
        return bean;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setDataSource(dataSource);

        bean.setQuartzProperties(new QuartzScheduleProperties());
        bean.setOverwriteExistingJobs(true);
        AutowiringSpringBeanJobFactory factory = new AutowiringSpringBeanJobFactory();
        factory.setApplicationContext(applicationContext);
        bean.setJobFactory(factory);
        bean.setApplicationContextSchedulerContextKey("applicationContextSchedulerContextKey");

        bean.setTriggers(
                projectSendRelayNotificationEmailTrigger().getObject(),
                projectOverdueAssignmentsNotificationEmailTrigger().getObject(),
                crmSendRelayNotificationEmailTrigger().getObject(),
                cleanUpTimelineCacheDataTrigger().getObject(),
                liveInstanceMonitorTrigger().getObject()
        );
        return bean;
    }
}
