/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

@SuppressWarnings("serial")
public class ComponentHistoryLogWindow extends HistoryLogWindow {

	public ComponentHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);
		
		this.generateFieldDisplayHandler("componentname", "Component Name");
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("userlead", "Lead");
	}

}
