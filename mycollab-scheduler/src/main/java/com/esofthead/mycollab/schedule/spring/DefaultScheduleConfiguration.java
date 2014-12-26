package com.esofthead.mycollab.schedule.spring;

import com.esofthead.mycollab.schedule.AutowiringSpringBeanJobFactory;
import com.esofthead.mycollab.schedule.email.user.impl.SendUserInvitationEmailJob;
import com.esofthead.mycollab.schedule.email.user.impl.UserSignUpEmailNotificationJob;
import com.esofthead.mycollab.schedule.jobs.CrmSendingRelayEmailNotificationJob;
import com.esofthead.mycollab.schedule.jobs.ProjectSendingRelayEmailNotificationJob;
import com.esofthead.mycollab.schedule.jobs.SendingErrorReportEmailJob;
import com.esofthead.mycollab.schedule.jobs.SendingRelayEmailJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Configuration
public class DefaultScheduleConfiguration {
    @Bean
    public JobDetailFactoryBean sendRelayEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SendingRelayEmailJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean sendInviteUserEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SendUserInvitationEmailJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean projectSendRelayNotificationEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(ProjectSendingRelayEmailNotificationJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean crmSendRelayNotificationEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(CrmSendingRelayEmailNotificationJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean userSignUpNotificationEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(UserSignUpEmailNotificationJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean sendErrorReportEmailJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SendingErrorReportEmailJob.class);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendingRelayEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendRelayEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
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
    public CronTriggerFactoryBean crmSendRelayNotificationEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(crmSendRelayNotificationEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean userSignUpNotificationEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(userSignUpNotificationEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendErrorReportEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendErrorReportEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean sendInviteUserEmailTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(sendInviteUserEmailJob().getObject());
        bean.setCronExpression("0 * * * * ?");
        return bean;
    }

    @Bean public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("classpath:quartz.properties"));
        bean.setOverwriteExistingJobs(true);
        bean.setAutoStartup(true);
        bean.setApplicationContextSchedulerContextKey("applicationContextSchedulerContextKey");
        bean.setJobFactory(new AutowiringSpringBeanJobFactory());

        // NOTE: Must add both the jobDetail and trigger to the scheduler!
        bean.setJobDetails(sendRelayEmailJob().getObject(), projectSendRelayNotificationEmailJob().getObject(),
                crmSendRelayNotificationEmailJob().getObject(), sendErrorReportEmailJob().getObject(),
                sendInviteUserEmailJob().getObject(), userSignUpNotificationEmailJob().getObject());

        bean.setTriggers(sendingRelayEmailTrigger().getObject(), projectSendRelayNotificationEmailTrigger().getObject
                (), crmSendRelayNotificationEmailTrigger().getObject(), sendErrorReportEmailTrigger().getObject(),
                sendInviteUserEmailTrigger().getObject(), userSignUpNotificationEmailTrigger().getObject());
        return bean;
    }
}
