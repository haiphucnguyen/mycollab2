package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.common.i18n.DayI18nEnum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.TaskEvent;
import com.esofthead.mycollab.mobile.ui.DefaultPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class TaskGroupListDisplay
		extends
		DefaultPagedBeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList> {

	private static final long serialVersionUID = -9048439116024747065L;

	public TaskGroupListDisplay() {
		super(ApplicationContextUtil
				.getSpringBean(ProjectTaskListService.class),
				new TaskGroupRowDisplayHandler());
	}

	private static class TaskGroupRowDisplayHandler implements
			RowDisplayHandler<SimpleTaskList> {

		@Override
		public Component generateRow(final SimpleTaskList taskList, int rowIndex) {
			HorizontalLayout taskListLayout = new HorizontalLayout();
			taskListLayout.setStyleName("task-list-layout");

			VerticalLayout taskListInfo = new VerticalLayout();
			taskListInfo.setStyleName("task-list-info");
			NavigationButton b = new NavigationButton(taskList.getName());
			b.addClickListener(new NavigationButton.NavigationButtonClickListener() {

				private static final long serialVersionUID = -2481787976727400924L;

				@Override
				public void buttonClick(
						NavigationButton.NavigationButtonClickEvent event) {
					EventBusFactory.getInstance().post(
							new TaskEvent.GoInsideList(this, taskList.getId()));
				}
			});
			b.setWidth("100%");
			b.setStyleName("task-list-name");
			taskListInfo.addComponent(b);

			Label taskListUpdateTime = new Label(AppContext.getMessage(
					DayI18nEnum.LAST_UPDATED_ON,
					AppContext.formatDateTime(taskList.getLastupdatedtime())));
			taskListUpdateTime.setWidthUndefined();
			taskListUpdateTime.setStyleName("last-updated-time");
			taskListInfo.addComponent(taskListUpdateTime);

			Label activeTasksNum = new Label(taskList.getNumOpenTasks() + "");
			activeTasksNum.setWidthUndefined();
			activeTasksNum.setStyleName("active-task-num");

			taskListLayout.addComponent(activeTasksNum);
			taskListLayout.setExpandRatio(taskListInfo, 1.0f);
			taskListLayout.addStyleName("list-item");

			return taskListLayout;
		}

	}

}
