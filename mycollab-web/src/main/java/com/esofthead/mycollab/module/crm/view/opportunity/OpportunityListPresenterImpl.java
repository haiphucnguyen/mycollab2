package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityListView.OpportunityListPresenter;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class OpportunityListPresenterImpl extends
		CrmGenericPresenter<OpportunityListView> implements
		OpportunityListPresenter {

	private OpportunityService opportunityService;

	private OpportunitySearchCriteria searchCriteria;

	private List<SimpleOpportunity> currentListData = new ArrayList<SimpleOpportunity>();

	private SelectionModel<SimpleOpportunity> selectionModel = new SelectionModel<SimpleOpportunity>();

	public OpportunityListPresenterImpl(OpportunityListView view) {
		this.view = view;
		opportunityService = AppContext.getSpringBean(OpportunityService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<OpportunitySearchCriteria>() {

					@Override
					public void onSearch(OpportunitySearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {

					@Override
					public void onSelect() {
						selectionModel.addSelections(currentListData);

						for (SimpleOpportunity opportunity : selectionModel) {
							CheckBox checkBox = (CheckBox) opportunity
									.getExtraData();
							checkBox.setValue(true);
						}

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						selectionModel.removeAll();
						for (SimpleOpportunity opportunity : currentListData) {
							CheckBox checkBox = (CheckBox) opportunity
									.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

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
				new SelectableItemHandler<SimpleOpportunity>() {

					@Override
					public void onSelect(SimpleOpportunity item) {
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
		OpportunitySearchCriteria opportunitySearchCriteria = new OpportunitySearchCriteria();
		opportunitySearchCriteria.setSaccountid(new NumberSearchField(
				SearchField.AND, AppContext.getAccountId()));
		doSearch(opportunitySearchCriteria);
	}

	@Override
	public void doSearch(OpportunitySearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
	}

	private void deleteSelectedItems() {
		List<Integer> keyList = new ArrayList<Integer>();
		for (SimpleOpportunity opportunity : selectionModel) {
			keyList.add(opportunity.getId());
		}

		if (keyList.size() > 0) {
			opportunityService.removeWithSession(keyList,
					AppContext.getUsername());
			doSearch(searchCriteria);
		}
	}
}
