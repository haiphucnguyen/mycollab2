package com.esofthead.mycollab.module.crm.ui.components;

import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.openesignforms.ckeditor.CKEditorTextField;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.SimpleNote;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.RichTextEditor;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class NoteListItems extends Depot {
	private static final long serialVersionUID = 1L;

	private String type;
	private Integer typeid;

	private BeanList<NoteService, NoteSearchCriteria, SimpleNote> noteList;

	private NoteService noteService;
	private NoteEditor noteEditor;

	private Button createBtn;

	public NoteListItems(String title, String type, Integer typeid) {
		super(title, new VerticalLayout());
		this.setWidth("900px");
		this.setSpacing(true);
		this.setMargin(true);

		noteService = AppContext.getSpringBean(NoteService.class);
		this.type = type;
		this.typeid = typeid;

		initUI();
	}

	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) content;
		createBtn = new Button("New Note", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				contentContainer.replaceComponent(createBtn, new NoteEditor());
			}
		});

		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createBtn.setStyleName(BaseTheme.BUTTON_LINK);
		contentContainer.addComponent(createBtn);

		noteList = new BeanList<NoteService, NoteSearchCriteria, SimpleNote>(
				noteService, new NoteRowDisplayHandler());
		contentContainer.addComponent(noteList);
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
			footer.addComponent(new Label("Posted by " + obj.getCreateduser()
					+ " on " + obj.getCreatedtime()));
			noteLayout.addComponent(footer);

			List<Attachment> attachments = obj.getAttachments();
			if (attachments != null && attachments.size() != 0) {
				for (Attachment attachment : attachments) {
					String docName = attachment.getDocumentpath();
					int lastIndex = docName.lastIndexOf("/");
					if (lastIndex != -1) {
						docName = docName.substring(lastIndex + 1,
								docName.length());
					}

					HorizontalLayout attachmentLayout = new HorizontalLayout();
					
					Button attachmentLink = new Button(docName);
					attachmentLink.setStyleName(BaseTheme.BUTTON_LINK);
					attachmentLayout.addComponent(attachmentLink);
					
					attachmentLayout.setSpacing(true);
					attachmentLayout.setMargin(false, false, false, true);

					Embedded trashBtn = new Embedded(null, new ThemeResource(
							"icons/16/trash.png"));
					attachmentLayout.addComponent(trashBtn);

					Embedded downloadBtn = new Embedded(null,
							new ThemeResource("icons/16/download.png"));
					attachmentLayout.addComponent(downloadBtn);
					
					noteLayout.addComponent(attachmentLayout);
				}
			}
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
			this.setWidth("900px");


			noteArea = new RichTextEditor();
			noteArea.setWidth("800px");
			this.addComponent(noteArea);

			final AttachmentPanel attachments = new AttachmentPanel();
			this.addComponent(attachments);

			HorizontalLayout controls = new HorizontalLayout();
			controls.setSpacing(true);
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
					note.setCreatedtime(new GregorianCalendar().getTime());
					note.setLastupdatedtime(new GregorianCalendar().getTime());
					int noteid = noteService.insertNoteExt(note);
					attachments.saveContentsToRepo("crm-note", noteid);
					displayNotes();
					addCreateBtn();
				}

			});
			controls.addComponent(saveBtn);

			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					addCreateBtn();
				}

			});
			controls.addComponent(cancelBtn);

			this.addComponent(controls);
		}
	}

	private void addCreateBtn() {
		VerticalLayout contentContainer = (VerticalLayout) content;
		Component component = contentContainer.getComponent(0);
		if (component instanceof NoteEditor) {
			contentContainer.replaceComponent(component, createBtn);
		}
	}
}
