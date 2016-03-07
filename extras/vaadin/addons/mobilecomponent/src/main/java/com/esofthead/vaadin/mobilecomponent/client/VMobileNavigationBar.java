package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationBar;

public class VMobileNavigationBar extends VNavigationBar {

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

		int rightSize = rightComponent != null ? getOffsetWidth()
				- rightElement.getOffsetLeft() : 0;

		int sideOffset = (leftSize > rightSize) ? leftSize : rightSize;

		caption.getStyle().setWidth(100, Unit.PCT);
		caption.getStyle().setPaddingLeft(sideOffset, Unit.PX);
		caption.getStyle().setPaddingRight(sideOffset, Unit.PX);
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
