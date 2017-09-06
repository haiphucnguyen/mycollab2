package com.mycollab.pro.module.crm.view.setting.customlayout;

import java.util.ArrayList;
import java.util.List;

import com.mycollab.form.view.builder.type.AbstractDynaField;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.vaadin.web.ui.WebThemes;
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
		this.addStyleName(WebThemes.CUSTOM_FIELD_SECTION);
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
				return !((CustomFieldComponent) component).isEmptyField();
			}

			return false;
		}
	}
}
