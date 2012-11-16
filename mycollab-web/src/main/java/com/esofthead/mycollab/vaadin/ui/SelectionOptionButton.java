package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.hene.splitbutton.SplitButton;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class SelectionOptionButton extends SplitButton {
	private static final long serialVersionUID = 1L;

	private boolean isSelected = false;

	private static Resource selectIcon = new ThemeResource(
			"icons/16/checkbox.png");

	private static Resource unSelectIcon = new ThemeResource(
			"icons/16/checkbox_empty.png");

	private Set<SelectionOptionListener> listeners;

	@SuppressWarnings("serial")
	public SelectionOptionButton() {
		super();
		this.addStyleName(SplitButton.STYLE_CHAMELEON);
		this.setIcon(unSelectIcon);

		this.addClickListener(new SplitButtonClickListener() {
			public void splitButtonClick(SplitButtonClickEvent event) {
				isSelected = !isSelected;
				changeOption();
			}
		});

		VerticalLayout selectContent = new VerticalLayout();
		selectContent.setWidth("100px");

		ButtonLink selectAllBtn = new ButtonLink("Select All",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						isSelected = true;
						changeOption();
					}
				});
		selectContent.addComponent(selectAllBtn);

		ButtonLink deSelectBtn = new ButtonLink("Deselect All",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						isSelected = false;
						changeOption();
					}
				});
		selectContent.addComponent(deSelectBtn);
		this.setComponent(selectContent);
	}

	private void changeOption() {
		Resource icon = (isSelected) ? selectIcon : unSelectIcon;
		SelectionOptionButton.this.setIcon(icon);

		if (listeners != null) {
			for (SelectionOptionListener listener : listeners) {
				if (isSelected) {
					listener.onSelect();
				} else {
					listener.onDeSelect();
				}
			}
		}
	}

	public void addListener(SelectionOptionListener listener) {
		if (listeners == null) {
			listeners = new HashSet<SelectionOptionButton.SelectionOptionListener>();
		}
		listeners.add(listener);
	}

	public static interface SelectionOptionListener {
		void onSelect();

		void onDeSelect();
	}
}
