package com.mycollab.schedule.email.user.service;

import com.mycollab.module.user.schedule.email.service.UserSignUpEmailNotificationJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.test.service.IntergrationServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserSignUpEmailNotificationJobIntegrationTest extends IntergrationServiceTest {
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
