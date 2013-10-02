package com.esofthead.mycollab.module.project.view.standup;

import java.util.Date;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.web.AppContext;

public class StandupUrlResolver extends ProjectUrlResolver {
	public StandupUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("add", new PreviewUrlResolver());
	}

	private static class ListUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			StandupReportSearchCriteria standupSearchCriteria = new StandupReportSearchCriteria();
			standupSearchCriteria
					.setProjectId(new NumberSearchField(projectId));
			standupSearchCriteria.setOnDate(new DateSearchField(
					SearchField.AND, new GregorianCalendar().getTime()));

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new StandupScreenData.Search(standupSearchCriteria));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class PreviewUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			String[] tokens = decodeUrl.split("/");

			int projectId = Integer.parseInt(tokens[0]);
			Date onDate = AppContext.convertDate(tokens[2]);

			StandupReportService reportService = ApplicationContextUtil
					.getSpringBean(StandupReportService.class);
			SimpleStandupReport report = reportService
					.findStandupReportByDateUser(projectId,
							AppContext.getUsername(), onDate,
							AppContext.getAccountId());
			if (report == null) {
				report = new SimpleStandupReport();
			}

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new StandupScreenData.Add(report));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
