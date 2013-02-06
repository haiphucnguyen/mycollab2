/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.CampaignLead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

/**
 *
 * @author haiphucnguyen
 */
public class CampaignLeadListComp  extends RelatedListComp<SimpleLead, LeadSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private Campaign campaign;

    public CampaignLeadListComp() {
        super("Leads");
        initUI();
    }

    public void displayLeads(Campaign campaign) {
        this.campaign = campaign;
        loadLeads();
    }

    private void loadLeads() {
        LeadSearchCriteria criteria = new LeadSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setCampaignId(new NumberSearchField(SearchField.AND, campaign
                .getId()));
        this.setSearchCriteria(criteria);
    }

    @SuppressWarnings("serial")
    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        final SplitButton controlsBtn = new SplitButton();
        controlsBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CONTACT));
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
        controlsBtn.setCaption("New Lead");
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
        controlsBtn.addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        Button selectBtn = new Button("Select from existing leads", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CampaignLeadSelectionWindow leadsWindow = new CampaignLeadSelectionWindow(CampaignLeadListComp.this);
                LeadSearchCriteria criteria = new LeadSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                getWindow().addWindow(leadsWindow);
                leadsWindow.setSearchCriteria(criteria);
                controlsBtn.setPopupVisible(false);
            }
        });
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        selectBtn.setStyleName("link");
        controlsBtn.addComponent(selectBtn);

        contentContainer.addComponent(controlsBtn);

        tableItem = new LeadTableDisplay(
                new String[]{"leadName", "status", "email", "officephone", "id"},
                new String[]{"Name", "Status", "Email", "Phone", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleLead lead = (SimpleLead) event.getData();
                if ("leadName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new LeadEvent.GotoRead(
                            CampaignLeadListComp.this, lead
                            .getId()));
                }
            }
        });


        tableItem.addGeneratedColumn("id", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                final SimpleLead lead = (SimpleLead) tableItem.getBeanByIndex(itemId);
                HorizontalLayout controlLayout = new HorizontalLayout();
                Button editBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new LeadEvent.GotoEdit(
                                CampaignLeadListComp.this, lead));
                    }
                });
                editBtn.setStyleName("link");
                editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
                controlLayout.addComponent(editBtn);

                Button deleteBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        ConfirmDialog.show(AppContext.getApplication().getMainWindow(),
                                "Please Confirm:",
                                "Are you sure to delete this relationship? Only the relationship is removed. The record will not be deleted.",
                                "Yes", "No", new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    CampaignService campaignService = AppContext
                                            .getSpringBean(CampaignService.class);
                                    CampaignLead associateLead = new CampaignLead();
                                    associateLead.setLeadid(lead.getId());
                                    associateLead.setCampaignid(campaign.getId());
                                    campaignService.removeCampaignLeadRelationship(associateLead);
                                    CampaignLeadListComp.this.refresh();
                                }
                            }
                        });
                    }
                });
                deleteBtn.setStyleName("link");
                deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
                controlLayout.addComponent(deleteBtn);
                return controlLayout;
            }
        });
        contentContainer.addComponent(tableItem);

    }

    @Override
    public void setSelectedItems(Set selectedItems) {
        fireSelectedRelatedItems(selectedItems);
    }

    @Override
    public void refresh() {
        loadLeads();
    }
}
