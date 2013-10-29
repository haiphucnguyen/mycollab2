package com.esofthead.mycollab.module.crm.view.setting;

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

class ActiveSectionLayout extends DDVerticalLayout {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ActiveSectionLayout.class);

	public ActiveSectionLayout() {
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
				if (srcComp instanceof ActiveSectionLayoutComp) {
					ActiveSectionLayoutComp overComp = (ActiveSectionLayoutComp) details
							.getOverComponent();
					int srcIndex = ActiveSectionLayout.this
							.getComponentIndex(srcComp);
					int destIndex = ActiveSectionLayout.this
							.getComponentIndex(overComp);

					if (srcIndex != destIndex) {
						ActiveSectionLayout.this.replaceComponent(overComp,
								srcComp);
					}
				}
				log.debug("Target {}", event.getTargetDetails());
			}
		});
	}

}
