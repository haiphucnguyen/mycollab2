package com.esofthead.mycollab.schedule.email.project.impl;

import com.esofthead.mycollab.common.NotificationType;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.GenericJobTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import scala.collection.immutable.Set;

import java.util.Arrays;
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
        notUser1.setUsername("hainguyen@esofthead.com");

        SimpleUser notUser2 = new SimpleUser();
        notUser2.setUsername("linhduong@esofthead.com");

        List<SimpleUser> notifyUsers = Arrays.asList(notUser1, notUser2);
        prjRelayNotification.setNotifyUsers(notifyUsers);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.None.name());
        noSetting1.setUsername("hainguyen@esofthead.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt()))
                .thenReturn(Arrays.asList(noSetting1));

        Set<SimpleUser> users = bugEmailNotification
                .getListNotifyUsersWithFilter(prjRelayNotification);
        Assert.assertEquals(1, users.size());
        assertThat(users.head().getUsername()).isEqualTo("linhduong@esofthead.com");
    }

    @Test
    public void testGetListNotifyUsersWithFilter2() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("hainguyen@esofthead.com");
        List<SimpleUser> notifyUsers = Arrays.asList(notUser1);
        prjRelayNotification.setNotifyUsers(notifyUsers);
        prjRelayNotification.setTypeid("1");
        prjRelayNotification.setSaccountid(1);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.Minimal.name());
        noSetting1.setProjectid(1);
        noSetting1.setSaccountid(1);
        noSetting1.setUsername("hainguyen@esofthead.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt()))
                .thenReturn(Arrays.asList(noSetting1));

        SimpleBug bug = new SimpleBug();
        bug.setAssignuser("hainguyen@esofthead.com");
        when(bugService.findById(anyInt(), anyInt())).thenReturn(bug);

        when(projectMemberService.getActiveUserOfProject(anyString(), anyInt(), anyInt())).thenReturn(notUser1);

        Set<SimpleUser> users = bugEmailNotification
                .getListNotifyUsersWithFilter(prjRelayNotification);
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void testGetListNotifyUsersWithFilter3() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("hainguyen@esofthead.com");
        List<SimpleUser> notifyUsers = Arrays.asList(notUser1);
        prjRelayNotification.setNotifyUsers(notifyUsers);
        prjRelayNotification.setTypeid("1");
        prjRelayNotification.setSaccountid(1);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.Minimal.name());
        noSetting1.setProjectid(1);
        noSetting1.setSaccountid(1);
        noSetting1.setUsername("hainguyen@esofthead.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt()))
                .thenReturn(Arrays.asList(noSetting1));

        SimpleBug bug = new SimpleBug();
        bug.setAssignuser("hainguyen@esofthead.com");
        when(bugService.findById(anyInt(), anyInt())).thenReturn(bug);

        when(projectMemberService.getActiveUserOfProject(anyString(), anyInt(), anyInt())).thenReturn(notUser1);

        Set<SimpleUser> users = bugEmailNotification
                .getListNotifyUsersWithFilter(prjRelayNotification);
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void testGetListNotifyUsersWithFull() {
        ProjectRelayEmailNotification prjRelayNotification = new ProjectRelayEmailNotification();
        SimpleUser notUser1 = new SimpleUser();
        notUser1.setUsername("linhduong@esofthead.com");
        List<SimpleUser> notifyUsers = Arrays.asList(notUser1);
        prjRelayNotification.setNotifyUsers(notifyUsers);
        prjRelayNotification.setTypeid("1");
        prjRelayNotification.setSaccountid(1);

        ProjectNotificationSetting noSetting1 = new ProjectNotificationSetting();
        noSetting1.setLevel(NotificationType.Full.name());
        noSetting1.setProjectid(1);
        noSetting1.setSaccountid(1);
        noSetting1.setUsername("hainguyen@esofthead.com");

        when(projectNotificationService.findNotifications(anyInt(), anyInt()))
                .thenReturn(Arrays.asList(noSetting1));

        Set<SimpleUser> users = bugEmailNotification
                .getListNotifyUsersWithFilter(prjRelayNotification);
        Assert.assertEquals(1, users.size());
    }
}
