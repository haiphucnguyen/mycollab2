package com.esofthead.mycollab.schedule.email.user.impl;

import com.esofthead.mycollab.schedule.email.GenericJobTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.quartz.JobExecutionException;

public class SendUserInvitationEmailJobTest extends GenericJobTest {

    @Spy
    @InjectMocks
    private SendUserInvitationEmailJob sendInvitationJob;

    @SuppressWarnings("unchecked")
    @Test
    public void testSendInvitation() throws JobExecutionException {
//		SimpleUserAccountInvitation accountInvitation = new SimpleUserAccountInvitation();
//		accountInvitation
//				.setInvitationstatus(RegisterStatusConstants.VERIFICATING);
//		List<SimpleUserAccountInvitation> invitations = Arrays
//				.asList(accountInvitation);
//
//		when(
//				userAccountInvitationMapperExt
//						.findAccountInvitations(RegisterStatusConstants.VERIFICATING))
//				.thenReturn(invitations);
//
//		sendInvitationJob.executeJob(context);
//
//		verify(extMailService, times(1)).sendHTMLMail(anyString(), anyString(),
//				any(List.class), any(List.class), any(List.class), anyString(),
//				anyString(), any(List.class));
//
//		ArgumentCaptor<SimpleUserAccountInvitation> invitationArgument = ArgumentCaptor
//				.forClass(SimpleUserAccountInvitation.class);
//		verify(userAccountInvitationMapper).updateByPrimaryKeySelective(
//				invitationArgument.capture());
//		Assert.assertEquals(RegisterStatusConstants.SENT_VERIFICATION_EMAIL,
//				invitationArgument.getValue().getInvitationstatus());
    }
}
