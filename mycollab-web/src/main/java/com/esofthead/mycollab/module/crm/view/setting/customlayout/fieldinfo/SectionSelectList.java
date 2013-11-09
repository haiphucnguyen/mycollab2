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