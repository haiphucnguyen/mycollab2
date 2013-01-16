package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import org.vaadin.hene.splitbutton.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class CampaignListViewImpl extends AbstractView implements
        CampaignListView {

    private CampaignSearchPanel campaignSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private CampaignTableDisplay tableItem;
    private VerticalLayout campainListLayout;
    private PopupButtonControl tableActionControls;
    private Label selectedItemsNumberLabel = new Label();

    public CampaignListViewImpl() {
        this.setSpacing(true);

        campaignSearchPanel = new CampaignSearchPanel();
        this.addComponent(campaignSearchPanel);

        campainListLayout = new VerticalLayout();
        campainListLayout.setSpacing(true);
        this.addComponent(campainListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new CampaignTableDisplay(new String[]{"selected",
                    "campaignname", "status", "type", "expectedrevenue",
                    "enddate", "assignUserFullName"}, new String[]{"",
                    "Campaign", "Status", "Type", "Expected Revenue",
                    "End Date", "Assign User"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleCampaign campaign = (SimpleCampaign) event.getData();
                if ("campaignname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new CampaignEvent.GotoRead(CampaignListViewImpl.this, campaign.getId()));
                }
            }
        });

        campainListLayout.addComponent(constructTableActionControls());
        campainListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers() {
        return campaignSearchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        selectOptionButton = new SelectionOptionButton(tableItem);
        layout.addComponent(selectOptionButton);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setEnabled(AppContext.canAccess(RolePermissionCollections.CRM_CAMPAIGN));
        
        tableActionControls = new PopupButtonControl("delete", deleteBtn);
        tableActionControls.addOptionItem("mail", "Mail");
        tableActionControls.addOptionItem("export", "Export");
        tableActionControls.setVisible(false);

        layout.addComponent(tableActionControls);
        layout.addComponent(selectedItemsNumberLabel);
        layout.setComponentAlignment(selectedItemsNumberLabel,
                Alignment.MIDDLE_CENTER);
        return layout;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setVisible(true);
        selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setVisible(false);
        selectedItemsNumberLabel.setValue("");
    }

    @Override
    public HasSelectionOptionHandlers getOptionSelectionHandlers() {
        return selectOptionButton;
    }

    @Override
    public HasPopupActionHandlers getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public HasSelectableItemHandlers<SimpleCampaign> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<CampaignSearchCriteria, SimpleCampaign> getPagedBeanTable() {
        return tableItem;
    }
}
