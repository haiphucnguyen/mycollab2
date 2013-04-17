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
public class UserLink extends Button {
	private static final long serialVersionUID = 1L;

	public UserLink(final String username, final String displayName) {
		super(displayName, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
			}
		});

		this.setStyleName("link");
		this.addStyleName(UIConstants.WORD_WRAP);
	}
}
