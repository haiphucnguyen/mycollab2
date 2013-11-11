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
package com.esofthead.mycollab.module.crm.view.setting.customlayout.fieldinfo;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.ui.ComboBox;

public class SectionSelectList extends ComboBox {
	private static final long serialVersionUID = 1L;

	public SectionSelectList(List<DynaSection> sections) {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);

		for (DynaSection section : sections) {
			this.addItem(section);
			this.setItemCaption(section, section.getHeader());
		}

		if (sections.size() > 0) {
			this.select(sections.get(0));
		}
	}
}