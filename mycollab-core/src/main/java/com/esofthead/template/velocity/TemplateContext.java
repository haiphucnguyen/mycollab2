/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.template.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;
import org.apache.velocity.tools.generic.DateTool;

/**
 * Template wrapper of velocity context
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class TemplateContext {
	private final VelocityContext velocityContext;

	private static ToolManager toolManager;

	static {
		EasyFactoryConfiguration config = new EasyFactoryConfiguration();
		config.toolbox(Scope.APPLICATION).tool(DateTool.class);

		toolManager = new ToolManager();
		toolManager.configure(config);
	}

	public TemplateContext() {
		velocityContext = new VelocityContext(toolManager.createContext());
	}

	public void put(String key, Object value) {
		velocityContext.put(key, value);
	}

	public VelocityContext getVelocityContext() {
		return velocityContext;
	}
}
