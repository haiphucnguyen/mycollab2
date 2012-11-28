package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignListView.CampaignListPresenter;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class CampaignListPresenterImpl extends
		CrmGenericPresenter<CampaignListView> implements CampaignListPresenter {
	private CampaignService campaignService;

	private CampaignSearchCriteria searchCriteria;

	private List<SimpleCampaign> currentListData = new ArrayList<SimpleCampaign>();

	private SelectionModel<SimpleCampaign> selectionModel = new SelectionModel<SimpleCampaign>();

	public CampaignListPresenterImpl(CampaignListView view) {
		this.view = view;
		campaignService = AppContext.getSpringBean(CampaignService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<CampaignSearchCriteria>() {

					@Override
					public void onSearch(CampaignSearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {

					@Override
					public void onSelectCurrentPage() {
						selectionModel.addSelections(currentListData);

						for (SimpleCampaign campaign : selectionModel) {
							CheckBox checkBox = (CheckBox) campaign
									.getExtraData();
							checkBox.setValue(true);
						}

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						selectionModel.removeAll();
						for (SimpleCampaign campaign : currentListData) {
							CheckBox checkBox = (CheckBox) campaign
									.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

					}

					@Override
					public void onSelectAll() {
						// TODO Auto-generated method stub
						
					}
				});

		view.getPopupActionHandlers().addPopupActionHandler(
				new PopupActionHandler() {

					@Override
					public void onSelect(String id, String caption) {
						if ("delete".equals(id)) {
							deleteSelectedItems();
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleCampaign>() {

					@Override
					public void onSelect(SimpleCampaign item) {
						if (selectionModel.isSelected(item)) {
							selectionModel.removeSelection(item);
						} else {
							selectionModel.addSelection(item);
						}

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void checkWhetherEnableTableActionControl() {
		if (selectionModel.size() > 0) {
			view.enableActionControls(selectionModel.size());
		} else {
			view.disableActionControls();
		}
	}

	@Override
	public void doDefaultSearch() {
		CampaignSearchCriteria campaignSearchCriteria = new CampaignSearchCriteria();
		campaignSearchCriteria.setSaccountid(new NumberSearchField(
				SearchField.AND, AppContext.getAccountId()));
		doSearch(campaignSearchCriteria);
	}

	@Override
	public void doSearch(CampaignSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
	}

	private void deleteSelectedItems() {
		List<Integer> keyList = new ArrayList<Integer>();
		for (SimpleCampaign campaign : selectionModel) {
			keyList.add(campaign.getId());
		}

		if (keyList.size() > 0) {
			campaignService
					.removeWithSession(keyList, AppContext.getUsername());
			doSearch(searchCriteria);
		}
	}
}
