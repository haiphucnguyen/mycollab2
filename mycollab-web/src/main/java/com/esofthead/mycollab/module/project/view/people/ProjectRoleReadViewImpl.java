/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.user.PermissionFlag;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectRoleReadViewImpl extends AbstractView implements
		ProjectRoleReadView {

	private static final long serialVersionUID = 1L;
	protected AdvancedPreviewBeanForm<ProjectRole> previewForm;
	protected SimpleProjectRole role;

	public ProjectRoleReadViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleProjectRole role) {
		this.role = role;
		previewForm.setItemDataSource(new BeanItem<ProjectRole>(role));
	}

	@Override
	public HasPreviewFormHandlers<ProjectRole> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<ProjectRole> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			ProjectRoleReadViewImpl printView = new ProjectRoleReadViewImpl.PrintView();
			printView.previewItem(role);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			getWindow().open(new ExternalResource(window.getURL()), "_blank",
					1100, 200, // Width and height
					Window.BORDER_NONE); // No decorations

			// Print automatically when the window opens.
			// This call will block until the print dialog exits!
			window.executeJavaScript("print();");

			// Close the window automatically after printing
			window.executeJavaScript("self.close();");
		}

		@Override
		protected void showHistory() {
			ProjectRoleHistoryLogWindow historyLog = new ProjectRoleHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.PROJECT_ROLE,
					role.getId());
			getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends ProjectRoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(role.getRolename());
			}

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<ProjectRole>(
						PreviewForm.this)).createButtonControls(
						ProjectRolePermissionCollections.ROLES,
						ModuleNameConstants.PRJ);
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout permissionsPanel = new VerticalLayout();
				Label organizationHeader = new Label("Permissions");
				organizationHeader.setStyleName("h2");
				permissionsPanel.addComponent(organizationHeader);

				PermissionMap permissionMap = role.getPermissionMap();

				GridFormLayoutHelper projectFormHelper = new GridFormLayoutHelper(
						2,
						ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length);

				for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
					String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
					projectFormHelper.addComponent(
							new Label(getValueFromPerPath(permissionMap,
									permissionPath)), permissionPath, 0, i);
				}

				permissionsPanel.addComponent(projectFormHelper.getLayout());

				return permissionsPanel;
			}

			private String getValueFromPerPath(PermissionMap permissionMap,
					String permissionItem) {
				Integer perVal = permissionMap.get(permissionItem);
				if (perVal == null) {
					return "No Access";
				} else {
					return PermissionFlag.toString(perVal);
				}
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends ProjectRoleReadViewImpl {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<ProjectRole>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(Item item,
								Object propertyId, Component uiContext) {
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends ProjectRoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(role.getRolename());
			}

			@Override
			protected Layout createTopPanel() {
				return new VerticalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout permissionsPanel = new VerticalLayout();
				Label organizationHeader = new Label("Permissions");
				organizationHeader.setStyleName("h2");
				permissionsPanel.addComponent(organizationHeader);

				PermissionMap permissionMap = role.getPermissionMap();

				GridFormLayoutHelper projectFormHelper = new GridFormLayoutHelper(
						2,
						ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length);

				for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
					String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
					projectFormHelper.addComponent(
							new Label(getValueFromPerPath(permissionMap,
									permissionPath)), permissionPath, 0, i);
				}

				permissionsPanel.addComponent(projectFormHelper.getLayout());

				return permissionsPanel;
			}

			private String getValueFromPerPath(PermissionMap permissionMap,
					String permissionItem) {
				Integer perVal = permissionMap.get(permissionItem);
				if (perVal == null) {
					return "No Access";
				} else {
					return PermissionFlag.toString(perVal);
				}
			}
		}
	}

	@Override
	public SimpleProjectRole getItem() {
		return role;
	}
}
