package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {

    private static final long serialVersionUID = 1L;

    public LeadReadPresenter() {
        super(LeadReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new PreviewFormHandlers<Lead>() {
                    @Override
                    public void onEdit(Lead data) {
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Lead data) {
                        LeadService LeadService = AppContext
                                .getSpringBean(LeadService.class);
                        LeadService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Lead data) {
                        Lead cloneData = (Lead) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoList(this, null));
                    }
                });
        
        view.getRelatedActivityHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        if (itemId.equals("task")) {
                            Task task = new Task();
                            task.setType(CrmTypeConstants.LEAD);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.TaskEdit(LeadReadPresenter.this, task));
                        } else if (itemId.equals("meeting")) {
                            Meeting meeting = new Meeting();
                            meeting.setType(CrmTypeConstants.LEAD);
                            meeting.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.MeetingEdit(LeadReadPresenter.this, meeting));
                        } else if (itemId.equals("call")) {
                            Call call = new Call();
                            call.setType(CrmTypeConstants.LEAD);
                            call.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.CallEdit(LeadReadPresenter.this, call));
                        }
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);

        if (data.getParams() instanceof Integer) {
            LeadService leadService = AppContext
                    .getSpringBean(LeadService.class);
            SimpleLead lead = leadService.findLeadById((Integer) data
                    .getParams());
            view.previewItem(lead);
        }
    }
}
