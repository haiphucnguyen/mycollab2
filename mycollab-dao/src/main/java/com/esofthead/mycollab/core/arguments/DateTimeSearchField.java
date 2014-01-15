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
package com.esofthead.mycollab.core.arguments;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class DateTimeSearchField extends SearchField {

	public static String LESSTHAN = "<";
	public static String LESSTHANEQUAL = "<=";
	public static String GREATERTHAN = ">";
	public static String GREATERTHANEQUAL = ">=";
	public static String EQUAL = "=";
	public static String NOTEQUAL = "<>";

	protected Date value;
	protected String comparision;

	public DateTimeSearchField() {
		this(AND, null);
	}

	public DateTimeSearchField(String oper, Date value) {
		this(oper, DateTimeSearchField.LESSTHAN, value);
	}

	public DateTimeSearchField(String oper, String comparision, Date value) {
		this.operation = oper;
		this.value = DateTimeUtils.convertTimeFromSystemTimezoneToUTC(value
				.getTime());
		this.comparision = comparision;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getComparision() {
		return comparision;
	}

	public void setComparision(String comparision) {
		this.comparision = comparision;
	}

}
