package com.esofthead.mycollab.form.view;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class DynaFormLayout implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(DynaFormLayout.class);

	private DynaForm dynaForm;

	private VerticalLayout layout;

	private Map<String, AbstractDynaField> fieldMappings = new HashMap<String, AbstractDynaField>();
	private Map<DynaSection, GridFormLayoutHelper> sectionMappings = new HashMap<DynaSection, GridFormLayoutHelper>();

	public DynaFormLayout(DynaForm dynaForm) {
		this.dynaForm = dynaForm;
	}

	@Override
	public Layout getLayout() {
		layout = new VerticalLayout();
		int sectionCount = dynaForm.getSectionCount();
		for (int i = 0; i < sectionCount; i++) {
			DynaSection section = dynaForm.getSection(i);
			Label header = new Label(section.getHeader());
			header.setStyleName("h2");
			layout.addComponent(header);

			GridFormLayoutHelper gridLayout;

			if (section.isDeletedSection() || section.getFieldCount() == 0) {
				continue;
			}

			if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
				gridLayout = new GridFormLayoutHelper(2,
						section.getFieldCount(), "100%", "167px",
						Alignment.MIDDLE_LEFT);
			} else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
				gridLayout = new GridFormLayoutHelper(2,
						(section.getFieldCount() + 1) / 2, "100%", "167px",
						Alignment.MIDDLE_LEFT);
			} else {
				throw new MyCollabException(
						"Does not support form layout except 1 or 2 columns");
			}

			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setMargin(false);
			gridLayout.getLayout().setSpacing(false);
			layout.addComponent(gridLayout.getLayout());

			sectionMappings.put(section, gridLayout);

			log.debug("Fill fields of section to map field");

			int fieldCount = section.getFieldCount();
			for (int j = 0; j < fieldCount; j++) {
				AbstractDynaField dynaField = section.getField(j);
				fieldMappings.put(dynaField.getFieldName(), dynaField);
			}
		}
		return layout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		AbstractDynaField dynaField = fieldMappings.get(propertyId);
		if (dynaField != null) {
			DynaSection section = dynaField.getOwnSection();
			GridFormLayoutHelper gridLayout = sectionMappings.get(section);

			if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
				gridLayout.addComponent(field, dynaField.getDisplayName(), 0,
						dynaField.getFieldIndex(), 2, "100%",
						Alignment.TOP_LEFT);
			} else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
				gridLayout.addComponent(field, dynaField.getDisplayName(),
						dynaField.getFieldIndex() % 2,
						dynaField.getFieldIndex() / 2);
			}

		}
	}

}
