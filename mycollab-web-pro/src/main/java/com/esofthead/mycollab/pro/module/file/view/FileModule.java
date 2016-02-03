package com.esofthead.mycollab.pro.module.file.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.view.IFileModule;
import com.esofthead.mycollab.premium.module.file.view.FileController;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.ServiceMenu;
import com.vaadin.ui.Button;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class FileModule extends AbstractPageView implements IFileModule {
    private static final long serialVersionUID = 1L;

    private MHorizontalLayout serviceMenuContainer;
    private ServiceMenu serviceMenu;

    public FileModule() {
        ControllerRegistry.addController(new FileController(this));
    }

    @Override
    public MHorizontalLayout buildMenu() {
        if (serviceMenuContainer == null) {
            serviceMenuContainer = new MHorizontalLayout();
            serviceMenu = new ServiceMenu();
            serviceMenu.addService("Projects", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{"dashboard"}));
                    serviceMenu.selectService(0);
                }
            });

            serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_CRM), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    EventBusFactory.getInstance().post(new ShellEvent.GotoCrmModule(this, null));
                }
            });

            serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    EventBusFactory.getInstance().post(new ShellEvent.GotoFileModule(this, null));
                }
            });


            serviceMenu.addService("People", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(
                            new ShellEvent.GotoUserAccountModule(this, new String[]{"user", "list"}));

                }
            });

            serviceMenuContainer.with(serviceMenu);
        }
        serviceMenu.selectService(2);
        return serviceMenuContainer;
    }
}
