package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
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
				return new FormLinkViewField(task.getAssignUserFullName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
							}
						});
			} else if (propertyId.equals("startdate")) {
				return new FormViewField(AppContext.formatDateTime(task
						.getStartdate()));
			} else if (propertyId.equals("duedate")) {
				return new FormViewField(AppContext.formatDateTime(task
						.getDuedate()));
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
		private TabSheet tabContainer;
		private VerticalLayout assignmentInformation;
		private AddViewLayout assignmentAddLayout;

		public ReadView() {
			assignmentAddLayout = new AddViewLayout("", new ThemeResource(
					"icons/48/crm/task.png"));
			assignmentAddLayout.addStyleName("preview");
			this.addComponent(assignmentAddLayout);
			initRelatedComponent();

			tabContainer = new TabSheet();
			tabContainer.setStyleName(UIConstants.WHITE_TABSHEET);

			previewForm = new AdvancedPreviewBeanForm<SimpleTask>() {
				private static final long serialVersionUID = 1L;

				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new AssignmentFormLayoutFactory.AssignmentInformationLayout());
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
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					getWindow().open(new ExternalResource(window.getURL()),
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
					AssignmentHistoryLogWindow historyLog = new AssignmentHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.TASK,
							task.getId());
					getWindow().addWindow(historyLog);
				}
			};

			assignmentInformation = new VerticalLayout();
			assignmentInformation.setMargin(true);
			Layout actionControls = new PreviewFormControlsGenerator<SimpleTask>(
					previewForm)
					.createButtonControls(RolePermissionCollections.CRM_TASK);
			assignmentInformation.addComponent(actionControls);
			assignmentInformation.addComponent(previewForm);
			assignmentInformation.addComponent(noteListItems);

			tabContainer
					.addTab(assignmentInformation, "Assignment Information");

			assignmentAddLayout.addBody(tabContainer);
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
