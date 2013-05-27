package com.esofthead.mycollab.module.crm.ui.components;

import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.easyuploads.MultiFileUploadExt;

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
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

public class NoteListItems extends Depot {

	private class NoteEditor extends VerticalLayout {

		private static final long serialVersionUID = 1L;
		private final RichTextArea noteArea;

		public NoteEditor() {
			super();
			setSpacing(true);
			this.setMargin(true);
			this.setWidth("600px");

			final AttachmentPanel attachments = new AttachmentPanel();

			noteArea = new RichTextArea();
			noteArea.setWidth("100%");
			this.addComponent(noteArea);
			this.addComponent(attachments);

			final HorizontalLayout controls = new HorizontalLayout();
			controls.setSpacing(true);
			controls.setWidth("100%");

			final MultiFileUploadExt uploadExt = new MultiFileUploadExt(
					attachments);
			controls.addComponent(uploadExt);
			controls.setComponentAlignment(uploadExt, Alignment.MIDDLE_LEFT);

			final Label emptySpace = new Label();
			controls.addComponent(emptySpace);
			controls.setExpandRatio(emptySpace, 1.0f);

			final Button cancelBtn = new Button("Cancel",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							addCreateBtn();
						}
					});
			cancelBtn.setStyleName("link");
			controls.addComponent(cancelBtn);
			controls.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);

			final Button saveBtn = new Button("Post",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							final Note note = new Note();
							note.setCreateduser(AppContext.getUsername());
							note.setNote((String) noteArea.getValue());
							note.setSaccountid(AppContext.getAccountId());
							note.setSubject("");
							note.setType(type);
							note.setTypeid(typeid);
							note.setCreatedtime(new GregorianCalendar()
									.getTime());
							note.setLastupdatedtime(new GregorianCalendar()
									.getTime());
							final int noteid = noteService.saveWithSession(
									note, AppContext.getUsername());
							attachments.saveContentsToRepo(
									AttachmentConstants.CRM_NOTE_TYPE, noteid);
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

	public static class NoteRowDisplayHandler implements
			RowDisplayHandler<SimpleNote>, ReloadableComponent {

		private VerticalLayout noteContentLayout;
		private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
		private CommentInput commentInput;
		private SimpleNote note;
		private Button replyBtn;

		@Override
		public void cancel() {
			if (commentInput != null) {
				final int compIndex = noteContentLayout
						.getComponentIndex(commentInput);
				if (compIndex >= 0) {
					noteContentLayout.removeComponent(commentInput);
					commentInput = null;
					replyBtn.setVisible(true);
				}
			}
		}

		private Component constructNoteHeader(final SimpleNote note) {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setStyleName("message");
			layout.setWidth("100%");
			layout.addComponent(UserAvatarControlFactory.createUserAvatarLink(
					note.getCreateduser(), note.getCreateUserFullName()));

			final CssLayout rowLayout = new CssLayout();
			rowLayout.setStyleName("message-container");
			rowLayout.setWidth("100%");

			final HorizontalLayout messageHeader = new HorizontalLayout();
			messageHeader.setStyleName("message-header");
			final VerticalLayout leftHeader = new VerticalLayout();
			final Label username = new Label(note.getCreateUserFullName());
			username.setStyleName("user-name");
			leftHeader.addComponent(username);

			final VerticalLayout rightHeader = new VerticalLayout();
			final Label timePostLbl = new Label(
					DateTimeUtils.getStringDateFromNow(note.getCreatedtime()));
			timePostLbl.setSizeUndefined();
			timePostLbl.setStyleName("time-post");
			rightHeader.addComponent(timePostLbl);

			messageHeader.addComponent(leftHeader);
			messageHeader.setExpandRatio(leftHeader, 1.0f);
			messageHeader.addComponent(timePostLbl);
			messageHeader.setWidth("100%");

			rowLayout.addComponent(messageHeader);

			final Label messageContent = new Label(note.getNote(),
					Label.CONTENT_XHTML);
			messageContent.setStyleName("message-body");
			rowLayout.addComponent(messageContent);

			final List<Attachment> attachments = note.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				rowLayout.addComponent(new AttachmentDisplayComponent(
						attachments));
			}

			final VerticalLayout messageFooter = new VerticalLayout();
			replyBtn = new Button("Reply", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final int compIndex = noteContentLayout
							.getComponentIndex(commentList);
					if (compIndex >= 0) {
						commentInput = new CommentInput(
								NoteRowDisplayHandler.this,
								CommentTypeConstants.CRM_NOTE, note.getId(),
								true, false);
						noteContentLayout.addComponent(commentInput, compIndex);
						replyBtn.setVisible(false);
					}
				}
			});

			replyBtn.setStyleName("link");
			messageFooter.addComponent(replyBtn);
			messageFooter.setWidth("100%");
			messageFooter.setComponentAlignment(replyBtn, Alignment.TOP_RIGHT);
			rowLayout.addComponent(messageFooter);

			layout.addComponent(rowLayout);
			layout.setExpandRatio(rowLayout, 1.0f);
			return layout;
		}

		private void displayComments() {
			final CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
			searchCriteria.setType(new StringSearchField(
					CommentTypeConstants.CRM_NOTE));
			searchCriteria.setTypeid(new NumberSearchField(note.getId()));
			commentList.setSearchCriteria(searchCriteria);
		}

		@Override
		public Component generateRow(final SimpleNote note, final int rowIndex) {
			this.note = note;

			noteContentLayout = new VerticalLayout();

			noteContentLayout.addComponent(constructNoteHeader(note));

			commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(
					AppContext.getSpringBean(CommentService.class),
					CommentRowDisplayHandler.class);
			commentList.setWidth("1050px");
			noteContentLayout.addComponent(commentList);
			noteContentLayout.setComponentAlignment(commentList,
					Alignment.TOP_RIGHT);
			commentList.loadItems(note.getComments());

			return noteContentLayout;
		}

		@Override
		public void reload() {
			displayComments();
			cancel();
		}
	}

	private static final long serialVersionUID = 1L;
	private String type;
	private Integer typeid;
	private BeanList<NoteService, NoteSearchCriteria, SimpleNote> noteList;

	private final NoteService noteService;

	private Button createBtn;

	public NoteListItems(final String title) {
		this(title, "", 0);
	}

	public NoteListItems(final String title, final String type,
			final Integer typeid) {
		super(title, new VerticalLayout(), "100%");
		this.setWidth("100%");
		this.setMargin(false);
		addStyleName("note-list");

		noteService = AppContext.getSpringBean(NoteService.class);
		this.type = type;
		this.typeid = typeid;

		initUI();
	}

	private void addCreateBtn() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;
		final Component component = contentContainer.getComponent(0);
		if (component instanceof NoteEditor) {
			contentContainer.replaceComponent(component, createBtn);
		}
	}

	private void displayNotes() {
		final NoteSearchCriteria searchCriteria = new NoteSearchCriteria();
		searchCriteria.setType(new StringSearchField(SearchField.AND, type));
		searchCriteria.setTypeid(new NumberSearchField(typeid));
		noteList.setSearchCriteria(searchCriteria);
	}

	private void initUI() {
		final VerticalLayout contentContainer = (VerticalLayout) bodyContent;
		contentContainer.setMargin(true);
		contentContainer.setSpacing(true);
		createBtn = new Button("New Note", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
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

	public void showNotes(final String type, final int typeid) {
		this.type = type;
		this.typeid = typeid;
		displayNotes();
	}
}
