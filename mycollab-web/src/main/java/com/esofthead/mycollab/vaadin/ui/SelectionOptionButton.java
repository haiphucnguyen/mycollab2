package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import java.util.HashSet;
import java.util.Set;
import org.vaadin.hene.splitbutton.SplitButton;

public class SelectionOptionButton extends SplitButton implements
		HasSelectionOptionHandlers {

	private static final long serialVersionUID = 1L;
	private boolean isSelectAll = false;
	private boolean isSelected = false;
	@SuppressWarnings("rawtypes")
	private HasSelectableItemHandlers selectableItemHandlers;
	private static Resource selectIcon = new ThemeResource(
			"icons/16/checkbox.png");
	private static Resource unSelectIcon = new ThemeResource(
			"icons/16/checkbox_empty.png");
	private Set<SelectionOptionHandler> handlers;
	private Button selectAllBtn;
	private Button selectThisPageBtn;
	private Button deSelectBtn;

	@SuppressWarnings("serial")
	public SelectionOptionButton(
			@SuppressWarnings("rawtypes") HasSelectableItemHandlers selectableItemHandlers) {
		super();
		this.selectableItemHandlers = selectableItemHandlers;
		this.addStyleName(SplitButton.STYLE_CHAMELEON);
		this.setIcon(unSelectIcon);

		this.addClickListener(new SplitButtonClickListener() {
			@Override
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

		selectAllBtn = new Button("", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				isSelectAll = true;
				SelectionOptionButton.this.setIcon(selectIcon);
				fireSelectAll();
			}
		});
		selectAllBtn.setStyleName("link");
		selectContent.addComponent(selectAllBtn);

		selectThisPageBtn = new Button("", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				isSelectAll = false;
				SelectionOptionButton.this.setIcon(selectIcon);
				fireSelectCurrentPage();
			}
		});
		selectThisPageBtn.setStyleName("link");
		selectContent.addComponent(selectThisPageBtn);

		deSelectBtn = new ButtonLink("Deselect All",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						isSelectAll = false;
						SelectionOptionButton.this.setIcon(unSelectIcon);
						fireDeselect();
					}
				});
		deSelectBtn.setStyleName("link");
		selectContent.addComponent(deSelectBtn);
		this.setComponent(selectContent);
	}

	public void setSelectedChecbox(boolean selected) {
		isSelected = selected;
		Resource icon = (selected) ? selectIcon : unSelectIcon;
		SelectionOptionButton.this.setIcon(icon);
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
