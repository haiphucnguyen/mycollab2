package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountUrlResolver;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class RoleUrlResolver extends AccountUrlResolver {
	public RoleUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("add", new AddUrlResolver());
		this.addSubResolver("edit", new EditUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}

	private class ListUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new RoleEvent.GotoList(ListUrlResolver.this, null));
		}
	}

	private class AddUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new RoleEvent.GotoAdd(AddUrlResolver.this, null));
		}
	}

	private class EditUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			int roleId = Integer.parseInt(UrlEncodeDecoder.decode(params[0]));
			RoleService roleService = AppContext
					.getSpringBean(RoleService.class);
			SimpleRole role = roleService.findRoleById(roleId,
					AppContext.getAccountId());
			EventBus.getInstance().fireEvent(
					new RoleEvent.GotoEdit(EditUrlResolver.this, role));
		}
	}

	private class PreviewUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			int roleId = Integer.parseInt(UrlEncodeDecoder.decode(params[0]));
			RoleService roleService = AppContext
					.getSpringBean(RoleService.class);
			SimpleRole role = roleService.findRoleById(roleId,
					AppContext.getAccountId());
			EventBus.getInstance().fireEvent(
					new RoleEvent.GotoRead(PreviewUrlResolver.this, role));
		}
	}
}
