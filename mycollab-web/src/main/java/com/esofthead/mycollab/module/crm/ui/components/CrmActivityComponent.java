package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.AuditChangeItem;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.SimpleLogging;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.project.ui.components.ProjectMemberBlock;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.*;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class CrmActivityComponent extends MVerticalLayout implements ReloadableComponent {
    private String type;
    private String typeId;
    private ELabel headerLbl;
    private CrmCommentInput commentBox;
    private MVerticalLayout activityBox;

    private CommentService commentService;
    private AuditLogService auditLogService;
    private FieldGroupFormatter groupFormatter;
    private boolean isAscending = true;
    private Comparator dateComparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            try {
                Date createTime1 = (Date) PropertyUtils.getProperty(o1, "createdtime");
                Date createTime2 = (Date) PropertyUtils.getProperty(o2, "createdtime");
                return createTime1.compareTo(createTime2);
            } catch (Exception e) {
                return 0;
            }
        }
    };

    public CrmActivityComponent(String type, FieldGroupFormatter groupFormatter, Class<? extends
            SendingRelayEmailNotificationAction> emailHandler) {
        withMargin(false).withStyleName("activity-comp");
        this.type = type;
        this.groupFormatter = groupFormatter;
        headerLbl = new ELabel("Change history").withStyleName("title");

        final OptionGroup sortDirection = new OptionGroup();
        sortDirection.addStyleName("sortDirection");
        sortDirection.addItems("Newest", "Oldest");
        sortDirection.setValue("Newest");
        sortDirection.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object value = sortDirection.getValue();
                isAscending = "Newest".equals(value);
                displayActivities();
            }
        });

        MHorizontalLayout headerPanel = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true))
                .withStyleName("header").withWidth("100%")
                .with(headerLbl, sortDirection).withAlign(headerLbl, Alignment.MIDDLE_LEFT).withAlign(sortDirection, Alignment.MIDDLE_RIGHT);

        commentBox = new CrmCommentInput(this, type, emailHandler);
        activityBox = new MVerticalLayout();
        this.with(headerPanel, commentBox, activityBox);

        commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
        auditLogService = ApplicationContextUtil.getSpringBean(AuditLogService.class);
    }

    public void loadActivities(String typeId) {
        this.typeId = typeId;
        if (commentBox != null) {
            commentBox.setTypeAndId(typeId);
        }
        displayActivities();
    }

    private void displayActivities() {
        activityBox.removeAllComponents();
        if (type == null || typeId == null) {
            return;
        }

        final CommentSearchCriteria commentCriteria = new CommentSearchCriteria();
        commentCriteria.setType(new StringSearchField(type));
        commentCriteria.setTypeid(new StringSearchField(typeId));
        final int commentCount = commentService.getTotalCount(commentCriteria);

        final AuditLogSearchCriteria logCriteria = new AuditLogSearchCriteria();
        logCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        logCriteria.setModule(new StringSearchField(ModuleNameConstants.CRM));
        logCriteria.setType(new StringSearchField(type));
        logCriteria.setTypeid(new StringSearchField(typeId));
        final int logCount = auditLogService.getTotalCount(logCriteria);
        setTotalNums(commentCount + logCount);

        List<SimpleComment> comments = commentService.findPagableListByCriteria(new SearchRequest<>
                (commentCriteria, 0, Integer.MAX_VALUE));
        List<SimpleAuditLog> auditLogs = auditLogService.findPagableListByCriteria(new SearchRequest<>
                (logCriteria, 0, Integer.MAX_VALUE));
        List activities = new ArrayList(commentCount + logCount);
        activities.addAll(comments);
        activities.addAll(auditLogs);
        if (isAscending) {
            Collections.sort(activities, dateComparator.reversed());
        } else {
            Collections.sort(activities, dateComparator);
        }

        for (Object activity : activities) {
            if (activity instanceof SimpleComment) {
                activityBox.addComponent(buildCommentBlock((SimpleComment) activity));
            } else if (activity instanceof SimpleAuditLog) {
                Component auditBlock = buildAuditBlock((SimpleAuditLog) activity);
                if (auditBlock != null) {
                    activityBox.addComponent(auditBlock);
                }
            } else {
                SimpleLogging.error("Do not support activity " + activity);
            }
        }
    }

    private Component buildCommentBlock(final SimpleComment comment) {
        final MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withWidth("100%").withStyleName("message");

        ProjectMemberBlock memberBlock = new ProjectMemberBlock(comment.getCreateduser(), comment.getOwnerAvatarId(), comment.getOwnerFullName());
        layout.addComponent(memberBlock);

        MVerticalLayout rowLayout = new MVerticalLayout().withWidth("100%").withStyleName("message-container");

        MHorizontalLayout messageHeader = new MHorizontalLayout().withWidth("100%").withStyleName("message-header");
        messageHeader.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        ELabel timePostLbl = new ELabel(AppContext.getMessage(
                GenericI18Enum.EXT_ADDED_COMMENT, comment.getOwnerFullName(),
                AppContext.formatPrettyTime(comment.getCreatedtime())), ContentMode.HTML).
                withDescription(AppContext.formatDateTime(comment.getCreatedtime()));
        timePostLbl.setStyleName("time-post");

        if (hasDeletePermission(comment)) {
            Button msgDeleteBtn = new Button();
            msgDeleteBtn.setIcon(FontAwesome.TRASH_O);
            msgDeleteBtn.setStyleName(UIConstants.BUTTON_ICON_ONLY);
            msgDeleteBtn.setVisible(true);
            msgDeleteBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    ConfirmDialogExt.show(UI.getCurrent(),
                            AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppContext.getSiteName()),
                            AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                            AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                            AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                            new ConfirmDialog.Listener() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public void onClose(ConfirmDialog dialog) {
                                    if (dialog.isConfirmed()) {
                                        CommentService commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
                                        commentService.removeWithSession(comment, AppContext.getUsername(), AppContext.getAccountId());
                                        activityBox.removeComponent(layout);
                                    }
                                }
                            });
                }
            });
            messageHeader.with(timePostLbl, msgDeleteBtn).expand(timePostLbl);
        } else {
            messageHeader.with(timePostLbl).expand(timePostLbl);
        }

        rowLayout.addComponent(messageHeader);

        Label messageContent = new SafeHtmlLabel(comment.getComment());
        messageContent.setStyleName("message-body");
        rowLayout.addComponent(messageContent);

        List<Content> attachments = comment.getAttachments();
        if (!CollectionUtils.isEmpty(attachments)) {
            MVerticalLayout messageFooter = new MVerticalLayout().withMargin(false).withSpacing(false).withWidth
                    ("100%").withStyleName("message-footer");
            AttachmentDisplayComponent attachmentDisplay = new AttachmentDisplayComponent(attachments);
            attachmentDisplay.setWidth("100%");
            messageFooter.with(attachmentDisplay);
            rowLayout.addComponent(messageFooter);
        }

        layout.with(rowLayout).expand(rowLayout);
        return layout;
    }

    private boolean hasDeletePermission(SimpleComment comment) {
        return (AppContext.getUsername().equals(comment.getCreateduser()) || AppContext.isAdmin());
    }

    private Component buildAuditBlock(SimpleAuditLog auditLog) {
        List<AuditChangeItem> changeItems = auditLog.getChangeItems();
        if (CollectionUtils.isNotEmpty(changeItems)) {
            final MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                    .withWidth("100%").withStyleName("message");

            ProjectMemberBlock memberBlock = new ProjectMemberBlock(auditLog.getPosteduser(), auditLog.getPostedUserAvatarId(),
                    auditLog.getPostedUserFullName());
            layout.addComponent(memberBlock);

            MVerticalLayout rowLayout = new MVerticalLayout().withWidth("100%").withStyleName("message-container");

            MHorizontalLayout messageHeader = new MHorizontalLayout().withWidth("100%").withStyleName("message-header");
            messageHeader.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

            ELabel timePostLbl = new ELabel(AppContext.getMessage(
                    GenericI18Enum.EXT_MODIFIED_ITEM, auditLog.getPostedUserFullName(),
                    AppContext.formatPrettyTime(auditLog.getPosteddate())), ContentMode.HTML).
                    withDescription(AppContext.formatDateTime(auditLog.getPosteddate()));
            timePostLbl.setStyleName("time-post");
            messageHeader.with(timePostLbl).expand(timePostLbl);

            rowLayout.addComponent(messageHeader);

            for (int i = 0; i < changeItems.size(); i++) {
                AuditChangeItem item = changeItems.get(i);
                String fieldName = item.getField();

                FieldGroupFormatter.FieldDisplayHandler fieldDisplayHandler = groupFormatter.getFieldDisplayHandler(fieldName);
                if (fieldDisplayHandler != null) {
                    Span fieldBlock = new Span().appendText(AppContext.getMessage(fieldDisplayHandler.getDisplayName
                            ())).setCSSClass("block");
                    Div historyDiv = new Div().appendChild(fieldBlock).appendText(fieldDisplayHandler.getFormat()
                            .toString(item.getOldvalue())).appendText(" " + FontAwesome.LONG_ARROW_RIGHT.getHtml() +
                            " ").appendText(fieldDisplayHandler.getFormat().toString(item.getNewvalue()));
                    rowLayout.addComponent(new Label(historyDiv.write(), ContentMode.HTML));
                } else {
                    System.out.println("History field: " + fieldName);
                }
            }

            layout.with(rowLayout).expand(rowLayout);
            return layout;
        } else {
            return null;
        }
    }

    private void setTotalNums(int nums) {
        headerLbl.setValue("Change history (" + nums + ")");
    }

    @Override
    public void reload() {
        displayActivities();
    }
}
