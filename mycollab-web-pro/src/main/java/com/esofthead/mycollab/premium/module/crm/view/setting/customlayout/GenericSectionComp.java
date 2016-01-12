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
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.interfaces.DragFilter;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class GenericSectionComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected DynaSection originSection;

	protected DDGridLayout dragLayout;

	public GenericSectionComp(DynaSection section) {
		this.originSection = section;
		this.addStyleName(UIConstants.CUSTOM_FIELD_SECTION);
	}

	protected void removeEmptyRow(DDGridLayout srcGridLayout, int srcRow) {
		// Check if there is any remaining item in the source gridLayout
		// if not, remove row
		int remainItem = 0;
		for (int i = srcGridLayout.getColumns(); i >= 0; i--) {
			if (srcGridLayout.getComponent(i, srcRow) != null)
				remainItem++;
		}
		if (remainItem == 0) {
			srcGridLayout.removeRow(srcRow);
		}
	}

	public DynaSection getOriginSection() {
		return originSection;
	}

	protected List<AbstractDynaField> getFields() {
		List<AbstractDynaField> fields = new ArrayList<AbstractDynaField>();
		for (int col = 0; col < dragLayout.getColumns(); col++) {
			for (int row = 0; row < dragLayout.getRows(); row++) {
				CustomFieldComponent component = (CustomFieldComponent) dragLayout
						.getComponent(col, row);

				if (component != null && !component.isEmptyField()) {
					AbstractDynaField field = component.getField();
					fields.add(field);
				}
			}
		}
		return fields;
	}

	static class CustomFieldDragFilter implements DragFilter {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isDraggable(Component component) {
			if (component instanceof CustomFieldComponent) {
				if (((CustomFieldComponent) component).isEmptyField())
					return false;
				return true;
			}

			return false;
		}
	}
}
