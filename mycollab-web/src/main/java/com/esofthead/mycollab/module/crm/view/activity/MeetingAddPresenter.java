package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class MeetingAddPresenter extends CrmGenericPresenter<MeetingAddView> {

    private static final long serialVersionUID = 1L;

    public MeetingAddPresenter() {
        super(MeetingAddView.class);

        view.getEditFormHandlers().addFormHandler(
                new EditFormHandler<Meeting>() {
                    @Override
                    public void onSave(final Meeting item) {
                        save(item);
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new ActivityEvent.GotoTodoList(this, null));
                        }
                    }

                    @Override
                    public void onCancel() {
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new ActivityEvent.GotoTodoList(this, null));
                        }
                    }

                    @Override
                    public void onSaveAndNew(final Meeting item) {
                        save(item);
                        EventBus.getInstance().fireEvent(
                                new ActivityEvent.MeetingAdd(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        Meeting meeting = (Meeting) data.getParams();
        view.editItem(meeting);
        
        if (meeting.getId() == null) {
            AppContext.addFragment("crm/meeting/add");
        } else {
            AppContext.addFragment("crm/meeting/edit/" + UrlEncodeDecoder.encode(meeting.getId()));
        }
    }

    public void save(Meeting item) {
        MeetingService meetingService = AppContext
                .getSpringBean(MeetingService.class);

        item.setSaccountid(AppContext.getAccountId());
        if (item.getId() == null) {
            meetingService.saveWithSession(item, AppContext.getUsername());
        } else {
            meetingService.updateWithSession(item, AppContext.getUsername());
        }
    }
}
