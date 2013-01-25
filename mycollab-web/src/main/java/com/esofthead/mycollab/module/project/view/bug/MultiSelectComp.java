package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public abstract class MultiSelectComp extends CustomField {

	private TextField componentsDisplay;
	private MultipleItemsPopupSelection selectBtn;

	public MultiSelectComp() {
		this.setWidth("100%");
		GridLayout content = new GridLayout(2,1);
		content.setSpacing(false);

		componentsDisplay = new TextField();
		componentsDisplay.setNullRepresentation("");
		componentsDisplay.setReadOnly(true);
		componentsDisplay.setWidth("210px");
		componentsDisplay.addStyleName("noBorderRight");

		selectBtn = new MultipleItemsPopupSelection(this);
		selectBtn.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				initData();
			}
		});
		
		content.addComponent(componentsDisplay, 0, 0);
		content.setComponentAlignment(componentsDisplay, Alignment.MIDDLE_CENTER);

		selectBtn.addStyleName("nonPopupIndicator");
		selectBtn.addStyleName(UIConstants.SELECT_BG);
		content.addComponent(selectBtn, 1, 0);
		content.setComponentAlignment(selectBtn, Alignment.MIDDLE_CENTER);
		
		this.setCompositionRoot(content);
	}
	
	abstract void initData();
	
	public void loadData(List<String> values) {
		for (int i = 0; i < values.size(); i++) {
			selectBtn.addItemComponent(values.get(i));
		}
	}
	
	protected List<String> getSelectedItem() {
		return lstStringValue;
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
