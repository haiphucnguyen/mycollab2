package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class SelectionOptionButton extends SplitButton implements
		HasSelectionOptionHandlers {

	private static final long serialVersionUID = 1L;
	private boolean isSelectAll = false;
	private boolean isSelected = false;
	@SuppressWarnings("rawtypes")
	private final HasSelectableItemHandlers selectableItemHandlers;
	private static Resource selectIcon = MyCollabResource
			.newResource("icons/16/checkbox.png");
	private static Resource unSelectIcon = MyCollabResource
			.newResource("icons/16/checkbox_empty.png");
	private Set<SelectionOptionHandler> handlers;
	private final Button selectAllBtn;
	private final Button selectThisPageBtn;
	private final Button deSelectBtn;

	@SuppressWarnings("serial")
	public SelectionOptionButton(
			@SuppressWarnings("rawtypes") final HasSelectableItemHandlers selectableItemHandlers) {
		super();
		this.selectableItemHandlers = selectableItemHandlers;
		addStyleName(UIConstants.THEME_GRAY_LINK);
		setIcon(SelectionOptionButton.unSelectIcon);

		addClickListener(new SplitButtonClickListener() {
			@Override
			public void splitButtonClick(final SplitButtonClickEvent event) {
				toogleChangeOption();
			}
		});

		addPopupVisibilityListener(new SplitButtonPopupVisibilityListener() {
			@Override
			public void splitButtonPopupVisibilityChange(
					final SplitButtonPopupVisibilityEvent event) {
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

		final VerticalLayout selectContent = new VerticalLayout();
		selectContent.setWidth("150px");

		selectAllBtn = new Button("", new Button.ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				isSelectAll = true;
				SelectionOptionButton.this
						.setIcon(SelectionOptionButton.selectIcon);
				fireSelectAll();
			}
		});
		selectAllBtn.setStyleName("link");
		selectContent.addComponent(selectAllBtn);

		selectThisPageBtn = new Button("", new Button.ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				isSelectAll = false;
				SelectionOptionButton.this
						.setIcon(SelectionOptionButton.selectIcon);
				fireSelectCurrentPage();
			}
		});
		selectThisPageBtn.setStyleName("link");
		selectContent.addComponent(selectThisPageBtn);

		deSelectBtn = new ButtonLink("Deselect All",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						isSelectAll = false;
						SelectionOptionButton.this
								.setIcon(SelectionOptionButton.unSelectIcon);
						fireDeselect();
					}
				});
		deSelectBtn.setStyleName("link");
		selectContent.addComponent(deSelectBtn);
		setComponent(selectContent);
	}

	@Override
	public void addSelectionOptionHandler(final SelectionOptionHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<SelectionOptionHandler>();
		}
		handlers.add(handler);
	}

	private void fireDeselect() {
		if (handlers != null) {
			for (final SelectionOptionHandler handler : handlers) {
				handler.onDeSelect();
			}
		}
	}

	private void fireSelectAll() {
		if (handlers != null) {
			for (final SelectionOptionHandler handler : handlers) {
				handler.onSelectAll();
			}
		}

	}

	private void fireSelectCurrentPage() {
		if (handlers != null) {
			for (final SelectionOptionHandler handler : handlers) {
				handler.onSelectCurrentPage();
			}
		}
	}

	public void setSelectedChecbox(final boolean selected) {
		isSelected = selected;
		final Resource icon = (selected) ? SelectionOptionButton.selectIcon
				: SelectionOptionButton.unSelectIcon;
		SelectionOptionButton.this.setIcon(icon);
	}

	private void toogleChangeOption() {
		if (isSelectAll) {
			return;
		}

		isSelected = !isSelected;
		final Resource icon = (isSelected) ? SelectionOptionButton.selectIcon
				: SelectionOptionButton.unSelectIcon;
		SelectionOptionButton.this.setIcon(icon);

		if (isSelected) {
			fireSelectCurrentPage();
		} else {
			fireDeselect();
		}
	}
}
