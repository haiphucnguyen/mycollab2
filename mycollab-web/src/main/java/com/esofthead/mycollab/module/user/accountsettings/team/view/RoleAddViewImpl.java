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
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.view.component.PermissionComboBoxFactory;
import com.esofthead.mycollab.security.PermissionDefItem;
import com.esofthead.mycollab.security.PermissionMap;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.KeyCaptionComboBox;
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
public class RoleAddViewImpl extends AbstractPageView implements RoleAddView {

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
		private final Map<String, KeyCaptionComboBox> permissionControlsMap = new HashMap<String, KeyCaptionComboBox>();

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new RoleAddViewImpl.EditForm.FormLayoutFactory());
			this.setFormFieldFactory(new RoleAddViewImpl.EditForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
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

				for (int i = 0; i < RolePermissionCollections.CRM_PERMISSIONS_ARR.length; i++) {
					PermissionDefItem permissionDefItem = RolePermissionCollections.CRM_PERMISSIONS_ARR[i];
					KeyCaptionComboBox permissionBox = PermissionComboBoxFactory
							.createPermissionSelection(permissionDefItem
									.getPermissionCls());

					final Integer flag = perMap
							.getPermissionFlag(permissionDefItem.getKey());
					permissionBox.setValue(flag);
					EditForm.this.permissionControlsMap.put(
							permissionDefItem.getKey(), permissionBox);
					crmFormHelper.addComponent(permissionBox,
							permissionDefItem.getCaption(), 0, i);
				}

				permissionsPanel.addComponent(constructGridLayout(
						"Project Management", perMap,
						RolePermissionCollections.PROJECT_PERMISSION_ARR));
				permissionsPanel.addComponent(constructGridLayout(
						"Customer Management", perMap,
						RolePermissionCollections.CRM_PERMISSIONS_ARR));
				permissionsPanel.addComponent(constructGridLayout("Document",
						perMap,
						RolePermissionCollections.DOCUMENT_PERMISSION_ARR));
				permissionsPanel.addComponent(constructGridLayout(
						"Account Management", perMap,
						RolePermissionCollections.ACCOUNT_PERMISSION_ARR));

				return permissionsPanel;
			}
		}

		private Depot constructGridLayout(String depotTitle,
				PermissionMap perMap, PermissionDefItem[] defItems) {
			final GridFormLayoutHelper formHelper = new GridFormLayoutHelper(2,
					defItems.length, "100%", "167px", Alignment.MIDDLE_LEFT);
			formHelper.getLayout().setMargin(false);
			formHelper.getLayout().setWidth("100%");
			formHelper.getLayout().addStyleName(UIConstants.COLORED_GRIDLAYOUT);
			final Depot component = new Depot(depotTitle,
					formHelper.getLayout());

			for (int i = 0; i < defItems.length; i++) {
				final PermissionDefItem permissionDefItem = defItems[i];
				KeyCaptionComboBox permissionBox = PermissionComboBoxFactory
						.createPermissionSelection(permissionDefItem
								.getPermissionCls());
				final Integer flag = perMap.getPermissionFlag(permissionDefItem
						.getKey());
				permissionBox.setValue(flag);
				EditForm.this.permissionControlsMap.put(
						permissionDefItem.getKey(), permissionBox);
				formHelper.addComponent(permissionBox,
						permissionDefItem.getCaption(), 0, i);

			}

			return component;
		}

		protected PermissionMap getPermissionMap() {
			final PermissionMap permissionMap = new PermissionMap();

			for (final String permissionItem : this.permissionControlsMap
					.keySet()) {
				final KeyCaptionComboBox permissionBox = this.permissionControlsMap
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
				} else if (propertyId.equals("rolename")) {
					if (role.getIssystemrole() != null
							&& role.getIssystemrole() == Boolean.TRUE) {
						return new DefaultFormViewFieldFactory.FormViewField(
								role.getRolename());
					} else {
						final TextField tf = new TextField();
						tf.setNullRepresentation("");
						tf.setRequired(true);
						tf.setRequiredError("Please enter a Role name");
						return tf;
					}

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
