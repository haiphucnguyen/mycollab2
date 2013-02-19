package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.web.AppContext;

public class ControllerRegistry {
	private static final String CONTROLLER_REGISTRY = "CONTROLLER_REGISTRY";

	private static ControllerRegistry instance = new ControllerRegistry();
	
	public static ControllerRegistry getInstance() {
		return instance;
	}
	
	public void addController(IController controler) {
		List<IController> controllerList = ((List<IController>) AppContext
				.getVariable(CONTROLLER_REGISTRY));
		if (controllerList == null) {
			controllerList = new ArrayList<IController>();
			AppContext.putVariable(CONTROLLER_REGISTRY, controllerList);
		}
		controllerList.add(controler);
	}
	
	public void clearRegistries() {
		AppContext.removeVariable(CONTROLLER_REGISTRY);
	}
}
