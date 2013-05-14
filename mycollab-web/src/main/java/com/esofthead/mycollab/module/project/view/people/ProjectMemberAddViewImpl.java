/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectRoleComboBox;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberAddViewImpl extends AbstractView implements
		ProjectMemberAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private ProjectMember user;

	public ProjectMemberAddViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(ProjectMember item) {
		this.user = item;
		editForm.setItemDataSource(new BeanItem<ProjectMember>(user));
	}

	private class EditForm extends AdvancedEditBeanForm<ProjectMember> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource,
				Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Project Member", UserAvatarControlFactory
						.getResource(AppContext.getAccountId(), null, 48));
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<ProjectMember>(
						EditForm.this)).createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("username")) {
					if (user.getUsername() == null) {
						ProjectMemberService prjMemberService = AppContext
								.getSpringBean(ProjectMemberService.class);
						List<SimpleUser> users = prjMemberService
								.getUsersNotInProject(CurrentProjectVariables
										.getProjectId());
						return new UserComboBox(users);
					} else {
						if (user instanceof SimpleProjectMember) {
							return new DefaultFormViewFieldFactory.FormViewField(
									((SimpleProjectMember) user)
											.getMemberFullName());
						} else {
							return new DefaultFormViewFieldFactory.FormViewField(
									user.getUsername());
						}
					}

				} else if (propertyId.equals("isadmin")) {
					return new AdminRoleSelectionField();
				}
				return null;
			}
		}

		private class AdminRoleSelectionField extends CustomField {
			private static final long serialVersionUID = 1L;
			private final CheckBox isAdminCheck;
			private final HorizontalLayout layout;
			private final HorizontalLayout roleLayout;
			private final ProjectRoleComboBox roleComboBox;

			public AdminRoleSelectionField() {
				layout = new HorizontalLayout();
				layout.setSpacing(true);

				roleLayout = new HorizontalLayout();
				roleLayout.setSpacing(true);
				roleLayout.addComponent(new Label("Role"));
				roleComboBox = new ProjectRoleComboBox();
				roleComboBox.addListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(Property.ValueChangeEvent event) {
						user.setProjectroleid((Integer) roleComboBox.getValue());
						user.setIsadmin(Boolean.FALSE);
					}
				});

				roleLayout.addComponent(roleComboBox);

				isAdminCheck = new CheckBox("");
				isAdminCheck.setImmediate(true);
				isAdminCheck.setWriteThrough(true);
				isAdminCheck.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						user.setIsadmin((Boolean) isAdminCheck.getValue());

						if (user.getIsadmin()) {
							user.setProjectroleid(null);
							layout.removeComponent(roleLayout);
						} else {
							if (roleComboBox.getContainerPropertyIds().size() > 0) {
								layout.addComponent(roleLayout);

								if (user.getProjectroleid() != null) {
									roleComboBox.setValue(user
											.getProjectroleid());
								}
							} else {
								AppContext
										.getApplication()
										.getMainWindow()
										.showNotification(
												LocalizationHelper
														.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
												"You must have at least one role to deselect admin checkbox",
												Window.Notification.TYPE_HUMANIZED_MESSAGE);
								isAdminCheck.setValue(Boolean.TRUE);
							}
						}
					}
				});

				isAdminCheck.setValue(user.getIsadmin());
				layout.addComponent(isAdminCheck);
				if (user.getIsadmin() == null || !user.getIsadmin()) {
					if (roleComboBox.isComboRoleNotEmpty()) {
						layout.addComponent(roleLayout);
					} else {
						isAdminCheck.setValue(true);
					}
				}

				this.setCompositionRoot(layout);
			}

			@Override
			public Class<?> getType() {
				return Object.class;
			}
		}
	}

	@Override
	public HasEditFormHandlers<ProjectMember> getEditFormHandlers() {
		return editForm;
	}
}
