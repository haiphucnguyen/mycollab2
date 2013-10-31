package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Component;

import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;

public class DeleteFormSectionArea extends DDVerticalLayout {
	private static final long serialVersionUID = 1L;

	public DeleteFormSectionArea() {
		this.setComponentVerticalDropRatio(0.3f);
		this.setDragMode(LayoutDragMode.CLONE);
		this.addStyleName("deleteSection");

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
				}
			}
		});
	}
}
