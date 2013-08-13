package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.web.AppContext;

public class TaskGroupUrlResolver extends ProjectUrlResolver {
	public TaskGroupUrlResolver() {
		this.addSubResolver("preview", new ReadUrlResolver());
		this.addSubResolver("edit", new EditUrlResolver());
	}

	private static class ReadUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			String[] tokens = decodeUrl.split("/");

			int projectId = Integer.parseInt(tokens[0]);
			int taskgroupId = Integer.parseInt(tokens[1]);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new TaskGroupScreenData.Read(taskgroupId));
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
			int taskgroupId = Integer.parseInt(tokens[1]);

			ProjectTaskListService taskGroupService = AppContext
					.getSpringBean(ProjectTaskListService.class);
			SimpleTaskList taskgroup = taskGroupService.findById(taskgroupId,
					AppContext.getAccountId());
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new TaskGroupScreenData.Edit(taskgroup));
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

}
