/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
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
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        MessageContainer riskContainer = (MessageContainer) container;
        riskContainer.removeAllComponents();
        riskContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof Integer) {
            MessageService messageService = AppContext
                    .getSpringBean(MessageService.class);
            SimpleMessage message = messageService.findMessageById((Integer) data
                    .getParams());
            view.previewItem(message);

            ProjectBreadcrumb breadCrumb = ViewManager.getView(ProjectBreadcrumb.class);
            breadCrumb.gotoMessage(message);
        } else {
            throw new MyCollabException("Unhanddle this case yet");
        }
    }
}
