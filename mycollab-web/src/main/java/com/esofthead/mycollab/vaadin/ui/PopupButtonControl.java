package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class PopupButtonControl extends SplitButton implements HasPopupActionHandlers{
	private static final long serialVersionUID = 1L;

	private VerticalLayout selectContent;

	private Set<PopupActionHandler> handlers;

	public PopupButtonControl(final String id, final String defaultName) {
		super();
		this.addStyleName(SplitButton.STYLE_CHAMELEON);
		this.setCaption(defaultName);
		this.setData(id);

		this.addClickListener(new SplitButtonClickListener() {
			private static final long serialVersionUID = 1L;

			public void splitButtonClick(SplitButtonClickEvent event) {
				changeOption(id, defaultName);
				PopupButtonControl.this.setPopupVisible(false);
			}
		});

		selectContent = new VerticalLayout();
		selectContent.setWidth("100px");
		this.setComponent(selectContent);
	}

	public void addOptionItem(final String id, final String name) {
		ButtonLink selectAllBtn = new ButtonLink(name,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						changeOption(id, name);
						PopupButtonControl.this.setPopupVisible(false);
					}
				});
		selectContent.addComponent(selectAllBtn);
	}

	private void changeOption(String id, String caption) {
		if (handlers != null) {
			for (PopupActionHandler handler : handlers) {
				handler.onSelect(id, caption);
			}
		}
	}

	@Override
	public void addPopupActionHandler(PopupActionHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<PopupActionHandler>();
		}
		handlers.add(handler);
		
	}
}
