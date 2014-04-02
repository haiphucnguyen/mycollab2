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
package com.esofthead.mycollab.module.project.view.task;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.parameters.TaskFilterParameter;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class UnresolvedTaskByAssigneeWidget extends Depot {
	private static final long serialVersionUID = 1L;

	private TaskSearchCriteria searchCriteria;

	public UnresolvedTaskByAssigneeWidget() {
		super(LocalizationHelper
				.getMessage(BugI18nEnum.UNRESOLVED_BY_ASSIGNEE_WIDGET_TITLE),
				new VerticalLayout());
		this.setContentBorder(true);
		((VerticalLayout) this.bodyContent).setSpacing(true);
		((VerticalLayout) this.bodyContent).setMargin(true);
	}

	public void setSearchCriteria(final TaskSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		this.bodyContent.removeAllComponents();
		ProjectTaskService projectTaskService = ApplicationContextUtil
				.getSpringBean(ProjectTaskService.class);
		int totalCountItems = projectTaskService.getTotalCount(searchCriteria);
		final List<GroupItem> groupItems = projectTaskService
				.getAssignedDefectsSummary(searchCriteria);

		if (!groupItems.isEmpty()) {
			for (final GroupItem item : groupItems) {
				final HorizontalLayout assigneeLayout = new HorizontalLayout();
				assigneeLayout.setSpacing(true);
				assigneeLayout.setWidth("100%");

				final String assignUser = item.getGroupid();
				final String assignUserFullName = (item.getGroupid() == null) ? "Undefined"
						: item.getGroupname();
				final TaskAssigneeLink userLbl = new TaskAssigneeLink(
						assignUser, item.getExtraValue(), assignUserFullName);
				assigneeLayout.addComponent(userLbl);
				final ProgressBarIndicator indicator = new ProgressBarIndicator(
						totalCountItems, totalCountItems - item.getValue(),
						false);
				indicator.setWidth("100%");
				assigneeLayout.addComponent(indicator);
				assigneeLayout.setExpandRatio(indicator, 1.0f);
				this.bodyContent.addComponent(assigneeLayout);
			}

		}
	}

	class TaskAssigneeLink extends Button {
		private static final long serialVersionUID = 1L;

		public TaskAssigneeLink(final String assignee,
				final String assigneeAvatarId, final String assigneeFullName) {
			super(assigneeFullName, new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					searchCriteria.setAssignUser(new StringSearchField(
							SearchField.AND, assignee));
					TaskFilterParameter filterParam = new TaskFilterParameter(
							searchCriteria, "Filter Tasks by Assignee: "
									+ assigneeFullName);
					EventBus.getInstance().fireEvent(
							new TaskEvent.Filter(this, filterParam));
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
