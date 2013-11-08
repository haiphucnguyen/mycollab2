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
package com.esofthead.mycollab.vaadin.ui;

import java.util.Calendar;
import java.util.Date;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

public class DateRangeField extends CustomField {

	private static final long serialVersionUID = 1L;
	
	private DateField dateStart = new DateField();
	private DateField dateEnd = new DateField();
	

	private ComponentContainer container;
	
	public DateRangeField(ComponentContainer container, float widthDateField) {
		this.container = container;
		
		if (container instanceof HorizontalLayout) {
			((HorizontalLayout) container).setSpacing(true);
		} else if (container instanceof VerticalLayout) {
			((VerticalLayout) container).setSpacing(true);
		}
		
		dateStart.setCaption("From: ");
		dateEnd.setCaption("To: ");
		
		container.addComponent(dateStart);
		container.addComponent(dateEnd);
		
		setDateWidth(widthDateField);
		setDefaultValue();
		
		this.setCompositionRoot(container);
	}
	
	
	public DateRangeField(float widthDateField) {
		this(new HorizontalLayout(), widthDateField);
	}
	
	public DateRangeField() {
		this(new HorizontalLayout(), 250);
	}
	
	public RangeDateSearchField getRangeSearchValue() {
		Date fDate = (Date) dateStart.getValue();
		Date tDate = (Date) dateEnd.getValue();
		
		if (fDate == null || tDate == null) return null;
		
		return new RangeDateSearchField(fDate, tDate);
	}
	
	public void setDateFormat(String dateFormat) {
    	dateStart.setDateFormat(dateFormat);
    	dateEnd.setDateFormat(dateFormat);
    }
	
	public void setDefaultValue() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		Date fDate = c.getTime();
		Date tDate = DateTimeUtils.subtractOrAddDayDuration(fDate, 7);
		
		dateStart.setValue(fDate);
		dateEnd.setValue(tDate);
	}
	
	private void setDateWidth (float width) {
		dateStart.setWidth(width, Sizeable.UNITS_PIXELS);
		dateEnd.setWidth(width, Sizeable.UNITS_PIXELS);
		dateStart.setResolution(PopupDateField.RESOLUTION_DAY);
		dateEnd.setResolution(PopupDateField.RESOLUTION_DAY);
	}

	@Override
	public Class<?> getType() {
		return null;
	}
	
}
