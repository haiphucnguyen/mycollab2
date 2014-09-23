package com.esofthead.mycollab.module.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class TaskListServiceTest extends ServiceTest {

	@Autowired
	private ProjectTaskListService projectTaskListService;

	@SuppressWarnings("unchecked")
	@DataSet
	@Test
	public void testGetArchivedTaskList() {
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		criteria.setStatus(new StringSearchField(StatusI18nEnum.Closed.name()));
		criteria.setProjectId(new NumberSearchField(1));
		criteria.setSaccountid(new NumberSearchField(1));
		List<SimpleTaskList> taskLists = (List<SimpleTaskList>) projectTaskListService
				.findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
						criteria));
		assertThat(taskLists.size()).isEqualTo(2);
		List<SimpleTask> subTasks = taskLists.get(0).getSubTasks();
		assertThat(subTasks.size()).isEqualTo(2);
		assertThat(subTasks).extracting("id", "taskname").contains(
				tuple(1, "task1"), tuple(2, "task2"));

		SimpleTaskList taskList3 = taskLists.get(1);
		assertThat(taskList3.getSubTasks().size()).isEqualTo(0);
	}
}
