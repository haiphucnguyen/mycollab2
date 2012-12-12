package com.esofthead.mycollab.module.crm.view.account;

import org.vaadin.openesignforms.ckeditor.CKEditorConfig;
import org.vaadin.openesignforms.ckeditor.CKEditorTextField;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.SimpleNote;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class NoteListItems extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private String type;
	private Integer typeid;

	private BeanList<NoteService, NoteSearchCriteria, SimpleNote> noteList;

	private NoteService noteService;

	private TextField noteTextField;
	private NoteEditor noteEditor;

	public NoteListItems(String title, String type, Integer typeid) {
		this.setWidth("600px");
		this.setSpacing(true);
		this.setMargin(true);

		noteService = AppContext.getSpringBean(NoteService.class);
		this.type = type;
		this.typeid = typeid;

		this.addComponent(new Label(title));

		noteTextField = new TextField();
		noteTextField.setWidth("300px");
		noteTextField.addListener(new FieldEvents.FocusListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {
				noteEditor = new NoteEditor();
				NoteListItems.this.replaceComponent(noteTextField, noteEditor);
			}
		});
		this.addComponent(noteTextField);

		noteList = new BeanList<NoteService, NoteSearchCriteria, SimpleNote>(
				noteService, new NoteRowDisplayHandler());
		this.addComponent(noteList);
		displayNotes();
	}

	private void displayNotes() {
		NoteSearchCriteria searchCriteria = new NoteSearchCriteria();
		searchCriteria.setType(new StringSearchField(SearchField.AND, type));
		noteList.setSearchCriteria(searchCriteria);
	}

	private static class NoteRowDisplayHandler implements
			RowDisplayHandler<SimpleNote> {

		@Override
		public Component generateRow(SimpleNote obj, int rowIndex) {
			VerticalLayout noteLayout = new VerticalLayout();
			if (obj.getSubject() != null && !obj.getSubject().equals("")) {
				noteLayout.addComponent(new Label(obj.getSubject()));
			}

			noteLayout.addComponent(new Label(obj.getNote(),
					Label.CONTENT_XHTML));

			HorizontalLayout footer = new HorizontalLayout();
			footer.addComponent(new Label("Posted by "
					+ obj.getCreateduser() + " on "
					+ obj.getCreatedtime()));
			noteLayout.addComponent(footer);
			return noteLayout;
		}

	}

	private class NoteEditor extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private CKEditorTextField noteArea;

		public NoteEditor() {
			super();
			this.setSpacing(true);
			this.setMargin(true);
			this.setWidth("600px");

			CKEditorConfig config = new CKEditorConfig();
			config.useCompactTags();
			config.disableElementsPath();
			config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
			config.disableSpellChecker();
			config.setToolbarCanCollapse(false);
			config.setWidth("100%");

			noteArea = new CKEditorTextField(config);
			this.addComponent(noteArea);

			HorizontalLayout controls = new HorizontalLayout();
			Button saveBtn = new Button("Save", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Note note = new Note();
					note.setCreateduser(AppContext.getUsername());
					note.setNote((String) noteArea.getValue());
					note.setSaccountid(AppContext.getAccountId());
					note.setSubject("");
					note.setType(type);
					note.setTypeid(typeid);
					noteService.saveWithSession(note, AppContext.getUsername());
					displayNotes();
					NoteListItems.this.replaceComponent(noteEditor,
							noteTextField);
				}

			});
			controls.addComponent(saveBtn);

			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					NoteListItems.this.replaceComponent(noteEditor,
							noteTextField);
				}

			});
			controls.addComponent(cancelBtn);

			this.addComponent(controls);
		}
	}
}
