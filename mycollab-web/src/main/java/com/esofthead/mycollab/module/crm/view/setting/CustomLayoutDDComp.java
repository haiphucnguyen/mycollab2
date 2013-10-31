package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.vaadin.ui.HorizontalLayout;

class CustomLayoutDDComp extends HorizontalLayout {
	private static final long serialVersionUID = 1L;

	private ActiveSectionLayout activeFormLayout;
	private DeleteSectionLayout deleteFormLayout;

	private DynaForm dynaForm;

	void displayLayoutCustom(DynaForm dynaForm) {
		this.removeAllComponents();
		this.dynaForm = dynaForm;
		this.setSpacing(true);
		this.setWidth("100%");

		activeFormLayout = new ActiveSectionLayout();
		activeFormLayout.setSpacing(true);
		activeFormLayout.setWidth("100%");

		deleteFormLayout = new DeleteSectionLayout();
		deleteFormLayout.setWidth("100%");

		this.addComponent(activeFormLayout);
		this.setExpandRatio(activeFormLayout, 1.0f);

		this.addComponent(deleteFormLayout);
		this.setExpandRatio(deleteFormLayout, 1.0f);

		boolean hasDeletedSection = false;

		int sectionCount = dynaForm.getSectionCount();
		for (int i = 0; i < sectionCount; i++) {
			DynaSection section = dynaForm.getSection(i);
			if (!section.isDeletedSection()) {
				ActiveSectionLayoutComp sectionLayout = new ActiveSectionLayoutComp(
						section);
				activeFormLayout.addComponent(sectionLayout);
			} else {
				DeleteSectionLayoutComp sectionLayout = new DeleteSectionLayoutComp(
						section);
				deleteFormLayout.addComponent(sectionLayout);
				hasDeletedSection = true;
			}
		}

		if (!hasDeletedSection) {
			DynaSection deleteSection = new DynaSectionBuilder()
					.layoutType(LayoutType.ONE_COLUMN).header("Removed fields")
					.deleteSection(true).build();
			DeleteSectionLayoutComp sectionLayout = new DeleteSectionLayoutComp(
					deleteSection);
			deleteFormLayout.addComponent(sectionLayout);
		}
	}
}
