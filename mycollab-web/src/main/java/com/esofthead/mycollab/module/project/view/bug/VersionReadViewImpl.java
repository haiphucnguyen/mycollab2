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
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class VersionReadViewImpl extends AbstractPageView implements
		VersionReadView {
	private static final long serialVersionUID = 1L;
	protected Version version;
	protected AdvancedPreviewBeanForm<Version> previewForm;

	public VersionReadViewImpl() {
		super();
		this.setMargin(true);
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
	}

	@Override
	public void previewItem(final Version item) {
		this.version = item;
		this.previewForm.setItemDataSource(new BeanItem<Version>(item));
	}

	@Override
	public Version getItem() {
		return this.version;
	}

	@Override
	public HasPreviewFormHandlers<Version> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Version> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					if (propertyId.equals("duedate")) {
						return new DefaultFormViewFieldFactory.FormDateViewField(
								VersionReadViewImpl.this.version.getDuedate());
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			final Window window = new Window("Window to Print");

			final VersionReadViewImpl printView = new VersionReadViewImpl.PrintView();
			printView.previewItem(VersionReadViewImpl.this.version);
			window.setContent(printView);

			UI.getCurrent().addWindow(window);

			// Print automatically when the window opens
			JavaScript.getCurrent().execute(
					"setTimeout(function() {"
							+ "  print(); self.close();}, 0);");
		}

		@Override
		protected void showHistory() {
			final VersionHistoryLogWindow historyLog = new VersionHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.BUG_VERSION,
					VersionReadViewImpl.this.version.getId());
			UI.getCurrent().addWindow(historyLog);
		}

		class FormLayoutFactory extends VersionFormLayoutFactory implements
				IBugReportDisplayContainer {

			private static final long serialVersionUID = 1L;
			private HorizontalLayout bottomLayout;
			private VerticalLayout mainBottomLayout;
			private ToggleButtonGroup viewGroup;
			private Button quickActionStatusBtn;

			public FormLayoutFactory() {
				super(VersionReadViewImpl.this.version.getVersionname());
			}

			@Override
			protected Layout createTopPanel() {
				ProjectPreviewFormControlsGenerator<Version> versionPreviewForm = new ProjectPreviewFormControlsGenerator<Version>(
						PreviewForm.this);
				final HorizontalLayout topPanel = versionPreviewForm
						.createButtonControls(ProjectRolePermissionCollections.VERSIONS);

				quickActionStatusBtn = new Button("",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								if (quickActionStatusBtn.getCaption().equals(
										"ReOpen")) {
									version.setStatus("Open");
									VersionService service = ApplicationContextUtil
											.getSpringBean(VersionService.class);
									service.updateWithSession(version,
											AppContext.getUsername());
									FormLayoutFactory.this
											.removeTitleStyleName(UIConstants.LINK_COMPLETED);
									quickActionStatusBtn.setCaption("Close");
									quickActionStatusBtn.setIcon(MyCollabResource
											.newResource("icons/16/project/closeTask.png"));
								} else {
									version.setStatus("Close");
									VersionService service = ApplicationContextUtil
											.getSpringBean(VersionService.class);
									service.updateWithSession(version,
											AppContext.getUsername());
									FormLayoutFactory.this
											.addTitleStyleName("headerName");
									FormLayoutFactory.this
											.addTitleStyleName(UIConstants.LINK_COMPLETED);
									quickActionStatusBtn.setCaption("ReOpen");
									quickActionStatusBtn.setIcon(MyCollabResource
											.newResource("icons/16/project/reopenTask.png"));
								}
							}
						});
				quickActionStatusBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				if (version.getStatus() == null
						|| version.getStatus().equals("Open")) {
					quickActionStatusBtn.setCaption("Close");
					quickActionStatusBtn.setIcon(MyCollabResource
							.newResource("icons/16/project/closeTask.png"));
					versionPreviewForm
							.addQuickActionButton(quickActionStatusBtn);
				} else {
					FormLayoutFactory.this.addTitleStyleName("headerName");
					FormLayoutFactory.this
							.addTitleStyleName(UIConstants.LINK_COMPLETED);
					quickActionStatusBtn.setCaption("ReOpen");
					quickActionStatusBtn.setIcon(MyCollabResource
							.newResource("icons/16/project/reopenTask.png"));
					versionPreviewForm
							.addQuickActionButton(quickActionStatusBtn);
				}
				return topPanel;
			}

			@Override
			protected Layout createBottomPanel() {
				this.mainBottomLayout = new VerticalLayout();
				this.mainBottomLayout.setMargin(new MarginInfo(false, false,
						true, false));
				this.mainBottomLayout.setSpacing(true);
				this.mainBottomLayout.setWidth("100%");
				this.mainBottomLayout.addStyleName("relatedbug-comp");

				final HorizontalLayout header = new HorizontalLayout();
				header.setMargin(new MarginInfo(false, true, false, false));
				header.setSpacing(true);
				header.setWidth("100%");
				header.addStyleName("relatedbug-comp-header");
				final Label taskGroupSelection = new Label("Related Bugs");
				taskGroupSelection.addStyleName("h2");
				taskGroupSelection.addStyleName(UIConstants.THEME_NO_BORDER);
				header.addComponent(taskGroupSelection);
				header.setExpandRatio(taskGroupSelection, 1.0f);
				header.setComponentAlignment(taskGroupSelection,
						Alignment.MIDDLE_LEFT);

				this.viewGroup = new ToggleButtonGroup();

				final Button simpleDisplay = new Button(null,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								FormLayoutFactory.this.displaySimpleView();
							}
						});
				simpleDisplay.setIcon(MyCollabResource
						.newResource("icons/16/project/list_display.png"));

				this.viewGroup.addButton(simpleDisplay);

				final Button advanceDisplay = new Button(null,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								FormLayoutFactory.this.displayAdvancedView();
							}
						});
				advanceDisplay
						.setIcon(MyCollabResource
								.newResource("icons/16/project/bug_advanced_display.png"));
				this.viewGroup.addButton(advanceDisplay);
				header.addComponent(this.viewGroup);
				header.setComponentAlignment(this.viewGroup,
						Alignment.MIDDLE_RIGHT);

				this.mainBottomLayout.addComponent(header);

				this.bottomLayout = new HorizontalLayout();
				this.bottomLayout.setMargin(new MarginInfo(false, true, false,
						true));
				this.bottomLayout.setSpacing(true);
				this.bottomLayout.setWidth("100%");

				advanceDisplay.addStyleName("selected");

				this.displayBugReports();
				return this.mainBottomLayout;
			}

			private void displaySimpleView() {
				if (this.mainBottomLayout.getComponentCount() > 1) {
					this.mainBottomLayout.removeComponent(this.mainBottomLayout
							.getComponent(1));
				}

				final BugSearchCriteria criteria = new BugSearchCriteria();
				criteria.setProjectId(new NumberSearchField(
						CurrentProjectVariables.getProjectId()));
				criteria.setVersionids(new SetSearchField<Integer>(
						VersionReadViewImpl.this.version.getId()));

				final BugSimpleDisplayWidget displayWidget = new BugSimpleDisplayWidget();
				this.mainBottomLayout.addComponent(displayWidget);
				displayWidget.setSearchCriteria(criteria);
			}

			private void displayAdvancedView() {
				if (this.mainBottomLayout.getComponentCount() > 1) {
					this.mainBottomLayout.removeComponent(this.mainBottomLayout
							.getComponent(1));
				}

				this.mainBottomLayout.addComponent(this.bottomLayout);

				this.bottomLayout.removeAllComponents();
				final SimpleProject project = CurrentProjectVariables
						.getProject();
				final VerticalLayout leftColumn = new VerticalLayout();
				leftColumn.setSpacing(true);
				this.bottomLayout.addComponent(leftColumn);
				this.bottomLayout.setExpandRatio(leftColumn, 1.0f);

				final UnresolvedBugsByPriorityWidget unresolvedBugWidget = new UnresolvedBugsByPriorityWidget(
						FormLayoutFactory.this);
				leftColumn.addComponent(unresolvedBugWidget);
				leftColumn.setComponentAlignment(unresolvedBugWidget,
						Alignment.MIDDLE_CENTER);

				final BugSearchCriteria unresolvedByPrioritySearchCriteria = new BugSearchCriteria();
				unresolvedByPrioritySearchCriteria
						.setProjectId(new NumberSearchField(project.getId()));
				unresolvedByPrioritySearchCriteria
						.setVersionids(new SetSearchField<Integer>(
								VersionReadViewImpl.this.version.getId()));
				unresolvedByPrioritySearchCriteria
						.setStatuses(new SetSearchField<String>(
								SearchField.AND, new String[] {
										BugStatusConstants.INPROGRESS,
										BugStatusConstants.OPEN,
										BugStatusConstants.REOPENNED }));
				unresolvedBugWidget
						.setSearchCriteria(unresolvedByPrioritySearchCriteria);

				final UnresolvedBugsByAssigneeWidget unresolvedByAssigneeWidget = new UnresolvedBugsByAssigneeWidget(
						FormLayoutFactory.this);
				leftColumn.addComponent(unresolvedByAssigneeWidget);
				leftColumn.setComponentAlignment(unresolvedByAssigneeWidget,
						Alignment.MIDDLE_CENTER);

				final BugSearchCriteria unresolvedByAssigneeSearchCriteria = new BugSearchCriteria();
				unresolvedByAssigneeSearchCriteria
						.setProjectId(new NumberSearchField(project.getId()));
				unresolvedByAssigneeSearchCriteria
						.setVersionids(new SetSearchField<Integer>(
								VersionReadViewImpl.this.version.getId()));
				unresolvedByAssigneeSearchCriteria
						.setStatuses(new SetSearchField<String>(
								SearchField.AND, new String[] {
										BugStatusConstants.INPROGRESS,
										BugStatusConstants.OPEN,
										BugStatusConstants.REOPENNED }));
				unresolvedByAssigneeWidget
						.setSearchCriteria(unresolvedByAssigneeSearchCriteria);

				final VerticalLayout rightColumn = new VerticalLayout();
				rightColumn
						.setMargin(new MarginInfo(false, false, false, true));
				this.bottomLayout.addComponent(rightColumn);

				final BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
				chartSearchCriteria.setProjectId(new NumberSearchField(
						CurrentProjectVariables.getProjectId()));
				chartSearchCriteria.setVersionids(new SetSearchField<Integer>(
						VersionReadViewImpl.this.version.getId()));

				BugChartComponent bugChartComponent = null;
				bugChartComponent = new BugChartComponent(chartSearchCriteria,
						400, 200);
				rightColumn.addComponent(bugChartComponent);
				rightColumn.setWidth("410px");
			}

			@Override
			public void displayBugReports() {
				// TODO: check selected tab index
				this.displayAdvancedView();
			}

			@Override
			public void displayBugListWidget(final String title,
					final BugSearchCriteria criteria) {
				this.bottomLayout.removeAllComponents();
				final BugListWidget bugListWidget = new BugListWidget(title
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
			this.previewForm = new AdvancedPreviewBeanForm<Version>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new VersionReadViewImpl.PrintView.FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(final Item item,
								final Object propertyId,
								final Component uiContext) {
							if (propertyId.equals("duedate")) {
								return new FormDateViewField(
										PrintView.this.version.getDuedate());
							}
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends VersionFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.version.getVersionname());
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
