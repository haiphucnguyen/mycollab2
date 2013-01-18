package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.tokenfield.TokenField;

import com.esofthead.mycollab.core.utils.EmailValidator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;


@SuppressWarnings("serial")
public class TokenFieldTextField extends TokenField {
	
	private EmailValidator emailValidate = new EmailValidator();
	public TokenFieldTextField() {
		super();
		
		this.setStyleName(TokenField.STYLE_TOKENFIELD);
		this.setWidth("300px");
		this.setInputWidth("300px");
		this.setRememberNewTokens(false);
		this.setInputPrompt("example@abc.com");
	}
	
	@Override
	protected void onTokenInput(Object tokenId) {
		String[] tokens = ((String) tokenId).split(",");
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i].trim();
			if (token.length() > 0) {
				if (emailValidate.validate(token)) {
					super.onTokenInput(token);
				} else {
					 AppContext.getApplication().getMainWindow().showNotification("Warming", "The email is not valid, pleas check again!", Window.Notification.TYPE_HUMANIZED_MESSAGE);
				}
			}
		}
	}
	
	public void setInputStyle(String styleName) {
		cb.setStyleName(styleName);
	}

}
