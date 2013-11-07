package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.file.ProjectFileUrlResolver;
import com.esofthead.mycollab.module.project.view.bug.BugUrlResolver;
import com.esofthead.mycollab.module.project.view.message.MessageUrlResolver;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneUrlResolver;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.problem.ProblemUrlResolver;
import com.esofthead.mycollab.module.project.view.risk.RiskUrlResolver;
import com.esofthead.mycollab.module.project.view.settings.RoleUrlResolver;
import com.esofthead.mycollab.module.project.view.settings.SettingUrlResolver;
import com.esofthead.mycollab.module.project.view.settings.UserUrlResolver;
import com.esofthead.mycollab.module.project.view.standup.StandupUrlResolver;
import com.esofthead.mycollab.module.project.view.task.ScheduleUrlResolver;
import com.esofthead.mycollab.module.project.view.time.TimeUrlResolver;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class ProjectUrlResolver extends UrlResolver {
	public UrlResolver build() {
		this.addSubResolver("dashboard", new ProjectPageUrlResolver());
		this.addSubResolver("message", new MessageUrlResolver());
		this.addSubResolver("milestone", new MilestoneUrlResolver());
		this.addSubResolver("task", new ScheduleUrlResolver());
		this.addSubResolver("bug", new BugUrlResolver());
		this.addSubResolver("risk", new RiskUrlResolver());
		this.addSubResolver("problem", new ProblemUrlResolver());
		this.addSubResolver("standup", new StandupUrlResolver());
		this.addSubResolver("user", new UserUrlResolver());
		this.addSubResolver("role", new RoleUrlResolver());
		this.addSubResolver("setting", new SettingUrlResolver());
		this.addSubResolver("time", new TimeUrlResolver());
		this.addSubResolver("file", new ProjectFileUrlResolver());
		return this;
	}

	@Override
	public void handle(String... params) {
		if (!ModuleHelper.isCurrentProjectModule()) {
			EventBus.getInstance().fireEvent(
					new ShellEvent.GotoProjectModule(this, params));
		} else {
			super.handle(params);
		}
	}

	@Override
	protected void defaultPageErrorHandler() {
		EventBus.getInstance().fireEvent(
				new ShellEvent.GotoProjectModule(this, null));
	}

	public static class ProjectPageUrlResolver extends ProjectUrlResolver {

		@Override
		protected void handlePage(String... params) {
			if (params == null || params.length == 0) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.GotoProjectModule(this, null));
			} else {
				String decodeUrl = UrlEncodeDecoder.decode(params[0]);
				int projectId = Integer.parseInt(decodeUrl);
				PageActionChain chain = new PageActionChain(
						new ProjectScreenData.Goto(projectId));
				EventBus.getInstance().fireEvent(
						new ProjectEvent.GotoMyProject(this, chain));
			}

		}
	}
}
