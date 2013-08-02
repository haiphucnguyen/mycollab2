/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.user.view.component.PermissionComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectRoleAddViewImpl extends AbstractView implements
		ProjectRoleAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private ProjectRole projectRole;

	public ProjectRoleAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final ProjectRole item) {
		this.projectRole = item;
		this.editForm.setItemDataSource(new BeanItem<ProjectRole>(
				this.projectRole));
	}

	@Override
	public PermissionMap getPermissionMap() {
		return this.editForm.getPermissionMap();
	}

	public class EditForm extends AdvancedEditBeanForm<ProjectRole> {

		private static final long serialVersionUID = 1L;
		private final Map<String, PermissionComboBox> permissionControlsMap = new HashMap<String, PermissionComboBox>();

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends ProjectRoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Role");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<ProjectRole>(
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
				final VerticalLayout permissionsPanel = new VerticalLayout();
				final Label organizationHeader = new Label("Permissions");
				organizationHeader.setStyleName("h2");
				permissionsPanel.addComponent(organizationHeader);

				PermissionMap perMap;
				if (ProjectRoleAddViewImpl.this.projectRole instanceof SimpleProjectRole) {
					perMap = ((SimpleProjectRole) ProjectRoleAddViewImpl.this.projectRole)
							.getPermissionMap();
				} else {
					perMap = new PermissionMap();
				}

				final GridFormLayoutHelper permissionFormHelper = new GridFormLayoutHelper(
						2,
						ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length,
						"100%", "167px", Alignment.MIDDLE_LEFT);

				for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
					final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
					final PermissionComboBox permissionBox = new PermissionComboBox();

					final Integer flag = perMap
							.getPermissionFlag(permissionPath);
					permissionBox.setValue(flag);
					com.esofthead.mycollab.module.project.view.people.ProjectRoleAddViewImpl.EditForm.this.permissionControlsMap
							.put(permissionPath, permissionBox);
					permissionFormHelper.addComponent(permissionBox,
							permissionPath, 0, i);
				}

				permissionFormHelper.getLayout().setWidth("100%");
				permissionFormHelper.getLayout().setMargin(false);
				permissionFormHelper.getLayout().addStyleName(
						"colored-gridlayout");
				permissionsPanel.addComponent(permissionFormHelper.getLayout());

				return permissionsPanel;
			}
		}

		public PermissionMap getPermissionMap() {
			final PermissionMap permissionMap = new PermissionMap();

			for (final String permissionItem : this.permissionControlsMap
					.keySet()) {
				final PermissionComboBox permissionBox = this.permissionControlsMap
						.get(permissionItem);
				final Integer perValue = (Integer) permissionBox.getValue();
				permissionMap.addPath(permissionItem, perValue);
			}
			return permissionMap;
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("description")) {
					final TextArea textArea = new TextArea();
					textArea.setNullRepresentation("");
					return textArea;
				} else if (propertyId.equals("isadmin")) {

				} else if (propertyId.equals("rolename")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a projectRole name");
					return tf;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<ProjectRole> getEditFormHandlers() {
		return this.editForm;
	}

}
