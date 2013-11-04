package com.esofthead.mycollab.vaadin.mvp;

import java.util.Set;

import org.reflections.Reflections;

import com.esofthead.mycollab.vaadin.ui.ViewComponent;

public abstract class ViewManager {

	private static ViewManager impl = new ViewManagerImpl();

	protected static Set<Class<?>> viewClasses;

	static {
		Reflections reflections = new Reflections("com.esofthead.mycollab");
		viewClasses = reflections.getTypesAnnotatedWith(ViewComponent.class);
	}

	protected abstract <T extends View> T getViewInstance(
			final Class<T> viewClass);

	public static void init() {

	}

	public static <T extends View> T getView(final Class<T> viewClass) {
		return impl.getViewInstance(viewClass);
	}
}
