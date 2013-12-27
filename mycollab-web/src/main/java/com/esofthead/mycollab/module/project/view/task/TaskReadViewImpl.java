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

package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.ui.components.DefaultProjectFormViewFieldFactory.ProjectFormAttachmentDisplayField;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

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
		this.previewForm.setBean(task);
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Task> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setBean(Task bean) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setBeanFormFieldFactory(new ReadFormFieldFactory(
					PreviewForm.this));
			super.setBean(bean);
		}

		@Override
		public void showHistory() {
			final TaskHistoryLogWindow historyLog = new TaskHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.TASK,
					TaskReadViewImpl.this.task.getId());
			UI.getCurrent().addWindow(historyLog);
		}
	}

	class FormLayoutFactory extends TaskFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		public FormLayoutFactory() {
			super(TaskReadViewImpl.this.task.getTaskname());

			if (TaskReadViewImpl.this.task.getPercentagecomplete() != null
					&& 100d == TaskReadViewImpl.this.task
							.getPercentagecomplete()) {
				this.addTitleStyle(UIConstants.LINK_COMPLETED);
			} else {
				if ("Pending".equals(TaskReadViewImpl.this.task.getStatus())) {
					this.addTitleStyle(UIConstants.LINK_PENDING);
				} else if ((TaskReadViewImpl.this.task.getEnddate() != null && (TaskReadViewImpl.this.task
						.getEnddate().before(new GregorianCalendar().getTime())))
						|| (TaskReadViewImpl.this.task.getActualenddate() != null && (TaskReadViewImpl.this.task
								.getActualenddate()
								.before(new GregorianCalendar().getTime())))
						|| (TaskReadViewImpl.this.task.getDeadline() != null && (TaskReadViewImpl.this.task
								.getDeadline().before(new GregorianCalendar()
								.getTime())))) {
					this.addTitleStyle(UIConstants.LINK_OVERDUE);
				}
			}
		}

		@Override
		protected ComponentContainer createBottomPanel() {
			final TabsheetLazyLoadComp tabTaskDetail = new TabsheetLazyLoadComp();
			tabTaskDetail.setWidth("100%");

			final CommentDisplay commentList = new CommentDisplay(
					CommentType.PRJ_TASK,
					CurrentProjectVariables.getProjectId(), true, true,
					ProjectTaskRelayEmailNotificationAction.class);
			commentList.setMargin(true);
			tabTaskDetail.addTab(commentList, "Comments", MyCollabResource
					.newResource("icons/16/project/gray/comment.png"));

			final TaskHistoryList historyList = new TaskHistoryList(
					TaskReadViewImpl.this.task.getId());
			historyList.setMargin(true);
			tabTaskDetail.addTab(historyList, "History", MyCollabResource
					.newResource("icons/16/project/gray/history.png"));

			final TaskFollowersSheet followerSheet = new TaskFollowersSheet(
					TaskReadViewImpl.this.task);
			tabTaskDetail.addTab(followerSheet, "Follower", MyCollabResource
					.newResource("icons/16/project/gray/follow.png"));

			final TaskTimeLogSheet timesheet = new TaskTimeLogSheet(
					TaskReadViewImpl.this.task);
			tabTaskDetail.addTab(timesheet, "Time", MyCollabResource
					.newResource("icons/16/project/gray/time.png"));

			return tabTaskDetail;
		}

		@Override
		protected ComponentContainer createTopPanel() {
			ProjectPreviewFormControlsGenerator<Task> taskPreviewForm = new ProjectPreviewFormControlsGenerator<Task>(
					previewForm);
			final HorizontalLayout topPanel = taskPreviewForm
					.createButtonControls(
							ProjectRolePermissionCollections.TASKS, true);
			topPanel.setMargin(true);
			return topPanel;
		}
	}

	private class ReadFormFieldFactory extends
			AbstractBeanFieldGroupViewFieldFactory<Task> {
		private static final long serialVersionUID = 1L;

		public ReadFormFieldFactory(GenericBeanForm<Task> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {

			if (propertyId.equals("assignuser")) {
				return new ProjectUserFormLinkField(task.getAssignuser(),
						task.getAssignUserAvatarId(),
						task.getAssignUserFullName());
			} else if (propertyId.equals("taskListName")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						task.getTaskListName());
			} else if (propertyId.equals("startdate")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						AppContext.formatDate(task.getStartdate()));
			} else if (propertyId.equals("enddate")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						AppContext.formatDate(task.getEnddate()));
			} else if (propertyId.equals("actualstartdate")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						AppContext.formatDate(task.getActualstartdate()));
			} else if (propertyId.equals("actualenddate")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						AppContext.formatDate(task.getActualenddate()));
			} else if (propertyId.equals("deadline")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						AppContext.formatDate(task.getDeadline()));
			} else if (propertyId.equals("tasklistid")) {
				return new DefaultFormViewFieldFactory.FormLinkViewField(
						task.getTaskListName(), new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new TaskListEvent.GotoRead(this, task
												.getTasklistid()));
							}
						});
			} else if (propertyId.equals("id")) {
				return new ProjectFormAttachmentDisplayField(
						task.getProjectid(), AttachmentType.PROJECT_TASK_TYPE,
						task.getId());
			} else if (propertyId.equals("priority")) {
				if (StringUtils.isNotNullOrEmpty(task.getPriority())) {
					final Resource iconPriority = TaskPriorityComboBox
							.getIconResourceByPriority(task.getPriority());
					final Embedded iconEmbedded = new Embedded(null,
							iconPriority);
					final Label lbPriority = new Label(task.getPriority());

					final FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
					containerField.addComponentField(iconEmbedded);
					containerField.getLayout().setComponentAlignment(
							iconEmbedded, Alignment.MIDDLE_LEFT);
					lbPriority.setWidth("220px");
					containerField.addComponentField(lbPriority);
					containerField.getLayout().setExpandRatio(lbPriority, 1.0f);
					return containerField;
				}
			} else if (propertyId.equals("notes")) {
				return new FormDetectAndDisplayUrlViewField(task.getNotes());
			}
			return null;
		}
	}
}
