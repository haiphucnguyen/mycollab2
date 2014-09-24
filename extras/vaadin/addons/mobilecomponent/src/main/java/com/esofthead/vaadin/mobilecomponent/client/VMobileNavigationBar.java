package com.esofthead.vaadin.mobilecomponent.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationBar;

public class VMobileNavigationBar extends VNavigationBar {

	private static Logger log = Logger.getLogger(VMobileNavigationBar.class
			.getName());

	private Widget leftComponent;
	private Widget rightComponent;

	@Override
	public void avoidCaptionOverlap() {
		DivElement caption = (DivElement) getElement().getFirstChildElement();
		DivElement rightElement = (DivElement) caption.getNextSiblingElement();
		DivElement leftElement = (DivElement) rightElement
				.getNextSiblingElement();

		int leftSize = leftComponent != null ? leftElement.getOffsetLeft()
				+ leftElement.getOffsetWidth() : 0;
		log.log(Level.INFO, "leftSize: " + leftSize);

		int rightSize = rightComponent != null ? getOffsetWidth()
				- rightElement.getOffsetLeft() : 0;
		log.log(Level.INFO, "rightSize: " + rightSize);

		int sideOffset = (leftSize > rightSize) ? leftSize : rightSize;
		log.log(Level.INFO, "side offset: " + sideOffset);

		log.log(Level.INFO, "offset width: " + getOffsetWidth());
		caption.getStyle().setWidth((getOffsetWidth() - (sideOffset * 2)),
				Unit.PX);
		caption.getStyle().setLeft(sideOffset, Unit.PX);
		caption.getStyle().setPosition(Position.ABSOLUTE);
	}

	@Override
	public void setLeftWidget(Widget left) {
		if (getWidgetCount() == 0) {
			super.setLeftWidget(left);
			leftComponent = left;
		} else {
			if (left != null) {
				if (leftComponent != left && leftComponent != null) {
					remove(leftComponent);
				}

				leftComponent = left;
				if (!leftComponent.isAttached()) {
					super.setLeftWidget(left);
				}
			} else if (leftComponent != null) {
				remove(leftComponent);
			}
		}

	}

	@Override
	public void setRightWidget(Widget right) {
		if (getWidgetCount() == 1) {
			super.setRightWidget(right);
			rightComponent = right;
		} else {
			if (right != null) {
				if (rightComponent != right && rightComponent != null) {
					remove(rightComponent);
				}

				rightComponent = right;
				if (!rightComponent.isAttached()) {
					super.setRightWidget(right);
				}
			} else if (rightComponent != null) {
				remove(rightComponent);
			}
		}
	}
}
