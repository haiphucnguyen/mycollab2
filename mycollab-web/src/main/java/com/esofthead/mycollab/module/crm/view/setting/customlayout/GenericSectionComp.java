package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.ui.components.CustomFieldComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.DDGridLayout.GridLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.HorizontalLocationIs;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import fi.jasoft.dragdroplayouts.interfaces.DragFilter;

public class GenericSectionComp extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(GenericSectionComp.class);

	protected DynaSection section;

	public GenericSectionComp(DynaSection section) {
		this.section = section;
		this.addStyleName(UIConstants.CUSTOM_FIELD_SECTION);

		
	}

	public DynaSection getSection() {
		return section;
	}

	protected void removeEmptyRow(DDGridLayout srcGridLayout, int srcRow) {
		// Check if there is any remaining item in this row
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
