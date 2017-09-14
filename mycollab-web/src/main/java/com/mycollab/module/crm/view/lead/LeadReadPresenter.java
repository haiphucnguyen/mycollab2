package com.mycollab.module.crm.view.lead;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.SecureAccessException;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.CrmLinkGenerator;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.*;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.mycollab.module.crm.event.ActivityEvent;
import com.mycollab.module.crm.event.CampaignEvent;
import com.mycollab.module.crm.event.LeadEvent;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
import com.mycollab.module.crm.service.CampaignService;
import com.mycollab.module.crm.service.LeadService;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.module.crm.view.CrmModule;
import com.mycollab.vaadin.reporting.FormReportLayout;
import com.mycollab.vaadin.reporting.PrintButton;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.AbstractRelatedListHandler;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {
    private static final long serialVersionUID = 1L;

    public LeadReadPresenter() {
        super(LeadReadView.class);
    }

    @Override
    protected void postInitView() {
        view.getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleLead>() {
            @Override
            public void onEdit(SimpleLead data) {
                EventBusFactory.getInstance().post(new LeadEvent.GotoEdit(this, data));
            }

            @Override
            public void onAdd(SimpleLead data) {
                EventBusFactory.getInstance().post(new LeadEvent.GotoAdd(this, null));
            }

            @Override
            public void onDelete(final SimpleLead data) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                LeadService LeadService = AppContextUtil.getSpringBean(LeadService.class);
                                LeadService.removeWithSession(data, UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new LeadEvent.GotoList(this, null));
                            }
                        });
            }

            @Override
            public void onPrint(Object source, SimpleLead data) {
                PrintButton btn = (PrintButton) source;
                btn.doPrint(data, new FormReportLayout(CrmTypeConstants.LEAD, Lead.Field.lastname.name(),
                        LeadDefaultDynaFormLayoutFactory.getForm()));
            }

            @Override
            public void onClone(SimpleLead data) {
                SimpleLead cloneData = (SimpleLead) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new LeadEvent.GotoEdit(this, cloneData));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new LeadEvent.GotoList(this, null));
            }

            @Override
            public void gotoNext(SimpleLead data) {
                LeadService contactService = AppContextUtil.getSpringBean(LeadService.class);
                LeadSearchCriteria criteria = new LeadSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATER));
                Integer nextId = contactService.getNextItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new LeadEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoLastRecordNotification();
                }
            }

            @Override
            public void gotoPrevious(SimpleLead data) {
                LeadService contactService = AppContextUtil.getSpringBean(LeadService.class);
                LeadSearchCriteria criteria = new LeadSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESS_THAN));
                Integer nextId = contactService.getPreviousItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new LeadEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoFirstRecordNotification();
                }
            }

            @Override
            public void onExtraAction(String action, SimpleLead data) {
                LeadConvertInfoWindow convertWindow = new LeadConvertInfoWindow(data);
                UI.getCurrent().addWindow(convertWindow);
            }
        });

        view.getRelatedActivityHandlers().addRelatedListHandler(new AbstractRelatedListHandler<SimpleActivity>() {
            @Override
            public void createNewRelatedItem(String itemId) {
                if (itemId.equals("task")) {
                    SimpleCrmTask task = new SimpleCrmTask();
                    task.setType(CrmTypeConstants.LEAD);
                    task.setTypeid(view.getItem().getId());
                    EventBusFactory.getInstance().post(new ActivityEvent.TaskEdit(LeadReadPresenter.this, task));
                } else if (itemId.equals("meeting")) {
                    SimpleMeeting meeting = new SimpleMeeting();
                    meeting.setType(CrmTypeConstants.LEAD);
                    meeting.setTypeid(view.getItem().getId());
                    EventBusFactory.getInstance().post(new ActivityEvent.MeetingEdit(LeadReadPresenter.this, meeting));
                } else if (itemId.equals("call")) {
                    SimpleCall call = new SimpleCall();
                    call.setType(CrmTypeConstants.LEAD);
                    call.setTypeid(view.getItem().getId());
                    EventBusFactory.getInstance().post(new ActivityEvent.CallEdit(LeadReadPresenter.this, call));
                }
            }
        });

        view.getRelatedCampaignHandlers().addRelatedListHandler(new AbstractRelatedListHandler<SimpleCampaign>() {
            @Override
            public void createNewRelatedItem(String itemId) {
                SimpleCampaign campaign = new SimpleCampaign();
                campaign.setExtraData(view.getItem());
                EventBusFactory.getInstance().post(new CampaignEvent.GotoEdit(LeadReadPresenter.this, campaign));
            }

            @Override
            public void selectAssociateItems(Set<SimpleCampaign> items) {
                if (!items.isEmpty()) {
                    SimpleLead lead = view.getItem();
                    List<CampaignLead> associateCampaigns = new ArrayList<>();
                    for (SimpleCampaign campaign : items) {
                        CampaignLead associateCampaign = new CampaignLead();
                        associateCampaign.setCampaignid(campaign.getId());
                        associateCampaign.setLeadid(lead.getId());
                        associateCampaign.setCreatedtime(new GregorianCalendar().getTime());
                        associateCampaigns.add(associateCampaign);
                    }

                    CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
                    campaignService.saveCampaignLeadRelationship(associateCampaigns, AppUI.getAccountId());
                    view.getRelatedCampaignHandlers().refresh();
                }
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        CrmModule.navigateItem(CrmTypeConstants.LEAD);
        if (UserUIContext.canRead(RolePermissionCollections.CRM_LEAD)) {
            if (data.getParams() instanceof SimpleLead) {
                SimpleLead lead = (SimpleLead) data.getParams();
                super.onGo(container, data);
                view.previewItem(lead);
                AppUI.addFragment(CrmLinkGenerator.generateLeadPreviewLink(lead.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE,
                                UserUIContext.getMessage(LeadI18nEnum.SINGLE), lead.getLeadName()));

            }
        } else {
            throw new SecureAccessException();
        }
    }
}
