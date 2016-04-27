package com.esofthead.mycollab.schedule.email.user.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.esofthead.mycollab.schedule.email.user.service.UserSignUpEmailNotificationJob;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.quartz.JobExecutionException;

import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.module.billing.UserStatusConstants;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.GenericJobTest;

public class UserSignUpEmailNotificationJobTest extends GenericJobTest {
	@Spy
	@InjectMocks
	private UserSignUpEmailNotificationJob confirmEmailJob;

	@Mock
	private UserService userService;

	@SuppressWarnings("unchecked")
	@Test
	public void testSendConfirmEmail() throws JobExecutionException {
		SimpleUser user = new SimpleUser();

		when(userService.findPagableListByCriteria(any(BasicSearchRequest.class)))
				.thenReturn(Collections.singletonList(user));

		confirmEmailJob.executeJob(context);

		verify(extMailService, times(1)).sendHTMLMail(anyString(), anyString(),
				any(List.class), any(List.class), any(List.class), anyString(),
				anyString(), any(List.class));

		ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);

		ArgumentCaptor<String> stringArgument = ArgumentCaptor
				.forClass(String.class);
		verify(userService).updateWithSession(userArgument.capture(),
				stringArgument.capture());

		Assert.assertEquals(UserStatusConstants.EMAIL_VERIFIED_REQUEST,
				userArgument.getValue().getStatus());
	}
}
