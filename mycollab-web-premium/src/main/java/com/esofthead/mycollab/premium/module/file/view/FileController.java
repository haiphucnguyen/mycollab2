package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.events.FileEvent;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.google.common.eventbus.Subscribe;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class FileController implements IController {
	private static final long serialVersionUID = 1L;

	private FileModule container;

	public FileController(FileModule container) {
		this.container = container;

		bindFileEvents();
	}

	private void bindFileEvents() {
		EventBusFactory.getInstance().register(
				new ApplicationEventListener<FileEvent.GotoList>() {
					private static final long serialVersionUID = 1L;

					@Subscribe
					@Override
					public void handle(FileEvent.GotoList event) {
						FileMainPresenter presenter = PresenterResolver
								.getPresenter(FileMainPresenter.class);
						presenter.go(container, null);
					}
				});
	}

}
