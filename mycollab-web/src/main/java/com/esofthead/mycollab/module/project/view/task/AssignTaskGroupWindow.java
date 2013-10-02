/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class AssignTaskGroupWindow extends Window {
	private static final long serialVersionUID = 1L;
	private final TaskList task;
	private final EditForm editForm;

	public AssignTaskGroupWindow(TaskList task) {
		super("Assign task group '" + task.getName() + "'");
		this.task = task;
		this.setWidth("750px");
		editForm = new EditForm();
		this.addComponent(editForm);
		editForm.setItemDataSource(new BeanItem<TaskList>(task));
		VerticalLayout contentLayout = (VerticalLayout) this.getContent();
		contentLayout.setSpacing(true);
		contentLayout.setMargin(false, false, true, false);
		center();
	}

	private class EditForm extends AdvancedEditBeanForm<BugWithBLOBs> {

		private static final long serialVersionUID = 1L;
		private RichTextArea commentArea;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory implements IFormLayoutFactory {

			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout layout = new VerticalLayout();
				this.informationLayout = new GridFormLayoutHelper(2, 2, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");

				layout.addComponent(informationLayout.getLayout());

				HorizontalLayout controlsBtn = new HorizontalLayout();
				controlsBtn.setSpacing(true);
				controlsBtn.setMargin(true, true, true, false);
				layout.addComponent(controlsBtn);

				Button cancelBtn = new Button("Cancel",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								AssignTaskGroupWindow.this.close();
							}
						});
				cancelBtn.setStyleName("link");
				controlsBtn.addComponent(cancelBtn);
				controlsBtn.setComponentAlignment(cancelBtn,
						Alignment.MIDDLE_LEFT);

				Button approveBtn = new Button("Assign",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {

								// Save task status and assignee
								ProjectTaskListService bugService = ApplicationContextUtil
										.getSpringBean(ProjectTaskListService.class);
								bugService.updateWithSession(task,
										AppContext.getUsername());

								// Save comment
								String commentValue = (String) commentArea
										.getValue();
								if (commentValue != null
										&& !commentValue.trim().equals("")) {
									Comment comment = new Comment();
									comment.setComment((String) commentArea
											.getValue());
									comment.setCreatedtime(new GregorianCalendar()
											.getTime());
									comment.setCreateduser(AppContext
											.getUsername());
									comment.setSaccountid(AppContext
											.getAccountId());
									comment.setType(CommentType.PRJ_TASK_LIST
											.toString());
									comment.setTypeid(task.getId());

									CommentService commentService = ApplicationContextUtil
											.getSpringBean(CommentService.class);
									commentService.saveWithSession(comment,
											AppContext.getUsername());
								}

								AssignTaskGroupWindow.this.close();
								EventBus.getInstance().fireEvent(
										new TaskListEvent.GotoRead(this, task
												.getId()));
							}
						});
				approveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				controlsBtn.addComponent(approveBtn);
				controlsBtn.setComponentAlignment(approveBtn,
						Alignment.MIDDLE_RIGHT);

				layout.setComponentAlignment(controlsBtn,
						Alignment.MIDDLE_RIGHT);

				return layout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("owner")) {
					informationLayout.addComponent(field, LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0,
							0);
				} else if (propertyId.equals("comment")) {
					informationLayout.addComponent(field, "Comment", 0, 1, 2,
							"100%");
				}
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("owner")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("comment")) {
					commentArea = new RichTextArea();
					commentArea.setNullRepresentation("");
					return commentArea;
				}
				return null;
			}
		}
	}
}
