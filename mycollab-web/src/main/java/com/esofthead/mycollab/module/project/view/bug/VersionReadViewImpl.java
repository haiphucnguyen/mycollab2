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
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
public class VersionReadViewImpl extends AbstractView implements
		VersionReadView {
	private static final long serialVersionUID = 1L;
	protected Version version;
	protected AdvancedPreviewBeanForm<Version> previewForm;

	public VersionReadViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(Version item) {
		version = item;
		previewForm.setItemDataSource(new BeanItem<Version>(item));
	}

	@Override
	public Version getItem() {
		return version;
	}

	@Override
	public HasPreviewFormHandlers<Version> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Version> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("duedate")) {
						return new DefaultFormViewFieldFactory.FormDateViewField(
								version.getDuedate());
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			VersionReadViewImpl printView = new VersionReadViewImpl.PrintView();
			printView.previewItem(version);
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
			VersionHistoryLogWindow historyLog = new VersionHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.BUG_VERSION,
					version.getId());
			getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends VersionFormLayoutFactory implements
				IBugReportDisplayContainer {

			private static final long serialVersionUID = 1L;
			private HorizontalLayout bottomLayout;
			private VerticalLayout mainBottomLayout;
			private ToggleButtonGroup viewGroup;

			public FormLayoutFactory() {
				super(version.getVersionname());
			}

			@Override
			protected Layout createTopPanel() {
				return (new ProjectPreviewFormControlsGenerator<Version>(
						PreviewForm.this)).createButtonControls(
						ProjectRolePermissionCollections.VERSIONS);
			}

			@Override
			protected Layout createBottomPanel() {
				mainBottomLayout = new VerticalLayout();
				mainBottomLayout.setSpacing(true);
				mainBottomLayout.setWidth("100%");
				
				HorizontalLayout header = new HorizontalLayout();
				header.setMargin(true, false, false, false);
				header.setSpacing(true);
				header.setWidth("100%");
				Label taskGroupSelection = new Label("Related Bugs");
				taskGroupSelection.addStyleName("h2");
				taskGroupSelection.addStyleName(UIConstants.THEME_NO_BORDER);
				header.addComponent(taskGroupSelection);
				header.setExpandRatio(taskGroupSelection, 1.0f);
				header.setComponentAlignment(taskGroupSelection, Alignment.MIDDLE_LEFT);
				
				viewGroup = new ToggleButtonGroup();

				Button simpleDisplay = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						displaySimpleView();
					}
				});
				simpleDisplay.setIcon(new ThemeResource(
						"icons/16/project/list_display.png"));

				viewGroup.addButton(simpleDisplay);

				Button advanceDisplay = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						displayAdvancedView();
					}
				});
				advanceDisplay.setIcon(new ThemeResource(
						"icons/16/project/bug_advanced_display.png"));
				viewGroup.addButton(advanceDisplay);
				header.addComponent(viewGroup);
				header.setComponentAlignment(viewGroup, Alignment.MIDDLE_RIGHT);
				
				mainBottomLayout.addComponent(header);
				
				bottomLayout = new HorizontalLayout();
				bottomLayout.setSpacing(true);
				bottomLayout.setWidth("100%");
				
				viewGroup.removeButtonsCss("selected");
				advanceDisplay.addStyleName("selected");
				
				displayBugReports();
				return mainBottomLayout;
			}

			private void displaySimpleView() {
				if (mainBottomLayout.getComponentCount() > 1) {
					mainBottomLayout.removeComponent(mainBottomLayout.getComponent(1));
				}

				BugSearchCriteria criteria = new BugSearchCriteria();
				criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
						.getProjectId()));
				criteria.setVersionids(new SetSearchField<Integer>(version.getId()));

				BugSimpleDisplayWidget displayWidget = new BugSimpleDisplayWidget();
				mainBottomLayout.addComponent(new LazyLoadWrapper(displayWidget));
				displayWidget.setSearchCriteria(criteria);
			}
			
			private void displayAdvancedView() {
				if (mainBottomLayout.getComponentCount() > 1) {
					mainBottomLayout.removeComponent(mainBottomLayout.getComponent(1));
				}
				
				mainBottomLayout.addComponent(bottomLayout);
				
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
						.setVersionids(new SetSearchField<Integer>(version
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
						.setVersionids(new SetSearchField<Integer>(version
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
			public void displayBugReports() {
				viewGroup.setDefaultSelectionByIndex(1);
				displayAdvancedView();
			}

			@Override
			public void displayBugListWidget(String title,
					BugSearchCriteria criteria) {
				bottomLayout.removeAllComponents();
				BugListWidget bugListWidget = new BugListWidget(title
						+ " Bug List", "Back to version dashboard", criteria,
						this);
				bugListWidget.setWidth("100%");
				this.bottomLayout.addComponent(bugListWidget);

			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends VersionReadViewImpl {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Version>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new VersionReadViewImpl.PrintView.FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(Item item,
								Object propertyId, Component uiContext) {
							if (propertyId.equals("duedate")) {
								return new FormDateViewField(version
										.getDuedate());
							}
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends VersionFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(version.getVersionname());
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
