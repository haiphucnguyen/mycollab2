package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectSettingScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;

public class SettingUrlResolver extends ProjectUrlResolver {
	public SettingUrlResolver() {
		this.addSubResolver("notification", new ViewUrlResolver());
	}

	private static class ViewUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new ProjectSettingScreenData.ViewNotification());
			EventBus.getInstance().fireEvent(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
