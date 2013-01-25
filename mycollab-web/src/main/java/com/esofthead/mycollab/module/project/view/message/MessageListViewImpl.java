package com.esofthead.mycollab.module.project.view.message;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.UserAvatar;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@ViewComponent
public class MessageListViewImpl extends AbstractView implements
		MessageListView, HasEditFormHandlers<Message> {

	private static final long serialVersionUID = 8433776359091397422L;
	private final PagedBeanList<MessageService, MessageSearchCriteria, SimpleMessage> tableItem;
	private Set<EditFormHandler<Message>> editFormHandlers;
	private MessageSearchCriteria searchCriteria;
	private final TopMessagePanel topMessagePanel;

	public MessageListViewImpl() {
		super();
		this.setSpacing(true);
		this.setWidth("100%");
		topMessagePanel = new TopMessagePanel();
		topMessagePanel.getSearchHandlers().addSearchHandler( new SearchHandler<MessageSearchCriteria>() {

			@Override
			public void onSearch(MessageSearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        });
		this.addComponent(topMessagePanel);
		tableItem = new PagedBeanList<MessageService, MessageSearchCriteria, SimpleMessage>(
				AppContext.getSpringBean(MessageService.class),
				new MessageRowDisplayHandler());
		this.addComponent(tableItem);
	}
	
	@Override
	public void setCriteria(MessageSearchCriteria criteria) {
		this.searchCriteria = criteria;
		topMessagePanel.createBasicLayout();
		tableItem.setSearchCriteria(searchCriteria);

	}

	private class MessageRowDisplayHandler implements
			RowDisplayHandler<SimpleMessage> {

		@Override
		public Component generateRow(final SimpleMessage message, int rowIndex) {
			HorizontalLayout messageLayout = new HorizontalLayout();
			messageLayout.setStyleName("message");
			messageLayout.setWidth("100%");
			messageLayout.addComponent(new UserAvatar(message.getPosteduser(),
					message.getFullPostedUserName()));

			CssLayout rowLayout = new CssLayout();
			rowLayout.setStyleName("message-container");
			rowLayout.setWidth("100%");
			Button title = new Button(message.getTitle(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new MessageEvent.GotoRead(
											MessageListViewImpl.this, message
													.getId()));
						}
					});
			title.setStyleName("link");

			HorizontalLayout messageHeader = new HorizontalLayout();
			messageHeader.setStyleName("message-header");
			VerticalLayout leftHeader = new VerticalLayout();

			Label username = new Label(message.getFullPostedUserName());
			username.setStyleName("user-name");
			leftHeader.addComponent(username);

			title.addStyleName("message-title");
			leftHeader.addComponent(title);

			VerticalLayout rightHeader = new VerticalLayout();
			Label timePostLbl = new Label(
					DateTimeUtils.getStringDateFromNow(message.getPosteddate()));
			timePostLbl.setSizeUndefined();
			timePostLbl.setStyleName("time-post");
			rightHeader.addComponent(timePostLbl);
			HorizontalLayout notification = new HorizontalLayout();
			notification.setStyleName("notification");
			notification.setSizeUndefined();
			if (message.getCommentsCount() > 0) {
				HorizontalLayout commentNotification = new HorizontalLayout();
				Label commentCountLbl = new Label(Integer.toString(message
						.getCommentsCount()));
				commentCountLbl.setStyleName("comment-count");
				commentCountLbl.setSizeUndefined();
				commentNotification.addComponent(commentCountLbl);
				Embedded commentIcon = new Embedded();
				commentIcon.setSource(new ThemeResource(
						"icons/16/project/message.png"));
				commentNotification.addComponent(commentIcon);

				notification.addComponent(commentNotification);

			}
			int attachmentCount = 1;
			if (attachmentCount > 0) {
				HorizontalLayout attachmentNotification = new HorizontalLayout();
				Label attachmentCountLbl = new Label(
						Integer.toString(attachmentCount));
				attachmentCountLbl.setStyleName("attachment-count");
				attachmentCountLbl.setSizeUndefined();
				attachmentNotification.addComponent(attachmentCountLbl);
				Embedded attachmentIcon = new Embedded();
				attachmentIcon.setSource(new ThemeResource(
						"icons/16/attachment.png"));
				attachmentNotification.addComponent(attachmentIcon);

				notification.addComponent(attachmentNotification);

			}
			rightHeader.addComponent(notification);
			rightHeader.setSizeUndefined();
			rightHeader.setComponentAlignment(notification,
					Alignment.MIDDLE_CENTER);

			messageHeader.addComponent(leftHeader);
			messageHeader.setExpandRatio(leftHeader, 1.0f);
			messageHeader.addComponent(rightHeader);
			messageHeader.setWidth("100%");

			rowLayout.addComponent(messageHeader);

			Label messageContent = new Label(message.getMessage(),
					Label.CONTENT_XHTML);
			messageContent.setStyleName("message-body");
			rowLayout.addComponent(messageContent);

			messageLayout.addComponent(rowLayout);
			messageLayout.setExpandRatio(rowLayout, 1.0f);

			return messageLayout;
		}
	}

	@SuppressWarnings({ "serial"})
	private class MessageSearchPanel extends
			GenericSearchPanel<MessageSearchCriteria> {
		private SimpleProject project;
		private MessageSearchCriteria messageSearchCriteria;
		private String textSearch = "";

		public MessageSearchPanel() {
			this.project = (SimpleProject) AppContext
					.getVariable(ProjectContants.PROJECT_NAME);
		}

		@Override
		public void attach() {
			super.attach();
			createBasicSearchLayout();
		}

		private void createBasicSearchLayout() {

			HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			final TextField nameField = new TextField();
			nameField.addListener(new TextChangeListener() {

				@Override
				public void textChange(TextChangeEvent event) {
					messageSearchCriteria = new MessageSearchCriteria();

					messageSearchCriteria
							.setProjectid(new NumberSearchField(
									SearchField.AND, project.getId()));

					textSearch = event.getText().toString()
							.trim();
					
					messageSearchCriteria.setMessage(new StringSearchField(
							textSearch));

					MessageSearchPanel.this
							.notifySearchHandler(messageSearchCriteria);
				}
			});

			nameField.setTextChangeEventMode(TextChangeEventMode.LAZY);
			nameField.setTextChangeTimeout(200);
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, nameField,
					Alignment.MIDDLE_RIGHT);

			Button searchBtn = new Button();
			searchBtn.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(Button.ClickEvent event) {
					messageSearchCriteria = new MessageSearchCriteria();

					messageSearchCriteria
							.setProjectid(new NumberSearchField(
									SearchField.AND, project
											.getId()));

					messageSearchCriteria
							.setMessage(new StringSearchField(
									textSearch));

					MessageSearchPanel.this
							.notifySearchHandler(messageSearchCriteria);
				}
			});
			searchBtn.setIcon(new ThemeResource("icons/22/search.png"));
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			basicSearchBody.addComponent(searchBtn);
			
			this.setCompositionRoot(basicSearchBody);
		}

	}

	private final class TopMessagePanel extends VerticalLayout {

		private static final long serialVersionUID = 1L;

		private MessageSearchPanel messageSearchPanel;

		public TopMessagePanel() {
			this.setWidth("100%");
			messageSearchPanel = new MessageSearchPanel();
			createBasicLayout();
		}

		public HasSearchHandlers<MessageSearchCriteria> getSearchHandlers() {
			return messageSearchPanel;
		}

		public void createBasicLayout() {
			this.removeAllComponents();

			HorizontalLayout layout = new HorizontalLayout();
			layout.setWidth("100%");
			Label lb = new Label("");
			layout.addComponent(lb);
			layout.setExpandRatio(lb, 1.0f);
			messageSearchPanel.setWidth("350px");

			layout.addComponent(messageSearchPanel);
			layout.setComponentAlignment(messageSearchPanel, Alignment.MIDDLE_CENTER);
			
			Button createAccountBtn = new Button("New Message",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							createAddMessageLayout();
						}
					});
			createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			createAccountBtn
					.setIcon(new ThemeResource("icons/16/addRecord.png"));

			layout.addComponent(createAccountBtn);
			layout.setComponentAlignment(createAccountBtn, Alignment.MIDDLE_CENTER);
			this.addComponent(layout);
		}

		private void createAddMessageLayout() {
			this.removeAllComponents();

			this.setSpacing(true);

			final RichTextArea ckEditorTextField = new RichTextArea();
			final AttachmentPanel attachments = new AttachmentPanel();
			final TextField titleField = new TextField();

			HorizontalLayout titleLayout = new HorizontalLayout();
			titleLayout.setSpacing(true);
			Label titleLbl = new Label("Title: ");

			titleField.setWidth("600px");
			titleField.setNullRepresentation("");
			titleField.setRequired(true);
			titleField.setRequiredError("Please enter a Title");

			titleLayout.addComponent(titleLbl);
			titleLayout.addComponent(titleField);

			this.addComponent(titleLayout);

			ckEditorTextField.setWidth("100%");
			this.addComponent(ckEditorTextField);
			this.addComponent(attachments);

			HorizontalLayout controls = new HorizontalLayout();
			controls.setWidth("100%");
			controls.setSpacing(true);

			MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);
			controls.addComponent(uploadExt);
			controls.setExpandRatio(uploadExt, 1.0f);
			controls.setComponentAlignment(uploadExt, Alignment.MIDDLE_LEFT);

			Button saveBtn = new Button("Post", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Message message = new Message();
					SimpleProject project = (SimpleProject) AppContext
							.getVariable(ProjectContants.PROJECT_NAME);
					message.setProjectid(project.getId());
					message.setPosteddate(new GregorianCalendar().getTime());
					if (!titleField.getValue().toString().trim().equals("")) {
						message.setTitle((String) titleField.getValue());
						message.setMessage((String) ckEditorTextField
								.getValue());
						message.setPosteduser(AppContext.getUsername());
						message.setSaccountid(AppContext.getAccountId());
						fireSaveItem(message);
						attachments.saveContentsToRepo(
								AttachmentConstants.PROJECT_MESSAGE,
								message.getId());
					} else {
						titleField.addStyleName("errorField");
						MessageBox mb = new MessageBox(
								AppContext.getApplication().getMainWindow(),
								"Error!",
								MessageBox.Icon.ERROR,
								"Title must be not null!",
								new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
						mb.show();
					}
				}
			});
			saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			controls.addComponent(saveBtn);

			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					createBasicLayout();
				}
			});
			cancelBtn.setStyleName("link");
			controls.addComponent(cancelBtn);

			this.addComponent(controls);
		}
	}

	@Override
	public void addFormHandler(EditFormHandler<Message> handler) {
		if (editFormHandlers == null) {
			editFormHandlers = new HashSet<EditFormHandler<Message>>();
		}
		editFormHandlers.add(handler);
	}

	private void fireSaveItem(Message message) {
		if (editFormHandlers != null) {
			for (EditFormHandler<Message> handler : editFormHandlers) {
				handler.onSave(message);
			}
		}
	}

	@Override
	public HasEditFormHandlers<Message> getEditFormHandlers() {
		return this;
	}
}
