/**
 * This file is part of mycollab-dao.
 *
 * mycollab-dao is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-dao is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-dao.  If not, see <http://www.gnu.org/licenses/>.
 */
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
package com.esofthead.mycollab.core.arguments;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RangeDateSearchField extends RangeDateTimeSearchField {

	public RangeDateSearchField() {
		this(null, null);
	}

	public RangeDateSearchField(Date from, Date to) {
		super(DateTimeUtils.trimHMSOfDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(from.getTime())),
				DateTimeUtils.trimHMSOfDate(DateTimeUtils
						.convertTimeFromSystemTimezoneToUTC(to.getTime())));
	}

	public RangeDateSearchField(String oper, Date from, Date to) {
		super(oper, DateTimeUtils.trimHMSOfDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(from.getTime())),
				DateTimeUtils.trimHMSOfDate(DateTimeUtils
						.convertTimeFromSystemTimezoneToUTC(to.getTime())));
	}
}
