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

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.PermissionChecker;
import com.esofthead.mycollab.security.PermissionDefItem;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class RoleReadViewImpl extends AbstractView implements RoleReadView {

	private static final long serialVersionUID = 1L;
	protected AdvancedPreviewBeanForm<Role> previewForm;
	protected SimpleRole role;

	public RoleReadViewImpl() {
		super();
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
	}

	@Override
	public void previewItem(final SimpleRole role) {
		this.role = role;
		this.previewForm.setItemDataSource(new BeanItem<Role>(role));
	}

	@Override
	public HasPreviewFormHandlers<Role> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private static String getValueFromPerPath(
			final PermissionMap permissionMap, final String permissionItem) {
		final Integer perVal = permissionMap.get(permissionItem);
		if (perVal == null) {
			return "Undefined";
		} else {
			if (PermissionChecker.isAccessPermission(perVal)) {
				return AccessPermissionFlag.toString(perVal);
			} else if (PermissionChecker.isBooleanPermission(perVal)) {
				return BooleanPermissionFlag.toString(perVal);
			} else {
				throw new MyCollabException("Do not support permission value "
						+ perVal);
			}

		}
	}

	protected Depot constructPermissionSectionView(String depotTitle,
			PermissionMap permissionMap, PermissionDefItem[] defItems) {
		final GridFormLayoutHelper formHelper = new GridFormLayoutHelper(2,
				defItems.length, "100%", "167px", Alignment.MIDDLE_LEFT);
		formHelper.getLayout().setMargin(false);
		formHelper.getLayout().setWidth("100%");
		formHelper.getLayout().addStyleName(UIConstants.COLORED_GRIDLAYOUT);
		final Depot component = new Depot(depotTitle, formHelper.getLayout());

		for (int i = 0; i < defItems.length; i++) {
			final PermissionDefItem permissionDefItem = defItems[i];
			formHelper.addComponent(
					new Label(getValueFromPerPath(permissionMap,
							permissionDefItem.getKey())), permissionDefItem
							.getCaption(), 0, i);
		}
		return component;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Role> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new RoleReadViewImpl.PreviewForm.FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			final Window window = new Window("Window to Print");

			final RoleReadViewImpl printView = new RoleReadViewImpl.PrintView();
			printView.previewItem(RoleReadViewImpl.this.role);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			this.getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			this.getWindow().open(new ExternalResource(window.getURL()),
					"_blank", 1100, 200, // Width and height
					Window.BORDER_NONE); // No decorations

			// Print automatically when the window opens.
			// This call will block until the print dialog exits!
			window.executeJavaScript("print();");

			// Close the window automatically after printing
			window.executeJavaScript("self.close();");
		}

		@Override
		protected void showHistory() {
		}

		class FormLayoutFactory extends RoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(RoleReadViewImpl.this.role.getRolename());
			}

			@Override
			protected Layout createTopPanel() {
				PreviewFormControlsGenerator<Role> buttonControls = new PreviewFormControlsGenerator<Role>(
						PreviewForm.this);
				HorizontalLayout layout = buttonControls
						.createButtonControls(RolePermissionCollections.ACCOUNT_ROLE);
				if (role.getIssystemrole() != null
						&& role.getIssystemrole() == Boolean.TRUE) {
					buttonControls.setDeleteButtonVisible(false);
				}
				return layout;
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout permissionsPanel = new VerticalLayout();
				final Label organizationHeader = new Label("Permissions");
				organizationHeader.setStyleName("h2");
				permissionsPanel.addComponent(organizationHeader);

				final PermissionMap permissionMap = RoleReadViewImpl.this.role
						.getPermissionMap();

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Project", permissionMap,
						RolePermissionCollections.PROJECT_PERMISSION_ARR));

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Customer Management", permissionMap,
						RolePermissionCollections.CRM_PERMISSIONS_ARR));

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Document", permissionMap,
						RolePermissionCollections.DOCUMENT_PERMISSION_ARR));

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Account Management", permissionMap,
						RolePermissionCollections.ACCOUNT_PERMISSION_ARR));

				return permissionsPanel;
			}

		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends RoleReadViewImpl {

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<Role>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(final Item item,
								final Object propertyId,
								final Component uiContext) {
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends RoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.role.getRolename());
			}

			@Override
			protected Layout createTopPanel() {
				return new VerticalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout permissionsPanel = new VerticalLayout();
				final Label organizationHeader = new Label("Permissions");
				organizationHeader.setStyleName("h2");
				permissionsPanel.addComponent(organizationHeader);

				final PermissionMap permissionMap = PrintView.this.role
						.getPermissionMap();

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Project", permissionMap,
						RolePermissionCollections.PROJECT_PERMISSION_ARR));

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Customer Management", permissionMap,
						RolePermissionCollections.CRM_PERMISSIONS_ARR));

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Document", permissionMap,
						RolePermissionCollections.DOCUMENT_PERMISSION_ARR));

				permissionsPanel.addComponent(constructPermissionSectionView(
						"Account Management", permissionMap,
						RolePermissionCollections.ACCOUNT_PERMISSION_ARR));

				return permissionsPanel;
			}
		}
	}

	@Override
	public SimpleRole getItem() {
		return this.role;
	}
}
