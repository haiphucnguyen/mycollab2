package com.mycollab.schedule.email.user.service;

import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.schedule.email.service.UserSignUpEmailNotificationJob;
import com.mycollab.test.service.IntegrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserSignUpEmailNotificationJobIntegrationTest extends IntegrationServiceTest {
    @Autowired
    private UserSignUpEmailNotificationJob userSignupNotificationJob;

    @Test
    public void testSendMail() {
        SimpleUser user = new SimpleUser();
        user.setAccountId(1);
        user.setUsername("hainguyen@esofthead.com");
        user.setEmail("hainguyen@esofthead.com");
        user.setLastname("hainguyen");

        userSignupNotificationJob.sendConfirmEmailToUser(user);
    }
}
