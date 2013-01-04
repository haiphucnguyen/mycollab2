/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class UserLink extends Button{
    public UserLink(final String username, final String displayName) {
        super(displayName, new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                
            }
        });
        
        this.setStyleName("link");
    }
}
