package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.hene.splitbutton.SplitButton;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class PopupButtonControl extends SplitButton {
	private static final long serialVersionUID = 1L;

	private VerticalLayout selectContent;

	private Set<PopupButtonControlListener> listeners;

	public PopupButtonControl(final String id, final String defaultName) {
		super();
		this.addStyleName(SplitButton.STYLE_CHAMELEON);
		this.setCaption(defaultName);
		this.setData(id);

		this.addClickListener(new SplitButtonClickListener() {
			public void splitButtonClick(SplitButtonClickEvent event) {
				changeOption(id, defaultName);
			}
		});

		selectContent = new VerticalLayout();
		selectContent.setWidth("100px");
		this.setComponent(selectContent);
	}

	public void addOptionItem(final String id, final String name) {
		ButtonLink selectAllBtn = new ButtonLink(name,
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						changeOption(id, name);
					}
				});
		selectContent.addComponent(selectAllBtn);
	}

	public void addListener(PopupButtonControlListener listener) {
		if (listeners == null) {
			listeners = new HashSet<PopupButtonControl.PopupButtonControlListener>();
		}
		listeners.add(listener);
	}

	private void changeOption(String id, String caption) {
		if (listeners != null) {
			for (PopupButtonControlListener listener : listeners) {
				listener.onSelect(id, caption);
			}
		}
	}

	public static interface PopupButtonControlListener {
		void onSelect(String id, String caption);
	}
}
