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

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.ui.CheckBox;

@SuppressWarnings("serial")
public class CheckboxField extends CustomField {
	
	private CheckBox chk;
	
	public CheckboxField() {
		chk = new CheckBox();
		chk.setImmediate(true);
		chk.setWriteThrough(true);
		this.setCompositionRoot(chk);
	}
	
	public CheckboxField(String caption) {
		chk = new CheckBox(caption);
		chk.setImmediate(true);
		chk.setWriteThrough(true);
		this.setCompositionRoot(chk);
	}
	
	public CheckBox getCheckBox() {
		return chk;
	}

	@Override
	public Class<?> getType() {
		return null;
	}

}
