package com.esofthead.mycollab.module.crm.view.lead;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.LeadSearchPanel;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class LeadListViewImpl extends AbstractView implements LeadListView {
	private static final long serialVersionUID = 1L;

	private final LeadSearchPanel leadSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable<SimpleLead> tableItem;

	private final VerticalLayout accountListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	private LeadListPresenter presenter;

	public LeadListViewImpl() {
		this.setSpacing(true);

		leadSearchPanel = new LeadSearchPanel();
		this.addComponent(leadSearchPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable<SimpleLead>();

		//generate column code here

		tableItem.setWidth("100%");

		accountListLayout.addComponent(constructTableActionControls());
		accountListLayout.addComponent(tableItem);
		accountListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public void displayLeads(List<SimpleLead> accounts, int currentPage,
			int totalPages) {
		tableItem.setCurrentPage(currentPage);
		tableItem.setTotalPage(totalPages);

		BeanItemContainer<SimpleLead> container = new BeanItemContainer<SimpleLead>(
				SimpleLead.class, accounts);
		tableItem.setContainerDataSource(container);
		tableItem.setVisibleColumns(new String[] { "selected", "leadName",
				"status", "accountname", "officephone", "email", "assignuser"});
		tableItem.setColumnHeaders(new String[] { "", "Name", "Status",
				"Account Name", "Office Phone", "Email", "Assign User"});

	}

	@Override
	public HasSearchHandlers<LeadSearchCriteria> getSearchHandlers() {
		return leadSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton();
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setEnabled(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setEnabled(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public void setPresenter(LeadListPresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public HasPagableHandlers getPagableHandlers() {
		return tableItem;
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}
}
