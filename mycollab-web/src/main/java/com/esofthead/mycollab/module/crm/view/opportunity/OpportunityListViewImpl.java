package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class OpportunityListViewImpl extends AbstractView implements
        OpportunityListView {

    private static final long serialVersionUID = 1L;
    private final OpportunitySearchPanel opportunitySeachPanel;
    private SelectionOptionButton selectOptionButton;
    private OpportunityTableDisplay tableItem;
    private final VerticalLayout accountListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public OpportunityListViewImpl() {
        this.setSpacing(true);

        opportunitySeachPanel = new OpportunitySearchPanel();
        this.addComponent(opportunitySeachPanel);

        accountListLayout = new VerticalLayout();
        accountListLayout.setSpacing(true);
        this.addComponent(accountListLayout);

        generateDisplayTable();
    }

    @SuppressWarnings("serial")
    private void generateDisplayTable() {
        tableItem = new OpportunityTableDisplay(
                new String[]{"selected",
                    "opportunityname", "accountName", "salesstage",
                    "amount", "expectedcloseddate", "assignUserFullName"},
                new String[]{"", "Name", "Account Name", "Sales Stage",
                    "Amount", "Close", "User"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends com.esofthead.mycollab.vaadin.events.ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleOpportunity opportunity = (SimpleOpportunity) event.getData();
                if (event.getFieldName().equals("opportunityname")) {
                    EventBus.getInstance().fireEvent(
                            new OpportunityEvent.GotoRead(this,
                            opportunity.getId()));
                } else if (event.getFieldName().equals("accountname")) {
                    EventBus.getInstance().fireEvent(
                            new AccountEvent.GotoRead(this,
                            opportunity.getAccountid()));
                }
            }
        });

        accountListLayout.addComponent(constructTableActionControls());
        accountListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<OpportunitySearchCriteria> getSearchHandlers() {
        return opportunitySeachPanel;
    }

    private ComponentContainer constructTableActionControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        selectOptionButton = new SelectionOptionButton(tableItem);
        layout.addComponent(selectOptionButton);

        tableActionControls = new PopupButtonControl("delete", "Delete");
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
    public HasSelectableItemHandlers<SimpleOpportunity> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<OpportunitySearchCriteria, SimpleOpportunity> getPagedBeanTable() {
        return tableItem;
    }
}
