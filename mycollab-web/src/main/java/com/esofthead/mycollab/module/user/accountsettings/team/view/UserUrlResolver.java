package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.UrlResolver;
import com.esofthead.mycollab.web.AppContext;

public class UserUrlResolver extends UrlResolver {
	public UserUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("add", new AddUrlResolver());
		this.addSubResolver("edit", new EditUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}

	private class ListUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoList(ListUrlResolver.this, null));
		}
	}

	private class AddUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoAdd(AddUrlResolver.this, null));
		}
	}

	private class EditUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			String username = UrlEncodeDecoder.decode(params[0]);
			UserService userService = AppContext
					.getSpringBean(UserService.class);
			SimpleUser user = userService.findUserByUserName(username,
					AppContext.getAccountId());
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoEdit(EditUrlResolver.this, user));
		}
	}

	private class PreviewUrlResolver extends UrlResolver {
		protected void handlePage(String... params) {
			String username = UrlEncodeDecoder.decode(params[0]);
			UserService userService = AppContext
					.getSpringBean(UserService.class);
			SimpleUser user = userService.findUserByUserName(username,
					AppContext.getAccountId());
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoRead(PreviewUrlResolver.this, user));
		}
	}
}
