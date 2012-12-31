package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.ui.components.CommentInput;
import com.esofthead.mycollab.common.ui.components.CommentRowDisplayHandler;
import com.esofthead.mycollab.common.ui.components.ReloadableComponent;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.SimpleNote;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.RichTextEditor;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import java.util.GregorianCalendar;
import java.util.List;
import org.vaadin.openesignforms.ckeditor.CKEditorTextField;

public class NoteListItems extends Depot {

    private static final long serialVersionUID = 1L;
    private final String type;
    private final Integer typeid;
    private BeanList<NoteService, NoteSearchCriteria, SimpleNote> noteList;
    private final NoteService noteService;
    private NoteEditor noteEditor;
    private Button createBtn;

    public NoteListItems(String title, String type, Integer typeid) {
        super(title, new VerticalLayout());
        this.setWidth("900px");
        this.setSpacing(false);
        this.setMargin(false);

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
                noteService, NoteRowDisplayHandler.class);
        noteList.setStyleName("noteList");
        contentContainer.addComponent(noteList);
        displayNotes();
    }

    private void displayNotes() {
        NoteSearchCriteria searchCriteria = new NoteSearchCriteria();
        searchCriteria.setType(new StringSearchField(SearchField.AND, type));
        noteList.setSearchCriteria(searchCriteria);
    }

    public static class NoteRowDisplayHandler implements
            RowDisplayHandler<SimpleNote>, ReloadableComponent {

        private CssLayout noteLayout;
        private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
        private CommentInput commentInput;
        private SimpleNote note;
        private Button replyBtn;

        @Override
        public Component generateRow(final SimpleNote note, int rowIndex) {
            this.note = note;

            noteLayout = new CssLayout();
            noteLayout.setStyleName("noteRow");

            if (note.getSubject() != null && !note.getSubject().equals("")) {
                noteLayout.addComponent(new Label(note.getSubject()));
            }

            Label noteContent = new Label(note.getNote(), Label.CONTENT_XHTML);
            noteContent.setStyleName("noteContent");
            noteContent.setWidth("100%");
            noteLayout.addComponent(noteContent);

            HorizontalLayout footer = new HorizontalLayout();
            footer.setSpacing(true);
            footer.setMargin(true);
            footer.setWidth("100%");

            replyBtn = new Button("Reply", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    int compIndex = noteLayout.getComponentIndex(commentList);
                    if (compIndex >= 0) {
                        commentInput = new CommentInput(NoteRowDisplayHandler.this, CommentTypeConstants.CRM_NOTE, note.getId(), true);
                        noteLayout.addComponent(commentInput, compIndex);
                        replyBtn.setVisible(false);
                    }
                }
            });

            replyBtn.setStyleName("link");
            footer.addComponent(replyBtn);
            footer.setComponentAlignment(replyBtn, Alignment.MIDDLE_LEFT);
            Label metadata = new Label("Posted by " + note.getCreateUserFullName()
                    + " on " + AppContext.formatDateToHumanRead(note.getCreatedtime()));
            footer.addComponent(metadata);

            metadata.setStyleName("metadata");
            footer.setComponentAlignment(metadata, Alignment.MIDDLE_LEFT);
            noteLayout.addComponent(footer);

            List<Attachment> attachments = note.getAttachments();
            if (attachments != null && !attachments.isEmpty()) {
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

            commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(AppContext.getSpringBean(CommentService.class), CommentRowDisplayHandler.class);
            commentList.setMargin(true);
            noteLayout.addComponent(commentList);
            displayComments();
            return noteLayout;
        }

        private void displayComments() {
            CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
            searchCriteria.setType(new StringSearchField(CommentTypeConstants.CRM_NOTE));
            searchCriteria.setTypeid(new NumberSearchField(note.getId()));
            commentList.setSearchCriteria(searchCriteria);
        }

        @Override
        public void reload() {
            displayComments();
            cancel();
        }

        @Override
        public void cancel() {
            if (commentInput != null) {
                int compIndex = noteLayout.getComponentIndex(commentInput);
                if (compIndex >= 0) {
                    noteLayout.removeComponent(commentInput);
                    commentInput = null;
                    replyBtn.setVisible(true);
                }
            }
        }
    }

    private class NoteEditor extends VerticalLayout {

        private static final long serialVersionUID = 1L;
        private final CKEditorTextField noteArea;

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
                    attachments.saveContentsToRepo(AttachmentConstants.CRM_NOTE_TYPE, noteid);
                    displayNotes();
                    addCreateBtn();
                }
            });
            saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            controls.addComponent(saveBtn);

            Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    addCreateBtn();
                }
            });
            cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
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
