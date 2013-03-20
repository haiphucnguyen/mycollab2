package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.parameters.ComponentScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ComponentUrlResolver extends UrlResolver {
	public ComponentUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}

	private static class ListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			ComponentSearchCriteria componentSearchCriteria = new ComponentSearchCriteria();
			componentSearchCriteria.setProjectid(new NumberSearchField(
					projectId));

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new ComponentScreenData.Search(componentSearchCriteria));
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
			int componentId = Integer.parseInt(tokens[1]);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new ComponentScreenData.Read(componentId));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
