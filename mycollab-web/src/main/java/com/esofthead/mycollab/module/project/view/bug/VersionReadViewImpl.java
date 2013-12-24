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

package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class VersionReadViewImpl extends AbstractPreviewItemComp<Version>
		implements VersionReadView {
	private static final long serialVersionUID = 1L;

	public VersionReadViewImpl() {
		super(MyCollabResource.newResource("icons/22/project/version.png"));
		
		this.setMargin(new MarginInfo(true, false, false, false));
	}

	@Override
	public HasPreviewFormHandlers<Version> getPreviewFormHandlers() {
		return this.previewForm;
	}

	@Override
	protected void initRelatedComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPreviewItem() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String initFormTitle() {
		return beanItem.getVersionname();
	}

	@Override
	protected AdvancedPreviewBeanForm<Version> initPreviewForm() {
		return new AdvancedPreviewBeanForm<Version>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final VersionHistoryLogWindow historyLog = new VersionHistoryLogWindow(
						ModuleNameConstants.PRJ, ProjectContants.BUG_VERSION,
						beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}

		};
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new VersionFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<Version> initBeanFormFieldFactory() {
		return new AbstractBeanFieldGroupViewFieldFactory<Version>(previewForm) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field<?> onCreateField(Object propertyId) {
				if (propertyId.equals("duedate")) {
					return new DefaultFormViewFieldFactory.FormDateViewField(
							beanItem.getDuedate());
				}
				return null;
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		ProjectPreviewFormControlsGenerator<Version> versionPreviewForm = new ProjectPreviewFormControlsGenerator<Version>(
				previewForm);
		final HorizontalLayout topPanel = versionPreviewForm
				.createButtonControls(ProjectRolePermissionCollections.VERSIONS);
		return topPanel;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		VerticalLayout mainBottomLayout = new VerticalLayout();
		// mainBottomLayout.setMargin(new MarginInfo(false, false,
		// true, false));
		// mainBottomLayout.setSpacing(true);
		// mainBottomLayout.setWidth("100%");
		// mainBottomLayout.addStyleName("relatedbug-comp");
		//
		// final HorizontalLayout header = new HorizontalLayout();
		// header.setMargin(new MarginInfo(false, true, false, false));
		// header.setSpacing(true);
		// header.setWidth("100%");
		// header.addStyleName("relatedbug-comp-header");
		// final Label taskGroupSelection = new Label("Related Bugs");
		// taskGroupSelection.addStyleName("h2");
		// taskGroupSelection.addStyleName(UIConstants.THEME_NO_BORDER);
		// header.addComponent(taskGroupSelection);
		// header.setExpandRatio(taskGroupSelection, 1.0f);
		// header.setComponentAlignment(taskGroupSelection,
		// Alignment.MIDDLE_LEFT);
		//
		// this.viewGroup = new ToggleButtonGroup();
		//
		// final Button simpleDisplay = new Button(null,
		// new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(final ClickEvent event) {
		// FormLayoutFactory.this.displaySimpleView();
		// }
		// });
		// simpleDisplay.setIcon(MyCollabResource
		// .newResource("icons/16/project/list_display.png"));
		//
		// this.viewGroup.addButton(simpleDisplay);
		//
		// final Button advanceDisplay = new Button(null,
		// new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(final ClickEvent event) {
		// FormLayoutFactory.this.displayAdvancedView();
		// }
		// });
		// advanceDisplay
		// .setIcon(MyCollabResource
		// .newResource("icons/16/project/bug_advanced_display.png"));
		// this.viewGroup.addButton(advanceDisplay);
		// header.addComponent(this.viewGroup);
		// header.setComponentAlignment(this.viewGroup,
		// Alignment.MIDDLE_RIGHT);
		//
		// mainBottomLayout.addComponent(header);
		//
		// this.bottomLayout = new HorizontalLayout();
		// this.bottomLayout.setMargin(new MarginInfo(false, true, false,
		// true));
		// this.bottomLayout.setSpacing(true);
		// this.bottomLayout.setWidth("100%");
		//
		// advanceDisplay.addStyleName("selected");
		//
		// this.displayBugReports();
		return mainBottomLayout;
	}

	private void displaySimpleView() {
		// if (mainBottomLayout.getComponentCount() > 1) {
		// mainBottomLayout.removeComponent(mainBottomLayout
		// .getComponent(1));
		// }
		//
		// final BugSearchCriteria criteria = new BugSearchCriteria();
		// criteria.setProjectId(new NumberSearchField(
		// CurrentProjectVariables.getProjectId()));
		// criteria.setVersionids(new SetSearchField<Integer>(
		// VersionReadViewImpl.this.version.getId()));
		//
		// final BugSimpleDisplayWidget displayWidget = new
		// BugSimpleDisplayWidget();
		// mainBottomLayout.addComponent(displayWidget);
		// displayWidget.setSearchCriteria(criteria);
	}

	private void displayAdvancedView() {
		// if (mainBottomLayout.getComponentCount() > 1) {
		// mainBottomLayout.removeComponent(mainBottomLayout
		// .getComponent(1));
		// }
		//
		// mainBottomLayout.addComponent(this.bottomLayout);
		//
		// this.bottomLayout.removeAllComponents();
		// final SimpleProject project = CurrentProjectVariables
		// .getProject();
		// final VerticalLayout leftColumn = new VerticalLayout();
		// leftColumn.setSpacing(true);
		// this.bottomLayout.addComponent(leftColumn);
		// this.bottomLayout.setExpandRatio(leftColumn, 1.0f);
		//
		// final UnresolvedBugsByPriorityWidget unresolvedBugWidget = new
		// UnresolvedBugsByPriorityWidget(
		// FormLayoutFactory.this);
		// leftColumn.addComponent(unresolvedBugWidget);
		// leftColumn.setComponentAlignment(unresolvedBugWidget,
		// Alignment.MIDDLE_CENTER);
		//
		// final BugSearchCriteria unresolvedByPrioritySearchCriteria = new
		// BugSearchCriteria();
		// unresolvedByPrioritySearchCriteria
		// .setProjectId(new NumberSearchField(project.getId()));
		// unresolvedByPrioritySearchCriteria
		// .setVersionids(new SetSearchField<Integer>(
		// VersionReadViewImpl.this.version.getId()));
		// unresolvedByPrioritySearchCriteria
		// .setStatuses(new SetSearchField<String>(
		// SearchField.AND, new String[] {
		// BugStatusConstants.INPROGRESS,
		// BugStatusConstants.OPEN,
		// BugStatusConstants.REOPENNED }));
		// unresolvedBugWidget
		// .setSearchCriteria(unresolvedByPrioritySearchCriteria);
		//
		// final UnresolvedBugsByAssigneeWidget unresolvedByAssigneeWidget = new
		// UnresolvedBugsByAssigneeWidget(
		// FormLayoutFactory.this);
		// leftColumn.addComponent(unresolvedByAssigneeWidget);
		// leftColumn.setComponentAlignment(unresolvedByAssigneeWidget,
		// Alignment.MIDDLE_CENTER);
		//
		// final BugSearchCriteria unresolvedByAssigneeSearchCriteria = new
		// BugSearchCriteria();
		// unresolvedByAssigneeSearchCriteria
		// .setProjectId(new NumberSearchField(project.getId()));
		// unresolvedByAssigneeSearchCriteria
		// .setVersionids(new SetSearchField<Integer>(
		// VersionReadViewImpl.this.version.getId()));
		// unresolvedByAssigneeSearchCriteria
		// .setStatuses(new SetSearchField<String>(
		// SearchField.AND, new String[] {
		// BugStatusConstants.INPROGRESS,
		// BugStatusConstants.OPEN,
		// BugStatusConstants.REOPENNED }));
		// unresolvedByAssigneeWidget
		// .setSearchCriteria(unresolvedByAssigneeSearchCriteria);
		//
		// final VerticalLayout rightColumn = new VerticalLayout();
		// rightColumn
		// .setMargin(new MarginInfo(false, false, false, true));
		// this.bottomLayout.addComponent(rightColumn);
		//
		// final BugSearchCriteria chartSearchCriteria = new
		// BugSearchCriteria();
		// chartSearchCriteria.setProjectId(new NumberSearchField(
		// CurrentProjectVariables.getProjectId()));
		// chartSearchCriteria.setVersionids(new SetSearchField<Integer>(
		// VersionReadViewImpl.this.version.getId()));
		//
		// BugChartComponent bugChartComponent = null;
		// bugChartComponent = new BugChartComponent(chartSearchCriteria,
		// 400, 200);
		// rightColumn.addComponent(bugChartComponent);
		// rightColumn.setWidth("410px");
	}

	public void displayBugReports() {
		// TODO: check selected tab index
		this.displayAdvancedView();
	}

	public void displayBugListWidget(final String title,
			final BugSearchCriteria criteria) {
		// this.bottomLayout.removeAllComponents();
		// final BugListWidget bugListWidget = new BugListWidget(title
		// + " Bug List", "Back to version dashboard", criteria,
		// this);
		// bugListWidget.setWidth("100%");
		// this.bottomLayout.addComponent(bugListWidget);

	}

	@Override
	public Version getItem() {
		return beanItem;
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {
		// TODO Auto-generated method stub

	}

}
