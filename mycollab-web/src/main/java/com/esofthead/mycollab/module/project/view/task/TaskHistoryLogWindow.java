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
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class TaskHistoryLogWindow extends HistoryLogWindow {
	public TaskHistoryLogWindow(String module, String type, int typeid) {
		super(module, type, typeid);

		this.generateFieldDisplayHandler("taskname", "Task Name");
		this.generateFieldDisplayHandler("startdate", "Start Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("enddate", "End Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("actualstartdate",
				"Actual Start Date", HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("actualenddate", "Actual End Date",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("deadline", "Deadline",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("isestimated", "Is Estimated");
		this.generateFieldDisplayHandler("assignuser", LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD));
		this.generateFieldDisplayHandler("tasklistid", LocalizationHelper
				.getMessage(TaskI18nEnum.FORM_TASKGROUP_FIELD));
		this.generateFieldDisplayHandler("percentagecomplete", "Complete(%)");
		this.generateFieldDisplayHandler("notes", "Notes");
	}
}
