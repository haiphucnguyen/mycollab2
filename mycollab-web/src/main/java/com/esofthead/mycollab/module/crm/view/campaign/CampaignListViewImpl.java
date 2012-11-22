package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSearchPanel;
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

@SuppressWarnings("serial")
public class CampaignListViewImpl extends AbstractView implements
		CampaignListView {

	private CampaignSearchPanel campaignSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable<SimpleCampaign> tableItem;

	private VerticalLayout campainListLayout;

	private PopupButtonControl tableActionControls;

	private Label selectedItemsNumberLabel = new Label();

	private CampaignListPresenter presenter;

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
		tableItem = new PagedBeanTable<SimpleCampaign>();
		tableItem.setWidth("1130px");

		campainListLayout.addComponent(constructTableActionControls());
		campainListLayout.addComponent(tableItem);
		campainListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public void displayCampaigns(List<SimpleCampaign> campaigns, int currentPage,
			int totalPages) {
		tableItem.setCurrentPage(currentPage);
		tableItem.setTotalPage(totalPages);

		BeanItemContainer<SimpleCampaign> container = new BeanItemContainer<SimpleCampaign>(
				SimpleCampaign.class, campaigns);
		tableItem.setContainerDataSource(container);
		
		tableItem.setVisibleColumns(new String[] { "selected", "campaignname",
				"status", "type", "expectedrevenue", "enddate", "assignUserFullName"});
		tableItem.setColumnHeaders(new String[] { "", "Campaign", "Status", "Type",
				"Expected Revenue", "End Date", "Assign User" });


	}

	@Override
	public HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers() {
		return campaignSearchPanel;
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
	public void setPresenter(CampaignListPresenter presenter) {
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
