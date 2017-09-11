package com.mycollab.module.crm.view.activity;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.mycollab.module.crm.event.ActivityEvent;
import com.mycollab.module.crm.i18n.MeetingI18nEnum;
import com.mycollab.module.crm.service.MeetingService;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MeetingAddPresenter extends CrmGenericPresenter<MeetingAddView> {
    private static final long serialVersionUID = 1L;

    public MeetingAddPresenter() {
        super(MeetingAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<MeetingWithBLOBs>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final MeetingWithBLOBs item) {
                save(item);
                EventBusFactory.getInstance().post(new ActivityEvent.MeetingRead(this, item.getId()));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ActivityEvent.GotoTodoList(this, null));
            }

            @Override
            public void onSaveAndNew(final MeetingWithBLOBs item) {
                save(item);
                EventBusFactory.getInstance().post(new ActivityEvent.MeetingAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_MEETING())) {
            MeetingWithBLOBs meeting = null;
            if (data.getParams() instanceof MeetingWithBLOBs) {
                meeting = (MeetingWithBLOBs) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                MeetingService meetingService = AppContextUtil.getSpringBean(MeetingService.class);
                meeting = meetingService.findByPrimaryKey((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (meeting == null) {
                NotificationUtil.showRecordNotExistNotification();
                return;
            }
            super.onGo(container, data);

            view.editItem(meeting);

            if (meeting.getId() == null) {
                AppUI.addFragment("crm/activity/meeting/add/", UserUIContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
                        UserUIContext.getMessage(MeetingI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("crm/activity/meeting/edit/" + UrlEncodeDecoder.encode(meeting.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
                                UserUIContext.getMessage(MeetingI18nEnum.SINGLE), meeting.getSubject()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    public void save(MeetingWithBLOBs item) {
        MeetingService meetingService = AppContextUtil.getSpringBean(MeetingService.class);
        item.setSaccountid(AppUI.getAccountId());
        if (item.getId() == null) {
            meetingService.saveWithSession(item, UserUIContext.getUsername());
        } else {
            meetingService.updateWithSession(item, UserUIContext.getUsername());
        }
    }
}
