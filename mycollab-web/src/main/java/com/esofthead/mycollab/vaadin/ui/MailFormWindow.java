package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.service.MailService;
import com.esofthead.mycollab.utils.ParsingUtils;
import com.esofthead.mycollab.utils.ParsingUtils.InvalidEmailException;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MailFormWindow extends Window {

	private static final long serialVersionUID = 1L;
	private TokenFieldTextField tokenFieldMailTo;
	private TokenFieldTextField tokenFieldMailCc;
	private TokenFieldTextField tokenFieldMailBcc;
	private GridLayout inputLayout;
	
	private Button btnLinkCc;
	private Button btnLinkBcc;
	
	private boolean isAddCc = false;
	private boolean isAddBcc = false;
	
	private String[] arrMail;

	public MailFormWindow() {
		initLayout();
	}
	
	public MailFormWindow(String[] arrMail) {
		this.arrMail = arrMail;
		initLayout();
	}
	
	private void initLayout() {
		this.setWidth("900px");
		this.setHeight("500px");
		initUI();
	}

	private void checkToReInitCcBcc() {
		if ((tokenFieldMailBcc == null) && (tokenFieldMailCc == null)) {
			initButtonLinkCcBcc();
		}
	}
	
	@SuppressWarnings("serial")
	private void initButtonLinkCcBcc() {
		btnLinkCc = new Button("Add Cc");
		inputLayout.addComponent(btnLinkCc, 1, 0);
		inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);
		
		btnLinkBcc = new Button("Add Bcc");
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
		GridLayout mainLayout = new GridLayout(2, 5);
		mainLayout.setWidth("100%");
		HorizontalLayout hLayoutInput = new HorizontalLayout();
		
		inputLayout = new GridLayout(4, 6);
		inputLayout.setSpacing(true);
		inputLayout.setMargin(true);

		inputLayout.setWidth("600px");
		hLayoutInput.addComponent(inputLayout);
		
		final AttachmentPanel attachments = new AttachmentPanel();
        
		 MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);
		 
		Panel attachedFilepanel = new Panel();
		attachedFilepanel.setHeight("80px");
		attachedFilepanel.setWidth("250px");
		attachedFilepanel.getContent().setSizeUndefined();
		attachedFilepanel.addComponent(attachments);
		
		VerticalLayout attachmentLayout = new VerticalLayout();
		attachmentLayout.setSpacing(true);
		attachmentLayout.setMargin(true);
		attachmentLayout.addComponent(attachedFilepanel);
		attachmentLayout.addComponent(uploadExt);
		hLayoutInput.addComponent(attachmentLayout);
		
		mainLayout.addComponent(hLayoutInput);
		tokenFieldMailTo = new TokenFieldTextField() ;
		inputLayout.addComponent(createTextFieldMail("To:", tokenFieldMailTo), 0, 0);
		
		if (arrMail != null) {
			for (int i = 0; i < arrMail.length; i++) {
				tokenFieldMailTo.addToken(arrMail[i]);
			}
		}
		
		initButtonLinkCcBcc();

		final TextField subject = new TextField();
		subject.setWidth("300px");
		inputLayout.addComponent(createTextFieldMail("Subject:", subject), 0, 5);
		
		final RichTextArea noteArea = new RichTextArea();
		noteArea.setWidth("100%");
		mainLayout.addComponent(noteArea, 0, 1);
		mainLayout.setComponentAlignment(noteArea, Alignment.MIDDLE_CENTER);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setWidth("100%");
		Label emptySpace = new Label("");
		controlsLayout.addComponent(emptySpace);
		controlsLayout.setExpandRatio(emptySpace, 1.0f);

		controlsLayout.setSpacing(true);
		controlsLayout.setMargin(true);
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
		mainLayout.addComponent(controlsLayout, 0, 2);
		
		this.setContent(mainLayout);
	}
	
	private void buttonLinkCcClick(ClickEvent event) {
		tokenFieldMailCc = new TokenFieldTextField();
		
		if (!isAddCc) {
			btnLinkCc.setCaption("Remove Cc");
			inputLayout.removeComponent(btnLinkCc);
			inputLayout.removeComponent(btnLinkBcc);
			if (tokenFieldMailBcc == null) {
				inputLayout.addComponent(createTextFieldMail("Cc:", tokenFieldMailCc), 0, 1);
				
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 2, 1);
			} else {
				inputLayout.addComponent(createTextFieldMail("Cc:", tokenFieldMailCc), 0, 2);
				
				inputLayout.addComponent(btnLinkCc, 1, 2);
				inputLayout.addComponent(btnLinkBcc, 1, 1);
			}
			inputLayout.setComponentAlignment(btnLinkBcc, Alignment.MIDDLE_CENTER);
			inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);
		} else {
			btnLinkCc.setCaption("Add Cc");
			tokenFieldMailCc = null;
			inputLayout.removeComponent(tokenFieldMailCc);
			inputLayout.removeComponent(btnLinkCc);
			
			if (tokenFieldMailBcc == null) {
				inputLayout.removeComponent(0, 1);
				inputLayout.removeComponent(2, 1);
			} else {
				inputLayout.removeComponent(0, 2);
			}
			
		}
		
		isAddCc = !isAddCc;
		
		checkToReInitCcBcc();
	}
	
	private void butonLinkBccClick(ClickEvent event) {
		tokenFieldMailBcc = new TokenFieldTextField();
		
		if (!isAddBcc) {
			btnLinkBcc.setCaption("Remove Bcc");
			
			inputLayout.removeComponent(btnLinkCc);
			inputLayout.removeComponent(btnLinkBcc);
			
			if (tokenFieldMailCc == null) {
				inputLayout.addComponent(createTextFieldMail("Bcc:", tokenFieldMailBcc), 0, 1);
				
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 2, 1);
			} else {
				inputLayout.addComponent(createTextFieldMail("Bcc:", tokenFieldMailBcc), 0, 2);
				
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 1, 2);
			}
			inputLayout.setComponentAlignment(btnLinkBcc, Alignment.MIDDLE_CENTER);
			inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);
			
		} else {
			btnLinkBcc.setCaption("Add Bcc");
			tokenFieldMailBcc = null;
			inputLayout.removeComponent(tokenFieldMailBcc);
			inputLayout.removeComponent(btnLinkBcc);
			
			if (tokenFieldMailCc == null) {
				inputLayout.removeComponent(0, 1);
				inputLayout.removeComponent(2, 1);
			} else {
				inputLayout.removeComponent(0, 2);
			}
		}
		checkToReInitCcBcc();
		
		isAddBcc = !isAddBcc;
	}
}
