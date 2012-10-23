package com.esofthead.mycollab.vaadin.mvp.ui;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;

@SuppressWarnings("serial")
public abstract class AbstractPresenter<T extends View> implements Serializable {

	private final Logger log = LoggerFactory.getLogger(AbstractPresenter.class);

	@Autowired
	protected EventBus eventBus;

	@Autowired
	private transient ApplicationContext context;

	protected T view;

	@SuppressWarnings("unchecked")
	@PostConstruct
	protected void postConstruct() {

		Class<? extends View> viewInterface = getClass().getAnnotation(
				ViewInterface.class).value();
		view = (T) context.getBean(viewInterface);

		registerViewOpenListener();

		initPresenter();
	}

	private void registerViewOpenListener() {
		eventBus.addListener(new ApplicationEventListener<ViewOpenedEvent>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ViewOpenedEvent.class;
			}

			@Override
			public void handle(ViewOpenedEvent event) {

				if (event.getView() == view) {
					log.debug("Received ViewOpenedEvent from view: {}",
							event.getSource());
					viewOpened();
				}
			}
		});
	}

	/**
	 * Initializes the presenter.
	 */
	protected void initPresenter() {
	};

	protected void viewOpened() {
	};

	/**
	 * Declares a view interface for MVP-pattern presenter implementation.
	 */
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface ViewInterface {
		Class<? extends View> value();
	}
}
