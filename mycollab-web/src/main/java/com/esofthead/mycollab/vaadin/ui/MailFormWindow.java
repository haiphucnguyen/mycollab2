package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.vaadin.openesignforms.ckeditor.CKEditorConfig;

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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MailFormWindow extends Window {
	private static final long serialVersionUID = 1L;

	private TextField mailTo;
	private TextField mailCc;
	private TextField mailBcc;

	public MailFormWindow() {
		this.setWidth("800px");
		this.setHeight("500px");
		initUI();
	}

	private void initUI() {
		GridFormLayoutHelper inputLayout = new GridFormLayoutHelper(2, 6);
		inputLayout.getLayout().setSpacing(true);
		inputLayout.getLayout().setMargin(true);

		inputLayout.getLayout().setWidth("100%");
		mailTo = new TextField();
		inputLayout.addComponent(mailTo, "To", 0, 0, "350px");

		mailCc = new TextField();
		inputLayout.addComponent(mailCc, "Cc", 0, 1, "350px");

		mailBcc = new TextField();
		inputLayout.addComponent(mailBcc, "Bcc", 0, 2, "350px");

		VerticalLayout attachmentLayout = new VerticalLayout();
		attachmentLayout.addComponent(new Label("Attachment"));
		inputLayout.addComponent(attachmentLayout, null, 1, 0, 1, 3);

		final TextField subject = new TextField();
		inputLayout.addComponent(subject, "Subject", 0, 3, 2, 1);

		CKEditorConfig config = new CKEditorConfig();
		config.useCompactTags();
		config.disableElementsPath();
		config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
		config.disableSpellChecker();
		config.setToolbarCanCollapse(false);
		config.setWidth("100%");

		final RichTextEditor noteArea = new RichTextEditor();
		noteArea.setWidth("800px");
		inputLayout.addComponent(noteArea, null, 0, 4, 2, 1);

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
							.parseEmailField((String) mailTo.getValue());
					List<MailRecipientField> ccFields = ParsingUtils
							.parseEmailField((String) mailCc.getValue());
					List<MailRecipientField> bccFields = ParsingUtils
							.parseEmailField((String) mailBcc.getValue());

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
		inputLayout.addComponent(controlsLayout, null, 0, 5, 2, 1);

		this.setContent(inputLayout.getLayout());
	}

}
