package com.esofthead.mycollab.vaadin.mvp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.AppContext;

public class PresenterResolver {

	private static final String PRESENTER_VAL = "presenterMap";
	private static Logger log = LoggerFactory
			.getLogger(PresenterResolver.class);

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
				value = (P) presenterClass.newInstance();
				presenterMap.put(presenterClass, value);
				return value;
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		} else {
			return value;
		}
	}

	public static void clearResources() {
		@SuppressWarnings("unchecked")
		Map<Class<?>, Object> presenterMap = (Map<Class<?>, Object>) AppContext
				.getVariable(PRESENTER_VAL);
		if (presenterMap != null) {
			presenterMap.clear();
			AppContext.removeVariable(PRESENTER_VAL);
			log.debug("Remove presenter map");
		}
	}
}
