/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountUrlResolver;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class UserUrlResolver extends AccountUrlResolver {
	public UserUrlResolver() {
		this.addSubResolver("list", new ListUrlResolver());
		this.addSubResolver("add", new AddUrlResolver());
		this.addSubResolver("edit", new EditUrlResolver());
		this.addSubResolver("preview", new PreviewUrlResolver());
	}

	private class ListUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoList(ListUrlResolver.this, null));
		}
	}

	private class AddUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoAdd(AddUrlResolver.this, null));
		}
	}

	private class EditUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			String username = UrlEncodeDecoder.decode(params[0]);
			UserService userService = ApplicationContextUtil
					.getSpringBean(UserService.class);
			SimpleUser user = userService.findUserByUserNameInAccount(username,
					AppContext.getAccountId());
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoEdit(EditUrlResolver.this, user));
		}
	}

	private class PreviewUrlResolver extends AccountUrlResolver {
		protected void handlePage(String... params) {
			String username = UrlEncodeDecoder.decode(params[0]);
			EventBus.getInstance().fireEvent(
					new UserEvent.GotoRead(PreviewUrlResolver.this, username));
		}
	}
}
