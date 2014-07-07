package com.esofthead.mycollab.schedule.email.project.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSettingType;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.GenericJobTest;

public class BugRelayEmailNotificationActionImplTest extends GenericJobTest {

	@Spy
	@InjectMocks
	private BugRelayEmailNotificationActionImpl bugEmailNotification;

	@Mock
	private ProjectNotificationSettingService projectNotificationService;

	@Mock
	private ProjectMemberService projectMemberService;

	@Test
	public void testGetListNotifyUsersWithFilterAndNone() {
		ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
		SimpleUser notUser1 = new SimpleUser();
		notUser1.setUsername("hainguyen@esofthead.com");
		List<SimpleUser> notifyUsers = new ArrayList<SimpleUser>();
		prjRelayNotification.setNotifyUsers(notifyUsers);

		ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
		noSetting1.setLevel(ProjectNotificationSettingType.NONE);
		noSetting1.setUsername("hainguyen@esofthead.com");

		when(projectNotificationService.findNotifications(anyInt(), anyInt()))
				.thenReturn(Arrays.asList(noSetting1));

		SimpleUser activeUser1 = new SimpleUser();
		activeUser1.setUsername("hainguyen@esofthead.com");
		when(projectMemberService.getActiveUsersInProject(anyInt(), anyInt()))
				.thenReturn(Arrays.asList(activeUser1));

		List<SimpleUser> users = bugEmailNotification
				.getListNotifyUsersWithFilter(prjRelayNotification);
		Assert.assertEquals(0, users.size());
	}
}
