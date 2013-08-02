package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver;
import com.esofthead.mycollab.module.project.view.parameters.MilestoneScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.web.AppContext;

public class MilestoneUrlResolver extends ProjectUrlResolver {
	public MilestoneUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("add", new AddUrlResolver());
		this.addSubResolver("edit", new EditUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}

	private static class ListUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			MilestoneSearchCriteria milestoneSearchCriteria = new MilestoneSearchCriteria();
			milestoneSearchCriteria.setProjectId(new NumberSearchField(
					projectId));

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new MilestoneScreenData.Search(milestoneSearchCriteria));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class AddUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new MilestoneScreenData.Add(new Milestone()));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class EditUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			String[] tokens = decodeUrl.split("/");

			int projectId = Integer.parseInt(tokens[0]);
			int milestoneid = Integer.parseInt(tokens[1]);

			MilestoneService milestoneService = AppContext
					.getSpringBean(MilestoneService.class);
			SimpleMilestone milestone = milestoneService.findById(milestoneid);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new MilestoneScreenData.Edit(milestone));
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
			int milestoneid = Integer.parseInt(tokens[1]);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new MilestoneScreenData.Read(milestoneid));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
