package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.project.view.UserDashboardViewImpl;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class AccountDashboardViewImpl extends AbstractView implements
        AccountDashboardView {
    
    private final HorizontalLayout root;
    private final DetachedTabs accountTab;
    private final CssLayout accountSpace = new CssLayout();
    private AccountController controller = new AccountController(this);
    
    public AccountDashboardViewImpl() {
        this.setStyleName("accountViewContainer");
        this.setMargin(false);
        root = new HorizontalLayout();
        
        accountSpace.setSizeFull();
        accountTab = new DetachedTabs.Vertical(accountSpace);
        accountTab.setWidth("200px");
        accountTab.setHeight(null);
        
        VerticalLayout menu = new VerticalLayout();
        menu.setSizeFull();
        menu.setStyleName("sidebar-menu");
        
        menu.addComponent(accountTab);
        root.addComponent(menu);
        root.addComponent(accountSpace);
        
        buildComponents();
        
        this.addComponent(root);
    }
    
    private void buildComponents() {
        accountTab.addTab(constructUserInformationComponent(),
                "User Information");
        accountTab.addTab(constructAccountSettingsComponent(),
                "Account Settings");
        
        if (AppContext.canRead(RolePermissionCollections.USER_USER) || AppContext.canRead(RolePermissionCollections.USER_ROLE)) {
            accountTab.addTab(constructUserPermissionComponent(), "Users & Permissions");
        }
        
        accountTab.addTabChangedListener(new DetachedTabs.TabChangedListener() {
            @Override
            public void tabChanged(TabChangedEvent event) {
                Button btn = event.getSource();
                String caption = btn.getCaption();
                if ("User Information".equals(caption)) {
                    gotoUserInformation();
                } else if ("Account Settings".equals(caption)) {
                    gotoAccountSettings();
                } else if ("Users & Permissions".equals(caption)) {
                    UserPermissionManagementPresenter presenter = PresenterResolver.getPresenter(UserPermissionManagementPresenter.class);
                    presenter.go(AccountDashboardViewImpl.this, null);
                }
            }
        });
    }
    
    private ComponentContainer constructAccountSettingsComponent() {
        AccountSettingsPresenter presenter = PresenterResolver
                .getPresenter(AccountSettingsPresenter.class);
        return presenter.getView();
    }
    
    private ComponentContainer constructUserInformationComponent() {
        UserInformationPresenter presenter = PresenterResolver
                .getPresenter(UserInformationPresenter.class);
        return presenter.getView();
    }
    
    private ComponentContainer constructUserPermissionComponent() {
        UserPermissionManagementPresenter presenter = PresenterResolver.getPresenter(UserPermissionManagementPresenter.class);
        return presenter.getView();
    }
    
    @Override
    public void gotoUserInformation() {
        accountTab.selectTab("User Information");
    }
    
    @Override
    public void gotoAccountSettings() {
        accountTab.selectTab("Account Settings");
    }
}
