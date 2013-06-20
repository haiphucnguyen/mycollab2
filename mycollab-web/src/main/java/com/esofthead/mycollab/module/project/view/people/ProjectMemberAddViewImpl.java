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
import com.vaadin.ui.Alignment;
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
		this.setMargin(false, false, true, false);
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final ProjectMember item) {
		this.user = item;
		this.editForm.setItemDataSource(new BeanItem<ProjectMember>(this.user));
	}

	private class EditForm extends AdvancedEditBeanForm<ProjectMember> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(ProjectMemberAddViewImpl.this.user.getId() == null) ? "Create Project Member"
								: ((SimpleProjectMember) ProjectMemberAddViewImpl.this.user)
										.getMemberFullName(),
						UserAvatarControlFactory.createAvatarResource(null, 24));
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<ProjectMember>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("username")) {
					if (ProjectMemberAddViewImpl.this.user.getUsername() == null) {
						final ProjectMemberService prjMemberService = AppContext
								.getSpringBean(ProjectMemberService.class);
						final List<SimpleUser> users = prjMemberService
								.getUsersNotInProject(CurrentProjectVariables
										.getProjectId());
						return new UserComboBox(users);
					} else {
						if (ProjectMemberAddViewImpl.this.user instanceof SimpleProjectMember) {
							return new DefaultFormViewFieldFactory.FormViewField(
									((SimpleProjectMember) ProjectMemberAddViewImpl.this.user)
											.getMemberFullName());
						} else {
							return new DefaultFormViewFieldFactory.FormViewField(
									ProjectMemberAddViewImpl.this.user
											.getUsername());
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
				this.layout = new HorizontalLayout();
				this.layout.setSpacing(true);

				this.roleLayout = new HorizontalLayout();
				this.roleLayout.setSpacing(true);
				this.roleLayout.addComponent(new Label("Role"));
				this.roleComboBox = new ProjectRoleComboBox();
				this.roleComboBox
						.addListener(new Property.ValueChangeListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void valueChange(
									final Property.ValueChangeEvent event) {
								ProjectMemberAddViewImpl.this.user
										.setProjectroleid((Integer) AdminRoleSelectionField.this.roleComboBox
												.getValue());
								ProjectMemberAddViewImpl.this.user
										.setIsadmin(Boolean.FALSE);
							}
						});

				this.roleLayout.addComponent(this.roleComboBox);

				this.isAdminCheck = new CheckBox("");
				this.isAdminCheck.setImmediate(true);
				this.isAdminCheck.setWriteThrough(true);
				this.isAdminCheck.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						ProjectMemberAddViewImpl.this.user
								.setIsadmin((Boolean) AdminRoleSelectionField.this.isAdminCheck
										.getValue());

						if (ProjectMemberAddViewImpl.this.user.getIsadmin()) {
							ProjectMemberAddViewImpl.this.user
									.setProjectroleid(null);
							AdminRoleSelectionField.this.layout
									.removeComponent(AdminRoleSelectionField.this.roleLayout);
						} else {
							if (AdminRoleSelectionField.this.roleComboBox
									.getContainerPropertyIds().size() > 0) {
								AdminRoleSelectionField.this.layout
										.addComponent(AdminRoleSelectionField.this.roleLayout);

								if (ProjectMemberAddViewImpl.this.user
										.getProjectroleid() != null) {
									AdminRoleSelectionField.this.roleComboBox
											.setValue(ProjectMemberAddViewImpl.this.user
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
								AdminRoleSelectionField.this.isAdminCheck
										.setValue(Boolean.TRUE);
							}
						}
					}
				});

				this.isAdminCheck.setValue(ProjectMemberAddViewImpl.this.user
						.getIsadmin());
				this.layout.addComponent(this.isAdminCheck);
				if (ProjectMemberAddViewImpl.this.user.getIsadmin() == null
						|| !ProjectMemberAddViewImpl.this.user.getIsadmin()) {
					if (this.roleComboBox.isComboRoleNotEmpty()) {
						this.layout.addComponent(this.roleLayout);
					} else {
						this.isAdminCheck.setValue(true);
					}
				}

				this.setCompositionRoot(this.layout);
			}

			@Override
			public Class<?> getType() {
				return Object.class;
			}
		}
	}

	@Override
	public HasEditFormHandlers<ProjectMember> getEditFormHandlers() {
		return this.editForm;
	}
}
