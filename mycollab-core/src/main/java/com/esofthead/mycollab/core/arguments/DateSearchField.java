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

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import java.util.Date;

public class DateSearchField extends DateTimeSearchField {

	public DateSearchField() {
		this(AND, null, null);
	}

	public DateSearchField(String oper, Date value) {
		super(oper, DateTimeUtils.convertDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(value.getTime())));
	}

	public DateSearchField(String oper, String comparision, Date value) {
		super(oper, comparision, DateTimeUtils.convertDate(DateTimeUtils
				.convertTimeFromSystemTimezoneToUTC(value.getTime())));
	}
}
