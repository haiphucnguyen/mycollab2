package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProjectMessagePresenter extends AbstractPresenter {
	private static final long serialVersionUID = 1L;

	private ProjectMessageView view;

	public ProjectMessagePresenter(ProjectMessageView view) {
		this.view = view;
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
						view.displayMessages();
					}

					@Override
					public void onCancel() {
						// do nothing
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectView projectViewContainer = (ProjectView) container;
		ProjectMessageView messageView = (ProjectMessageView) projectViewContainer
				.gotoSubView("Messages");
		messageView.displayMessages();
	}

}
