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

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
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
		this.setImmediate(true);
		contentLayout = new HorizontalLayout();
		contentLayout.setStyleName("splitbutton");
		this.parentButton = parentButton;
		this.parentButton.addStyleName("parent-button");
		this.parentButton.setImmediate(true);
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

	@Override
	public void setCaption(String caption) {
		parentButton.setCaption(caption);
	}

	@Override
	public void setIcon(Resource icon) {
		parentButton.setIcon(icon);
	}

	public void setPopupVisible(boolean isvisible) {
		this.isPopupVisible = isvisible;
		popupButton.setPopupVisible(isPopupVisible);
	}

	public void setContent(ComponentContainer content) {
		popupButton.setContent(content);
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

	@Override
	public void addStyleName(String stylename) {
		super.addStyleName(stylename);

		parentButton.addStyleName(stylename);
		popupButton.addStyleName(stylename);
	}
	
	public void setWidthPopupButton(String width)
	{
		popupButton.setWidth(width);
	}

}
