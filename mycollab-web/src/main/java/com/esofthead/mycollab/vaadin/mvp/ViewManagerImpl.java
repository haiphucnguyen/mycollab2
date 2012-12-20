package com.esofthead.mycollab.vaadin.mvp;

import java.util.Set;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.vaadin.ui.ViewComponent;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;

class ViewManagerImpl extends ViewManager {
	private static Logger log = LoggerFactory.getLogger(ViewManagerImpl.class);
	
	private Set<Class<?>> viewClasses;
	private WeakHashMap<Class<?>, Object> viewMap = new WeakHashMap<Class<?>, Object>();
	
	public ViewManagerImpl() {
		ComponentScanner scanner = new ComponentScanner();
		viewClasses = scanner.getClasses(new ComponentQuery() {
			protected void query() {
				select().from("com.esofthead.mycollab.**.view.**").returning(
						allAnnotatedWith(ViewComponent.class));
			}
		});

		log.info("Scan packages to search view. There are "
				+ viewClasses.size() + " views are found");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T extends View> T getViewInstance(final Class<T> viewClass) {
		try {
			T value = (T) viewMap.get(viewClass);
			if (value != null) {
				log.debug("Get implementation of view " + viewClass.getName()
						+ " is " + value.getClass().getName());
				return value;
			} else {
				for (Class<?> classInstance : viewClasses) {
					if (viewClass.isAssignableFrom(classInstance)) {
						value = (T) classInstance.newInstance();
						viewMap.put(viewClass, value);
						log.debug("Get implementation of view "
								+ viewClass.getName() + " is "
								+ value.getClass().getName());
						return value;
					}
				}

				// find the possible reason can not detect the view class
				ComponentScanner scanner = new ComponentScanner();
				Set<Class<?>> candidateClasses;
				if (viewClass.isInterface()) {
					candidateClasses = scanner.getClasses(new ComponentQuery() {
						protected void query() {
							select().from("com.esofthead.mycollab.**.view.**")
									.returning(allImplementing(viewClass));
						}
					});
				} else {
					candidateClasses = scanner.getClasses(new ComponentQuery() {
						protected void query() {
							select().from("com.esofthead.mycollab.**.view.**")
									.returning(allExtending(viewClass));
						}
					});
				}

				if (candidateClasses == null || candidateClasses.size() == 0) {
					log.error("Can not find any implementation of view class "
							+ viewClass.getName());
				} else {
					log.error("There are "
							+ candidateClasses.size()
							+ " implementation of view class, but they are not initiated. Probably they do not have @ViewComponent annotation in class declaration. They are: ");
					for (Class<?> classItem : candidateClasses) {
						log.error("  Class: " + classItem.getName());
					}
				}

				throw new RuntimeException(
						"Can not find implementation of view class: "
								+ viewClass.getName());
			}
		} catch (Exception e) {
			throw new RuntimeException("Can not create view class: "
					+ viewClass.getName(), e);
		}
	}

	@Override
	protected void resetResources() {
		viewMap.clear();
	}
	
	
}
