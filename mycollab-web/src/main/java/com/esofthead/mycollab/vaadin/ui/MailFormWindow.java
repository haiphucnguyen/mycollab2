package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.openesignforms.ckeditor.CKEditorConfig;
import org.vaadin.openesignforms.ckeditor.CKEditorTextField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class MailFormWindow extends Window {
	private static final long serialVersionUID = 1L;

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
		inputLayout.addComponent(new TextField(), "To", 0, 0, "350px");
		inputLayout.addComponent(new TextField(), "Cc", 0, 1, "350px");
		inputLayout.addComponent(new TextField(), "Bcc", 0, 2, "350px");

		inputLayout.addComponent(new VerticalLayout(), null, 1, 0, 1, 3);
		inputLayout.addComponent(new TextField(), "Subject", 0, 3, 2, 1);

		CKEditorConfig config = new CKEditorConfig();
		config.useCompactTags();
		config.disableElementsPath();
		config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
		config.disableSpellChecker();
		config.setToolbarCanCollapse(false);
		config.setWidth("100%");

		CKEditorTextField noteArea = new CKEditorTextField(config);
		noteArea.setWidth("800px");
		inputLayout.addComponent(noteArea, null, 0, 4, 2, 1);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		Label emptySpace = new Label("");
		controlsLayout.addComponent(emptySpace);
		controlsLayout.setExpandRatio(emptySpace, 1.0f);

		controlsLayout.setWidth("100px");
		controlsLayout.setSpacing(true);
		Button sendBtn = new Button("Send");
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
