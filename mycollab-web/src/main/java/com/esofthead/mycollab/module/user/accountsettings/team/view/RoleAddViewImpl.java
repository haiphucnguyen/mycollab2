/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.view.component.PermissionComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
public class RoleAddViewImpl extends AbstractView implements RoleAddView {

	private static final long serialVersionUID = 1L;
	private final RoleAddViewImpl.EditForm editForm;
	private Role role;

	public RoleAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final Role item) {
		this.role = item;
		this.editForm.setItemDataSource(new BeanItem<Role>(this.role));
	}

	@Override
	public PermissionMap getPermissionMap() {
		return this.editForm.getPermissionMap();
	}

	public class EditForm extends AdvancedEditBeanForm<Role> {

		private static final long serialVersionUID = 1L;
		private final Map<String, PermissionComboBox> permissionControlsMap = new HashMap<String, PermissionComboBox>();

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new RoleAddViewImpl.EditForm.FormLayoutFactory());
			this.setFormFieldFactory(new RoleAddViewImpl.EditForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends RoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Role");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Role>(
						RoleAddViewImpl.EditForm.this)).createButtonControls();
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
				if (RoleAddViewImpl.this.role instanceof SimpleRole) {
					perMap = ((SimpleRole) RoleAddViewImpl.this.role)
							.getPermissionMap();
				} else {
					perMap = new PermissionMap();
				}

				final GridFormLayoutHelper crmFormHelper = new GridFormLayoutHelper(
						2,
						RolePermissionCollections.CRM_PERMISSIONS_ARR.length,
						"100%", "167px", Alignment.MIDDLE_LEFT);
				crmFormHelper.getLayout().setMargin(false);
				crmFormHelper.getLayout().setWidth("100%");
				crmFormHelper.getLayout().addStyleName(
						UIConstants.COLORED_GRIDLAYOUT);
				final Depot crmHeader = new Depot(
						"Customer Relationship Management",
						crmFormHelper.getLayout());

				for (int i = 0; i < RolePermissionCollections.CRM_PERMISSIONS_ARR.length; i++) {
					final String permissionPath = RolePermissionCollections.CRM_PERMISSIONS_ARR[i];
					final PermissionComboBox permissionBox = new PermissionComboBox();

					final Integer flag = perMap
							.getPermissionFlag(permissionPath);
					permissionBox.setValue(flag);
					EditForm.this.permissionControlsMap.put(permissionPath,
							permissionBox);
					crmFormHelper.addComponent(permissionBox, permissionPath,
							0, i);
				}

				permissionsPanel.addComponent(crmHeader);

				final GridFormLayoutHelper userFormHelper = new GridFormLayoutHelper(
						2,
						RolePermissionCollections.USER_PERMISSION_ARR.length,
						"100%", "167px", Alignment.MIDDLE_LEFT);
				userFormHelper.getLayout().setMargin(false);
				userFormHelper.getLayout().setWidth("100%");
				userFormHelper.getLayout().addStyleName(
						UIConstants.COLORED_GRIDLAYOUT);
				final Depot userHeader = new Depot("User Management",
						userFormHelper.getLayout());

				for (int i = 0; i < RolePermissionCollections.USER_PERMISSION_ARR.length; i++) {
					final String permissionPath = RolePermissionCollections.USER_PERMISSION_ARR[i];
					final PermissionComboBox permissionBox = new PermissionComboBox();
					final Integer flag = perMap
							.getPermissionFlag(permissionPath);
					permissionBox.setValue(flag);
					EditForm.this.permissionControlsMap.put(permissionPath,
							permissionBox);
					userFormHelper.addComponent(permissionBox, permissionPath,
							0, i);
				}

				permissionsPanel.addComponent(userHeader);

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
					tf.setRequiredError("Please enter a Role name");
					return tf;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Role> getEditFormHandlers() {
		return this.editForm;
	}
}
