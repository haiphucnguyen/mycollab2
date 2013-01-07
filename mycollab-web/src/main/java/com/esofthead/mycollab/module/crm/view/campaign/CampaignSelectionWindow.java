package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CampaignSelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private CampaignSearchCriteria searchCriteria;
    private CampaignTableDisplay tableItem;
    private FieldSelection fieldSelection;

    public CampaignSelectionWindow(FieldSelection fieldSelection) {
        super("Campaign Name Lookup");

        this.setWidth("1035px");

        this.fieldSelection = fieldSelection;
    }

    public void show() {
        searchCriteria = new CampaignSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        createCampaignList();
        layout.addComponent(createSearchPanel());
        layout.addComponent(tableItem);
        this.setContent(layout);

        tableItem.setSearchCriteria(searchCriteria);
        center();
    }

    private ComponentContainer createSearchPanel() {
        GridFormLayoutHelper layout = new GridFormLayoutHelper(3, 2);

        return layout.getLayout();
    }

    private void createCampaignList() {
        tableItem = new CampaignTableDisplay(new String[]{"campaignname", "type",
                    "status", "startdate", "enddate"},
                new String[]{"Campaign", "Type", "Status", "Start Date",
                    "End Date"});
        tableItem.setWidth("100%");

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleCampaign campaign = (SimpleCampaign) event.getData();
                if ("campaignname".equals(event.getFieldName())) {
                    fieldSelection.fireValueChange(campaign);
                    CampaignSelectionWindow.this.getParent()
                            .removeWindow(
                            CampaignSelectionWindow.this);
                }

            }
        });
    }
}
