/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 */
@ViewComponent
public class TaskReadViewImpl extends AbstractPageView implements TaskReadView {

	private static final long serialVersionUID = 1L;

	protected AdvancedPreviewBeanForm<Task> previewForm;

	protected SimpleTask task;

	public TaskReadViewImpl() {
		super();
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
		this.setMargin(true);
	}

	@Override
	public SimpleTask getItem() {
		return this.task;
	}

	@Override
	public HasPreviewFormHandlers<Task> getPreviewFormHandlers() {
		return this.previewForm;
	}

	@Override
	public void previewItem(final SimpleTask task) {
		this.task = task;
		this.previewForm.setItemDataSource(new BeanItem<Task>(task));
	}

	private class PreviewForm extends TaskPreviewForm {
		private static final long serialVersionUID = 1L;

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;
			private Button quickActionStatusBtn;

			public FormLayoutFactory() {
				super(TaskReadViewImpl.this.task.getTaskname());

				if (TaskReadViewImpl.this.task.getPercentagecomplete() != null
						&& 100d == TaskReadViewImpl.this.task
								.getPercentagecomplete()) {
					this.addTitleStyle(UIConstants.LINK_COMPLETED);
				} else {
					if ("Pending"
							.equals(TaskReadViewImpl.this.task.getStatus())) {
						this.addTitleStyle(UIConstants.LINK_PENDING);
					} else if ((TaskReadViewImpl.this.task.getEnddate() != null && (TaskReadViewImpl.this.task
							.getEnddate().before(new GregorianCalendar()
							.getTime())))
							|| (TaskReadViewImpl.this.task.getActualenddate() != null && (TaskReadViewImpl.this.task
									.getActualenddate()
									.before(new GregorianCalendar().getTime())))
							|| (TaskReadViewImpl.this.task.getDeadline() != null && (TaskReadViewImpl.this.task
									.getDeadline()
									.before(new GregorianCalendar().getTime())))) {
						this.addTitleStyle(UIConstants.LINK_OVERDUE);
					}
				}
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				final TabSheet tabTaskDetail = new TabSheet();
				tabTaskDetail.setWidth("100%");

				final CommentDisplay commentList = new CommentDisplay(
						CommentType.PRJ_TASK,
						TaskReadViewImpl.this.task.getId(),
						CurrentProjectVariables.getProjectId(), true, true,
						ProjectTaskRelayEmailNotificationAction.class);
				commentList.setMargin(true);
				tabTaskDetail.addTab(commentList, "Comments");

				final TaskHistoryList historyList = new TaskHistoryList(
						TaskReadViewImpl.this.task.getId());
				historyList.setMargin(true);
				tabTaskDetail.addTab(historyList, "History");

				final TaskFollowersSheet followerSheet = new TaskFollowersSheet(
						TaskReadViewImpl.this.task);
				tabTaskDetail.addTab(followerSheet, "Follower");

				final TaskTimeLogSheet timesheet = new TaskTimeLogSheet(
						TaskReadViewImpl.this.task);
				tabTaskDetail.addTab(timesheet, "Time");

				return tabTaskDetail;
			}

			@Override
			protected ComponentContainer createTopPanel() {
				ProjectPreviewFormControlsGenerator<Task> taskPreviewForm = new ProjectPreviewFormControlsGenerator<Task>(
						PreviewForm.this);
				final HorizontalLayout topPanel = taskPreviewForm
						.createButtonControls(
								ProjectRolePermissionCollections.TASKS, true);
				topPanel.setMargin(true);

				quickActionStatusBtn = new Button("",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								if (quickActionStatusBtn.getCaption().equals(
										"ReOpen")) {
									task.setPercentagecomplete(0d);
									ProjectTaskService service = ApplicationContextUtil
											.getSpringBean(ProjectTaskService.class);
									service.updateWithSession(task,
											AppContext.getUsername());
									FormLayoutFactory.this
											.removeTitleStyleName(UIConstants.LINK_COMPLETED);
									FormLayoutFactory.this.setPercent("0.0");
									quickActionStatusBtn.setCaption("Close");
									quickActionStatusBtn.setIcon(MyCollabResource
											.newResource("icons/16/project/closeTask.png"));
								} else {
									task.setPercentagecomplete(100d);
									ProjectTaskService service = ApplicationContextUtil
											.getSpringBean(ProjectTaskService.class);
									service.updateWithSession(task,
											AppContext.getUsername());
									FormLayoutFactory.this
											.addTitleStyleName("headerName");
									FormLayoutFactory.this
											.addTitleStyleName(UIConstants.LINK_COMPLETED);
									FormLayoutFactory.this.setPercent("100.0");
									quickActionStatusBtn.setCaption("ReOpen");
									quickActionStatusBtn.setIcon(MyCollabResource
											.newResource("icons/16/project/reopenTask.png"));
								}
							}
						});
				quickActionStatusBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				if (task.getPercentagecomplete() == 100d) {
					quickActionStatusBtn.setCaption("ReOpen");
					quickActionStatusBtn.setIcon(MyCollabResource
							.newResource("icons/16/project/reopenTask.png"));
					taskPreviewForm.addQuickActionButton(quickActionStatusBtn);
				} else {
					quickActionStatusBtn.setCaption("Close");
					quickActionStatusBtn.setIcon(MyCollabResource
							.newResource("icons/16/project/closeTask.png"));
					taskPreviewForm.addQuickActionButton(quickActionStatusBtn);
				}
				return topPanel;
			}
		}

		@Override
		TaskFormLayoutFactory getFormLayoutFactory() {
			return new FormLayoutFactory();
		}

		@Override
		protected void taskDoPrint() {
			// Create a window that contains what you want to print
			final Window window = new Window("Window to Print");

			final TaskReadViewImpl printView = new TaskReadViewImpl.PrintView();
			printView.previewItem(TaskReadViewImpl.this.task);
			window.setContent(printView);

			UI.getCurrent().addWindow(window);

			// Print automatically when the window opens
			JavaScript.getCurrent().execute(
					"setTimeout(function() {"
							+ "  print(); self.close();}, 0);");
		}

		@Override
		protected void taskShowHistory() {
			final TaskHistoryLogWindow historyLog = new TaskHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.TASK,
					TaskReadViewImpl.this.task.getId());
			UI.getCurrent().addWindow(historyLog);
		}
	}

	public static class PrintView extends TaskReadViewImpl {
		private static final long serialVersionUID = 1L;

		public PrintView() {
			super();
		}

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.task.getTaskname());
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				return new CommentListDepot(CommentType.PRJ_TASK,
						PrintView.this.task.getId(),
						CurrentProjectVariables.getProjectId(), false, false);
			}

			@Override
			protected ComponentContainer createTopPanel() {
				return new HorizontalLayout();
			}
		}
	}
}
