package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class OpportunityReadPresenter extends CrmGenericPresenter<OpportunityReadView> {

    private static final long serialVersionUID = 1L;

    public OpportunityReadPresenter() {
        super(OpportunityReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Opportunity>() {
                    @Override
                    public void onEdit(Opportunity data) {
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Opportunity data) {
                        OpportunityService OpportunityService = AppContext
                                .getSpringBean(OpportunityService.class);
                        OpportunityService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Opportunity data) {
                        Opportunity cloneData = (Opportunity) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoList(this, null));
                    }
                });

        view.getRelatedActivityHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        if (itemId.equals("task")) {
                            Task task = new Task();
                            task.setType(CrmTypeConstants.OPPORTUNITY);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.TaskEdit(OpportunityReadPresenter.this, task));
                        } else if (itemId.equals("meeting")) {
                            Meeting meeting = new Meeting();
                            meeting.setType(CrmTypeConstants.OPPORTUNITY);
                            meeting.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.MeetingEdit(OpportunityReadPresenter.this, meeting));
                        } else if (itemId.equals("call")) {
                            Call call = new Call();
                            call.setType(CrmTypeConstants.OPPORTUNITY);
                            call.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(new ActivityEvent.CallEdit(OpportunityReadPresenter.this, call));
                        }
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        if (data.getParams() instanceof Integer) {
            OpportunityService opportunityService = AppContext
                    .getSpringBean(OpportunityService.class);
            SimpleOpportunity opportunity = opportunityService
                    .findOpportunityById((Integer) data.getParams());
            view.previewItem(opportunity);
        }
    }
}
