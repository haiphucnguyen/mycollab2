package com.esofthead.mycollab.vaadin.mvp;

import java.util.Set;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;

import com.esofthead.mycollab.vaadin.ui.ViewComponent;

public abstract class ViewManager {

	private static ViewManager impl = new ViewManagerImpl();

	protected static Set<Class<?>> viewClasses;

	static {
		ComponentScanner scanner = new ComponentScanner();
		viewClasses = scanner.getClasses(new ComponentQuery() {
			@Override
			protected void query() {
				select().from("com.esofthead.mycollab.**.view.**").returning(
						allAnnotatedWith(ViewComponent.class));
			}
		});
	}

	protected abstract <T extends View> T getViewInstance(
			final Class<T> viewClass);

	public static void init() {

	}

	public static <T extends View> T getView(final Class<T> viewClass) {
		return impl.getViewInstance(viewClass);
	}
}
