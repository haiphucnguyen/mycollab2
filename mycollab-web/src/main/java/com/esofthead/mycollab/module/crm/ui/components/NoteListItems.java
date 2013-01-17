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
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.SimpleNote;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatar;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import java.util.GregorianCalendar;
import java.util.List;
import org.vaadin.easyuploads.MultiFileUploadExt;

public class NoteListItems extends Depot {

    private static final long serialVersionUID = 1L;
    private String type;
    private Integer typeid;
    private BeanList<NoteService, NoteSearchCriteria, SimpleNote> noteList;
    private final NoteService noteService;
    private Button createBtn;

    public NoteListItems(String title) {
        this(title, "", 0);
    }

    public NoteListItems(String title, String type, Integer typeid) {
        super(title, new VerticalLayout());
        this.setWidth("900px");
        this.setMargin(false);

        noteService = AppContext.getSpringBean(NoteService.class);
        this.type = type;
        this.typeid = typeid;

        initUI();
    }

    public void showNotes(String type, int typeid) {
        this.type = type;
        this.typeid = typeid;
        displayNotes();
    }

    private void initUI() {
        final VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);
        createBtn = new Button("New Note", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                contentContainer.replaceComponent(createBtn, new NoteEditor());
            }
        });

        createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
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
        searchCriteria.setTypeid(new NumberSearchField(typeid));
        noteList.setSearchCriteria(searchCriteria);
    }

    public static class NoteRowDisplayHandler implements
            RowDisplayHandler<SimpleNote>, ReloadableComponent {

        private VerticalLayout noteContentLayout;
        private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
        private CommentInput commentInput;
        private SimpleNote note;
        private Button replyBtn;

        @Override
        public Component generateRow(final SimpleNote note, int rowIndex) {
            this.note = note;

            HorizontalLayout noteContainer = new HorizontalLayout();
            noteContainer.addComponent(new UserAvatar(note.getCreateduser(), note.getCreateUserFullName()));

            noteContentLayout = new VerticalLayout();
            noteContainer.addComponent(noteContentLayout);

            if (note.getSubject() != null && !note.getSubject().equals("")) {
                noteContentLayout.addComponent(new Label(note.getSubject()));
            }

            HorizontalLayout noteHeader = new HorizontalLayout();

            UserLink userLink = new UserLink(note.getCreateduser(), note.getCreateUserFullName());
            noteHeader.addComponent(userLink);
            noteHeader.setComponentAlignment(userLink, Alignment.MIDDLE_LEFT);

            Label timeLbl = new Label(" - " + DateTimeUtils.getStringDateFromNow(note.getCreatedtime()));
            noteHeader.addComponent(timeLbl);
            noteHeader.setComponentAlignment(timeLbl, Alignment.MIDDLE_LEFT);

            noteContentLayout.addComponent(noteHeader);

            Label noteContent = new Label(note.getNote(), Label.CONTENT_XHTML);
            noteContent.setWidth("100%");
            noteContentLayout.addComponent(noteContent);


            List<Attachment> attachments = note.getAttachments();
            if (attachments != null && !attachments.isEmpty()) {
                noteContentLayout.addComponent(new LazyLoadWrapper(new AttachmentDisplayComponent(attachments)));
            }

            HorizontalLayout footer = new HorizontalLayout();
            footer.setSpacing(true);
            footer.setMargin(true);
            footer.setWidth("100%");

            replyBtn = new Button("Reply", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    int compIndex = noteContentLayout.getComponentIndex(commentList);
                    if (compIndex >= 0) {
                        commentInput = new CommentInput(NoteRowDisplayHandler.this, CommentTypeConstants.CRM_NOTE, note.getId(), true);
                        noteContentLayout.addComponent(commentInput, compIndex);
                        replyBtn.setVisible(false);
                    }
                }
            });

            replyBtn.setStyleName("link");
            footer.addComponent(replyBtn);
            footer.setComponentAlignment(replyBtn, Alignment.MIDDLE_LEFT);
            noteContentLayout.addComponent(footer);

            commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(AppContext.getSpringBean(CommentService.class), CommentRowDisplayHandler.class);
            noteContentLayout.addComponent(commentList);
            displayComments();
            return noteContainer;
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
                int compIndex = noteContentLayout.getComponentIndex(commentInput);
                if (compIndex >= 0) {
                    noteContentLayout.removeComponent(commentInput);
                    commentInput = null;
                    replyBtn.setVisible(true);
                }
            }
        }
    }

    private class NoteEditor extends VerticalLayout {

        private static final long serialVersionUID = 1L;
        private final RichTextArea noteArea;

        public NoteEditor() {
            super();
            this.setSpacing(true);
            this.setMargin(true);
            this.setWidth("600px");

            final AttachmentPanel attachments = new AttachmentPanel();

            noteArea = new RichTextArea();
            noteArea.setWidth("560px");
            this.addComponent(noteArea);
            this.addComponent(attachments);

            HorizontalLayout controls = new HorizontalLayout();
            controls.setSpacing(true);
            controls.setWidth("100%");
            
            MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);
            controls.addComponent(uploadExt);
            controls.setComponentAlignment(uploadExt, Alignment.MIDDLE_LEFT);
            
            Label emptySpace = new Label();
            controls.addComponent(emptySpace);
            controls.setExpandRatio(emptySpace, 1.0f);
            

            Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    addCreateBtn();
                }
            });
            cancelBtn.setStyleName("link");
            controls.addComponent(cancelBtn);
            controls.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);
            
            Button saveBtn = new Button("Post", new Button.ClickListener() {
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
                    int noteid = noteService.saveWithSession(note, AppContext.getUsername());
                    attachments.saveContentsToRepo(AttachmentConstants.CRM_NOTE_TYPE, noteid);
                    displayNotes();
                    addCreateBtn();
                }
            });
            saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            controls.addComponent(saveBtn);
            controls.setComponentAlignment(saveBtn, Alignment.MIDDLE_RIGHT);

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
