package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ProblemUrlResolver extends UrlResolver {
	public ProblemUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}
	
	private static class ListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			ProblemSearchCriteria problemSearchCriteria = new ProblemSearchCriteria();
			problemSearchCriteria.setProjectId(new NumberSearchField(projectId));

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new ProblemScreenData.Search(problemSearchCriteria));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class PreviewUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			String[] tokens = decodeUrl.split("/");

			int projectId = Integer.parseInt(tokens[0]);
			int problemId = Integer.parseInt(tokens[1]);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new ProblemScreenData.Read(problemId));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
