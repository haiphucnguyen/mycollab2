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
import com.esofthead.mycollab.vaadin.ui.ProgressBar;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class UnresolvedBugsByAssigneeWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private final IBugReportDisplayContainer componentLayout;
	private BugSearchCriteria bugSearchCriteria;

	public UnresolvedBugsByAssigneeWidget(
			final IBugReportDisplayContainer componentLayout) {
		super("Unresolved by assignee", new VerticalLayout());

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
				.getAssignedDefectsSummary(searchCriteria);
		if (!groupItems.isEmpty()) {
			for (final GroupItem item : groupItems) {
				final HorizontalLayout assigneeLayout = new HorizontalLayout();
				assigneeLayout.setSpacing(true);
				assigneeLayout.setWidth("100%");

				final String assignUser = item.getGroupid();
				final String assignUserFullName = (item.getGroupid() == null) ? "Undefnined"
						: item.getGroupname();
				final BugAssigneeButton userLbl = new BugAssigneeButton(
						assignUser, item.getExtraValue(), assignUserFullName);
				assigneeLayout.addComponent(userLbl);
				final ProgressBar indicator = new ProgressBar(totalCount,
						totalCount - item.getValue(), false);
				indicator.setWidth("100%");
				assigneeLayout.addComponent(indicator);
				assigneeLayout.setExpandRatio(indicator, 1.0f);

				this.bodyContent.addComponent(assigneeLayout);
			}

		}
	}

	class BugAssigneeButton extends Button {
		private static final long serialVersionUID = 1L;

		public BugAssigneeButton(final String assignee,
				final String assigneeAvatarId, final String assigneeFullName) {
			super(assigneeFullName, new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					UnresolvedBugsByAssigneeWidget.this.bugSearchCriteria
							.setAssignuser(new StringSearchField(
									SearchField.AND, assignee));
					UnresolvedBugsByAssigneeWidget.this.componentLayout
							.displayBugListWidget(
									assigneeFullName + " Bugs List",
									UnresolvedBugsByAssigneeWidget.this.bugSearchCriteria);
				}
			});

			this.setStyleName("link");
			this.setWidth("110px");
			this.addStyleName(UIConstants.WORD_WRAP);
			this.setIcon(UserAvatarControlFactory.createAvatarResource(
					assigneeAvatarId, 16));
		}
	}
}
