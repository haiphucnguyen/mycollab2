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
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
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
public class ApproveInputWindow extends Window {
	private static final long serialVersionUID = 1L;
	private final SimpleBug bug;
	private final EditForm editForm;
	private final IBugCallbackStatusComp callbackForm;

	public ApproveInputWindow(final IBugCallbackStatusComp callbackForm,
			final SimpleBug bug) {
		super("Approve bug '" + bug.getSummary() + "'");
		this.bug = bug;
		this.callbackForm = callbackForm;
		this.setWidth("750px");
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		((VerticalLayout) this.getContent()).setMargin(false, false, true,
				false);
		this.editForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
		this.center();
	}

	private class EditForm extends AdvancedEditBeanForm<BugWithBLOBs> {

		private static final long serialVersionUID = 1L;
		private RichTextArea commentArea;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new EditForm.FormLayoutFactory());
			this.setFormFieldFactory(new EditForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory implements IFormLayoutFactory {

			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout layout = new VerticalLayout();
				this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");

				layout.addComponent(this.informationLayout.getLayout());

				final HorizontalLayout controlsBtn = new HorizontalLayout();
				controlsBtn.setSpacing(true);
				controlsBtn.setMargin(true, false, true, false);
				layout.addComponent(controlsBtn);

				final Button cancelBtn = new Button("Cancel",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								ApproveInputWindow.this.close();
							}
						});
				cancelBtn.setStyleName("link");
				controlsBtn.addComponent(cancelBtn);
				controlsBtn.setComponentAlignment(cancelBtn,
						Alignment.MIDDLE_LEFT);

				final Button approveBtn = new Button("Approve & Close",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								// Save bug status and assignee
								ApproveInputWindow.this.bug
										.setStatus(BugStatusConstants.CLOSE);
								final BugService bugService = AppContext
										.getSpringBean(BugService.class);
								bugService.updateWithSession(
										ApproveInputWindow.this.bug,
										AppContext.getUsername());

								// Save comment
								final String commentValue = (String) EditForm.this.commentArea
										.getValue();
								if (commentValue != null
										&& !commentValue.trim().equals("")) {
									final Comment comment = new Comment();
									comment.setComment((String) EditForm.this.commentArea
											.getValue());
									comment.setCreatedtime(new GregorianCalendar()
											.getTime());
									comment.setCreateduser(AppContext
											.getUsername());
									comment.setSaccountid(AppContext
											.getAccountId());
									comment.setType(CommentTypeConstants.PRJ_BUG);
									comment.setTypeid(ApproveInputWindow.this.bug
											.getId());

									final CommentService commentService = AppContext
											.getSpringBean(CommentService.class);
									commentService.saveWithSession(comment,
											AppContext.getUsername());
								}

								ApproveInputWindow.this.close();
								ApproveInputWindow.this.callbackForm
										.refreshBugItem();
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
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("assignuser")) {
					this.informationLayout.addComponent(field, "Assign User",
							0, 0);
				} else if (propertyId.equals("comment")) {
					this.informationLayout.addComponent(field, "Comments", 0,
							1, 2, "100%", Alignment.MIDDLE_LEFT);
				}
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("assignuser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("comment")) {
					EditForm.this.commentArea = new RichTextArea();
					EditForm.this.commentArea.setNullRepresentation("");
					return EditForm.this.commentArea;
				}

				return null;
			}
		}
	}
}
