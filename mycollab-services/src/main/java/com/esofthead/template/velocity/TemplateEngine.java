package com.esofthead.template.velocity;

import java.io.Reader;
import java.io.Writer;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;
import org.apache.velocity.tools.generic.DateTool;

public class TemplateEngine {
	
	private static ToolManager toolManager;

	private static VelocityEngine voEngine;

	static {
		EasyFactoryConfiguration config = new EasyFactoryConfiguration();
		config.toolbox(Scope.APPLICATION).tool(DateTool.class);

		toolManager = new ToolManager();
		toolManager.configure(config);

		voEngine = new VelocityEngine();
		voEngine.init();
	}

	public static ToolContext createContext() {
		return toolManager.createContext();
	}
	

	public static void evaluate(TemplateContext context, Writer writer,
			String message, Reader reader) {
		Velocity.evaluate(context.getVelocityContext(), writer, "log", reader);
	}
}
