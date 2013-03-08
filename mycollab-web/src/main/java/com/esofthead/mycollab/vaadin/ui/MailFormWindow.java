package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class MailFormWindow extends Window {

	private static final long serialVersionUID = 1L;
	private TokenFieldTextField tokenFieldMailTo;
	private final TokenFieldTextField tokenFieldMailCc = new TokenFieldTextField();
	private final TokenFieldTextField tokenFieldMailBcc = new TokenFieldTextField();
	private GridLayout inputLayout;
	private Layout subjectField;
	private Layout ccField;
	private Layout bccField;

	private Button btnLinkCc;
	private Button btnLinkBcc;

	private boolean isAddCc = false;
	private boolean isAddBcc = false;

	private List<String> lstMail;

	public MailFormWindow() {
		initLayout();
	}

	public MailFormWindow(List<String> lstMail) {
		this.lstMail = lstMail;
		initLayout();
	}

	private void initLayout() {
		this.setWidth("830px");
		this.setHeight("500px");
		initUI();
		center();
	}

	@SuppressWarnings("serial")
	private void initButtonLinkCcBcc() {
		btnLinkCc = new Button("Add Cc");
		btnLinkCc.setStyleName("link");
		inputLayout.addComponent(btnLinkCc, 1, 0);
		inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);

		btnLinkBcc = new Button("Add Bcc");
		btnLinkBcc.setStyleName("link");
		inputLayout.addComponent(btnLinkBcc, 2, 0);
		inputLayout.setComponentAlignment(btnLinkBcc, Alignment.MIDDLE_CENTER);

		btnLinkCc.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				buttonLinkCcClick(event);
			}
		});

		btnLinkBcc.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				butonLinkBccClick(event);

			}
		});
	}

	private Layout createTextFieldMail(String title, Component component) {
		HorizontalLayout layout = new HorizontalLayout();
		Label lbTitle = new Label(title);
		lbTitle.setWidth("60px");
		lbTitle.setStyleName("lbmail");
		layout.addComponent(lbTitle);
		layout.setComponentAlignment(lbTitle, Alignment.MIDDLE_RIGHT);
		layout.addComponent(component);
		layout.setComponentAlignment(component, Alignment.MIDDLE_LEFT);
		return layout;
	}

	private void initUI() {
		GridLayout mainLayout = new GridLayout(1, 5);
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		CssLayout inputPanel = new CssLayout();
		inputPanel.setWidth("100%");
		inputPanel.setStyleName("mail-panel");

		inputLayout = new GridLayout(3, 4);
		inputLayout.setSpacing(true);
		inputLayout.setSizeUndefined();

		inputPanel.addComponent(inputLayout);

		mainLayout.addComponent(inputPanel);

		tokenFieldMailTo = new TokenFieldTextField();
		inputLayout.addComponent(createTextFieldMail("To:", tokenFieldMailTo),
				0, 0);

		if (lstMail != null) {
			for (String mail : lstMail) {
				if (mail != null && !mail.equals("")) {
					if (mail.indexOf("<") > -1) {
						String strMail = mail.substring(mail.indexOf("<") + 1,
								mail.lastIndexOf(">"));
						if (strMail != null
								&& !strMail.equalsIgnoreCase("null")) {
							tokenFieldMailTo.addToken(mail);
						}
					} else {
						tokenFieldMailTo.addToken(mail);
					}
				}
			}
		}

		final TextField subject = new TextField();
		subject.setWidth("550px");
		subjectField = createTextFieldMail("Subject:", subject);
		inputLayout.addComponent(subjectField, 0, 1);

		initButtonLinkCcBcc();

		ccField = createTextFieldMail("Cc:", tokenFieldMailCc);
		bccField = createTextFieldMail("Bcc:", tokenFieldMailBcc);

		final RichTextArea noteArea = new RichTextArea();
		noteArea.setWidth("100%");
		mainLayout.addComponent(noteArea, 0, 1);
		mainLayout.setComponentAlignment(noteArea, Alignment.MIDDLE_CENTER);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setWidth("100%");

		final AttachmentPanel attachments = new AttachmentPanel();
		attachments.setWidth("500px");

		MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);

		Panel attachedFilepanel = new Panel();
		attachedFilepanel.setScrollable(true);
		attachedFilepanel.setHeight("80px");
		attachedFilepanel.setStyleName("noneBorder-panel");
		attachedFilepanel.getContent().setSizeUndefined();
		attachedFilepanel.addComponent(attachments);

		attachedFilepanel.addComponent(uploadExt);

		controlsLayout.addComponent(attachedFilepanel);
		controlsLayout.setExpandRatio(attachedFilepanel, 1.0f);

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
		controlsLayout.addComponent(sendBtn);
		controlsLayout.setComponentAlignment(sendBtn, Alignment.MIDDLE_RIGHT);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MailFormWindow.this.close();
			}
		});

		cancelBtn.setStyleName("link");
		controlsLayout.addComponent(cancelBtn);
		controlsLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);
		mainLayout.addComponent(controlsLayout, 0, 2);

		this.setContent(mainLayout);
	}

	private void checkToReInitCcBcc() {
		if ((!isAddCc) && (!isAddBcc)) {
			inputLayout.removeComponent(btnLinkCc);
			inputLayout.removeComponent(btnLinkBcc);
			initButtonLinkCcBcc();
			inputLayout.removeComponent(subjectField);
			inputLayout.removeComponent(0, 1);
			inputLayout.addComponent(subjectField, 0, 1);
		}
	}

	private void buttonLinkCcClick(ClickEvent event) {
		removeAllInputField();
		if (!isAddCc) {
			btnLinkCc.setCaption("Remove Cc");
			if (!isAddBcc) {
				inputLayout.addComponent(ccField, 0, 1);
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 2, 1);
				inputLayout.addComponent(subjectField, 0, 2);
			} else {
				addFullInputFieldByOrder();
			}
		} else {
			btnLinkCc.setCaption("Add Cc");

			if (isAddBcc) {
				inputLayout.addComponent(bccField, 0, 1);
				inputLayout.addComponent(btnLinkBcc, 1, 1);
				inputLayout.addComponent(btnLinkCc, 2, 1);
				inputLayout.addComponent(subjectField, 0, 2);
			}
		}

		inputLayout.setComponentAlignment(btnLinkBcc, Alignment.MIDDLE_CENTER);
		inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);

		isAddCc = !isAddCc;

		checkToReInitCcBcc();
	}

	private void addFullInputFieldByOrder() {
		inputLayout.addComponent(ccField, 0, 1);
		inputLayout.addComponent(btnLinkCc, 1, 1);

		inputLayout.addComponent(bccField, 0, 2);
		inputLayout.addComponent(btnLinkBcc, 1, 2);

		inputLayout.addComponent(subjectField, 0, 3);
	}

	private void removeAllInputField() {
		inputLayout.removeComponent(btnLinkCc);
		inputLayout.removeComponent(ccField);
		inputLayout.removeComponent(subjectField);
		inputLayout.removeComponent(bccField);
		inputLayout.removeComponent(btnLinkBcc);
	}

	private void butonLinkBccClick(ClickEvent event) {

		removeAllInputField();

		if (!isAddBcc) {
			btnLinkBcc.setCaption("Remove Bcc");

			if (!isAddCc) {
				inputLayout.addComponent(bccField, 0, 1);
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 2, 1);
				inputLayout.addComponent(subjectField, 0, 2);
			} else {
				addFullInputFieldByOrder();
			}
		} else {
			btnLinkBcc.setCaption("Add Bcc");

			if (isAddCc) {
				inputLayout.addComponent(ccField, 0, 1);
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 2, 1);
				inputLayout.addComponent(subjectField, 0, 2);
			}
		}
		inputLayout.setComponentAlignment(btnLinkBcc, Alignment.MIDDLE_CENTER);
		inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);
		isAddBcc = !isAddBcc;
		checkToReInitCcBcc();
	}
}
