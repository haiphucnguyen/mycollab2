package com.esofthead.mycollab.module.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class ProjectTaskServiceTest extends ServiceTest {

	@Autowired
	private ProjectTaskService projectTaskService;

	@DataSet
	@Test
	public void testFindById() {
		SimpleTask task = projectTaskService.findById(1, 1);
		assertThat(task.getTaskname()).isEqualTo("task1");
		assertThat(task.getProjectShortname()).isEqualTo("aaa");
	}

	@DataSet
	@Test
	public void testFindByProjectAndTaskKey() {
		SimpleTask task = projectTaskService.findByProjectAndTaskKey(1, "aaa",
				1);
		assertThat(task.getTaskname()).isEqualTo("task1");
		assertThat(task.getProjectShortname()).isEqualTo("aaa");
	}
}
