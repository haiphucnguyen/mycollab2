/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class RoleReadPresenter extends AbstractPresenter<RoleReadView> {

    public RoleReadPresenter() {
        super(RoleReadView.class);

        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Role>() {
                    @Override
                    public void onEdit(Role data) {
                        EventBus.getInstance().fireEvent(
                                new RoleEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Role data) {
                        RoleService taskService = AppContext
                                .getSpringBean(RoleService.class);
                        taskService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new RoleEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Role data) {
                        Role cloneData = (Role) data.copy();
                        cloneData.setRolename(null);
                        EventBus.getInstance().fireEvent(
                                new RoleEvent.GotoAdd(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new RoleEvent.GotoList(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        RoleContainer roleContainer = (RoleContainer) container;
        roleContainer.removeAllComponents();
        roleContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof Role) {
            view.previewItem((Role) data.getParams());
        }
    }
}
