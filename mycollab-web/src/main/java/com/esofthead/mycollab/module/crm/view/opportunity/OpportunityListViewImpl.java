package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.OpportunitySearchPanel;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class OpportunityListViewImpl extends AbstractView implements
		OpportunityListView {
	private static final long serialVersionUID = 1L;

	private final OpportunitySearchPanel opportunitySeachPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable<SimpleOpportunity> tableItem;

	private final VerticalLayout accountListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	private OpportunityListPresenter presenter;

	public OpportunityListViewImpl() {
		this.setSpacing(true);

		opportunitySeachPanel = new OpportunitySearchPanel();
		this.addComponent(opportunitySeachPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable<SimpleOpportunity>();

		// add code generate column here

		tableItem.setWidth("100%");
		
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("salesstage", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("amount", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("expectedcloseddate", UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("createdtime", UIConstants.TABLE_DATE_WIDTH);

		accountListLayout.addComponent(constructTableActionControls());
		accountListLayout.addComponent(tableItem);
		accountListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public void displayOpportunitys(List<SimpleOpportunity> opportunities,
			int currentPage, int totalPages) {
		tableItem.setCurrentPage(currentPage);
		tableItem.setTotalPage(totalPages);

		BeanItemContainer<SimpleOpportunity> container = new BeanItemContainer<SimpleOpportunity>(
				SimpleOpportunity.class, opportunities);
		tableItem.setContainerDataSource(container);

		tableItem.setVisibleColumns(new String[] { "selected", "accountName",
				"salesstage", "amount", "expectedcloseddate",
				"assignUserFullName", "createdtime" });
		tableItem.setColumnHeaders(new String[] { "", "Account Name",
				"Sales Stage", "Amount", "Close", "User", "Date Created" });

	}

	@Override
	public HasSearchHandlers<OpportunitySearchCriteria> getSearchHandlers() {
		return opportunitySeachPanel;
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
	public void setPresenter(OpportunityListPresenter presenter) {
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
