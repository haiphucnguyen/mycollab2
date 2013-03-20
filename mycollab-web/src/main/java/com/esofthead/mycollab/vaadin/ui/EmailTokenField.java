package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.tokenfield.TokenField;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.core.utils.EmailValidator;
import com.esofthead.mycollab.utils.ParsingUtils;
import com.esofthead.mycollab.utils.ParsingUtils.InvalidEmailException;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class EmailTokenField extends TokenField {

	private final EmailValidator emailValidate = new EmailValidator();

	private List<MailRecipientField> lstMailToken = new ArrayList<MailRecipientField>();

	public EmailTokenField() {
		super();

		this.setStyleName(TokenField.STYLE_TOKENFIELD);
		this.setInputWidth("544px");
		this.setRememberNewTokens(false);
	}

	@Override
	protected void onTokenClick(Object tokenId) {
		super.onTokenClick(tokenId);
		int index = -1;
		try {
			index = getItemIndexInListToEmail(ParsingUtils
					.getMailRecipient(tokenId.toString()));
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		}
		if (index > -1) {
			lstMailToken.remove(index);
		}
	}

	public void addToken(Object tokenId) {
		super.addToken(tokenId);
		try {
			lstMailToken.add(ParsingUtils.getMailRecipient(tokenId.toString()));
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		}
	}

	private int getItemIndexInListToEmail(MailRecipientField item) {
		for (int i = 0; i < lstMailToken.size(); i++) {
			MailRecipientField recipient = lstMailToken.get(i);
			if (item.getEmail().equals(recipient.getEmail())
					&& item.getName().equals(recipient.getName())) {
				return i;
			}
		}
		return -1;
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
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									"Warming",
									"The email is not valid, pleas check again!",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
				}
			}
		}
	}

	public void setInputStyle(String styleName) {
		cb.setStyleName(styleName);
	}
	
	public void removeAllRecipients() {
		
		for (MailRecipientField recipient : lstMailToken) {
			removeToken(recipient.getEmail());
		}
		
		lstMailToken.removeAll(lstMailToken);
	}

	public List<MailRecipientField> getListRecipient() {
		return lstMailToken;
	}

}
