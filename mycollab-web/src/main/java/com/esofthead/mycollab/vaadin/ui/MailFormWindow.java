/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.module.mail.EmailAttachementSource;
import com.esofthead.mycollab.module.mail.FileEmailAttachmentSource;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class MailFormWindow extends Window {

	private static final long serialVersionUID = 1L;
	private EmailTokenField tokenFieldMailTo;
	private EmailTokenField tokenFieldMailCc = new EmailTokenField();
	private final EmailTokenField tokenFieldMailBcc = new EmailTokenField();
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
		this.setHeight(Sizeable.SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		initUI();
		center();
		this.setModal(true);
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

		btnLinkCc.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				buttonLinkCcClick(event);
			}
		});

		btnLinkBcc.addClickListener(new Button.ClickListener() {

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
		layout.setWidth("100%");
		layout.setExpandRatio(component, 1.0f);
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
		inputLayout.setWidth("100%");
		inputLayout.setColumnExpandRatio(0, 1.0f);

		inputPanel.addComponent(inputLayout);

		mainLayout.addComponent(inputPanel);

		tokenFieldMailTo = new EmailTokenField();
		tokenFieldMailTo.setRequired(true);

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
		subject.setRequired(true);
		subject.setWidth("100%");
		subjectField = createTextFieldMail("Subject:", subject);
		inputLayout.addComponent(subjectField, 0, 1);

		initButtonLinkCcBcc();

		ccField = createTextFieldMail("Cc:", tokenFieldMailCc);
		bccField = createTextFieldMail("Bcc:", tokenFieldMailBcc);

		final RichTextArea noteArea = new RichTextArea();
		noteArea.setWidth("100%");
		noteArea.setHeight("200px");
		mainLayout.addComponent(noteArea, 0, 1);
		mainLayout.setComponentAlignment(noteArea, Alignment.MIDDLE_CENTER);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setWidth("100%");

		final AttachmentPanel attachments = new AttachmentPanel();
		attachments.setWidth("500px");

		MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);

		controlsLayout.addComponent(uploadExt);
		controlsLayout.setExpandRatio(uploadExt, 1.0f);

		controlsLayout.setSpacing(true);

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

		Button sendBtn = new Button("Send", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (tokenFieldMailTo.getListRecipient().size() <= 0
						|| subject.getValue().equals("")) {
					NotificationUtil
							.showErrorNotification("To Email field and Subject field must be not empty! Please fulfil them before sending email.");
					return;
				}
				if (AppContext.getSession().getEmail() != null
						&& AppContext.getSession().getEmail().length() > 0) {
					ExtMailService systemMailService = ApplicationContextUtil
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

					systemMailService.sendHTMLMail(AppContext.getSession()
							.getEmail(), AppContext.getSession()
							.getDisplayName(), tokenFieldMailTo
							.getListRecipient(), tokenFieldMailCc
							.getListRecipient(), tokenFieldMailBcc
							.getListRecipient(), subject.getValue().toString(),
							noteArea.getValue().toString(),
							emailAttachmentSource);
					MailFormWindow.this.close();
				} else {
					NotificationUtil
							.showErrorNotification("Your email is empty value, please fulfil it before sending email!");
				}
			}
		});
		sendBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(sendBtn);
		controlsLayout.setComponentAlignment(sendBtn, Alignment.MIDDLE_RIGHT);
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
			tokenFieldMailCc.removeAllRecipients();
			btnLinkCc.setCaption("Add Cc");

			if (isAddBcc) {
				inputLayout.addComponent(bccField, 0, 1);
				inputLayout.addComponent(btnLinkBcc, 1, 1);
				inputLayout.addComponent(btnLinkCc, 2, 1);
				inputLayout.addComponent(subjectField, 0, 2);
			} else {
				inputLayout.addComponent(btnLinkBcc, 1, 0);
				inputLayout.addComponent(btnLinkCc, 2, 0);
				inputLayout.addComponent(subjectField, 0, 1);
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
			tokenFieldMailBcc.removeAllRecipients();
			if (isAddCc) {
				inputLayout.addComponent(ccField, 0, 1);
				inputLayout.addComponent(btnLinkCc, 1, 1);
				inputLayout.addComponent(btnLinkBcc, 2, 1);
				inputLayout.addComponent(subjectField, 0, 2);
			} else {
				inputLayout.addComponent(btnLinkBcc, 1, 0);
				inputLayout.addComponent(btnLinkCc, 2, 0);
				inputLayout.addComponent(subjectField, 0, 1);
			}
		}
		inputLayout.setComponentAlignment(btnLinkBcc, Alignment.MIDDLE_CENTER);
		inputLayout.setComponentAlignment(btnLinkCc, Alignment.MIDDLE_CENTER);
		isAddBcc = !isAddBcc;
		checkToReInitCcBcc();
	}
}
