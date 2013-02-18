/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ComponentReadViewImpl extends AbstractView implements
		ComponentReadView {

	private static final long serialVersionUID = 1L;
	protected SimpleComponent component;
	protected AdvancedPreviewBeanForm<SimpleComponent> previewForm;

	public ComponentReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleComponent item) {
		component = item;
		previewForm.setItemDataSource(new BeanItem<SimpleComponent>(item));
	}

	@Override
	public SimpleComponent getItem() {
		return component;
	}

	@Override
	public HasPreviewFormHandlers<SimpleComponent> getPreviewFormHandlers() {
		return previewForm;
	}

	protected class ComponentFormFieldLayout extends
			DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("userlead")) {
				return new UserLinkViewField(component.getUserlead(),
						component.getUserLeadFullName());
			}
			return null;
		}
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleComponent> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new ComponentFormFieldLayout());
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			ComponentReadViewImpl printView = new ComponentReadViewImpl.PrintView();
			printView.previewItem(component);
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
			ComponentHistoryLogWindow historyLog = new ComponentHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.BUG_COMPONENT,
					component.getId());
			getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends ComponentFormLayoutFactory implements
				IBugReportDisplayContainer {

			private static final long serialVersionUID = 1L;
			private HorizontalLayout bottomLayout;

			public FormLayoutFactory() {
				super(component.getComponentname());
			}

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<SimpleComponent>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				bottomLayout = new HorizontalLayout();
				bottomLayout.setSpacing(true);
				bottomLayout.setWidth("100%");
				displayBugReports();
				return bottomLayout;
			}

			@Override
			public void displayBugReports() {
				bottomLayout.removeAllComponents();
				SimpleProject project = CurrentProjectVariables.getProject();
				VerticalLayout leftColumn = new VerticalLayout();
				bottomLayout.addComponent(leftColumn);
				UnresolvedBugsByPriorityWidget unresolvedBugWidget = new UnresolvedBugsByPriorityWidget(
						FormLayoutFactory.this);
				unresolvedBugWidget.setWidth("450px");
				leftColumn.addComponent(unresolvedBugWidget);

				BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
				unresolvedByPrioritySearchCriteria
						.setProjectId(new NumberSearchField(project.getId()));
				unresolvedByPrioritySearchCriteria
						.setComponentids(new SetSearchField<Integer>(component
								.getId()));
				unresolvedByPrioritySearchCriteria
						.setStatuses(new SetSearchField<String>(
								SearchField.AND, new String[] {
										BugStatusConstants.INPROGRESS,
										BugStatusConstants.OPEN,
										BugStatusConstants.REOPENNED }));
				unresolvedBugWidget
						.setSearchCriteria(unresolvedByPrioritySearchCriteria);

				VerticalLayout rightColumn = new VerticalLayout();
				bottomLayout.addComponent(rightColumn);

				UnresolvedBugsByAssigneeWidget unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget(
						FormLayoutFactory.this);
				unresolvedByAssigneeWidget.setWidth("450px");
				rightColumn.addComponent(unresolvedByAssigneeWidget);

				BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
				unresolvedByAssigneeSearchCriteria
						.setProjectId(new NumberSearchField(project.getId()));
				unresolvedByAssigneeSearchCriteria
						.setComponentids(new SetSearchField<Integer>(component
								.getId()));
				unresolvedByAssigneeSearchCriteria
						.setStatuses(new SetSearchField<String>(
								SearchField.AND, new String[] {
										BugStatusConstants.INPROGRESS,
										BugStatusConstants.OPEN,
										BugStatusConstants.REOPENNED }));
				unresolvedByAssigneeWidget
						.setSearchCriteria(unresolvedByAssigneeSearchCriteria);
			}

			@Override
			public void displayBugListWidget(String title,
					BugSearchCriteria criteria) {
				bottomLayout.removeAllComponents();
				BugListWidget bugListWidget = new BugListWidget(title,
						"Back to component dashboard", criteria, this);
				bugListWidget.setWidth("100%");
				this.bottomLayout.addComponent(bugListWidget);
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends ComponentReadViewImpl {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<SimpleComponent>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new ComponentReadViewImpl.PrintView.FormLayoutFactory());
					this.setFormFieldFactory(new ComponentFormFieldLayout());
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends ComponentFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(component.getComponentname());
			}

			@Override
			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				return new HorizontalLayout();
			}
		}
	}
}
