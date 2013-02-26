/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.UserScreenData;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class UserPresenter extends AbstractPresenter<UserContainer> {
	private static final long serialVersionUID = 1L;

	public UserPresenter() {
        super(UserContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (data == null) {
            UserListPresenter listPresenter = PresenterResolver.getPresenter(UserListPresenter.class);
            UserSearchCriteria criteria = new UserSearchCriteria();
            criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
            listPresenter.go(view.getWidget(), new ScreenData.Search<UserSearchCriteria>(criteria));
        } else if (data instanceof UserScreenData.Read) {
        	UserReadPresenter presenter = PresenterResolver.getPresenter(UserReadPresenter.class);
        	presenter.go(view.getWidget(), data);
        } else if (data instanceof UserScreenData.Search) {
        	UserListPresenter presenter = PresenterResolver.getPresenter(UserListPresenter.class);
        	presenter.go(view.getWidget(), data);
        } else if (data instanceof UserScreenData.Add || data instanceof UserScreenData.Edit) {
        	UserAddPresenter presenter = PresenterResolver.getPresenter(UserAddPresenter.class);
        	presenter.go(view.getWidget(), data);
        }
    }
}
