package com.esofthead.mycollab.module.project.ui.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.project.view.bug.MultipleItemsPopupSelection;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public abstract class MultiSelectComp extends CustomField {

	private final TextField componentsDisplay;
	private final MultipleItemsPopupSelection componentPopupSelection;
	private boolean isClicked = false;
	private String displayName;
	private final HashMap<String, CheckBox> componentPoupMap = new HashMap<String, CheckBox>();

	@SuppressWarnings("rawtypes")
	private List selectedItemsList = new ArrayList();

	@SuppressWarnings("rawtypes")
	protected List dataList = new ArrayList();

	public MultiSelectComp() {
		this.setWidth("100%");
		HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(false);

		componentsDisplay = new TextField();
		componentsDisplay.setNullRepresentation("");
		componentsDisplay.setReadOnly(true);
		componentsDisplay.addStyleName("noBorderRight");
		componentsDisplay.setWidth("195px");

		componentPopupSelection = new MultipleItemsPopupSelection();
		componentPopupSelection.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				initData();
				createItemPopup();
				setSelectedComponentsDisplay();
				isClicked = true;
			}
		});

		content.addComponent(componentsDisplay);
		content.setComponentAlignment(componentsDisplay,
				Alignment.TOP_RIGHT);

		componentPopupSelection.addStyleName("nonPopupIndicator");
		componentPopupSelection.addStyleName(UIConstants.SELECT_BG);
		componentPopupSelection.setWidth("23px");
		content.addComponent(componentPopupSelection);
		content.setComponentAlignment(componentPopupSelection,
				Alignment.TOP_LEFT);

		content.setWidth("100%");
		//content.setExpandRatio(componentsDisplay, 1.0f);

		this.setCompositionRoot(content);
	}

	public MultiSelectComp(String displayName, String width) {
		this(displayName);
		componentsDisplay.setWidth(width);
	}

	public MultiSelectComp(String displayName) {
		this();
		this.displayName = displayName;
	}

	public void resetComp() {
		for (int i = 0; i < selectedItemsList.size(); i++) {
			selectedItemsList.remove(i);
		}

		componentsDisplay.setReadOnly(false);
		componentsDisplay.setValue("");
		componentsDisplay.setReadOnly(true);

		for (CheckBox chk : componentPoupMap.values()) {
			chk.setValue(false);
		}
	}

	private void createItemPopup() {
		for (int i = 0; i < dataList.size(); i++) {

			Object itemComp = dataList.get(i);
			String itemName = "";
			if (displayName != "") {
				try {
					itemName = (String) PropertyUtils.getProperty(itemComp,
							displayName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				itemName = (String) dataList.get(i);
			}
			final CheckBox chkItem = new CheckBox(itemName);
			chkItem.setImmediate(true);
			chkItem.addListener(new ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Boolean value = (Boolean) chkItem.getValue();
					String objDisplayName = "";
					if (displayName != "") {
						Object itemObj = getElementInDataListByName(chkItem
								.getCaption());
						try {
							objDisplayName = (String) PropertyUtils
									.getProperty(itemObj, displayName);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (itemObj != null) {
							if (isClicked) {
								removeElementSelectedListByName(objDisplayName);
								if (value) {
									if (!selectedItemsList.contains(itemObj)) {
										selectedItemsList.add(itemObj);
									}
								}
								setSelectedItems(selectedItemsList);
							}
						}
					} else {
						if (isClicked) {
							if (value) {
								if (!selectedItemsList.contains(chkItem
										.getCaption())) {
									selectedItemsList.add(chkItem.getCaption());
								}
							} else {
								selectedItemsList.remove(chkItem.getCaption());
							}
							setSelectedItems(selectedItemsList);
						}
					}
				}
			});
			if (!componentPoupMap.containsKey(chkItem.getCaption())) {
				componentPoupMap.put(chkItem.getCaption(), chkItem);
				addItemToComponent(chkItem);
			}
		}
	}

	private void removeElementSelectedListByName(String name) {

		for (int i = 0; i < selectedItemsList.size(); i++) {
			Object item = selectedItemsList.get(i);
			String itemName = "";
			try {
				itemName = (String) PropertyUtils
						.getProperty(item, displayName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (itemName.equals(name)) {
				selectedItemsList.remove(i);
				return;
			}
		}
	}

	public Object getElementInDataListByName(String name) {
		for (Object item : dataList) {
			String itemName = "";
			try {
				itemName = (String) PropertyUtils
						.getProperty(item, displayName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (itemName.equals(name)) {
				return item;
			}
		}
		return null;
	}

	protected void setSelectedComponentsDisplay() {
		for (int i = 0; i < selectedItemsList.size(); i++) {
			Object itemObj = selectedItemsList.get(i);

			String objDisplayName = "";
			if (displayName != "") {
				try {
					objDisplayName = (String) PropertyUtils.getProperty(
							itemObj, displayName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				objDisplayName = (String) selectedItemsList.get(i);
			}

			if (componentPoupMap.containsKey(objDisplayName)) {
				CheckBox chk = componentPoupMap.get(objDisplayName);
				chk.setValue(true);
			}
		}
	}

	protected void addItemToComponent(Component comp) {
		componentPopupSelection.addItemComponent(comp);
	}

	public List getDataList() {
		return dataList;
	}

	public void setSelectedItems(List items) {
		this.selectedItemsList = items;
		componentsDisplay.setReadOnly(false);
		componentsDisplay.setValue(getDisplaySelectedItemsString());
		componentsDisplay.setReadOnly(true);
	}

	public List getSelectedItems() {
		return selectedItemsList;
	}

	protected String getDisplaySelectedItemsString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < selectedItemsList.size(); i++) {
			Object itemObj = selectedItemsList.get(i);

			String objDisplayName = "";
			if (displayName != "") {
				try {
					objDisplayName = (String) PropertyUtils.getProperty(
							itemObj, displayName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				objDisplayName = (String) selectedItemsList.get(i);
			}
			if (i == selectedItemsList.size() - 1) {
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
