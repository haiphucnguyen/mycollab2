/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class UserAvatar extends Button {

    public UserAvatar(String username, String displayName) {
        this.setIcon(new ThemeResource("icons/default_user_avatar_64_64.png"));
        this.setStyleName("link");
    }
}
