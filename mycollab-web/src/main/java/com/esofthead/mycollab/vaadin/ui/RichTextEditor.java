package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.openesignforms.ckeditor.CKEditorConfig;
import org.vaadin.openesignforms.ckeditor.CKEditorTextField;

public class RichTextEditor extends CKEditorTextField {
	private static final long serialVersionUID = 1L;
	private static CKEditorConfig config;
	
	static {
		config = new CKEditorConfig();
		config.useCompactTags();
		config.disableElementsPath();
		config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
		config.disableSpellChecker();
		config.setToolbarCanCollapse(false);
		config.setWidth("100%");
	}
	public RichTextEditor() {
		super(config);
		
	}
}
