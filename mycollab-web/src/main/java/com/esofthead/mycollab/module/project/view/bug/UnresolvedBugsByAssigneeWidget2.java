package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18Enum;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

public class UnresolvedBugsByAssigneeWidget2 extends Depot {
	private static final long serialVersionUID = 1L;

	private BugSearchCriteria bugSearchCriteria;

	public UnresolvedBugsByAssigneeWidget2() {
		super(AppContext
				.getMessage(BugI18Enum.UNRESOLVED_BY_ASSIGNEE_WIDGET_TITLE),
				new VerticalLayout());
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
				String assignUserFullName = (item.getGroupid() == null) ? "Undefined"
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
					BugSearchParameter param = new BugSearchParameter(
							"Unresolved Bug List of " + assigneeFullName,
							bugSearchCriteria);
					EventBus.getInstance().fireEvent(
							new BugEvent.GotoList(this,
									new BugScreenData.Search(param)));
				}
			});

			this.setStyleName("link");
			this.setWidth("100px");
		}
	}

}
