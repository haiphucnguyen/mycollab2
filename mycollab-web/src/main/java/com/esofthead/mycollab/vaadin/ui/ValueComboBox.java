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

import com.vaadin.ui.ComboBox;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@SuppressWarnings("serial")
public class ValueComboBox extends ComboBox {

	public ValueComboBox() {
		super();
	}

	/**
	 * 
	 * @param nullIsAllowable
	 * @param values
	 */
	public ValueComboBox(boolean nullIsAllowable, String... values) {
		super();
		this.setNullSelectionAllowed(nullIsAllowable);
		this.setImmediate(true);
		this.loadData(values);

		this.select(this.getItemIds().iterator().next());
	}

	public ValueComboBox(boolean nullIsAllowable, Number... values) {
		super();
		this.setNullSelectionAllowed(nullIsAllowable);
		this.setImmediate(true);
		this.loadData(values);

		this.select(this.getItemIds().iterator().next());
	}

	public final void loadData(String... values) {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		for (int i = 0; i < values.length; i++) {
			this.addItem(values[i]);
		}

		this.select(this.getItemIds().iterator().next());
	}

	public final void loadData(Number... values) {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		for (int i = 0; i < values.length; i++) {
			this.addItem(values[i]);
		}
	}
}
