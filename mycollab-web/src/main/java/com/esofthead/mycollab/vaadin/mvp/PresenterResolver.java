package com.esofthead.mycollab.vaadin.mvp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.AppContext;

public class PresenterResolver {

	private static final String PRESENTER_VAL = "presenterMap";

	private static Logger log = LoggerFactory
			.getLogger(PresenterResolver.class);

	protected static Set<Class<? extends Presenter>> presenterClasses;

	static {
		log.debug("Scan presenter implementation");
		Reflections reflections = new Reflections("com.esofthead.mycollab");
		presenterClasses = reflections.getSubTypesOf(Presenter.class);
	}

	public static void init() {

	}

	@SuppressWarnings("unchecked")
	public static <P extends Presenter> P getPresenter(Class<P> presenterClass) {
		Map<Class<?>, Object> presenterMap = (Map<Class<?>, Object>) AppContext
				.getVariable(PRESENTER_VAL);
		if (presenterMap == null) {
			presenterMap = new HashMap<Class<?>, Object>();
			AppContext.putVariable(PRESENTER_VAL, presenterMap);
		}

		P value = (P) presenterMap.get(presenterClass);
		if (value == null) {
			try {
				if (!presenterClass.isInterface()) {
					value = (P) presenterClass.newInstance();
				} else {
					for (Class<?> classInstance : presenterClasses) {
						if (presenterClass.isAssignableFrom(classInstance)
								&& !classInstance.isInterface()) {

							value = (P) classInstance.newInstance();
							log.debug("Get implementation of presenter "
									+ presenterClass.getName() + " is "
									+ value.getClass().getName());
						}
					}
				}

				presenterMap.put(presenterClass, value);
				return value;
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		} else {
			return value;
		}
	}
}
