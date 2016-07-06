package com.mycollab.pro.module.file.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.view.IFileModule;
import com.mycollab.pro.module.file.view.FileController;
import com.mycollab.shell.events.ShellEvent;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ControllerRegistry;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.web.ui.ServiceMenu;
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
            serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_PROJECT), new Button.ClickListener() {
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


            serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_PEOPLE), new Button.ClickListener() {
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
