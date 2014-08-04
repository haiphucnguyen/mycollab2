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
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.utils.FieldGroupFomatter;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
class MeetingHistoryLogWindow extends HistoryLogWindow {
	private static final long serialVersionUID = 1L;

	public static final FieldGroupFomatter meetingFormatter;

	static {
		meetingFormatter = new FieldGroupFomatter();

		meetingFormatter.generateFieldDisplayHandler("subject", "Subject");
		meetingFormatter.generateFieldDisplayHandler("status", "Status");
		meetingFormatter.generateFieldDisplayHandler("type", "Type");
		meetingFormatter.generateFieldDisplayHandler("startdate", "Start Date",
				FieldGroupFomatter.DATETIME_FIELD);
		meetingFormatter.generateFieldDisplayHandler("enddate", "End Date",
				FieldGroupFomatter.DATETIME_FIELD);
		meetingFormatter.generateFieldDisplayHandler("location", "Location");
	}

	public MeetingHistoryLogWindow(String module, String type) {
		super(module, type);
	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return meetingFormatter;
	}

}
