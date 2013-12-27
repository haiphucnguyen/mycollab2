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
package com.esofthead.mycollab.module.project.ui.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class MultiSelectComp<T> extends CustomComponent {
	private static final long serialVersionUID = 1L;

	protected TextField componentsDisplay;
	protected PopupButton componentPopupSelection;

	private String propertyDisplayField;
	private String widthVal;

	protected List<T> selectedItems = new ArrayList<T>();

	protected List<T> items = new ArrayList<T>();

	public MultiSelectComp(final String displayName, List<T> items) {
		this.propertyDisplayField = displayName;
		this.items = items;

		this.setCompositionRoot(initContent());
	}

	protected Component initContent() {
		this.setWidth("100%");
		final HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(false);

		this.componentsDisplay = new TextField();
		this.componentsDisplay.setNullRepresentation("");
		this.componentsDisplay.setReadOnly(true);
		this.componentsDisplay.addStyleName("noBorderRight");
		this.componentsDisplay.setWidth("100%");

		this.componentPopupSelection = new PopupButton();
		this.componentPopupSelection
				.addClickListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						MultiSelectComp.this.initContentPopup();
					}
				});

		content.addComponent(this.componentsDisplay);
		content.setComponentAlignment(this.componentsDisplay,
				Alignment.MIDDLE_LEFT);

		this.componentPopupSelection.addStyleName("nonPopupIndicator");
		this.componentPopupSelection.addStyleName(UIConstants.SELECT_BG);
		this.componentPopupSelection.setWidth("25px");

		CssLayout btnWrapper = new CssLayout();
		btnWrapper.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		btnWrapper.addStyleName(UIConstants.SELECT_BG);
		btnWrapper.addComponent(componentPopupSelection);

		content.addComponent(btnWrapper);
		content.setComponentAlignment(btnWrapper, Alignment.MIDDLE_LEFT);

		content.setWidth(widthVal);
		content.setExpandRatio(this.componentsDisplay, 1.0f);

		return content;
	}

	public void resetComp() {
		selectedItems.clear();

		this.componentsDisplay.setReadOnly(false);
		this.componentsDisplay.setValue("");
		this.componentsDisplay.setReadOnly(true);

		VerticalLayout layout = (VerticalLayout) componentPopupSelection
				.getContent();

		Iterator<Component> iterator = layout.iterator();
		while (iterator.hasNext()) {
			CheckBox checkBox = (CheckBox) iterator.next();
			checkBox.setValue(false);
		}
	}

	private void initContentPopup() {
		VerticalLayout popupContent = new VerticalLayout();
		for (final T item : items) {

			final ItemSelectionComp chkItem = new ItemSelectionComp(item);
			chkItem.setImmediate(true);

			chkItem.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						final com.vaadin.data.Property.ValueChangeEvent event) {
					final Boolean value = (Boolean) chkItem.getValue();

					if (value && !selectedItems.contains(item)) {
						selectedItems.add(item);
					} else {
						selectedItems.remove(item);
					}

					displaySelectedItems();
				}
			});
			popupContent.addComponent(chkItem);
		}

		componentPopupSelection.setContent(popupContent);

	}

	private void displaySelectedItems() {
		this.componentsDisplay.setReadOnly(false);
		this.componentsDisplay.setValue(this.getDisplaySelectedItemsString());
		this.componentsDisplay.setReadOnly(true);
	}

	public void setSelectedItems(List<T> selectedValues) {
		selectedItems.clear();

		if (selectedValues != null) {
			for (T item : selectedValues) {
				for (T oriItem : items) {
					if (compareVal(item, oriItem)) {
						selectedItems.add(oriItem);
					}
				}
			}
		}

		displaySelectedItems();
	}

	private boolean compareVal(T value1, T value2) {
		if (value1 == null && value2 == null) {
			return true;
		} else if (value1 == null || value2 == null) {
			return false;
		} else {
			if (this.propertyDisplayField != "") {
				try {
					Integer field1 = (Integer) PropertyUtils.getProperty(
							value1, "id");
					Integer field2 = (Integer) PropertyUtils.getProperty(
							value2, "id");
					return field1.equals(field2);
				} catch (final Exception e) {
					return false;
				}
			} else {
				return value1.equals(value2);
			}
		}
	}

	public List<T> getSelectedItems() {
		return this.selectedItems;
	}

	protected String getDisplaySelectedItemsString() {
		final StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.selectedItems.size(); i++) {
			final Object itemObj = this.selectedItems.get(i);

			String objDisplayName = "";
			if (this.propertyDisplayField != "") {
				try {
					objDisplayName = (String) PropertyUtils.getProperty(
							itemObj, this.propertyDisplayField);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else {
				objDisplayName = (String) this.selectedItems.get(i);
			}
			if (i == this.selectedItems.size() - 1) {
				str.append(objDisplayName);
			} else {
				str.append(objDisplayName + ", ");
			}
		}
		return str.toString();
	}

	class ItemSelectionComp extends CheckBox {
		private static final long serialVersionUID = 1L;

		private T item;

		public ItemSelectionComp(T item) {
			super();
			this.item = item;

			String itemName = "";
			if (propertyDisplayField != "") {
				try {
					itemName = (String) PropertyUtils.getProperty(item,
							propertyDisplayField);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else {
				itemName = (String) item.toString();
			}
			this.setCaption(itemName);
		}

	}
}
