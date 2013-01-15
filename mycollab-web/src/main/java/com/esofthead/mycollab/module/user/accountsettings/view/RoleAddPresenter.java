/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class RoleAddPresenter extends AbstractPresenter<RoleAddView> {

    public RoleAddPresenter() {
        super(RoleAddView.class);

        view.getEditFormHandlers().addFormHandler(new EditFormHandler<Role>() {
            @Override
            public void onSave(final Role item) {
                save(item);
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new RoleEvent.GotoList(this, null));
                }
            }

            @Override
            public void onCancel() {
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new RoleEvent.GotoList(this, null));
                }
            }

            @Override
            public void onSaveAndNew(Role item) {
                save(item);
                EventBus.getInstance().fireEvent(
                        new RoleEvent.GotoAdd(this, null));
            }
        });
    }

    public void save(Role item) {
        RoleService roleService = AppContext.getSpringBean(RoleService.class);
        item.setSaccountid(AppContext.getAccountId());

        if (item.getId() == null) {
            roleService.saveWithSession(item, AppContext.getUsername());
        } else {
            roleService.updateWithSession(item, AppContext.getUsername());
        }


        roleService.savePermission(item.getId(), view.getPermissionMap());

    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        RoleContainer roleContainer = (RoleContainer) container;
        roleContainer.removeAllComponents();
        roleContainer.addComponent(view.getWidget());
        view.editItem((Role) data.getParams());
    }
}
