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
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AssignmentPreviewBuilder extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected SimpleTask task;
	protected NoteListItems noteListItems;
	protected AdvancedPreviewBeanForm<SimpleTask> previewForm;

	protected void initRelatedComponent() {
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleTask item) {
		task = item;
		previewForm.setItemDataSource(new BeanItem<SimpleTask>(task));

		displayNotes();
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.TASK, task.getId());
	}

	protected class TaskFormFieldFactory extends DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("assignuser")) {
				return new UserLinkViewField(
						AssignmentPreviewBuilder.this.task.getAssignuser(),
						AssignmentPreviewBuilder.this.task
								.getAssignUserAvatarId(),
						AssignmentPreviewBuilder.this.task
								.getAssignUserFullName());
			} else if (propertyId.equals("startdate")) {
				if (task.getStartdate() == null)
					return null;
				return new DateFieldWithUserTimeZone(task.getStartdate(),
						"DATETIME_FIELD");
			} else if (propertyId.equals("duedate")) {
				if (task.getDuedate() == null)
					return null;
				return new DateFieldWithUserTimeZone(task.getDuedate(),
						"DATETIME_FIELD");
			} else if (propertyId.equals("contactid")) {
				return new FormViewField(task.getContactName());
			} else if (propertyId.equals("type")) {
				return new RelatedReadItemField(task);

			}

			return null;
		}
	}

	public static class ReadView extends AssignmentPreviewBuilder {
		private static final long serialVersionUID = 1L;
		private VerticalLayout assignmentInformation;
		private ReadViewLayout assignmentAddLayout;

		public ReadView() {
			assignmentAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/task.png"));
			this.addComponent(assignmentAddLayout);
			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<SimpleTask>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.TASK,
							AssignmentDefaultFormLayoutFactory.getForm()));
					this.setFormFieldFactory(new TaskFormFieldFactory());
					super.setItemDataSource(newDataSource);
					assignmentAddLayout.setTitle(task.getSubject());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					AssignmentPreviewBuilder printView = new AssignmentPreviewBuilder.PrintView();
					printView.previewItem(task);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				protected void showHistory() {
					AssignmentHistoryLogWindow historyLog = new AssignmentHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.TASK,
							task.getId());
					UI.getCurrent().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_TASK);

			assignmentAddLayout.addControlButtons(optionalActionControls);

			assignmentInformation = new VerticalLayout();
			assignmentInformation.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_TASK);
			actionControls.addStyleName("control-buttons");
			assignmentInformation.addComponent(actionControls);

			assignmentInformation.addComponent(previewForm);
			assignmentInformation.addComponent(noteListItems);

			assignmentAddLayout.addTab(assignmentInformation,
					"Assignment Information");

			this.addComponent(assignmentAddLayout);
		}
	}

	/**
	 * 
	 * @author haiphucnguyen
	 * 
	 */
	public static class PrintView extends AssignmentPreviewBuilder {
		private static final long serialVersionUID = 1L;

		public PrintView() {
			initRelatedComponent();
			previewForm = new AdvancedPreviewBeanForm<SimpleTask>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new TaskFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends AssignmentFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(task.getSubject());
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(noteListItems);
				return relatedItemsPanel;
			}
		}
	}

	public SimpleTask getAssignment() {
		return task;
	}

	public AdvancedPreviewBeanForm<SimpleTask> getPreviewForm() {
		return previewForm;
	}
}
