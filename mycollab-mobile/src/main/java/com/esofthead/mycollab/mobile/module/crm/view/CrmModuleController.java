package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CrmModuleController implements IController {
    final private NavigationManager crmViewNavigation;

    public CrmModuleController(NavigationManager navigationManager) {
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
                        ActivityStreamPresenter presenter = PresenterResolver
                                .getPresenter(ActivityStreamPresenter.class);
                        presenter.go(crmViewNavigation, null);
                    }
                });
    }
}
