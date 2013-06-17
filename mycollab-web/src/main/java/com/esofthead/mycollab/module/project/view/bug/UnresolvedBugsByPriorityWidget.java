/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
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

	private final IBugReportDisplayContainer componentLayout;
	private BugSearchCriteria bugSearchCriteria;

	public UnresolvedBugsByPriorityWidget(
			final IBugReportDisplayContainer componentLayout) {
		super("Unresolved by Priority", new VerticalLayout());

		this.componentLayout = componentLayout;
		this.setContentBorder(true);
		((VerticalLayout) this.bodyContent).setSpacing(true);
	}

	public void setSearchCriteria(final BugSearchCriteria searchCriteria) {
		this.bugSearchCriteria = searchCriteria;
		this.bodyContent.removeAllComponents();
		final BugService bugService = AppContext
				.getSpringBean(BugService.class);
		final int totalCount = bugService.getTotalCount(searchCriteria);
		final List<GroupItem> groupItems = bugService
				.getPrioritySummary(searchCriteria);
		final BugPriorityClickListener listener = new BugPriorityClickListener();

		if (!groupItems.isEmpty()) {
			for (final String status : ProjectDataTypeFactory
					.getBugPriorityList()) {
				boolean isFound = false;
				for (final GroupItem item : groupItems) {
					if (status.equals(item.getGroupid())) {
						isFound = true;
						final HorizontalLayout priorityLayout = new HorizontalLayout();
						priorityLayout.setSpacing(true);
						final Button userLbl = new Button(status, listener);
						userLbl.setWidth("110px");
						userLbl.setStyleName("link");

						priorityLayout.addComponent(userLbl);
						final ProgressIndicator indicator = new ProgressIndicator(
								new Float((float) item.getValue() / totalCount));
						indicator.setPollingInterval(1000000000);
						priorityLayout.addComponent(indicator);

						final Label progressLbl = new Label("("
								+ item.getValue() + "/" + totalCount + ")");
						priorityLayout.addComponent(progressLbl);
						this.bodyContent.addComponent(priorityLayout);
						continue;
					}
				}

				if (!isFound) {
					final HorizontalLayout priorityLayout = new HorizontalLayout();
					priorityLayout.setSpacing(true);
					final Button userLbl = new Button(status, listener);
					userLbl.setWidth("110px");
					userLbl.setStyleName("link");
					priorityLayout.addComponent(userLbl);
					final ProgressIndicator indicator = new ProgressIndicator(
							0f);
					indicator.setPollingInterval(1000000000);
					priorityLayout.addComponent(indicator);

					final Label progressLbl = new Label("(" + 0 + "/"
							+ totalCount + ")");
					priorityLayout.addComponent(progressLbl);
					this.bodyContent.addComponent(priorityLayout);
				}
			}

		}
	}

	class BugPriorityClickListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(final ClickEvent event) {
			final String caption = event.getButton().getCaption();
			UnresolvedBugsByPriorityWidget.this.bugSearchCriteria
					.setPriorities(new SetSearchField<String>(SearchField.AND,
							new String[] { caption }));
			UnresolvedBugsByPriorityWidget.this.componentLayout
					.displayBugListWidget(
							caption,
							UnresolvedBugsByPriorityWidget.this.bugSearchCriteria);
		}

	}
}
