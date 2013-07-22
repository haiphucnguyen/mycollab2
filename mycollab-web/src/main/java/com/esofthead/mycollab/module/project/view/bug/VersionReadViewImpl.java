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
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.Sizeable;
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
            final VersionHistoryLogWindow historyLog = new VersionHistoryLogWindow(
                    ModuleNameConstants.PRJ, ProjectContants.BUG_VERSION,
                    VersionReadViewImpl.this.version.getId());
            this.getWindow().addWindow(historyLog);
        }

        class FormLayoutFactory extends VersionFormLayoutFactory implements
                IBugReportDisplayContainer {

            private static final long serialVersionUID = 1L;
            private HorizontalLayout bottomLayout;
            private VerticalLayout mainBottomLayout;
            private ToggleButtonGroup viewGroup;

            public FormLayoutFactory() {
                super(VersionReadViewImpl.this.version.getVersionname());
            }

            @Override
            protected Layout createTopPanel() {
                return (new ProjectPreviewFormControlsGenerator<Version>(
                        PreviewForm.this))
                        .createButtonControls(ProjectRolePermissionCollections.VERSIONS);
            }

            @Override
            protected Layout createBottomPanel() {
                this.mainBottomLayout = new VerticalLayout();
                this.mainBottomLayout.setMargin(false, false, true, false);
                this.mainBottomLayout.setSpacing(true);
                this.mainBottomLayout.setWidth("100%");
                this.mainBottomLayout.addStyleName("relatedbug-comp");

                final HorizontalLayout header = new HorizontalLayout();
                header.setMargin(false, true, false, false);
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
                this.bottomLayout.setMargin(false, true, false, true);
                this.bottomLayout.setSpacing(true);
                this.bottomLayout.setWidth("100%");

                this.viewGroup.removeButtonsCss("selected");
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
                this.mainBottomLayout.addComponent(new LazyLoadWrapper(
                        displayWidget));
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
                rightColumn.setMargin(false, false, false, true);
                this.bottomLayout.addComponent(rightColumn);

                final BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
                chartSearchCriteria.setProjectId(new NumberSearchField(
                        CurrentProjectVariables.getProjectId()));
                chartSearchCriteria.setVersionids(new SetSearchField<Integer>(
                        VersionReadViewImpl.this.version.getId()));

                BugChartComponent bugChartComponent = null;
                bugChartComponent = new BugChartComponent(chartSearchCriteria,
                        "400px", "200px");
                rightColumn.addComponent(bugChartComponent);
                rightColumn.setWidth(Sizeable.SIZE_UNDEFINED, 0);
            }

            @Override
            public void displayBugReports() {
                this.viewGroup.setDefaultSelectionByIndex(1);
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
