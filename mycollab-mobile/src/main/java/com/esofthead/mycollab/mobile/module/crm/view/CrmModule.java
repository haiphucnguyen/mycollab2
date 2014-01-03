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
