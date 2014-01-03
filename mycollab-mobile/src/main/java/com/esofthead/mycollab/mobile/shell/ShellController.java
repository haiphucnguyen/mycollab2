package com.esofthead.mycollab.mobile.shell;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.view.CrmModulePresenter;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent.GotoMainPage;
import com.esofthead.mycollab.mobile.shell.ui.MainView;
import com.esofthead.mycollab.mobile.shell.ui.MainViewPresenter;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class ShellController implements IController {
	private static final long serialVersionUID = 1L;

    final private NavigationManager mainNav;

	public ShellController(NavigationManager navigationManager) {
        this.mainNav = navigationManager;
        this.mainNav.addComponent(new Label());
		bind();
	}

	private void bind() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoMainPage>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoMainPage.class;
					}

					@Override
					public void handle(GotoMainPage event) {
                        MainViewPresenter presenter = PresenterResolver.getPresenter(MainViewPresenter.class);
                        MainView view = presenter.initView();

                        presenter.go(mainNav, null);
					}

				});
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ShellEvent.GotoCrmModule>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ShellEvent.GotoCrmModule.class;
                    }

                    @Override
                    public void handle(ShellEvent.GotoCrmModule event) {
                        CrmModulePresenter crmModulePresenter = PresenterResolver
                                .getPresenter(CrmModulePresenter.class);
                        //CrmModuleScreenData.GotoModule screenData = new CrmModuleScreenData.GotoModule(
                        //        (String[]) event.getData());
                        crmModulePresenter.go(mainNav, null);
                    }
                });
	}
}
