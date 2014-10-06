package com.esofthead.mycollab.schedule.email.user.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.test.MyCollabSpringRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabSpringRunner.class)
public class UserSignUpEmailNotificationJobIntegrationTest extends ServiceTest {
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
