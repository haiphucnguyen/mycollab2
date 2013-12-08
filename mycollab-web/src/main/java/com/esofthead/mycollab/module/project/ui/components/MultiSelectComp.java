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
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public abstract class MultiSelectComp extends CustomField {

	private TextField componentsDisplay;
	private MultipleItemsPopupSelection componentPopupSelection;

	private String displayName;
	private String widthVal;

	protected boolean isClicked = false;
	protected HashMap<String, CheckBox> componentPoupMap = new HashMap<String, CheckBox>();

	@SuppressWarnings("rawtypes")
	protected List selectedItemsList = new ArrayList();

	@SuppressWarnings("rawtypes")
	protected List dataList = new ArrayList();

	public MultiSelectComp() {
		this("", "195px");
	}

	public MultiSelectComp(String displayName, String width) {
		this.displayName = displayName;
		this.widthVal = width;
	}

	public MultiSelectComp(final String displayName) {
		this(displayName, "195px");
	}

	@Override
	protected Component initContent() {
		this.setWidth("100%");
		final HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(false);

		this.componentsDisplay = new TextField();
		this.componentsDisplay.setNullRepresentation("");
		this.componentsDisplay.setReadOnly(true);
		this.componentsDisplay.addStyleName("noBorderRight");
		this.componentsDisplay.setWidth("100%");

		this.componentPopupSelection = new MultipleItemsPopupSelection();
		this.componentPopupSelection
				.addClickListener(new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						MultiSelectComp.this.initData();
						MultiSelectComp.this.createItemPopup();
						MultiSelectComp.this.setSelectedComponentsDisplay();
						MultiSelectComp.this.isClicked = true;
					}
				});

		content.addComponent(this.componentsDisplay);
		content.setComponentAlignment(this.componentsDisplay,
				Alignment.MIDDLE_LEFT);

		this.componentPopupSelection.addStyleName("nonPopupIndicator");
		this.componentPopupSelection.addStyleName(UIConstants.SELECT_BG);
		this.componentPopupSelection.setWidth("25px");

		CssLayout btnWrapper = new CssLayout();
		btnWrapper.setWidth("100%");
		btnWrapper.addStyleName(UIConstants.SELECT_BG);
		btnWrapper.addComponent(componentPopupSelection);

		content.addComponent(btnWrapper);
		content.setComponentAlignment(btnWrapper, Alignment.MIDDLE_LEFT);

		content.setWidth(widthVal);
		content.setExpandRatio(this.componentsDisplay, 1.0f);

		return content;
	}

	public void resetComp() {
		for (int i = 0; i < this.selectedItemsList.size(); i++) {
			this.selectedItemsList.remove(i);
		}

		this.componentsDisplay.setReadOnly(false);
		this.componentsDisplay.setValue("");
		this.componentsDisplay.setReadOnly(true);

		for (final CheckBox chk : this.componentPoupMap.values()) {
			chk.setValue(false);
		}
	}

	protected void createItemPopup() {
		for (int i = 0; i < this.dataList.size(); i++) {

			final Object itemComp = this.dataList.get(i);
			String itemName = "";
			if (this.displayName != "") {
				try {
					itemName = (String) PropertyUtils.getProperty(itemComp,
							this.displayName);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else {
				itemName = (String) this.dataList.get(i);
			}
			final CheckBox chkItem = new CheckBox(itemName);
			chkItem.setImmediate(true);
			chkItem.addValueChangeListener(new ValueChangeListener() {
				@Override
				public void valueChange(
						final com.vaadin.data.Property.ValueChangeEvent event) {
					final Boolean value = (Boolean) chkItem.getValue();
					String objDisplayName = "";
					if (MultiSelectComp.this.displayName != "") {
						final Object itemObj = MultiSelectComp.this
								.getElementInDataListByName(chkItem
										.getCaption());
						try {
							objDisplayName = (String) PropertyUtils
									.getProperty(itemObj,
											MultiSelectComp.this.displayName);
						} catch (final Exception e) {
							e.printStackTrace();
						}
						if (itemObj != null) {
							if (MultiSelectComp.this.isClicked) {
								MultiSelectComp.this
										.removeElementSelectedListByName(objDisplayName);
								if (value) {
									if (!MultiSelectComp.this.selectedItemsList
											.contains(itemObj)) {
										MultiSelectComp.this.selectedItemsList
												.add(itemObj);
									}
								}
								MultiSelectComp.this
										.setSelectedItems(MultiSelectComp.this.selectedItemsList);
							}
						}
					} else {
						if (MultiSelectComp.this.isClicked) {
							if (value) {
								if (!MultiSelectComp.this.selectedItemsList
										.contains(chkItem.getCaption())) {
									MultiSelectComp.this.selectedItemsList
											.add(chkItem.getCaption());
								}
							} else {
								MultiSelectComp.this.selectedItemsList
										.remove(chkItem.getCaption());
							}
							MultiSelectComp.this
									.setSelectedItems(MultiSelectComp.this.selectedItemsList);
						}
					}
				}
			});
			if (!this.componentPoupMap.containsKey(chkItem.getCaption())) {
				this.componentPoupMap.put(chkItem.getCaption(), chkItem);
				this.addItemToComponent(chkItem);
			}
		}
	}

	protected void removeElementSelectedListByName(final String name) {

		for (int i = 0; i < this.selectedItemsList.size(); i++) {
			final Object item = this.selectedItemsList.get(i);
			String itemName = "";
			try {
				itemName = (String) PropertyUtils.getProperty(item,
						this.displayName);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			if (itemName.equals(name)) {
				this.selectedItemsList.remove(i);
				return;
			}
		}
	}

	public Object getElementInDataListByName(final String name) {
		for (final Object item : this.dataList) {
			String itemName = "";
			try {
				itemName = (String) PropertyUtils.getProperty(item,
						this.displayName);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			if (itemName.equals(name)) {
				return item;
			}
		}
		return null;
	}

	protected void setSelectedComponentsDisplay() {
		for (int i = 0; i < this.selectedItemsList.size(); i++) {
			final Object itemObj = this.selectedItemsList.get(i);

			String objDisplayName = "";
			if (this.displayName != "") {
				try {
					objDisplayName = (String) PropertyUtils.getProperty(
							itemObj, this.displayName);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else {
				objDisplayName = (String) this.selectedItemsList.get(i);
			}

			if (this.componentPoupMap.containsKey(objDisplayName)) {
				final CheckBox chk = this.componentPoupMap.get(objDisplayName);
				chk.setValue(true);
			}
		}
	}

	protected void addItemToComponent(final Component comp) {
		this.componentPopupSelection.addItemComponent(comp);
	}

	public List getDataList() {
		return this.dataList;
	}

	public void setSelectedItems(final List items) {
		this.selectedItemsList = items;
		this.componentsDisplay.setReadOnly(false);
		this.componentsDisplay.setValue(this.getDisplaySelectedItemsString());
		this.componentsDisplay.setReadOnly(true);
	}

	public List getSelectedItems() {
		return this.selectedItemsList;
	}

	protected String getDisplaySelectedItemsString() {
		final StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.selectedItemsList.size(); i++) {
			final Object itemObj = this.selectedItemsList.get(i);

			String objDisplayName = "";
			if (this.displayName != "") {
				try {
					objDisplayName = (String) PropertyUtils.getProperty(
							itemObj, this.displayName);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else {
				objDisplayName = (String) this.selectedItemsList.get(i);
			}
			if (i == this.selectedItemsList.size() - 1) {
				str.append(objDisplayName);
			} else {
				str.append(objDisplayName + ", ");
			}
		}
		return str.toString();
	}

	abstract protected void initData();

	@Override
	public Class<?> getType() {
		return Object.class;
	}
}
