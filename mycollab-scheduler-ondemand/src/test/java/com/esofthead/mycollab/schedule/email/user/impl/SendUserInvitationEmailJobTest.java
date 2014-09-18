package com.esofthead.mycollab.schedule.email.user.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.quartz.JobExecutionException;

import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapperExt;
import com.esofthead.mycollab.module.user.domain.SimpleUserAccountInvitation;
import com.esofthead.mycollab.schedule.email.GenericJobTest;

public class SendUserInvitationEmailJobTest extends GenericJobTest {

	@Spy
	@InjectMocks
	private SendUserInvitationEmailJob sendInvitationJob;

	@Mock
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Mock
	private UserAccountInvitationMapperExt userAccountInvitationMapperExt;

	@SuppressWarnings("unchecked")
	@Test
	public void testSendInvitation() throws JobExecutionException {
		SimpleUserAccountInvitation accountInvitation = new SimpleUserAccountInvitation();
		accountInvitation
				.setInvitationstatus(RegisterStatusConstants.VERIFICATING);
		List<SimpleUserAccountInvitation> invitations = Arrays
				.asList(accountInvitation);

		when(
				userAccountInvitationMapperExt
						.findAccountInvitations(RegisterStatusConstants.VERIFICATING))
				.thenReturn(invitations);

		sendInvitationJob.executeJob(context);

		verify(extMailService, times(1)).sendHTMLMail(anyString(), anyString(),
				any(List.class), any(List.class), any(List.class), anyString(),
				anyString(), any(List.class));

		ArgumentCaptor<SimpleUserAccountInvitation> invitationArgument = ArgumentCaptor
				.forClass(SimpleUserAccountInvitation.class);
		verify(userAccountInvitationMapper).updateByPrimaryKeySelective(
				invitationArgument.capture());
		Assert.assertEquals(RegisterStatusConstants.SENT_VERIFICATION_EMAIL,
				invitationArgument.getValue().getInvitationstatus());
	}
}
