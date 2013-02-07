package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class LeadAddPresenter extends CrmGenericPresenter<LeadAddView> {

    private static final long serialVersionUID = 1L;

    public LeadAddPresenter() {
        super(LeadAddView.class);
        bind();
    }

    private void bind() {
        view.getEditFormHandlers().addFormHandler(new EditFormHandler<Lead>() {
            @Override
            public void onSave(final Lead lead) {
                saveLead(lead);
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new LeadEvent.GotoList(this, null));
                }
            }

            @Override
            public void onCancel() {
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new LeadEvent.GotoList(this, null));
                }
            }

            @Override
            public void onSaveAndNew(final Lead lead) {
                saveLead(lead);
                EventBus.getInstance().fireEvent(
                        new LeadEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        Lead lead = (Lead) data.getParams();
        view.editItem(lead);
        
        if (lead.getId() == null) {
            AppContext.addFragment("crm/lead/add");
        } else {
            AppContext.addFragment("crm/lead/edit/" + UrlEncodeDecoder.encode(lead.getId()));
        }
    }

    public void saveLead(Lead lead) {
        LeadService leadService = AppContext.getSpringBean(LeadService.class);

        lead.setSaccountid(AppContext.getAccountId());
        if (lead.getId() == null) {
            leadService.saveWithSession(lead, AppContext.getUsername());
            
            if (lead.getExtraData() != null && (lead.getExtraData() instanceof SimpleCampaign)) {
                CampaignLead associateLead = new CampaignLead();
                associateLead.setCampaignid(((SimpleCampaign)lead.getExtraData()).getId());
                associateLead.setLeadid(lead.getId());
                associateLead.setCreatedtime(new GregorianCalendar().getTime());
                
                CampaignService campaignService = AppContext.getSpringBean(CampaignService.class);
                campaignService.saveCampaignLeadRelationship(Arrays.asList(associateLead));
            } else if (lead.getExtraData() != null && lead.getExtraData() instanceof SimpleOpportunity) {
                OpportunityLead associateLead = new OpportunityLead();
                associateLead.setOpportunityid(((SimpleOpportunity)lead.getExtraData()).getId());
                associateLead.setLeadid(lead.getId());
                associateLead.setCreatedtime(new GregorianCalendar().getTime());
                
                OpportunityService opportunityService = AppContext.getSpringBean(OpportunityService.class);
                opportunityService.saveOpportunityLeadRelationship(Arrays.asList(associateLead));
            }
        } else {
            leadService.updateWithSession(lead, AppContext.getUsername());
        }

    }
}
