/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
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
public class AssignBugWindow extends Window {
	private static final long serialVersionUID = 1L;
	private SimpleBug bug;
	private EditForm editForm;
	private IBugCallbackStatusComp callbackForm;

	public AssignBugWindow(IBugCallbackStatusComp callbackForm, SimpleBug bug) {
		this.bug = bug;
		this.callbackForm = callbackForm;
		this.setWidth("830px");
		editForm = new EditForm();
		this.addComponent(editForm);
		editForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
		center();
	}

	private class EditForm extends AdvancedEditBeanForm<Bug> {

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
				informationLayout = new GridFormLayoutHelper(2, 6);
				informationLayout.getLayout().setWidth("800px");

				layout.addComponent(informationLayout.getLayout());

				HorizontalLayout controlsBtn = new HorizontalLayout();
				controlsBtn.setSpacing(true);
				layout.addComponent(controlsBtn);

				Button cancelBtn = new Button("Cancel",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								AssignBugWindow.this.close();
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

								// Save bug status and assignee
								BugService bugService = AppContext
										.getSpringBean(BugService.class);
								bugService.updateWithSession(bug,
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
									comment.setType(CommentTypeConstants.PRJ_BUG);
									comment.setTypeid(bug.getId());

									CommentService commentService = AppContext
											.getSpringBean(CommentService.class);
									commentService.saveWithSession(comment,
											AppContext.getUsername());
								}

								AssignBugWindow.this.close();
								callbackForm.refreshBugItem();
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
				if (propertyId.equals("assignuser")) {
					informationLayout.addComponent(field, "Assign User", 0, 0);
				} else if (propertyId.equals("comment")) {
					informationLayout.addComponent(field, "Comments", 0, 1, 2,
							UIConstants.DEFAULT_2XCONTROL_WIDTH);
				}
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("assignuser")) {
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
