package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.vaadin.tokenfield.TokenField;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.service.MailService;
import com.esofthead.mycollab.utils.ParsingUtils;
import com.esofthead.mycollab.utils.ParsingUtils.InvalidEmailException;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MailFormWindow extends Window {

	private static final long serialVersionUID = 1L;
	private TokenFieldTextField tokenFieldMailTo;
	private TokenFieldTextField tokenFieldMailCc;
	private TokenFieldTextField tokenFieldMailBcc;
	private HorizontalLayout hLayoutLinkCcBcc;
	private GridFormLayoutHelper inputLayout;

	public MailFormWindow() {
		this.setWidth("800px");
		this.setHeight("500px");
		initUI();
	}

	private void checkToRemoveLinkCcBcc() {
		if (hLayoutLinkCcBcc.getComponentCount() < 1) {
			inputLayout.getLayout().removeComponent(hLayoutLinkCcBcc);
		}
	}

	@SuppressWarnings("serial")
	private void initUI() {
		inputLayout = new GridFormLayoutHelper(2, 10);
		inputLayout.getLayout().setSpacing(true);
		inputLayout.getLayout().setMargin(true);

		inputLayout.getLayout().setWidth("100%");
		tokenFieldMailTo = new TokenFieldTextField() {

			@Override
			protected void onTokenInput(Object tokenId) {
				String[] tokens = ((String) tokenId).split(",");
				for (int i = 0; i < tokens.length; i++) {
					String token = tokens[i].trim();
					if (token.length() > 0) {
						super.onTokenInput(token);
					}
				}
			}

		};
		tokenFieldMailTo.setStyleName(TokenField.STYLE_TOKENFIELD);
		tokenFieldMailTo.setWidth("350px");
		tokenFieldMailTo.setRememberNewTokens(false);
		tokenFieldMailTo.setInputPrompt("example@abc.com");
		inputLayout.addComponent(tokenFieldMailTo, "To", 0, 0, "350px");

		hLayoutLinkCcBcc = new HorizontalLayout();
		hLayoutLinkCcBcc.setSpacing(false);
		final Button btnLinkCc = new Button("Add Cc");
		btnLinkCc.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				tokenFieldMailCc = new TokenFieldTextField() {

					@Override
					protected void onTokenInput(Object tokenId) {
						String[] tokens = ((String) tokenId).split(",");
						for (int i = 0; i < tokens.length; i++) {
							String token = tokens[i].trim();
							if (token.length() > 0) {
								super.onTokenInput(token);
							}
						}
					}

				};
				tokenFieldMailCc.setWidth("350px");
				tokenFieldMailCc.setStyleName(TokenField.STYLE_TOKENFIELD);
				tokenFieldMailCc.setRememberNewTokens(false);
				tokenFieldMailCc.setInputPrompt("example@abc.com");
				if (inputLayout.getComponent(0, 2) == null) {
					inputLayout.addComponent(tokenFieldMailCc, "Cc", 0, 2,
							"350px");
					inputLayout.getLayout().removeComponent(hLayoutLinkCcBcc);
					inputLayout.addComponent(hLayoutLinkCcBcc, null, 0, 3,
							"350px");
				} else {
					inputLayout.addComponent(tokenFieldMailCc, "Cc", 0, 4,
							"350px");
				}
				hLayoutLinkCcBcc.removeComponent(btnLinkCc);
				checkToRemoveLinkCcBcc();
			}
		});
		hLayoutLinkCcBcc.addComponent(btnLinkCc);

		final Button btnLinkBcc = new Button("Add Bcc");
		btnLinkBcc.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				tokenFieldMailBcc = new TokenFieldTextField() {

					@Override
					protected void onTokenInput(Object tokenId) {
						String[] tokens = ((String) tokenId).split(",");
						for (int i = 0; i < tokens.length; i++) {
							String token = tokens[i].trim();
							if (token.length() > 0) {
								super.onTokenInput(token);
							}
						}
					}

				};
				tokenFieldMailBcc.setWidth("350px");
				tokenFieldMailBcc.setStyleName(TokenField.STYLE_TOKENFIELD);
				tokenFieldMailBcc.setRememberNewTokens(false);
				tokenFieldMailBcc.setInputPrompt("example@abc.com");
				if (inputLayout.getComponent(0, 2) == null) {
					inputLayout.addComponent(tokenFieldMailBcc, "Bcc", 0, 2,
							"350px");
					inputLayout.getLayout().removeComponent(hLayoutLinkCcBcc);
					inputLayout.addComponent(hLayoutLinkCcBcc, null, 0, 3,
							"200px");
				} else {
					inputLayout.addComponent(tokenFieldMailBcc, "Bcc", 0, 4,
							"350px");
				}
				hLayoutLinkCcBcc.removeComponent(btnLinkBcc);
				checkToRemoveLinkCcBcc();
			}
		});
		hLayoutLinkCcBcc.addComponent(btnLinkBcc);
		inputLayout.addComponent(hLayoutLinkCcBcc, null, 0, 1, "150px");

		final TextField subject = new TextField();
		subject.setWidth("350px");
		inputLayout.addComponent(subject, "Subject", 0, 5, 2, 1);
		
		VerticalLayout attachmentLayout = new VerticalLayout();
		attachmentLayout.addComponent(new Button("Attachment"));
		inputLayout.addComponent(attachmentLayout, null, 0, 6, "350px");

		final RichTextArea noteArea = new RichTextArea();
		noteArea.setWidth("800px");
		inputLayout.addComponent(noteArea, null, 0, 7, 2, 1);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		Label emptySpace = new Label("");
		controlsLayout.addComponent(emptySpace);
		controlsLayout.setExpandRatio(emptySpace, 1.0f);

		controlsLayout.setWidth("100px");
		controlsLayout.setSpacing(true);
		Button sendBtn = new Button("Send", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MailService mailService = AppContext
						.getSpringBean(MailService.class);
				try {

					List<MailRecipientField> toFields = ParsingUtils
							.parseEmailField((String) tokenFieldMailTo
									.getValue());
					List<MailRecipientField> ccFields = ParsingUtils
							.parseEmailField((String) tokenFieldMailCc
									.getValue());
					List<MailRecipientField> bccFields = ParsingUtils
							.parseEmailField((String) tokenFieldMailBcc
									.getValue());

					mailService.sendMail(toFields, ccFields, bccFields,
							(String) subject.getValue(),
							(String) noteArea.getValue());
				} catch (InvalidEmailException e) {
					// TODO: add more descriptive error message
					getWindow()
							.showNotification("Error", "Email invalid error");
				} catch (EmailException e1) {
					getWindow().showNotification("Error", "Send email error");
					e1.printStackTrace();
				}
			}
		});
		controlsLayout.addComponent(sendBtn);
		controlsLayout.setComponentAlignment(sendBtn, Alignment.MIDDLE_RIGHT);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MailFormWindow.this.close();
			}
		});

		controlsLayout.addComponent(cancelBtn);
		controlsLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);
		inputLayout.addComponent(controlsLayout, null, 0, 8, 2, 1);
		
		this.setContent(inputLayout.getLayout());
	}
}
