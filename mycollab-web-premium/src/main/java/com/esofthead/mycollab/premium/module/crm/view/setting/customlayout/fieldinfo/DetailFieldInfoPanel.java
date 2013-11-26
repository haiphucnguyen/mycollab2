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
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.fieldinfo;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.ui.VerticalLayout;

public abstract class DetailFieldInfoPanel<F extends AbstractDynaField> extends
		VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected List<DynaSection> activeSections;
	protected String candidateFieldName;
	protected F field;

	public DetailFieldInfoPanel(String candidateFieldName,
			List<DynaSection> activeSections) {
		this.activeSections = activeSections;
		this.candidateFieldName = candidateFieldName;
	}

	public abstract DynaSection updateCustomField();
}
