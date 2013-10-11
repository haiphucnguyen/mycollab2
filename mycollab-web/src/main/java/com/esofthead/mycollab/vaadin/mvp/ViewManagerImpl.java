package com.esofthead.mycollab.vaadin.mvp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.AppContext;

class ViewManagerImpl extends ViewManager {

	public static final String VIEW_MANAGER_VAL = "viewMap";

	private static Logger log = LoggerFactory.getLogger(ViewManagerImpl.class);
	

	@SuppressWarnings("unchecked")
	@Override
	protected <T extends View> T getViewInstance(final Class<T> viewClass) {
		try {
			Map<Class<?>, Object> viewMap = (Map<Class<?>, Object>) AppContext
					.getVariable(VIEW_MANAGER_VAL);
			if (viewMap == null) {
				viewMap = new HashMap<Class<?>, Object>();
				AppContext.putVariable(VIEW_MANAGER_VAL, viewMap);
			}

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
						@Override
						protected void query() {
							select().from("com.esofthead.mycollab.**.view.**")
									.returning(allImplementing(viewClass));
						}
					});
				} else {
					candidateClasses = scanner.getClasses(new ComponentQuery() {
						@Override
						protected void query() {
							select().from("com.esofthead.mycollab.**.view.**")
									.returning(allExtending(viewClass));
						}
					});
				}

				if (candidateClasses == null || candidateClasses.isEmpty()) {
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

				throw new MyCollabException(
						"Can not find implementation of view class: "
								+ viewClass.getName());
			}
		} catch (Throwable e) {
			throw new MyCollabException("Can not create view class: "
					+ viewClass.getName(), e);
		}
	}
}
