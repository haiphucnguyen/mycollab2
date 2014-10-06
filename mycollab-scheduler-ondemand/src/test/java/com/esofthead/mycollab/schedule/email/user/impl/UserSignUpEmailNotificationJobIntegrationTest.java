package com.esofthead.mycollab.schedule.email.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;

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
