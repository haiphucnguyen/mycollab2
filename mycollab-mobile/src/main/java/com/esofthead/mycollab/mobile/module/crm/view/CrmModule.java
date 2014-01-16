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

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractMobileMainView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class CrmModule extends AbstractMobileMainView implements PageView {

    final private NavigationManager crmViewNavigation;

    public CrmModule() {
        super();
        this.setSizeFull();

        crmViewNavigation = new NavigationManager();
        this.addComponent(crmViewNavigation);

        ControllerRegistry.addController(new CrmModuleController(this.crmViewNavigation));
    }

    public void gotoCrmDashboard() {
        EventBus.getInstance().fireEvent(new CrmEvent.GotoHome(this, null));
    }
}
