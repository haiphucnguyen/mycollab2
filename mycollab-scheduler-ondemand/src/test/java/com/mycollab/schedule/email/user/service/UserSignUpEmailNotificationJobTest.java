package com.mycollab.schedule.email.user.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.module.billing.UserStatusConstants;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.domain.User;
import com.mycollab.module.user.schedule.email.service.UserSignUpEmailNotificationJob;
import com.mycollab.module.user.service.UserService;
import com.mycollab.schedule.email.GenericJobTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.quartz.JobExecutionException;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UserSignUpEmailNotificationJobTest extends GenericJobTest {
    @Spy
    @InjectMocks
    private UserSignUpEmailNotificationJob confirmEmailJob;

    @Mock
    private UserService userService;

    @Test
    public void testSendConfirmEmail() throws JobExecutionException {
        SimpleUser user = new SimpleUser();

        when(userService.findPagableListByCriteria(any(BasicSearchRequest.class))).thenReturn(Collections.singletonList(user));

        confirmEmailJob.executeJob(context);

        verify(extMailService, times(1)).sendHTMLMail(anyString(), anyString(),
                any(List.class), any(List.class), any(List.class), anyString(),
                anyString(), any(List.class));

        ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);

        ArgumentCaptor<String> stringArgument = ArgumentCaptor.forClass(String.class);
        verify(userService).updateWithSession(userArgument.capture(), stringArgument.capture());

        Assert.assertEquals(UserStatusConstants.EMAIL_VERIFIED_REQUEST, userArgument.getValue().getStatus());
    }
}
