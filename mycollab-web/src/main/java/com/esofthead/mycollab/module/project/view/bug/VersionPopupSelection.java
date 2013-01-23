package com.esofthead.mycollab.module.project.view.bug;

import java.util.HashMap;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.vaadin.events.SelectOrRemoveItemHandler;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class VersionPopupSelection extends PopupButton {

	private Panel panel;
	
	private SelectOrRemoveItemHandler<String> versionItem;
	
	private HashMap<String, CheckBox> hashMapCheckbox = new HashMap<String, CheckBox>();
	
	public VersionPopupSelection(SelectOrRemoveItemHandler<String> owner) {
		this.versionItem = owner;
		panel = new Panel();
		panel.setStyleName(Reindeer.PANEL_LIGHT);
	    panel.setHeight("200px");
	    panel.setWidth("300px");
		this.addComponent(panel);
	}
	
	public void unCheckItem(String itemName) {
		CheckBox chk = hashMapCheckbox.get(itemName);
		chk.setValue(false);
		System.out.println("token field " + itemName);
	}
	
	public void addItemComponent(String itemName) {
		final CheckBox chkItem = new CheckBox(itemName);
		chkItem.setImmediate(true);
		chkItem.addListener(new ValueChangeListener() {
			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				Boolean value = (Boolean) chkItem.getValue();
				if (value) {
					versionItem.onSelect(chkItem.getCaption());
				} else {
					versionItem.onRemove(chkItem.getCaption());
				}
			}
		});
		hashMapCheckbox.put(itemName, chkItem);
		panel.addComponent(chkItem);
	}
}
