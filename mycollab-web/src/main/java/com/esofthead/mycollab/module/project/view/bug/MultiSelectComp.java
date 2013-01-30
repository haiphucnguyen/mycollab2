package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public abstract class MultiSelectComp extends CustomField {

	private TextField componentsDisplay;
	private MultipleItemsPopupSelection componentPopupSelection;
	private boolean isClicked = false;
	private String displayName;
	private HashMap<String, CheckBox> componentPoupMap = new HashMap<String, CheckBox>();
	private List selectedItemsList = new ArrayList();
	protected List dataList;

	public MultiSelectComp(String displayName) {
		this.displayName = displayName;
		this.setWidth("100%");
		GridLayout content = new GridLayout(2, 1);
		content.setSpacing(false);

		componentsDisplay = new TextField();
		componentsDisplay.setNullRepresentation("");
		componentsDisplay.setReadOnly(true);
		componentsDisplay.setWidth("210px");
		componentsDisplay.addStyleName("noBorderRight");

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

		content.addComponent(componentsDisplay, 0, 0);
		content.setComponentAlignment(componentsDisplay,
				Alignment.MIDDLE_CENTER);

		componentPopupSelection.addStyleName("nonPopupIndicator");
		componentPopupSelection.addStyleName(UIConstants.SELECT_BG);
		content.addComponent(componentPopupSelection, 1, 0);
		content.setComponentAlignment(componentPopupSelection,
				Alignment.MIDDLE_CENTER);

		this.setCompositionRoot(content);
	}

	private void createItemPopup() {
		for (int i = 0; i < dataList.size(); i++) {

			Object itemComp = dataList.get(i);
			String itemName = "";
			try {
				itemName = (String) PropertyUtils.getProperty(itemComp,
						displayName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			final CheckBox chkItem = new CheckBox(itemName);
			chkItem.setImmediate(true);
			chkItem.addListener(new ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Boolean value = (Boolean) chkItem.getValue();
					Object itemObj = getElementInDataListByName(chkItem
							.getCaption());
					String objDisplayName = "";
					try {
						objDisplayName = (String) PropertyUtils.getProperty(
								itemObj, displayName);
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
			try {
				objDisplayName = (String) PropertyUtils.getProperty(itemObj,
						displayName);
			} catch (Exception e) {
				e.printStackTrace();
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
			try {
				objDisplayName = (String) PropertyUtils.getProperty(itemObj,
						displayName);
			} catch (Exception e) {
				e.printStackTrace();
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
