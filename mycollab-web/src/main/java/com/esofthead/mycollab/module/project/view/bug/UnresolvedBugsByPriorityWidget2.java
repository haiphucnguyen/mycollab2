package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

public class UnresolvedBugsByPriorityWidget2 extends Depot {
	private static final long serialVersionUID = 1L;

	private BugSearchCriteria bugSearchCriteria;

	public UnresolvedBugsByPriorityWidget2() {
		super(LocalizationHelper
				.getMessage(BugI18nEnum.UNRESOLVED_BY_PRIORITY_WIDGET_TITLE),
				new VerticalLayout());
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.bugSearchCriteria = searchCriteria;
		this.bodyContent.removeAllComponents();
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

	private class BugPriorityClickListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();
			bugSearchCriteria.setPriorities(new SetSearchField<String>(
					new String[] { caption }));
			BugSearchParameter param = new BugSearchParameter("Unresolved "
					+ caption + " Bug List", bugSearchCriteria);
			EventBus.getInstance()
					.fireEvent(
							new BugEvent.GotoList(this,
									new BugScreenData.Search(param)));
		}
	}
}
