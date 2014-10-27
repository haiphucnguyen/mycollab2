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
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;

class ActiveFormSectionArea extends DDVerticalLayout {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(ActiveFormSectionArea.class);

	public ActiveFormSectionArea() {
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

					ActiveSectionComp overComp = (ActiveSectionComp) details
							.getOverComponent();
					int srcIndex = ActiveFormSectionArea.this
							.getComponentIndex(srcComp);
					int destIndex = ActiveFormSectionArea.this
							.getComponentIndex(overComp);

					if (srcIndex != destIndex) {
						ActiveFormSectionArea.this.replaceComponent(srcComp,
								overComp);
						ActiveFormSectionArea.this.getState();
					}
				}
				LOG.debug("Target {}", event.getTargetDetails());
			}
		});
	}

	public List<DynaSection> rebuildSections() {
		List<DynaSection> sections = new ArrayList<DynaSection>();

		int componentCount = this.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = this.getComponent(i);
			if (component instanceof ActiveSectionComp) {
				ActiveSectionComp sectionComp = (ActiveSectionComp) component;
				DynaSection section = sectionComp.rebuildSection();
				section.setOrderIndex(sections.size());
				sections.add(section);
			}
		}
		return sections;
	}

	@Override
	public void removeComponent(Component c) {
		super.removeComponent(c);

		if (this.getComponentCount() == 0) {
			this.addComponent(new Label("&nbsp;", ContentMode.HTML));
		}
	}

	public void refreshSectionLayout(DynaSection section) {
		int componentCount = this.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = this.getComponent(i);
			if (component instanceof ActiveSectionComp) {
				ActiveSectionComp sectionComp = (ActiveSectionComp) component;
				DynaSection originSection = sectionComp.getOriginSection();
				if (originSection.getHeader().equals(section.getHeader())) {
					sectionComp.constructLayout();
				}
			}
		}
	}

}
