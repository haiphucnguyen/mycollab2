package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.tokenfield.TokenField;

@SuppressWarnings("serial")
public class TokenFieldTextField extends TokenField {
	
	public TokenFieldTextField() {
		super();
	}
	
	public void setInputStyle(String styleName) {
		cb.setStyleName(styleName);
	}

}
