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

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
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
		((VerticalLayout) this.bodyContent).setMargin(true);
	}

	public void setSearchCriteria(final BugSearchCriteria searchCriteria) {
		this.bugSearchCriteria = searchCriteria;
		this.bodyContent.removeAllComponents();
		final BugService bugService = ApplicationContextUtil
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
						priorityLayout.setWidth("100%");
						final Button userLbl = new Button(status, listener);
						userLbl.setWidth("110px");
						userLbl.setStyleName("link");

						priorityLayout.addComponent(userLbl);
						final ProgressBarIndicator indicator = new ProgressBarIndicator(
								totalCount, totalCount - item.getValue(), false);
						indicator.setWidth("100%");
						priorityLayout.addComponent(indicator);
						priorityLayout.setExpandRatio(indicator, 1.0f);

						this.bodyContent.addComponent(priorityLayout);
						continue;
					}
				}

				if (!isFound) {
					final HorizontalLayout priorityLayout = new HorizontalLayout();
					priorityLayout.setSpacing(true);
					priorityLayout.setWidth("100%");
					final Button userLbl = new Button(status, listener);
					userLbl.setWidth("110px");
					userLbl.setStyleName("link");
					priorityLayout.addComponent(userLbl);
					final ProgressBarIndicator indicator = new ProgressBarIndicator(
							totalCount, totalCount, false);
					indicator.setWidth("100%");
					priorityLayout.addComponent(indicator);
					priorityLayout.setExpandRatio(indicator, 1.0f);

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
