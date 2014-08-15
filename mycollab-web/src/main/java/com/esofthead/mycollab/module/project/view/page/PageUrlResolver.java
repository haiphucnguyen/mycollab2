package com.esofthead.mycollab.module.project.view.page;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.UrlTokenizer;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver;
import com.esofthead.mycollab.module.project.view.parameters.PageScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.wiki.domain.Page;
import com.esofthead.mycollab.module.wiki.service.WikiService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
public class PageUrlResolver extends ProjectUrlResolver {
	public PageUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("add", new AddUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}

	private static class ListUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			String[] tokens = decodeUrl.split("/");

			int projectId = Integer.parseInt(tokens[0]);
			String pagePath = tokens[1];

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new PageScreenData.Search(pagePath));
			EventBusFactory.getInstance().post(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}

	private static class PreviewUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			try {
				UrlTokenizer tokenizer = new UrlTokenizer(params[0]);

				int projectId = tokenizer.getInt();
				String pagePath = tokenizer.getRemainValue();

				WikiService wikiService = ApplicationContextUtil
						.getSpringBean(WikiService.class);
				Page page = wikiService.getPage(pagePath);

				if (page != null) {
					PageActionChain chain = new PageActionChain(
							new ProjectScreenData.Goto(projectId),
							new PageScreenData.Read(page));
					EventBusFactory.getInstance().post(
							new ProjectEvent.GotoMyProject(this, chain));
				} else {
					PageActionChain chain = new PageActionChain(
							new ProjectScreenData.Goto(projectId));
					EventBusFactory.getInstance().post(
							new ProjectEvent.GotoMyProject(this, chain));
				}
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}
	}

	private static class AddUrlResolver extends ProjectUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);

			PageActionChain chain = new PageActionChain(
					new ProjectScreenData.Goto(projectId),
					new PageScreenData.Add(new Page()));
			EventBusFactory.getInstance().post(
					new ProjectEvent.GotoMyProject(this, chain));
		}
	}
}
