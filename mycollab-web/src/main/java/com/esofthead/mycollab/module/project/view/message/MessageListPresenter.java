/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListCommand;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class MessageListPresenter extends AbstractPresenter<MessageListView>
        implements ListCommand<MessageSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private MessageSearchCriteria searchCriteria;

    public MessageListPresenter() {
        super(MessageListView.class);
        bind();
    }

    private void bind() {
        view.getEditFormHandlers().addFormHandler(
                new EditFormHandler<Message>() {
                    @Override
                    public void onSaveAndNew(Message bean) {
                        // do nothing
                    }

                    @Override
                    public void onSave(Message message) {
                        MessageService messageService = ApplicationContextUtil
                                .getSpringBean(MessageService.class);
                        messageService.saveWithSession(message,
                                AppContext.getUsername());
                        doSearch(searchCriteria);
                    }

                    @Override
                    public void onCancel() {
                        // do nothing
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables
                .canRead(ProjectRolePermissionCollections.MESSAGES)) {
            ProjectBreadcrumb breadCrumb = ViewManager
                    .getView(ProjectBreadcrumb.class);
            breadCrumb.gotoMessageList();

            MessageContainer messageContainer = (MessageContainer) container;
            messageContainer.removeAllComponents();
            messageContainer.addComponent(view.getWidget());
            doSearch((MessageSearchCriteria) data.getParams());
        } else {
            MessageConstants.showMessagePermissionAlert();
        }
    }

    @Override
    public void doSearch(MessageSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        view.setCriteria(searchCriteria);
    }
}
