package com.esofthead.template.velocity;

import org.apache.velocity.VelocityContext;

public class TemplateContext {
	private final VelocityContext velocityContext;

	public TemplateContext() {
		velocityContext = new VelocityContext(TemplateEngine.createContext());
	}

	public void put(String key, Object value) {
		velocityContext.put(key, value);
	}

	VelocityContext getVelocityContext() {
		return velocityContext;
	}
}
