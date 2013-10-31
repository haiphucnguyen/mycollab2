package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.crm.ui.components.CustomFieldComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.interfaces.DragFilter;

public class GenericSectionComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected DynaSection section;

	protected DDGridLayout dragLayout;

	public GenericSectionComp(DynaSection section) {
		this.section = section;
		this.addStyleName(UIConstants.CUSTOM_FIELD_SECTION);

	}

	public DynaSection getSection() {
		return section;
	}

	protected void removeEmptyRow(int srcRow) {
		// Check if there is any remaining item in this row
		// if not, remove row
		int remainItem = 0;
		for (int i = dragLayout.getColumns(); i >= 0; i--) {
			if (dragLayout.getComponent(i, srcRow) != null)
				remainItem++;
		}
		if (remainItem == 0) {
			dragLayout.removeRow(srcRow);
		}
	}

	protected class CustomFieldDragFilter implements DragFilter {

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
