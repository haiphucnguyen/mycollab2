package com.mycollab.premium.mobile.module.project.view.message;

import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.message.IMessageAddPresenter;
import com.mycollab.mobile.module.project.view.message.MessageAddView;
import com.mycollab.mobile.shell.event.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleMessage;
import com.mycollab.module.project.i18n.MessageI18nEnum;
import com.mycollab.module.project.service.MessageService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
public class MessageAddPresenter extends AbstractProjectPresenter<MessageAddView> implements IMessageAddPresenter {
    private static final long serialVersionUID = -6518878184021039341L;

    public MessageAddPresenter() {
        super(MessageAddView.class);
    }

    @Override
    protected void postInitView() {
        super.postInitView();
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleMessage>() {
            private static final long serialVersionUID = 2381946253040633727L;

            @Override
            public void onSave(SimpleMessage bean) {
                MessageService messageService = AppContextUtil.getSpringBean(MessageService.class);
                messageService.saveWithSession(bean, UserUIContext.getUsername());
                ProjectFormAttachmentUploadField uploadField = getView().getUploadField();
                uploadField.saveContentsToRepo(CurrentProjectVariables.getProjectId(), ProjectTypeConstants.MESSAGE, bean.getId());
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents navigator, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MESSAGES)) {
            super.onGo(navigator, data);
            AppUI.addFragment(ProjectLinkGenerator.generateMessageAddLink(CurrentProjectVariables.getProjectId()),
                    UserUIContext.getMessage(MessageI18nEnum.NEW));
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
        super.onGo(navigator, data);
    }

}
