package com.mycollab.pro.module.crm.view.setting.customlayout;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycollab.form.view.builder.type.DynaSection;
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

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
class ActiveFormSectionArea extends DDVerticalLayout {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ActiveFormSectionArea.class);

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

	public DynaSection[] rebuildSections() {
		List<DynaSection> sections = new ArrayList<>();

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
		return sections.toArray(new DynaSection[0]);
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
