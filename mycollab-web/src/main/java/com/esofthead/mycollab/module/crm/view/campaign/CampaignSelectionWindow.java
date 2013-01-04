package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CampaignSelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private CampaignSearchCriteria searchCriteria;
    private PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign> tableItem;
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
        tableItem = new PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign>(
                AppContext.getSpringBean(CampaignService.class),
                SimpleCampaign.class, new String[]{"campaignname", "type",
                    "status", "startdate", "enddate"},
                new String[]{"Campaign", "Type", "Status", "Start Date",
                    "End Date"});
        tableItem.setWidth("100%");

        tableItem.setColumnWidth("campaignname", 250);
        tableItem.setColumnWidth("type", 150);
        tableItem.setColumnWidth("status", 150);
        tableItem.setColumnWidth("startdate", 190);
        tableItem.setColumnWidth("enddate", 190);

        tableItem.addGeneratedColumn("campaignname", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            public com.vaadin.ui.Component generateCell(final Table source,
                    final Object itemId, Object columnId) {
                final SimpleCampaign campaign = tableItem
                        .getBeanByIndex(itemId);
                Button b = new Button(campaign.getCampaignname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                fieldSelection.fireValueChange(campaign);
                                CampaignSelectionWindow.this.getParent()
                                        .removeWindow(
                                        CampaignSelectionWindow.this);
                            }
                        });
                b.setStyleName("link");
                b.addStyleName("medium-text");
                return b;
            }
        });
    }
}
