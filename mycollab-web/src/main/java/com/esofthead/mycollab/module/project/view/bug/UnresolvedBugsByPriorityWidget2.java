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
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

public class UnresolvedBugsByPriorityWidget2 extends Depot {
	private class BugPriorityClickListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(final ClickEvent event) {
			final String caption = event.getButton().getCaption();
			bugSearchCriteria.setPriorities(new SetSearchField<String>(
					new String[] { caption }));
			final BugSearchParameter param = new BugSearchParameter(
					"Unresolved " + caption + " Bug List", bugSearchCriteria);
			EventBus.getInstance()
					.fireEvent(
							new BugEvent.GotoList(this,
									new BugScreenData.Search(param)));
		}
	}

	private static final long serialVersionUID = 1L;

	private BugSearchCriteria bugSearchCriteria;

	public UnresolvedBugsByPriorityWidget2() {
		super(LocalizationHelper
				.getMessage(BugI18nEnum.UNRESOLVED_BY_PRIORITY_WIDGET_TITLE),
				new VerticalLayout());
		setContentBorder(true);
		((VerticalLayout) bodyContent).setSpacing(true);
	}

	public void setSearchCriteria(final BugSearchCriteria searchCriteria) {
		bugSearchCriteria = searchCriteria;
		bodyContent.removeAllComponents();
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
						final ThemeResource iconPriority = BugPriorityComboBox
								.getIconResourceByPriority(status);
						userLbl.setIcon(iconPriority);
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
						bodyContent.addComponent(priorityLayout);
						continue;
					}
				}

				if (!isFound) {
					final HorizontalLayout priorityLayout = new HorizontalLayout();
					priorityLayout.setSpacing(true);
					final Button userLbl = new Button(status, listener);
					final ThemeResource iconPriority = BugPriorityComboBox
							.getIconResourceByPriority(status);
					userLbl.setIcon(iconPriority);
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
					bodyContent.addComponent(priorityLayout);
				}
			}

		}
	}
}
