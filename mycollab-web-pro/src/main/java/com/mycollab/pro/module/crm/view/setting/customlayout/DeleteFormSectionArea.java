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

import com.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class DeleteFormSectionArea extends DDVerticalLayout {
	private static final long serialVersionUID = 1L;

	public DeleteFormSectionArea() {
		this.setComponentVerticalDropRatio(0.3f);
		this.setDragMode(LayoutDragMode.CLONE);

		this.setDropHandler(new DropHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}

			@Override
			public void drop(DragAndDropEvent event) {
				VerticalLayoutTargetDetails details = (VerticalLayoutTargetDetails) event
						.getTargetDetails();
				LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
						.getTransferable();
				Component srcComp = transferable.getComponent();
				if (srcComp instanceof ActiveSectionComp) {
					ActiveFormSectionArea parentSection = (ActiveFormSectionArea) srcComp
							.getParent();
					parentSection.removeComponent(srcComp);
					parentSection.getState();
				}
			}
		});
	}

	public DynaSection rebuildSection() {
		DynaSection section = null;

		int componentCount = this.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component comp = this.getComponent(i);
			if (comp instanceof DeleteSectionComp) {
				DeleteSectionComp sectionComp = (DeleteSectionComp) comp;
				section = sectionComp.rebuildSection();
				break;
			}
		}

		if (section == null) {
			section = new DynaSection();
			section.setDeletedSection(true);
		}
		return section;
	}
}
