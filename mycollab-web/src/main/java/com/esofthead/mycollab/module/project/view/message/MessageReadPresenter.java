/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */

public class MessageReadPresenter extends AbstractPresenter<MessageReadView> {
    private static final long serialVersionUID = 1L;

    public MessageReadPresenter() {
        super(MessageReadView.class);
        bind();
    }

    protected void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new PreviewFormHandlers<SimpleMessage>() {

                    @Override
                    public void onEdit(SimpleMessage data) {
                    }

                    @Override
                    public void onDelete(SimpleMessage data) {
                    }

                    @Override
                    public void onClone(SimpleMessage data) {
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new MessageEvent.GotoList(this, null));
                    }

                    @Override
                    public void onAssign(SimpleMessage data) {
                    }

                    @Override
                    public void gotoPrevious(SimpleMessage data) {
                    }

                    @Override
                    public void gotoNext(SimpleMessage data) {
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables
                .canRead(ProjectRolePermissionCollections.MESSAGES)) {
            MessageContainer messageContainer = (MessageContainer) container;
            messageContainer.removeAllComponents();
            messageContainer.addComponent(view.getWidget());

            if (data.getParams() instanceof Integer) {
                MessageService messageService = ApplicationContextUtil
                        .getSpringBean(MessageService.class);
                SimpleMessage message = messageService.findMessageById(
                        (Integer) data.getParams(), AppContext.getAccountId());
                view.previewItem(message);

                ProjectBreadcrumb breadCrumb = ViewManager
                        .getView(ProjectBreadcrumb.class);
                breadCrumb.gotoMessage(message);
            } else {
                throw new MyCollabException("Unhanddle this case yet");
            }
        } else {
            MessageConstants.showMessagePermissionAlert();
        }
    }
}
