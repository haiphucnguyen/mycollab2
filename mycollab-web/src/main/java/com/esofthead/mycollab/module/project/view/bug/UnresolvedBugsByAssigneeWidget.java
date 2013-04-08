/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class UnresolvedBugsByAssigneeWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private IBugReportDisplayContainer componentLayout;
	private BugSearchCriteria bugSearchCriteria;

	public UnresolvedBugsByAssigneeWidget(
			IBugReportDisplayContainer componentLayout) {
		super("Unresolved by assignee", new VerticalLayout());

		this.componentLayout = componentLayout;
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.bugSearchCriteria = searchCriteria;
		this.bodyContent.removeAllComponents();
		BugService bugService = AppContext.getSpringBean(BugService.class);
		int totalCount = bugService.getTotalCount(searchCriteria);
		List<GroupItem> groupItems = bugService
				.getAssignedDefectsSummary(searchCriteria);
		if (!groupItems.isEmpty()) {
			for (GroupItem item : groupItems) {
				HorizontalLayout assigneeLayout = new HorizontalLayout();
				assigneeLayout.setSpacing(true);

				String assignUser = item.getGroupid();
				String assignUserFullName = (item.getGroupid() == null) ? "Undefnined"
						: item.getGroupname();
				BugAssigneeButton userLbl = new BugAssigneeButton(assignUser,
						assignUserFullName);
				assigneeLayout.addComponent(userLbl);
				ProgressIndicator indicator = new ProgressIndicator(new Float(
						(float) item.getValue() / totalCount));
				indicator.setPollingInterval(1000000000);
				assigneeLayout.addComponent(indicator);

				Label progressLbl = new Label("(" + item.getValue() + "/"
						+ totalCount + ")");
				assigneeLayout.addComponent(progressLbl);
				bodyContent.addComponent(assigneeLayout);
			}

		}
	}

	class BugAssigneeButton extends Button {
		private static final long serialVersionUID = 1L;

		public BugAssigneeButton(final String assignee,
				final String assigneeFullName) {
			super(assigneeFullName, new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					bugSearchCriteria.setAssignuser(new StringSearchField(
							SearchField.AND, assignee));
					componentLayout.displayBugListWidget(assigneeFullName
							+ " Bugs List", bugSearchCriteria);
				}
			});

			this.setStyleName("link");
			this.setWidth("100px");
			this.setIcon(UserAvatarControlFactory.getResource(assignee, 16));
		}
	}
}
