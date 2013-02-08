/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class UnresolvedBugsByPriorityWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private IBugReportDisplayContainer componentLayout;

	public UnresolvedBugsByPriorityWidget(
			IBugReportDisplayContainer componentLayout) {
		super("Unresolved by Priority", new VerticalLayout());

		this.componentLayout = componentLayout;
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {

		BugService bugService = AppContext.getSpringBean(BugService.class);
		int totalCount = bugService.getTotalCount(searchCriteria);
		List<GroupItem> groupItems = bugService
				.getPrioritySummary(searchCriteria);
		BugPriorityClickListener listener = new BugPriorityClickListener();

		if (!groupItems.isEmpty()) {
			for (String status : ProjectDataTypeFactory.getBugPriorityList()) {
				boolean isFound = false;
				for (GroupItem item : groupItems) {
					if (status.equals(item.getGroupid())) {
						isFound = true;
						HorizontalLayout priorityLayout = new HorizontalLayout();
						priorityLayout.setSpacing(true);
						Button userLbl = new Button(status, listener);
						userLbl.setWidth("100px");
						userLbl.setStyleName("link");
						userLbl.setWidth("100px");

						priorityLayout.addComponent(userLbl);
						ProgressIndicator indicator = new ProgressIndicator(
								new Float((float) item.getValue() / totalCount));
						indicator.setPollingInterval(1000000000);
						priorityLayout.addComponent(indicator);

						Label progressLbl = new Label("(" + item.getValue()
								+ "/" + totalCount + ")");
						priorityLayout.addComponent(progressLbl);
						bodyContent.addComponent(priorityLayout);
						continue;
					}
				}

				if (!isFound) {
					HorizontalLayout priorityLayout = new HorizontalLayout();
					priorityLayout.setSpacing(true);
					Button userLbl = new Button(status, listener);
					userLbl.setWidth("100px");
					userLbl.setStyleName("link");
					priorityLayout.addComponent(userLbl);
					ProgressIndicator indicator = new ProgressIndicator(0f);
					indicator.setPollingInterval(1000000000);
					priorityLayout.addComponent(indicator);

					Label progressLbl = new Label("(" + 0 + "/" + totalCount
							+ ")");
					priorityLayout.addComponent(progressLbl);
					bodyContent.addComponent(priorityLayout);
				}
			}

		}
	}

	class BugPriorityClickListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();
			SimpleProject project = (SimpleProject) AppContext
					.getVariable(ProjectContants.PROJECT_NAME);
			BugSearchCriteria criteria = new BugSearchCriteria();
			criteria.setProjectId(new NumberSearchField(project.getId()));
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { caption }));
			
			componentLayout.displayBugListWidget();
		}

	}
}
