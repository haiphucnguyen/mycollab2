/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.RoleScreenData;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class RolePresenter extends AbstractPresenter<RoleContainer> {
	private static final long serialVersionUID = 1L;

	public RolePresenter() {
		super(RoleContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (data == null) {
			RoleListPresenter listPresenter = PresenterResolver
					.getPresenter(RoleListPresenter.class);
			RoleSearchCriteria criteria = new RoleSearchCriteria();
			criteria.setsAccountId(new NumberSearchField(AppContext
					.getAccountId()));
			listPresenter.go(view.getWidget(),
					new ScreenData.Search<RoleSearchCriteria>(criteria));
		} else if (data instanceof RoleScreenData.Add
				|| data instanceof RoleScreenData.Edit) {
			RoleAddPresenter presenter = PresenterResolver
					.getPresenter(RoleAddPresenter.class);
			presenter.go(view.getWidget(), data);
		} else if (data instanceof RoleScreenData.Read) {
			RoleReadPresenter presenter = PresenterResolver
					.getPresenter(RoleReadPresenter.class);
			presenter.go(view.getWidget(), data);
		} else if (data instanceof RoleScreenData.Search) {
			RoleListPresenter presenter = PresenterResolver
					.getPresenter(RoleListPresenter.class);
			presenter.go(view.getWidget(), data);
		}
	}
}
