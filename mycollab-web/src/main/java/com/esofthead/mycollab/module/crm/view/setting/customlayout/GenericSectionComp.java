package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.crm.ui.components.CustomFieldComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.interfaces.DragFilter;

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
