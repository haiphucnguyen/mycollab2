/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
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

    public RolePresenter() {
        super(RoleContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (data == null) {
            RoleListPresenter listPresenter = PresenterResolver.getPresenter(RoleListPresenter.class);
            RoleSearchCriteria criteria = new RoleSearchCriteria();
            criteria.setsAccountid(new NumberSearchField(AppContext.getAccountId()));
            listPresenter.go(view.getWidget(), new ScreenData.Search<RoleSearchCriteria>(criteria));
        }
    }
}
