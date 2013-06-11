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
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
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
		this.setMargin(false, false, true, false);
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
	}

	@Override
	public void previewItem(final SimpleProjectRole role) {
		this.role = role;
		this.previewForm.setItemDataSource(new BeanItem<ProjectRole>(role));
	}

	@Override
	public HasPreviewFormHandlers<ProjectRole> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<ProjectRole> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
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

			final ProjectRoleReadViewImpl printView = new ProjectRoleReadViewImpl.PrintView();
			printView.previewItem(ProjectRoleReadViewImpl.this.role);
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
			final ProjectRoleHistoryLogWindow historyLog = new ProjectRoleHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.PROJECT_ROLE,
					ProjectRoleReadViewImpl.this.role.getId());
			this.getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends ProjectRoleFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(ProjectRoleReadViewImpl.this.role.getRolename());
			}

			@Override
			protected Layout createTopPanel() {
				return (new ProjectPreviewFormControlsGenerator<ProjectRole>(
						PreviewForm.this))
						.createButtonControls(ProjectRolePermissionCollections.ROLES);
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout permissionsPanel = new VerticalLayout();
				final Label organizationHeader = new Label("Permissions");
				organizationHeader.setStyleName("h2");
				permissionsPanel.addComponent(organizationHeader);

				final PermissionMap permissionMap = ProjectRoleReadViewImpl.this.role
						.getPermissionMap();

				final GridFormLayoutHelper projectFormHelper = new GridFormLayoutHelper(
						2,
						ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length,
						"100%", "167px", Alignment.MIDDLE_LEFT);

				for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
					final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
					projectFormHelper.addComponent(
							new Label(this.getValueFromPerPath(permissionMap,
									permissionPath)), permissionPath, 0, i);
				}

				projectFormHelper.getLayout().setWidth("100%");
				projectFormHelper.getLayout().setMargin(false);
				projectFormHelper.getLayout()
						.addStyleName("colored-gridlayout");

				permissionsPanel.addComponent(projectFormHelper.getLayout());

				return permissionsPanel;
			}

			private String getValueFromPerPath(
					final PermissionMap permissionMap,
					final String permissionItem) {
				final Integer perVal = permissionMap.get(permissionItem);
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
			this.previewForm = new AdvancedPreviewBeanForm<ProjectRole>() {
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

		class FormLayoutFactory extends ProjectRoleFormLayoutFactory {

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

				final GridFormLayoutHelper projectFormHelper = new GridFormLayoutHelper(
						2,
						ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length);

				for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
					final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
					projectFormHelper.addComponent(
							new Label(this.getValueFromPerPath(permissionMap,
									permissionPath)), permissionPath, 0, i);
				}

				permissionsPanel.addComponent(projectFormHelper.getLayout());

				return permissionsPanel;
			}

			private String getValueFromPerPath(
					final PermissionMap permissionMap,
					final String permissionItem) {
				final Integer perVal = permissionMap.get(permissionItem);
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
		return this.role;
	}
}
