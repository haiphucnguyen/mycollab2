package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.vaadin.ui.HorizontalLayout;

public class CustomLayoutDDComp extends HorizontalLayout {
	private static final long serialVersionUID = 1L;

	private ActiveFormSectionArea activeFormLayout;
	private DeleteFormSectionArea deleteFormLayout;

	public void displayLayoutCustom(DynaForm dynaForm) {
		this.removeAllComponents();
		this.setSpacing(true);
		this.setWidth("100%");

		activeFormLayout = new ActiveFormSectionArea();
		activeFormLayout.setSpacing(true);
		activeFormLayout.setWidth("100%");

		deleteFormLayout = new DeleteFormSectionArea();
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
				ActiveSectionComp sectionLayout = new ActiveSectionComp(section);
				activeFormLayout.addComponent(sectionLayout);
			} else {
				DeleteSectionComp sectionLayout = new DeleteSectionComp(section);
				deleteFormLayout.addComponent(sectionLayout);
				hasDeletedSection = true;
			}
		}

		if (!hasDeletedSection) {
			DynaSection deleteSection = new DynaSectionBuilder()
					.layoutType(LayoutType.ONE_COLUMN).header("Removed fields")
					.deleteSection(true).build();
			DeleteSectionComp sectionLayout = new DeleteSectionComp(
					deleteSection);
			deleteFormLayout.addComponent(sectionLayout);
		}
	}

	public DynaForm rebuildForm() {
		DynaForm form = new DynaForm();
		return form;
	}
}
