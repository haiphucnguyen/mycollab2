package com.esofthead.mycollab.form.view.builder;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

public class SectionBuilder {

	private DynaSection section;

	public SectionBuilder() {
		section = new DynaSection();
	}

	public SectionBuilder header(String header) {
		section.setHeader(header);
		return this;
	}

	public SectionBuilder layoutType(LayoutType layoutType) {
		section.setLayoutType(layoutType);
		return this;
	}

	public SectionBuilder orderIndex(int orderIndex) {
		section.setOrderIndex(orderIndex);
		return this;
	}

	public SectionBuilder fields(AbstractFieldBuilder... fields) {
		for (AbstractFieldBuilder field : fields) {
			section.addField(field.build());
		}
		return this;
	}

	public DynaSection build() {
		return section;
	}
}
