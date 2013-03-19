package com.esofthead.mycollab.vaadin.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.core.utils.EmailValidator;
import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.FileEmailAttachmentSource;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

public class FeedbackWindow extends Window {
	private static final long serialVersionUID = 1L;
	private TextField emailTextField;
	private TextField subjectTextField;
	private TextField emailNameTextField;

	public FeedbackWindow() {
		initLayout();
	}

	private void initLayout() {
		this.setWidth(Sizeable.SIZE_UNDEFINED, 0);
		this.setHeight("450px");
		this.setCaption("Send us feedback for MyCollab ");
		initUI();
		center();
		this.setModal(true);
	}

	private void initDefaultData() {
		if (AppContext.getSession() != null) {
			String name = AppContext.getSession().getDisplayName().equals("") ? ""
					: AppContext.getSession().getDisplayName();
			String email = (AppContext.getSession().getEmail() == null && AppContext
					.getSession().getEmail().equals("")) ? "" : AppContext
					.getSession().getEmail();
			emailNameTextField.setValue(name);
			emailTextField.setValue(email);
		}
	}

	private void initUI() {
		GridLayout mainLayout = new GridLayout(2, 5);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		emailNameTextField = new TextField();
		emailNameTextField.setWidth("500px");
		Label emailName = new Label("Your name: ");

		mainLayout.addComponent(emailName, 0, 0);
		mainLayout.addComponent(emailNameTextField, 1, 0);

		emailTextField = new TextField();
		emailTextField.setWidth("500px");
		emailTextField.setRequired(true);
		Label emailLbl = new Label("Your email: ");

		mainLayout.addComponent(emailLbl, 0, 1);
		mainLayout.addComponent(emailTextField, 1, 1);

		subjectTextField = new TextField();
		subjectTextField.setWidth("500px");
		subjectTextField.setRequired(true);
		Label subjectLbl = new Label("Subject: ");

		mainLayout.addComponent(subjectLbl, 0, 2);
		mainLayout.addComponent(subjectTextField, 1, 2);

		final RichTextArea contentArea = new RichTextArea();
		Label contentLbl = new Label("Your feedback: ");

		mainLayout.addComponent(contentLbl, 0, 3);
		mainLayout.addComponent(contentArea, 1, 3);

		initDefaultData();

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setWidth("100%");

		final AttachmentPanel attachments = new AttachmentPanel();
		attachments.setWidth("350px");

		MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);

		Panel attachedFilepanel = new Panel();
		attachedFilepanel.setScrollable(true);
		attachedFilepanel.setHeight("80px");
		attachedFilepanel.setStyleName("noneBorder-panel");
		attachedFilepanel.getContent().setSizeUndefined();
		attachedFilepanel.addComponent(attachments);

		attachedFilepanel.addComponent(uploadExt);

		controlsLayout.addComponent(attachedFilepanel);
		controlsLayout.setComponentAlignment(attachedFilepanel,
				Alignment.BOTTOM_LEFT);
		controlsLayout.setExpandRatio(attachedFilepanel, 1.0f);

		controlsLayout.setSpacing(true);

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

		Button sendBtn = new Button("Send", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String email = emailTextField.getValue().toString().trim();
				String subject = subjectTextField.getValue().toString().trim();
				EmailValidator emailValidator = new EmailValidator();
				if (!email.equals("") && !subject.equals("") && emailValidator.validate(email)) {
					ExtMailService systemMailService = AppContext
							.getSpringBean(ExtMailService.class);
					List<File> listFile = attachments.getListFile();
					List<EmailAttachementSource> emailAttachmentSource = null;
					if (listFile != null && listFile.size() > 0) {
						emailAttachmentSource = new ArrayList<EmailAttachementSource>();
						for (File file : listFile) {
							emailAttachmentSource
									.add(new FileEmailAttachmentSource(file));
						}
					}

					String nameEmailFrom = emailNameTextField.getValue()
							.toString().trim();
					nameEmailFrom = nameEmailFrom.equals("") ? email
							: nameEmailFrom;
					String toEmail = ApplicationProperties
							.getProperty(ApplicationProperties.MAIL_SENDTO);

					FeedbackWindow.this.close();

					systemMailService.sendHTMLMail(email, nameEmailFrom,
							new String[] { toEmail }, new String[] { toEmail },
							subject, contentArea.getValue().toString(),
							emailAttachmentSource);

				} else {
					MessageBox mb = new MessageBox(
							AppContext.getApplication().getMainWindow(),
							"Warming!",
							MessageBox.Icon.WARN,
							"The email field and subject field must be not empty! Please fulfil them before pressing enter button.",
							new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show();
				}
			}
		});
		sendBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(sendBtn);
		controlsLayout.setComponentAlignment(sendBtn, Alignment.MIDDLE_RIGHT);
		mainLayout.addComponent(controlsLayout, 0, 4, 1, 4);

		this.setContent(mainLayout);
	}
}
