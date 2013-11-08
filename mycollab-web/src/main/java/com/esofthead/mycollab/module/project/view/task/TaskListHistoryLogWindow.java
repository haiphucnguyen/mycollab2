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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class TaskListHistoryLogWindow extends HistoryLogWindow {
	public TaskListHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("name", "Name");
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("owner", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("milestoneid", "Related Milestone");
		this.generateFieldDisplayHandler("percentageComplete", "Progress");
		this.generateFieldDisplayHandler("numOpenTasks", "Number of open tasks");
	}
}
