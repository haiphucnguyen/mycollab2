package com.esofthead.mycollab.module.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class ProjectNotificationSettingServiceTest extends ServiceTest {

	@Autowired
	private ProjectNotificationSettingService projectNotificationSettingService;

	@DataSet
	@Test
	public void testFindNotificationSetting() {
		List<ProjectNotificationSetting> notifications = projectNotificationSettingService
				.findNotifications(1, 1);
		assertThat(notifications.size()).isEqualTo(2);
	}
}
