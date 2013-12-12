package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class SplitButton extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout contentLayout;

	private Button parentButton;

	private PopupButton popupButton;

	private boolean isPopupVisible = false;

	public SplitButton() {
		this(new Button());

	}

	public SplitButton(Button parentButton) {
		contentLayout = new HorizontalLayout();
		this.parentButton = parentButton;
		this.parentButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				SplitButton.this.fireEvent(new SplitButtonClickEvent(
						SplitButton.this));
			}
		});

		popupButton = new PopupButton();
		popupButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				SplitButton.this.isPopupVisible = !SplitButton.this.isPopupVisible;
				SplitButton.this.fireEvent(new SplitButtonPopupVisibilityEvent(
						SplitButton.this, SplitButton.this.isPopupVisible));

			}
		});

		contentLayout.addComponent(this.parentButton);
		contentLayout.addComponent(popupButton);

		this.setCompositionRoot(contentLayout);
	}

	public void setHostIcon(Resource icon) {
		parentButton.setIcon(icon);
	}

	public void setPopupVisible(boolean isvisible) {
		this.isPopupVisible = isvisible;
		popupButton.setPopupVisible(isPopupVisible);
	}

	public void setContent(ComponentContainer content) {
		popupButton.setContent(content);
	}

	public void addComponent(Component component) {

	}

	private static final Method SPLIT_BUTTON_CLICK_CHANGE_METHOD;
	private static final Method SPLIT_POPUP_VISIBLE_CHANGE_METHOD;
	static {
		try {
			SPLIT_BUTTON_CLICK_CHANGE_METHOD = SplitButtonClickListener.class
					.getDeclaredMethod("splitButtonClick",
							new Class[] { SplitButtonClickEvent.class });

			SPLIT_POPUP_VISIBLE_CHANGE_METHOD = SplitButtonPopupVisibilityListener.class
					.getDeclaredMethod(
							"splitButtonPopupVisibilityChange",
							new Class[] { SplitButtonPopupVisibilityEvent.class });
		} catch (final java.lang.NoSuchMethodException e) {
			// This should never happen
			throw new java.lang.RuntimeException(
					"Internal error finding methods in TabSheet");
		}
	}

	public static interface SplitButtonClickListener extends Serializable {
		public void splitButtonClick(SplitButtonClickEvent event);
	}

	public static class SplitButtonClickEvent extends Component.Event {
		private static final long serialVersionUID = 1L;

		public SplitButtonClickEvent(Component source) {
			super(source);
		}

	}

	public void addClickListener(SplitButtonClickListener listener) {
		addListener(SplitButtonClickEvent.class, listener,
				SPLIT_BUTTON_CLICK_CHANGE_METHOD);
	}

	public void addPopupVisibilityListener(
			SplitButtonPopupVisibilityListener listener) {
		addListener(SplitButtonPopupVisibilityEvent.class, listener,
				SPLIT_POPUP_VISIBLE_CHANGE_METHOD);
	}

	public static interface SplitButtonPopupVisibilityListener extends
			Serializable {
		void splitButtonPopupVisibilityChange(
				SplitButtonPopupVisibilityEvent event);
	}

	public static class SplitButtonPopupVisibilityEvent extends Component.Event {
		private static final long serialVersionUID = 1L;

		private boolean isVisible;

		public SplitButtonPopupVisibilityEvent(Component source,
				boolean isVisible) {
			super(source);
			this.isVisible = isVisible;
		}

		public boolean isPopupVisible() {
			return this.isVisible;
		}
	}
}
