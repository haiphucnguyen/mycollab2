package com.mycollab.schedule.email.project.service;

import com.mycollab.common.NotificationType;
import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.mycollab.module.project.schedule.email.service.BugRelayEmailNotificationActionImpl;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.service.ProjectNotificationSettingService;
import com.mycollab.module.project.domain.SimpleBug;
import com.mycollab.module.project.service.BugService;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.schedule.email.GenericJobTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class BugRelayEmailNotificationActionImplTest extends GenericJobTest {

    @Spy
    @InjectMocks
    private BugRelayEmailNotificationActionImpl bugEmailNotification;

    @Mock
    private BugService bugService;

    @Mock
    private ProjectNotificationSettingService projectNotificationService;

    @Mock
    private ProjectMemberService projectMemberService;

    @Test
    public void testGetListNotifyUsersWithFilterAndNone() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("hainguyen@mycollab.com");

        SimpleUser notUser2 = new SimpleUser();
        notUser2.setUsername("linhduong@mycollab.com");

        List<SimpleUser> notifyUsers = Arrays.asList(notUser1, notUser2);
        prjRelayNotification.setNotifyUsers(notifyUsers);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.None.name());
        noSetting1.setUsername("hainguyen@mycollab.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt())).thenReturn(Collections.singletonList(noSetting1));

        List<SimpleUser> users = bugEmailNotification.getListNotifyUsersWithFilter(prjRelayNotification);
        Assertions.assertEquals(1, users.size());
        assertThat(users.get(0).getUsername()).isEqualTo("linhduong@mycollab.com");
    }

    @Test
    public void testGetListNotifyUsersWithFilter2() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("hainguyen@mycollab.com");
        List<SimpleUser> notifyUsers = Collections.singletonList(notUser1);
        prjRelayNotification.setNotifyUsers(notifyUsers);
        prjRelayNotification.setTypeid("1");
        prjRelayNotification.setSaccountid(1);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.Minimal.name());
        noSetting1.setProjectid(1);
        noSetting1.setSaccountid(1);
        noSetting1.setUsername("hainguyen@mycollab.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(noSetting1));

        SimpleBug bug = new SimpleBug();
        bug.setAssignuser("hainguyen@mycollab.com");
        when(bugService.findById(anyInt(), anyInt())).thenReturn(bug);

        when(projectMemberService.getActiveUserOfProject(anyString(), anyInt(), anyInt())).thenReturn(notUser1);

        List<SimpleUser> users = bugEmailNotification.getListNotifyUsersWithFilter(prjRelayNotification);
        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void testGetListNotifyUsersWithFilter3() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("hainguyen@mycollab.com");
        List<SimpleUser> notifyUsers = Collections.singletonList(notUser1);
        prjRelayNotification.setNotifyUsers(notifyUsers);
        prjRelayNotification.setTypeid("1");
        prjRelayNotification.setSaccountid(1);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.Minimal.name());
        noSetting1.setProjectid(1);
        noSetting1.setSaccountid(1);
        noSetting1.setUsername("hainguyen@mycollab.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt())).thenReturn(Collections.singletonList(noSetting1));

        SimpleBug bug = new SimpleBug();
        bug.setAssignuser("hainguyen@mycollab.com");
        when(bugService.findById(anyInt(), anyInt())).thenReturn(bug);

        when(projectMemberService.getActiveUserOfProject(anyString(), anyInt(), anyInt())).thenReturn(notUser1);

        List<SimpleUser> users = bugEmailNotification.getListNotifyUsersWithFilter(prjRelayNotification);
        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void testGetListNotifyUsersWithFull() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("linhduong@mycollab.com");
        List<SimpleUser> notifyUsers = Collections.singletonList(notUser1);
        prjRelayNotification.setNotifyUsers(notifyUsers);
        prjRelayNotification.setTypeid("1");
        prjRelayNotification.setSaccountid(1);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.Full.name());
        noSetting1.setProjectid(1);
        noSetting1.setSaccountid(1);
        noSetting1.setUsername("hainguyen@mycollab.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(noSetting1));

        when(projectMemberService.getActiveUserOfProject(anyString(), anyInt(), anyInt())).thenReturn(notUser1);

        List<SimpleUser> users = bugEmailNotification.getListNotifyUsersWithFilter(prjRelayNotification);
        Assertions.assertEquals(1, users.size());
    }
}
