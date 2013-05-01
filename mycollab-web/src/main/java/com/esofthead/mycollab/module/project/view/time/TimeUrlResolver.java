package com.esofthead.mycollab.module.project.view.time;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TimeTrackingScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class TimeUrlResolver extends UrlResolver {
	public TimeUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
	}

	private static class ListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(projectId));
			searchCriteria.setRangeDate(ItemTimeLoggingSearchCriteria
					.getCurrentRangeDateOfWeekSearchField());

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new TimeTrackingScreenData.Search(searchCriteria));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
