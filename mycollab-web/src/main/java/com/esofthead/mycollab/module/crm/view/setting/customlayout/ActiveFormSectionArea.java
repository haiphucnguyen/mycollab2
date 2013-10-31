package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;

class ActiveFormSectionArea extends DDVerticalLayout {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
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
						ActiveFormSectionArea.this.replaceComponent(overComp,
								srcComp);
					}
				}
				log.debug("Target {}", event.getTargetDetails());
			}
		});
	}

}
