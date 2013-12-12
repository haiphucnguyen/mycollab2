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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.UserScreenData;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class UserPresenter extends AbstractPresenter<UserContainer> {
	private static final long serialVersionUID = 1L;

	public UserPresenter() {
		super(UserContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserPermissionManagementView groupContainer = (UserPermissionManagementView) container;
		groupContainer.gotoSubView("Users");

		if (data == null) {
			UserListPresenter listPresenter = PresenterResolver
					.getPresenter(UserListPresenter.class);
			UserSearchCriteria criteria = new UserSearchCriteria();
			criteria.setSaccountid(new NumberSearchField(AppContext
					.getAccountId()));
			criteria.setRegisterStatuses(new SetSearchField<String>(
					SearchField.AND,
					new String[] { RegisterStatusConstants.ACTIVE }));
			listPresenter.go(view.getWidget(),
					new ScreenData.Search<UserSearchCriteria>(criteria));
		} else if (data instanceof UserScreenData.Read) {
			UserReadPresenter presenter = PresenterResolver
					.getPresenter(UserReadPresenter.class);
			presenter.go(view.getWidget(), data);
		} else if (data instanceof UserScreenData.Search) {
			UserListPresenter presenter = PresenterResolver
					.getPresenter(UserListPresenter.class);
			presenter.go(view.getWidget(), data);
		} else if (data instanceof UserScreenData.Add
				|| data instanceof UserScreenData.Edit) {
			UserAddPresenter presenter = PresenterResolver
					.getPresenter(UserAddPresenter.class);
			presenter.go(view.getWidget(), data);
		}
	}
}
