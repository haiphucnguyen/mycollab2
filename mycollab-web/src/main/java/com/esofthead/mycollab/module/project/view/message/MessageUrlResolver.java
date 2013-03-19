package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;

public class MessageUrlResolver extends UrlResolver {
	public MessageUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
	}

	private static class ListUrlResolver extends UrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int projectId = Integer.parseInt(decodeUrl);
			
		}
	}
}
