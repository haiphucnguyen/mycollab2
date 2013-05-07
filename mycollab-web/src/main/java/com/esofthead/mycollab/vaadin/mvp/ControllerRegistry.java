package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.web.AppContext;

public class ControllerRegistry {
	private static final String CONTROLLER_REGISTRY = "CONTROLLER_REGISTRY";

	private static Logger log = LoggerFactory
			.getLogger(ControllerRegistry.class);

	public static void addController(IController controler) {
		List<IController> controllerList = ((List<IController>) AppContext
				.getVariable(CONTROLLER_REGISTRY));
		if (controllerList == null) {
			controllerList = new ArrayList<IController>();
			AppContext.putVariable(CONTROLLER_REGISTRY, controllerList);
		}
		controllerList.add(controler);
		log.debug("Add controller {} to registry associates with context {}",
				controler, AppContext.getInstance());
	}

	public static void clearRegistries() {
		AppContext.removeVariable(CONTROLLER_REGISTRY);
	}
}
