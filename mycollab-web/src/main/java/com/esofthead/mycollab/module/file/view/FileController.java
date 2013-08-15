package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.module.file.events.FileEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;

public class FileController implements IController {
	private static final long serialVersionUID = 1L;

	private FileModule container;

	public FileController(FileModule container) {
		this.container = container;

		bindFileEvents();
	}

	private void bindFileEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<FileEvent.GotoList>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return FileEvent.GotoList.class;
					}

					@Override
					public void handle(FileEvent.GotoList event) {
						FileMainPresenter presenter = PresenterResolver
								.getPresenter(FileMainPresenter.class);
						presenter.go(container, null);
					}
				});
	}

}
