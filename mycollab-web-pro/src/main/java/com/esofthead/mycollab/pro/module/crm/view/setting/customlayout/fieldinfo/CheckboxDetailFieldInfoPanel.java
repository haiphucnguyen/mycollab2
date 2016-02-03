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
package com.esofthead.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.BooleanDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class CheckboxDetailFieldInfoPanel extends
		DetailFieldInfoPanel<BooleanDynaField> {
	private static final long serialVersionUID = 1L;

	public CheckboxDetailFieldInfoPanel(String candidateFieldName,
			List<DynaSection> activeSections) {
		super(candidateFieldName, activeSections);
	}

	@Override
	public DynaSection updateCustomField() {
		return null;
	}

}
