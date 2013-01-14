package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class LeadListViewImpl extends AbstractView implements LeadListView {

    private static final long serialVersionUID = 1L;
    private final LeadSearchPanel leadSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private LeadTableDisplay tableItem;
    private final VerticalLayout accountListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public LeadListViewImpl() {
        this.setSpacing(true);

        leadSearchPanel = new LeadSearchPanel();
        this.addComponent(leadSearchPanel);

        accountListLayout = new VerticalLayout();
        accountListLayout.setSpacing(true);
        this.addComponent(accountListLayout);

        generateDisplayTable();
    }

    @SuppressWarnings("serial")
    private void generateDisplayTable() {
        tableItem = new LeadTableDisplay(
                new String[]{"selected", "leadName", "status", "accountname",
                    "officephone", "email", "assignUserFullName"},
                new String[]{"", "Name", "Status", "Account Name",
                    "Office Phone", "Email", "Assign User"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleLead lead = (SimpleLead) event.getData();
                if ("leadName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new LeadEvent.GotoRead(LeadListViewImpl.this, lead.getId()));
                }
            }
        });

        accountListLayout.addComponent(constructTableActionControls());
        accountListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<LeadSearchCriteria> getSearchHandlers() {
        return leadSearchPanel;
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
    public HasSelectableItemHandlers<SimpleLead> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<LeadSearchCriteria, SimpleLead> getPagedBeanTable() {
        return tableItem;
    }
}
