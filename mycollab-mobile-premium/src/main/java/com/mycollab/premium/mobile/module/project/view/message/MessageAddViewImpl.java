package com.mycollab.premium.mobile.module.project.view.message;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.message.MessageAddView;
import com.mycollab.mobile.ui.AbstractMobilePageView;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.SimpleMessage;
import com.mycollab.module.project.i18n.MessageI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasEditFormHandlers;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
@ViewComponent
public class MessageAddViewImpl extends AbstractMobilePageView implements MessageAddView, HasEditFormHandlers<SimpleMessage> {
    private static final long serialVersionUID = -5665807255892654312L;

    private final TextField subjectField;
    private final TextArea contentField;
    private final Switch isStickField;

    private ProjectFormAttachmentUploadField attachmentPanel;
    private Set<IEditFormHandler<SimpleMessage>> handlers = new HashSet<>();

    public MessageAddViewImpl() {
        this.setCaption(UserUIContext.getMessage(MessageI18nEnum.NEW));

        MVerticalLayout contentLayout = new MVerticalLayout().withSpacing(false).withMargin(false);
        this.setContent(contentLayout);

        subjectField = new TextField();
        subjectField.setWidth("100%");
        subjectField.setInputPrompt(UserUIContext.getMessage(MessageI18nEnum.FORM_TITLE));
        contentLayout.addComponent(subjectField);

        contentLayout.addComponent(ELabel.hr());

        contentField = new TextArea();
        contentField.setWidth("100%");
        contentField.setInputPrompt(UserUIContext.getMessage(MessageI18nEnum.M_FORM_CONTENT_FIELD_PROMPT));
        contentLayout.addComponent(contentField);

        VerticalComponentGroup bottomRow = new VerticalComponentGroup();
        bottomRow.setWidth("100%");
        isStickField = new Switch(UserUIContext.getMessage(MessageI18nEnum.FORM_IS_STICK), false);
        bottomRow.addComponent(isStickField);

        attachmentPanel = new ProjectFormAttachmentUploadField();
        bottomRow.addComponent(attachmentPanel);

        contentLayout.addComponent(bottomRow);

        Button saveBtn = new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            final SimpleMessage message = new SimpleMessage();
            message.setProjectid(CurrentProjectVariables.getProjectId());
            message.setPosteddate(new GregorianCalendar().getTime());
            if (StringUtils.isNotBlank(subjectField.getValue())) {
                message.setTitle(subjectField.getValue());
                message.setMessage(contentField.getValue());
                message.setPosteduser(UserUIContext.getUsername());
                message.setSaccountid(AppUI.getAccountId());
                message.setIsstick(isStickField.getValue());
                MessageAddViewImpl.this.fireSaveItem(message);

            } else {
                subjectField.addStyleName("errorField");
                NotificationUtil.showErrorNotification(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                        UserUIContext.getMessage(MessageI18nEnum.FORM_TITLE)));
            }
        });
        this.setRightComponent(saveBtn);
    }

    @Override
    public void addFormHandler(IEditFormHandler<SimpleMessage> handler) {
        handlers.add(handler);
    }

    @Override
    public HasEditFormHandlers<SimpleMessage> getEditFormHandlers() {
        return this;
    }

    private void fireSaveItem(final SimpleMessage message) {
        if (this.handlers != null) {
            for (final IEditFormHandler<SimpleMessage> handler : this.handlers) {
                handler.onSave(message);
            }
        }
    }

    @Override
    public void initView() {
        subjectField.setValue("");
        contentField.setValue("");
        isStickField.setValue(false);
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        initView();
    }

    @Override
    public ProjectFormAttachmentUploadField getUploadField() {
        return attachmentPanel;
    }

}
