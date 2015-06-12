package com.esofthead.mycollab.schedule.email.project.impl;

import com.esofthead.mycollab.common.NotificationType;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.GenericJobTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import scala.collection.immutable.Set;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

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
		java.util.List<SimpleUser> notifyUsers = new ArrayList<SimpleUser>();
		prjRelayNotification.setNotifyUsers(notifyUsers);

		ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
		noSetting1.setLevel(NotificationType.None.name());
		noSetting1.setUsername("hainguyen@esofthead.com");

		when(projectNotificationService.findNotifications(anyInt(), anyInt()))
				.thenReturn(Arrays.asList(noSetting1));

		SimpleUser activeUser1 = new SimpleUser();
		activeUser1.setUsername("hainguyen@esofthead.com");
		when(projectMemberService.getActiveUsersInProject(anyInt(), anyInt()))
				.thenReturn(Arrays.asList(activeUser1));

		Set<SimpleUser> users = bugEmailNotification
				.getListNotifyUsersWithFilter(prjRelayNotification);
		Assert.assertEquals(0, users.size());
	}
}
