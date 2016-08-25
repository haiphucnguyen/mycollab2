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
package com.mycollab.pro.module.crm.view.setting.customlayout;

import java.util.ArrayList;
import java.util.List;

import com.mycollab.form.view.builder.DynaSectionBuilder;
import com.mycollab.form.view.builder.type.AbstractDynaField;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.mycollab.form.view.builder.type.TextDynaField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 3.0
 */
public class CustomLayoutDDComp extends MHorizontalLayout {
	private static final long serialVersionUID = 1L;

	private ActiveFormSectionArea activeFormArea;
	private DeleteFormSectionArea deleteFormArea;

	public void displayLayoutCustom(DynaForm dynaForm) {
		this.removeAllComponents();
		this.setWidth("100%");

		activeFormArea = new ActiveFormSectionArea();
		activeFormArea.setSpacing(true);
		activeFormArea.setWidth("100%");

		deleteFormArea = new DeleteFormSectionArea();
		deleteFormArea.setWidth("450px");

		with(activeFormArea, deleteFormArea).expand(activeFormArea);

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
//			DynaSection deleteSection = new DynaSectionBuilder()
//					.layoutType(LayoutType.ONE_COLUMN).header("Removed fields")
//					.deleteSection(true).build();
//			DeleteSectionComp sectionLayout = new DeleteSectionComp(
//					deleteSection);
//			deleteFormArea.addComponent(sectionLayout);
		}
	}

	public void addActiveSection(DynaSection section) {
		ActiveSectionComp sectionLayout = new ActiveSectionComp(section);
		activeFormArea.addComponent(sectionLayout);
	}

	public DynaSection[] getActiveSections() {
		return activeFormArea.rebuildSections();
	}

	public DynaForm rebuildForm() {
		DynaForm form = new DynaForm();
		DynaSection[] sections = activeFormArea.rebuildSections();
		form.sections(sections);

		form.sections(deleteFormArea.rebuildSection());
		return form;
	}

	public String getCandidateTextFieldName() {
		return getCandidateAvailableSlotField(TextDynaField.class,
				AbstractDynaField.TEXT_FIELD_ARR);
	}

	private String getCandidateAvailableSlotField(Class fieldCls,
			String[] fieldNames) {
		List<String> fieldNamesList = new ArrayList<>();
		for (String fieldname : fieldNames) {
			fieldNamesList.add(fieldname);
		}

		DynaForm form = rebuildForm();
		int sectionCount = form.getSectionCount();
		for (int i = 0; i < sectionCount; i++) {
			DynaSection section = form.getSection(i);
			int fieldCount = section.getFieldCount();
			for (int j = 0; j < fieldCount; j++) {
				AbstractDynaField field = section.getField(j);
				if (field.getClass() == fieldCls
						&& fieldNamesList.contains(field.getFieldName())) {
					fieldNamesList.remove(field.getFieldName());
					if (fieldNamesList.size() == 0) {
						return null;
					}
				}
			}
		}

		if (fieldNamesList.size() > 0) {
			return fieldNamesList.get(0);
		}
		return null;
	}

	public void refreshSectionLayout(DynaSection section) {
		activeFormArea.refreshSectionLayout(section);
	}
}
