package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class MultiSelectComp extends CustomField {

	private TextField componentsDisplay;
	private MultipleItemsPopupSelection selectBtn;

	public MultiSelectComp() {
		this.setWidth("100%");
		GridLayout content = new GridLayout(2,1);
		content.setSpacing(false);

		componentsDisplay = new TextField();
		componentsDisplay.setNullRepresentation("");
		componentsDisplay.setReadOnly(true);
		componentsDisplay.setWidth("220px");

		selectBtn = new MultipleItemsPopupSelection(this);
		content.addComponent(componentsDisplay, 0, 0);

		selectBtn.setIcon(new ThemeResource("icons/16/select.png"));

		selectBtn.addStyleName("link");
		selectBtn.addStyleName("nonPopupIndicator");
		content.addComponent(selectBtn, 1, 0);
		this.setCompositionRoot(content);
	}
	
	public void loadData(List<String> values) {
		for (int i = 0; i < values.size(); i++) {
			selectBtn.addItemComponent(values.get(i));
		}
	}

	public void setSelectedItem(String item) {
		componentsDisplay.setReadOnly(false);
		lstStringValue.add(item);
		componentsDisplay.setValue(buildStringDisplay());
		componentsDisplay.setReadOnly(true);
	}
	
	private List<String> lstStringValue = new ArrayList<String>();
	
	private String buildStringDisplay() {
		String str = "";
		for (int i = 0; i < lstStringValue.size(); i++) {
			if (i == lstStringValue.size() - 1) {
				str += lstStringValue.get(i);
			} else {
				str += lstStringValue.get(i) + ", ";
			}
		}
		return str;
	}

	public void removeSelectedItem(String item) {
		componentsDisplay.setReadOnly(false);
		lstStringValue.remove(item);
		componentsDisplay.setValue(buildStringDisplay());
		componentsDisplay.setReadOnly(true);
	}

	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
