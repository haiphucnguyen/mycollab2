package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class SplitButton extends CustomComponent {

	private HorizontalLayout contentLayout;

	private Button parentButton;

	private PopupButton popupButton;

	public SplitButton() {
		contentLayout = new HorizontalLayout();
		parentButton = new Button();
		popupButton = new PopupButton();
	}

	public void addClickListener(SplitButtonClickListener listener) {

	}

	public void setPopupVisible(boolean isvisible) {

	}

	public void setContent(ComponentContainer content) {

	}

	public void addComponent(Component component) {

	}

	public static interface SplitButtonClickListener {
		public void splitButtonClick(SplitButtonClickEvent event);
	}

	public static class SplitButtonClickEvent {

	}
}
