package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class FeedbackWindow extends Window {
	private static final long serialVersionUID = 1L;
	private TextField emailTextField;
	private TextField subjectTextField;

	public FeedbackWindow() {
		initLayout();
	}

	private void initLayout() {
		this.setWidth(Sizeable.SIZE_UNDEFINED, 0);
		this.setHeight("360px");
		this.setCaption("Send us feedback for MyCollab ");
		initUI();
		center();
	}

	private void initUI() {
		GridLayout mainLayout = new GridLayout(2, 4);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		emailTextField = new TextField();
		emailTextField.setWidth("500px");
		Label emailLbl = new Label("Your email: ");

		mainLayout.addComponent(emailLbl, 0, 0);
		mainLayout.addComponent(emailTextField, 1, 0);

		subjectTextField = new TextField();
		subjectTextField.setWidth("500px");
		Label subjectLbl = new Label("Subject: ");

		mainLayout.addComponent(subjectLbl, 0, 1);
		mainLayout.addComponent(subjectTextField, 1, 1);

		RichTextArea contentArea = new RichTextArea();
		Label contentLbl = new Label("Your feedback: ");

		mainLayout.addComponent(contentLbl, 0, 2);
		mainLayout.addComponent(contentArea, 1, 2);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setWidth("100%");

		controlsLayout.setSpacing(true);
		Button sendBtn = new Button("Send", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// MailService mailService = AppContext
				// .getSpringBean(MailService.class);
				// try {
				//
				// List<MailRecipientField> toFields = ParsingUtils
				// .parseEmailField((String) tokenFieldMailTo
				// .getValue());
				// List<MailRecipientField> ccFields = ParsingUtils
				// .parseEmailField((String) tokenFieldMailCc
				// .getValue());
				// List<MailRecipientField> bccFields = ParsingUtils
				// .parseEmailField((String) tokenFieldMailBcc
				// .getValue());
				//
				// mailService.sendMail(toFields, ccFields, bccFields,
				// (String) subject.getValue(),
				// (String) noteArea.getValue());
				// } catch (InvalidEmailException e) {
				// // TODO: add more descriptive error message
				// getWindow()
				// .showNotification("Error", "Email invalid error");
				// } catch (EmailException e1) {
				// getWindow().showNotification("Error", "Send email error");
				// e1.printStackTrace();
				// }
			}
		});
		sendBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FeedbackWindow.this.close();
			}
		});

		cancelBtn.setStyleName("link");
		controlsLayout.addComponent(cancelBtn);
		controlsLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);
		controlsLayout.addComponent(sendBtn);
		controlsLayout.setComponentAlignment(sendBtn, Alignment.MIDDLE_RIGHT);
		controlsLayout.setExpandRatio(cancelBtn, 1.0f);
		mainLayout.addComponent(controlsLayout, 0, 3, 1, 3);

		this.setContent(mainLayout);
	}
}
