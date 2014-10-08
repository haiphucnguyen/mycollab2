package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.common.UrlTokenizer;
import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver;
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent;
import com.esofthead.mycollab.mobile.module.project.view.parameters.BugFilterParameter;
import com.esofthead.mycollab.mobile.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.mobile.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class BugUrlResolver extends ProjectUrlResolver {
	public BugUrlResolver() {
		this.addSubResolver("list", new DashboardUrlResolver());
		this.addSubResolver("add", new AddUrlResolver());
		this.addSubResolver("edit", new EditUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());

	}

	private static class DashboardUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			int projectId = new UrlTokenizer(params[0]).getInt();
			BugSearchCriteria criteria = new BugSearchCriteria();

			criteria.setProjectId(new NumberSearchField(SearchField.AND,
					projectId));
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { BugStatus.InProgress.name(),
							BugStatus.Open.name(), BugStatus.ReOpened.name() }));
			BugFilterParameter parameter = new BugFilterParameter("Open Bugs",
					criteria);
			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new BugScreenData.Search(parameter));
			EventBusFactory.getInstance().post(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class PreviewUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			UrlTokenizer token = new UrlTokenizer(params[0]);

			int projectId = token.getInt();
			int bugId = token.getInt();

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new BugScreenData.Read(bugId));
			EventBusFactory.getInstance().post(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class EditUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			UrlTokenizer token = new UrlTokenizer(params[0]);

			int projectId = token.getInt();
			int bugId = token.getInt();

			SimpleBug bug;

			BugService bugService = ApplicationContextUtil
					.getSpringBean(BugService.class);
			bug = bugService.findById(bugId, AppContext.getAccountId());

			if (bug == null) {
				throw new ResourceNotFoundException();
			}

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new BugScreenData.Edit(bug));
			EventBusFactory.getInstance().post(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class AddUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			int projectId = new UrlTokenizer(params[0]).getInt();

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new BugScreenData.Add(new SimpleBug()));
			EventBusFactory.getInstance().post(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
