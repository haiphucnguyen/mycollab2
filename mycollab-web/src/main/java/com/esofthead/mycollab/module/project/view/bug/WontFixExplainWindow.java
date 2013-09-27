/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import com.esofthead.mycollab.module.tracker.service.BugService;
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
import com.vaadin.ui.Button.ClickEvent;
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
public class WontFixExplainWindow extends Window {

	private final SimpleBug bug;
	private final EditForm editForm;
	private VersionMultiSelectField fixedVersionSelect;
	private final IBugCallbackStatusComp callbackForm;

	public WontFixExplainWindow(final IBugCallbackStatusComp callbackForm,
			final SimpleBug bug) {
		super("Won't fix bug '" + bug.getSummary() + "'");
		this.bug = bug;
		this.callbackForm = callbackForm;
		this.setWidth("750px");
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.editForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
		this.center();
		((VerticalLayout) this.getContent()).setMargin(false, false, true,
				false);
	}

	private class EditForm extends AdvancedEditBeanForm<BugWithBLOBs> {

		private static final long serialVersionUID = 1L;
		private RichTextArea commentArea;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
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
				controlsBtn.setMargin(true, true, false, false);
				layout.addComponent(controlsBtn);

				final Button cancelBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
						new Button.ClickListener() {
							@Override
							public void buttonClick(final ClickEvent event) {
								WontFixExplainWindow.this.close();
							}
						});
				cancelBtn.setStyleName("link");
				controlsBtn.addComponent(cancelBtn);
				controlsBtn.setComponentAlignment(cancelBtn,
						Alignment.MIDDLE_LEFT);

				final Button wonFixBtn = new Button("Won't Fix",
						new Button.ClickListener() {
							@SuppressWarnings("unchecked")
							@Override
							public void buttonClick(final ClickEvent event) {
								WontFixExplainWindow.this.bug
										.setStatus(BugStatusConstants.RESOLVED);

								final BugRelatedItemService bugRelatedItemService = ApplicationContextUtil
										.getSpringBean(BugRelatedItemService.class);
								bugRelatedItemService.updateFixedVersionsOfBug(
										WontFixExplainWindow.this.bug.getId(),
										WontFixExplainWindow.this.fixedVersionSelect
												.getSelectedItems());

								// Save bug status and assignee
								final BugService bugService = ApplicationContextUtil
										.getSpringBean(BugService.class);
								bugService.updateWithSession(
										WontFixExplainWindow.this.bug,
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
									comment.setType(CommentType.PRJ_BUG
											.toString());
									comment.setTypeid(WontFixExplainWindow.this.bug
											.getId());

									final CommentService commentService = ApplicationContextUtil
											.getSpringBean(CommentService.class);
									commentService.saveWithSession(comment,
											AppContext.getUsername());

									WontFixExplainWindow.this.close();
									WontFixExplainWindow.this.callbackForm
											.refreshBugItem();
								} else {
									AppContext
											.getApplication()
											.getMainWindow()
											.showNotification(
													"Error",
													"You must enter a comment to explain for won't fix resolution",
													Window.Notification.TYPE_HUMANIZED_MESSAGE);
									return;
								}

								WontFixExplainWindow.this.close();
							}
						});
				wonFixBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				controlsBtn.addComponent(wonFixBtn);
				controlsBtn.setComponentAlignment(wonFixBtn,
						Alignment.MIDDLE_RIGHT);

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
					this.informationLayout
							.addComponent(
									field,
									LocalizationHelper
											.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
									0, 1);
				} else if (propertyId.equals("fixedVersions")) {
					this.informationLayout.addComponent(field,
							"Fixed Versions", 0, 2);
				} else if (propertyId.equals("comment")) {
					this.informationLayout.addComponent(field, "Comments", 0,
							3, 2, "100%", Alignment.MIDDLE_LEFT);
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
					return BugResolutionComboBox.getInstanceForWontFixWindow();
				} else if (propertyId.equals("assignuser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("fixedVersions")) {
					WontFixExplainWindow.this.fixedVersionSelect = new VersionMultiSelectField(
							"227px");
					if (WontFixExplainWindow.this.bug.getFixedVersions().size() > 0) {
						WontFixExplainWindow.this.fixedVersionSelect
								.setSelectedItems(WontFixExplainWindow.this.bug
										.getFixedVersions());
					}
					return WontFixExplainWindow.this.fixedVersionSelect;
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
