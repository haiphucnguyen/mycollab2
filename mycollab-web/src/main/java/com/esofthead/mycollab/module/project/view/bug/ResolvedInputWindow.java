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
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
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
@SuppressWarnings("serial")
public class ResolvedInputWindow extends Window {
	private final SimpleBug bug;
	private final EditForm editForm;
	private VersionMultiSelectField fixedVersionSelect;
	private final IBugCallbackStatusComp callbackForm;

	public ResolvedInputWindow(final IBugCallbackStatusComp callbackForm,
			final SimpleBug bug) {
		this.bug = bug;
		this.setWidth("800px");
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.editForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
		this.callbackForm = callbackForm;
		((VerticalLayout) this.getContent()).setMargin(false, false, true,
				false);
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
						"150px", Alignment.MIDDLE_LEFT);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");

				layout.addComponent(this.informationLayout.getLayout());

				final HorizontalLayout controlsBtn = new HorizontalLayout();
				controlsBtn.setSpacing(true);
				layout.addComponent(controlsBtn);

				final Button cancelBtn = new Button("Cancel",
						new Button.ClickListener() {
							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								ResolvedInputWindow.this.close();
							}
						});
				cancelBtn.setStyleName("link");
				controlsBtn.addComponent(cancelBtn);
				controlsBtn.setComponentAlignment(cancelBtn,
						Alignment.MIDDLE_LEFT);

				final Button wonFixBtn = new Button("Resolved",
						new Button.ClickListener() {
							@SuppressWarnings("unchecked")
							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								ResolvedInputWindow.this.bug
										.setStatus(BugStatusConstants.TESTPENDING);

								final BugRelatedItemService bugRelatedItemService = AppContext
										.getSpringBean(BugRelatedItemService.class);
								bugRelatedItemService.updateFixedVersionsOfBug(
										ResolvedInputWindow.this.bug.getId(),
										ResolvedInputWindow.this.fixedVersionSelect
												.getSelectedItems());

								// Save bug status and assignee
								final BugService bugService = AppContext
										.getSpringBean(BugService.class);
								bugService.updateWithSession(
										ResolvedInputWindow.this.bug,
										AppContext.getUsername());

								// Save comment
								final String commentValue = (String) EditForm.this.commentArea
										.getValue();
								if (commentValue != null
										&& !commentValue.trim().equals("")) {
									final Comment comment = new Comment();
									comment.setComment(commentValue);
									comment.setCreatedtime(new GregorianCalendar()
											.getTime());
									comment.setCreateduser(AppContext
											.getUsername());
									comment.setSaccountid(AppContext
											.getAccountId());
									comment.setType(CommentTypeConstants.PRJ_BUG);
									comment.setTypeid(ResolvedInputWindow.this.bug
											.getId());

									final CommentService commentService = AppContext
											.getSpringBean(CommentService.class);
									commentService.saveWithSession(comment,
											AppContext.getUsername());
								}

								ResolvedInputWindow.this.close();
								ResolvedInputWindow.this.callbackForm
										.refreshBugItem();
							}
						});
				wonFixBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				controlsBtn.addComponent(wonFixBtn);
				controlsBtn.setComponentAlignment(wonFixBtn,
						Alignment.MIDDLE_RIGHT);
				controlsBtn.setMargin(true);

				layout.setComponentAlignment(controlsBtn,
						Alignment.MIDDLE_RIGHT);

				return layout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("resolution")) {
					this.informationLayout.addComponent(field, "Resolution", 0,
							0);
				} else if (propertyId.equals("assignuser")) {
					this.informationLayout.addComponent(field, "Assign User",
							0, 1);
				} else if (propertyId.equals("fixedVersions")) {
					this.informationLayout.addComponent(field,
							"Fixed Versions", 0, 2);
				} else if (propertyId.equals("comment")) {
					this.informationLayout.addComponent(field, "Comments", 0,
							3, 2, "100%");
				}
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("resolution")) {
					ResolvedInputWindow.this.bug
							.setResolution(BugResolutionConstants.FIXED);
					return new BugResolutionComboBox();
				} else if (propertyId.equals("assignuser")) {
					ResolvedInputWindow.this.bug
							.setAssignuser(ResolvedInputWindow.this.bug
									.getLogby());
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("fixedVersions")) {
					ResolvedInputWindow.this.fixedVersionSelect = new VersionMultiSelectField(
							"227px");
					if (ResolvedInputWindow.this.bug.getFixedVersions().size() > 0) {
						ResolvedInputWindow.this.fixedVersionSelect
								.setSelectedItems(ResolvedInputWindow.this.bug
										.getFixedVersions());
					}
					return ResolvedInputWindow.this.fixedVersionSelect;
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
