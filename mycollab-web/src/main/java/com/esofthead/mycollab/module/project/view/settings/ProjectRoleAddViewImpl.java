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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.user.view.component.AccessPermissionComboBox;
import com.esofthead.mycollab.security.PermissionMap;
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
		private final Map<String, AccessPermissionComboBox> permissionControlsMap = new HashMap<String, AccessPermissionComboBox>();

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
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
					final AccessPermissionComboBox permissionBox = new AccessPermissionComboBox();

					final Integer flag = perMap
							.getPermissionFlag(permissionPath);
					permissionBox.setValue(flag);
					com.esofthead.mycollab.module.project.view.settings.ProjectRoleAddViewImpl.EditForm.this.permissionControlsMap
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
				final AccessPermissionComboBox permissionBox = this.permissionControlsMap
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
