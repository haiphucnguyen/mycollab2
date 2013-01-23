package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.tokenfield.TokenField;

@SuppressWarnings("serial")
public class UnEditableTokenField extends TokenField {
	
	public UnEditableTokenField() {
		super();
		
		this.setStyleName(TokenField.STYLE_TOKENFIELD);
		this.setRememberNewTokens(false);
		cb.setTextInputAllowed(false);
	}

}
