package com.esofthead.mycollab.module.project.view.message;

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
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.RichTextEditor;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.vaadin.openesignforms.ckeditor.CKEditorConfig;

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
        public Component generateRow(final SimpleMessage obj, int rowIndex) {
            VerticalLayout rowLayout = new VerticalLayout();
            Button title = new Button(obj.getTitle(),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            EventBus.getInstance().fireEvent(new MessageEvent.GotoRead(MessageListViewImpl.this, obj.getId()));
                        }
                    });
            title.setStyleName("link");

            rowLayout.addComponent(title);

            Label messageInfo = new Label();
            messageInfo.setValue("Posted by " + obj.getFullPostedUserName()
                    + " on " + obj.getPosteddate());

            rowLayout.addComponent(messageInfo);

            rowLayout.addComponent(new Label(obj.getMessage(),
                    Label.CONTENT_XHTML));

            HorizontalLayout footer = new HorizontalLayout();
            footer.addComponent(new Label(obj.getCommentsCount() + " comments"));
            rowLayout.addComponent(footer);
            return rowLayout;
        }
    }

    private final class TopMessagePanel extends VerticalLayout {

        private static final long serialVersionUID = 1L;

        public TopMessagePanel() {
            this.setWidth("100%");
            createBasicLayout();
        }

        public void createBasicLayout() {
            this.removeAllComponents();

            HorizontalLayout layout = new HorizontalLayout();
            layout.setWidth("100%");
            Button createAccountBtn = new Button("New Message",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            createAddMessageLayout();
                        }
                    });
            createAccountBtn.setStyleName("link");
            createAccountBtn
                    .setIcon(new ThemeResource("icons/16/addRecord.png"));

            UiUtils.addComponent(layout, createAccountBtn,
                    Alignment.MIDDLE_RIGHT);
            this.addComponent(layout);
        }

        private void createAddMessageLayout() {
            this.removeAllComponents();

            this.setSpacing(true);

            HorizontalLayout titleLayout = new HorizontalLayout();
            titleLayout.setSpacing(true);
            Label titleLbl = new Label("Title: ");
            final TextField titleField = new TextField();
            titleField.setWidth("600px");
            titleLayout.addComponent(titleLbl);
            titleLayout.addComponent(titleField);

            this.addComponent(titleLayout);

            CKEditorConfig config = new CKEditorConfig();
            config.useCompactTags();
            config.disableElementsPath();
            config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
            config.disableSpellChecker();
            config.setToolbarCanCollapse(false);
            config.setWidth("100%");

            final RichTextEditor ckEditorTextField = new RichTextEditor();
            ckEditorTextField.setWidth("100%");
            this.addComponent(ckEditorTextField);

            HorizontalLayout controls = new HorizontalLayout();
            controls.setSpacing(true);

            Button saveBtn = new Button("Save",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            Message message = new Message();
                            SimpleProject project = (SimpleProject) AppContext
                                    .getVariable(ProjectContants.PROJECT_NAME);
                            message.setProjectid(project.getId());
                            message.setPosteddate(new GregorianCalendar()
                                    .getTime());
                            message.setTitle((String) titleField.getValue());
                            message.setMessage((String) ckEditorTextField
                                    .getValue());
                            message.setPosteduser(AppContext.getUsername());
                            message.setSaccountid(AppContext.getAccountId());
                            fireSaveItem(message);
                        }
                    });
            saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            controls.addComponent(saveBtn);

            Button cancelBtn = new Button("Cancel",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            createBasicLayout();
                        }
                    });
            cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
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
