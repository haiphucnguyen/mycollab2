/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.events.SelectOrRemoveItemHandler;
import com.esofthead.mycollab.vaadin.ui.UnEditableTokenField;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class VersionMultiSelectField extends CustomField implements SelectOrRemoveItemHandler<String> {

	private UnEditableTokenField componentsDisplay;
	private VersionPopupSelection selectBtn;
	
    public VersionMultiSelectField() {
    	this.setWidth("100%");
        HorizontalLayout content = new HorizontalLayout();
        content.setSpacing(true);
        
        componentsDisplay = new UnEditableTokenField(){
        	
        	@Override
        	protected void onTokenClick(Object tokenId) {
        		System.out.println("token field 1 " + tokenId.toString());
        		selectBtn.unCheckItem((String) tokenId);
        	};
        };
        componentsDisplay.setWidth("220px");
        componentsDisplay.setInputWidth("210px");
        
        selectBtn = new VersionPopupSelection(this);
        content.addComponent(componentsDisplay);
        
        
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        
        selectBtn.addItemComponent("value 1");
        selectBtn.addItemComponent("value 2");
        selectBtn.addItemComponent("value 3");
        selectBtn.addItemComponent("value 4");
        selectBtn.addItemComponent("value 5");
        selectBtn.addItemComponent("value 6");
        selectBtn.addItemComponent("value 7");
        selectBtn.addItemComponent("value 8");
        selectBtn.addItemComponent("value 9");
        selectBtn.addItemComponent("value 10");
        selectBtn.addStyleName("link");
        selectBtn.addStyleName("nonPopupIndicator");
        content.addComponent(selectBtn);
        this.setCompositionRoot(content);
    }
    
    @Override
    public Class<?> getType() {
        return Object.class;
    }

	@Override
	public void onSelect(String item) {
		componentsDisplay.addToken(item);
	}

	@Override
	public void onRemove(String item) {
		componentsDisplay.removeToken(item);
	}
    
}
