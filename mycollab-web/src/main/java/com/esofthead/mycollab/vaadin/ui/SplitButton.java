package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class SplitButton extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout contentLayout;

	private Button parentButton;

	private PopupButton popupButton;

	public SplitButton() {
		this(new Button());

	}

	public SplitButton(Button parentButton) {
		contentLayout = new HorizontalLayout();
		this.parentButton = parentButton;
		popupButton = new PopupButton();

		contentLayout.addComponent(parentButton);
		contentLayout.addComponent(popupButton);

		this.setCompositionRoot(contentLayout);
	}

	public void addClickListener(SplitButtonClickListener listener) {

	}

	public void addPopupVisibilityListener(
			SplitButtonPopupVisibilityListener listener) {

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

	public static interface SplitButtonPopupVisibilityListener {
		void splitButtonPopupVisibilityChange(
				SplitButtonPopupVisibilityEvent event);
	}

	public static class SplitButtonPopupVisibilityEvent {
		public boolean isPopupVisible() {
			return true;
		}
	}
}
