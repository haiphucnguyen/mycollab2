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

package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugResolution;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.project.view.bug.components.BugResolutionComboBox;
import com.esofthead.mycollab.module.project.view.bug.components.VersionMultiSelectField;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
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
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ResolvedInputWindow extends Window {
	private static final long serialVersionUID = 1L;

	private final SimpleBug bug;
	private final EditForm editForm;
	private VersionMultiSelectField fixedVersionSelect;
	private final IBugCallbackStatusComp callbackForm;

	public ResolvedInputWindow(final IBugCallbackStatusComp callbackForm,
			final SimpleBug bug) {
		super("Resolve bug '" + bug.getSummary() + "'");
		this.bug = bug;
		this.callbackForm = callbackForm;

		VerticalLayout contentLayout = new VerticalLayout();
		this.setWidth("800px");
		this.setResizable(false);
		this.setModal(true);
		this.editForm = new EditForm();
		contentLayout.addComponent(this.editForm);
		this.editForm.setBean(bug);

		contentLayout.setMargin(new MarginInfo(false, false, true, false));
		this.setContent(contentLayout);
		this.center();
	}

	private class EditForm extends AdvancedEditBeanForm<BugWithBLOBs> {

		private static final long serialVersionUID = 1L;
		private RichTextArea commentArea;

		@Override
		public void setBean(final BugWithBLOBs newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setBeanFormFieldFactory(new EditFormFieldFactory(EditForm.this));
			super.setBean(newDataSource);
		}

		class FormLayoutFactory implements IFormLayoutFactory {

			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout layout = new VerticalLayout();
				this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"150px", Alignment.TOP_LEFT);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");

				layout.addComponent(this.informationLayout.getLayout());

				final HorizontalLayout controlsBtn = new HorizontalLayout();
				controlsBtn.setSpacing(true);
				layout.addComponent(controlsBtn);

				final Button cancelBtn = new Button(
						AppContext
								.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								ResolvedInputWindow.this.close();
							}
						});
				cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
				controlsBtn.addComponent(cancelBtn);
				controlsBtn.setComponentAlignment(cancelBtn,
						Alignment.MIDDLE_LEFT);

				final Button wonFixBtn = new Button(
						AppContext.getMessage(BugI18nEnum.BUTTON_RESOLVED),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								if (EditForm.this.validateForm()) {
									ResolvedInputWindow.this.bug
											.setStatus(BugStatus.Resolved
													.name());

									final BugRelatedItemService bugRelatedItemService = ApplicationContextUtil
											.getSpringBean(BugRelatedItemService.class);
									bugRelatedItemService
											.updateFixedVersionsOfBug(
													ResolvedInputWindow.this.bug
															.getId(),
													ResolvedInputWindow.this.fixedVersionSelect
															.getSelectedItems());

									// Save bug status and assignee
									final BugService bugService = ApplicationContextUtil
											.getSpringBean(BugService.class);
									bugService.updateWithSession(
											ResolvedInputWindow.this.bug,
											AppContext.getUsername());

									// Save comment
									final String commentValue = EditForm.this.commentArea
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
										comment.setTypeid(""
												+ ResolvedInputWindow.this.bug
														.getId());
										comment.setExtratypeid(CurrentProjectVariables
												.getProjectId());

										final CommentService commentService = ApplicationContextUtil
												.getSpringBean(CommentService.class);
										commentService.saveWithSession(comment,
												AppContext.getUsername());
									}

									ResolvedInputWindow.this.close();
									ResolvedInputWindow.this.callbackForm
											.refreshBugItem();
								}

							}
						});
				wonFixBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
				controlsBtn.addComponent(wonFixBtn);
				controlsBtn.setComponentAlignment(wonFixBtn,
						Alignment.MIDDLE_RIGHT);
				controlsBtn.setMargin(true);

				layout.setComponentAlignment(controlsBtn,
						Alignment.MIDDLE_RIGHT);

				return layout;
			}

			@Override
			public void attachField(final Object propertyId,
					final Field<?> field) {
				if (propertyId.equals("resolution")) {
					this.informationLayout.addComponent(field,
							AppContext.getMessage(BugI18nEnum.FORM_RESOLUTION),
							0, 0);
				} else if (propertyId.equals("assignuser")) {
					this.informationLayout
							.addComponent(field, AppContext
									.getMessage(GenericI18Enum.FORM_ASSIGNEE),
									0, 1);
				} else if (propertyId.equals("fixedVersions")) {
					this.informationLayout.addComponent(field, AppContext
							.getMessage(BugI18nEnum.FORM_FIXED_VERSIONS), 0, 2);
				} else if (propertyId.equals("comment")) {
					this.informationLayout.addComponent(field,
							AppContext.getMessage(BugI18nEnum.FORM_COMMENT), 0,
							3, 2, "100%");
				}
			}
		}

		private class EditFormFieldFactory extends
				AbstractBeanFieldGroupEditFieldFactory<BugWithBLOBs> {

			private static final long serialVersionUID = 1L;

			public EditFormFieldFactory(GenericBeanForm<BugWithBLOBs> form) {
				super(form);
			}

			@Override
			protected Field<?> onCreateField(final Object propertyId) {
				if (propertyId.equals("resolution")) {
					ResolvedInputWindow.this.bug
							.setResolution(BugResolution.Fixed.name());
					return BugResolutionComboBox
							.getInstanceForResolvedBugWindow();
				} else if (propertyId.equals("assignuser")) {
					ResolvedInputWindow.this.bug
							.setAssignuser(ResolvedInputWindow.this.bug
									.getLogby());
					return new ProjectMemberSelectionField();
				} else if (propertyId.equals("fixedVersions")) {
					ResolvedInputWindow.this.fixedVersionSelect = new VersionMultiSelectField();
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
