/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.vaadin.addon.customfield.CustomField;

/**
 *
 * @author haiphucnguyen
 */
public class VersionMultiSelectField extends CustomField {

    public VersionMultiSelectField() {
        HorizontalLayout content = new HorizontalLayout();
        content.setWidth("100%");
        content.setSpacing(true);
        TextField componentsDisplay = new TextField();
        content.addComponent(componentsDisplay);
        content.setExpandRatio(componentsDisplay, 1.0f);
        
        Button selectBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
            }
        });
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        selectBtn.setStyleName("link");
        content.addComponent(selectBtn);
        this.setCompositionRoot(content);
    }
    
    @Override
    public Class<?> getType() {
        return Object.class;
    }
    
}
