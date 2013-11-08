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
package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.vaadin.ui.HorizontalLayout;

public class CustomLayoutDDComp extends HorizontalLayout {
	private static final long serialVersionUID = 1L;

	private ActiveFormSectionArea activeFormArea;
	private DeleteFormSectionArea deleteFormArea;

	public void displayLayoutCustom(DynaForm dynaForm) {
		this.removeAllComponents();

		this.setSpacing(true);
		this.setWidth("100%");

		activeFormArea = new ActiveFormSectionArea();
		activeFormArea.setSpacing(true);
		activeFormArea.setWidth("100%");

		deleteFormArea = new DeleteFormSectionArea();
		deleteFormArea.setWidth("100%");

		this.addComponent(activeFormArea);
		this.setExpandRatio(activeFormArea, 1.0f);

		this.addComponent(deleteFormArea);
		this.setExpandRatio(deleteFormArea, 1.0f);

		boolean hasDeletedSection = false;

		int sectionCount = dynaForm.getSectionCount();
		for (int i = 0; i < sectionCount; i++) {
			DynaSection section = dynaForm.getSection(i);
			if (!section.isDeletedSection()) {
				ActiveSectionComp sectionLayout = new ActiveSectionComp(section);
				activeFormArea.addComponent(sectionLayout);
			} else {
				DeleteSectionComp sectionLayout = new DeleteSectionComp(section);
				deleteFormArea.addComponent(sectionLayout);
				hasDeletedSection = true;
			}
		}

		if (!hasDeletedSection) {
			DynaSection deleteSection = new DynaSectionBuilder()
					.layoutType(LayoutType.ONE_COLUMN).header("Removed fields")
					.deleteSection(true).build();
			DeleteSectionComp sectionLayout = new DeleteSectionComp(
					deleteSection);
			deleteFormArea.addComponent(sectionLayout);
		}
	}

	public void addActiveSection(DynaSection section) {
		ActiveSectionComp sectionLayout = new ActiveSectionComp(section);
		activeFormArea.addComponent(sectionLayout);
	}

	public List<DynaSection> getActiveSections() {
		return activeFormArea.rebuildSections();
	}

	public DynaForm rebuildForm() {
		DynaForm form = new DynaForm();
		List<DynaSection> sections = activeFormArea.rebuildSections();
		form.addSections(sections);

		form.addSection(deleteFormArea.rebuildSection());
		return form;
	}
}
