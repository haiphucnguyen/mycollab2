package com.esofthead.mycollab.vaadin.mvp;

import java.util.Set;
import java.util.WeakHashMap;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;

import com.esofthead.mycollab.vaadin.ui.ViewComponent;

public class ViewManager {
	private static WeakHashMap<Class, Object> viewMap = new WeakHashMap<Class, Object>();

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(Class<T> viewClass) {
		try {
			T value = (T) viewMap.get(viewClass);
			if (value != null) {
				return value;
			} else {
				value = (T) viewClass.newInstance();
				viewMap.put(viewClass, value);
				return value;
			}
		} catch (Exception e) {
			throw new RuntimeException("Can not create view class: "
					+ viewClass.getName(), e);
		}
	}

	public static void main(String[] args) {
		ComponentScanner scanner = new ComponentScanner();

		Set<Class<?>> samples = scanner.getClasses(new ComponentQuery() {
			protected void query() {
				select().from("com.esofthead.mycollab.**.view.**").returning(
						allAnnotatedWith(ViewComponent.class));
			}
		});

		System.out.println(samples.size() + " " + samples.toArray()[0]);
	}
}
