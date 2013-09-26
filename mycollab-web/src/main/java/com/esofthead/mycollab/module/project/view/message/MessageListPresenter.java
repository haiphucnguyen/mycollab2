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
