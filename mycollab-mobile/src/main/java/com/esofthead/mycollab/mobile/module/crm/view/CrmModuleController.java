/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmNavigationMenu;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CrmModuleController implements IController {
    final private MobileNavigationManager crmViewNavigation;

    public CrmModuleController(MobileNavigationManager navigationManager) {
        this.crmViewNavigation = navigationManager;

        bindCrmEvents();
    }

    private void bindCrmEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<CrmEvent.GotoHome>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return CrmEvent.GotoHome.class;
                    }

                    @Override
                    public void handle(CrmEvent.GotoHome event) {
                    	/*
                    	 * TODO: put setNavigationMenu here seems not right with current structure,
                    	 * need to move it to somewhere else
                    	 */                    	
                    	if(crmViewNavigation.getNavigationMenu() == null)
                    		crmViewNavigation.setNavigationMenu(new CrmNavigationMenu());
                    	
                        ActivityStreamPresenter presenter = PresenterResolver
                                .getPresenter(ActivityStreamPresenter.class);
                        presenter.go(crmViewNavigation, null);
                    }
                });
    }
}
