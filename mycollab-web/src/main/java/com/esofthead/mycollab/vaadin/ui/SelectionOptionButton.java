package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class SelectionOptionButton extends SplitButton implements
		HasSelectionOptionHandlers {
	private static final long serialVersionUID = 1L;
	
	private boolean isSelectAll = false;

	private boolean isSelected = false;

	private HasSelectableItemHandlers selectableItemHandlers;

	private static Resource selectIcon = new ThemeResource(
			"icons/16/checkbox.png");

	private static Resource unSelectIcon = new ThemeResource(
			"icons/16/checkbox_empty.png");

	private Set<SelectionOptionHandler> handlers;

	private ButtonLink selectAllBtn;

	private ButtonLink selectThisPageBtn;

	private ButtonLink deSelectBtn;

	@SuppressWarnings("serial")
	public SelectionOptionButton(
			HasSelectableItemHandlers selectableItemHandlers) {
		super();
		this.selectableItemHandlers = selectableItemHandlers;
		this.addStyleName(SplitButton.STYLE_CHAMELEON);
		this.setIcon(unSelectIcon);

		this.addClickListener(new SplitButtonClickListener() {
			public void splitButtonClick(SplitButtonClickEvent event) {
				toogleChangeOption();
			}
		});

		this.addPopupVisibilityListener(new SplitButtonPopupVisibilityListener() {

			@Override
			public void splitButtonPopupVisibilityChange(
					SplitButtonPopupVisibilityEvent event) {
				if (event.isPopupVisible()) {
					selectAllBtn.setCaption("Select All ("
							+ SelectionOptionButton.this.selectableItemHandlers
									.totalItemsCount() + ")");

					selectThisPageBtn.setCaption("Select This Page ("
							+ SelectionOptionButton.this.selectableItemHandlers
									.currentViewCount() + ")");
				}
			}
		});

		VerticalLayout selectContent = new VerticalLayout();
		selectContent.setWidth("150px");

		selectAllBtn = new ButtonLink("", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				isSelectAll = true;
				SelectionOptionButton.this.setIcon(selectIcon);
				fireSelectAll();
			}
		});
		selectContent.addComponent(selectAllBtn);

		selectThisPageBtn = new ButtonLink("", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				isSelectAll = false;
				SelectionOptionButton.this.setIcon(selectIcon);
				fireSelectCurrentPage();
			}
		});
		selectContent.addComponent(selectThisPageBtn);

		deSelectBtn = new ButtonLink("Deselect All",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						isSelectAll = false;
						fireDeselect();
					}
				});
		selectContent.addComponent(deSelectBtn);
		this.setComponent(selectContent);
	}

	private void toogleChangeOption() {
		if (isSelectAll) {
			return;
		}
		
		isSelected = !isSelected;
		Resource icon = (isSelected) ? selectIcon : unSelectIcon;
		SelectionOptionButton.this.setIcon(icon);

		if (isSelected) {
			fireSelectCurrentPage();
		} else {
			fireDeselect();
		}
	}

	private void fireSelectCurrentPage() {
		if (handlers != null) {
			for (SelectionOptionHandler handler : handlers) {
				handler.onSelectCurrentPage();
			}
		}
	}

	private void fireSelectAll() {
		if (handlers != null) {
			for (SelectionOptionHandler handler : handlers) {
				handler.onSelectAll();
			}
		}

	}

	private void fireDeselect() {
		if (handlers != null) {
			for (SelectionOptionHandler handler : handlers) {
				handler.onDeSelect();
			}
		}
	}

	@Override
	public void addSelectionOptionHandler(SelectionOptionHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<SelectionOptionHandler>();
		}
		handlers.add(handler);
	}
}
