package com.esofthead.mycollab.module.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectNotificationServiceTest extends IntergrationServiceTest {
    @Autowired
    private ProjectService projectService;

    @DataSet
    @Test
    public void testGetNotification() {
        List<ProjectRelayEmailNotification> projectRelayEmailNotifications = projectService.findProjectRelayEmailNotifications();
        assertThat(projectRelayEmailNotifications.size()).isEqualTo(1);
        ProjectRelayEmailNotification projectRelayEmailNotification = projectRelayEmailNotifications.get(0);
        assertThat(projectRelayEmailNotification.getNotifyUsers().size()).isEqualTo(2);
        assertThat(projectRelayEmailNotification.getNotifyUsers()).extracting("username").
                contains("admin").contains("user2");
    }
}
