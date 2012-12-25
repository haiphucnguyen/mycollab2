package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class MessageListPresenter extends AbstractPresenter<MessageListView> implements ListPresenter<MessageSearchCriteria> {

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
                        MessageService messageService = AppContext
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
        MessageContainer messageContainer = (MessageContainer) container;
        messageContainer.removeAllComponents();
        messageContainer.addComponent(view.getWidget());
        doSearch((MessageSearchCriteria) data.getParams());
    }

    @Override
    public void doSearch(MessageSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        view.setCriteria(searchCriteria);
    }
}
