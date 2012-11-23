package com.esofthead.mycollab.module.crm.view.contact;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.SelectionModel;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.contact.ContactListView.ContactListPresenter;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

public class ContactListPresenterImpl extends CrmGenericPresenter<ContactListView>
		implements ContactListPresenter {

	private ContactService contactService;

	private SearchRequest<ContactSearchCriteria> searchRequest;

	private List<SimpleContact> currentListData = new ArrayList<SimpleContact>();

	private SelectionModel<SimpleContact> selectionModel = new SelectionModel<SimpleContact>();

	public ContactListPresenterImpl(ContactListView view) {
		this.view = view;
		contactService = AppContext.getSpringBean(ContactService.class);

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<ContactSearchCriteria>() {

					@Override
					public void onSearch(ContactSearchCriteria criteria) {
						searchRequest = new SearchRequest<ContactSearchCriteria>(
								criteria, 0,
								SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
						doSearch();
					}
				});

		view.getPagableHandlers().addPagableHandler(new PagableHandler() {

			@Override
			public void move(int newPageNumber) {
				searchRequest.setCurrentPage(newPageNumber);
				doSearch();
			}

			@Override
			public void displayItemChange(int numOfItems) {
				searchRequest.setNumberOfItems(numOfItems);
				doSearch();
			}
		});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {

					@Override
					public void onSelect() {
						selectionModel.addSelections(currentListData);

						for (SimpleContact contact : selectionModel) {
							CheckBox checkBox = (CheckBox) contact
									.getExtraData();
							checkBox.setValue(true);
						}

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						selectionModel.removeAll();
						for (SimpleContact contact : currentListData) {
							CheckBox checkBox = (CheckBox) contact
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
		
		view.getSelectableItemHandlers().addSelectableItemHandler(new SelectableItemHandler<SimpleContact>() {
			
			@Override
			public void onSelect(SimpleContact item) {
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
		ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria();
		contactSearchCriteria.setSaccountid(new NumberSearchField(
				SearchField.AND, AppContext.getAccountId()));
		doSearch(contactSearchCriteria);
	}

	@Override
	public void doSearch(ContactSearchCriteria searchCriteria) {
		this.searchRequest = new SearchRequest<ContactSearchCriteria>(
				searchCriteria, 1, SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
		doSearch();
	}

	@SuppressWarnings("unchecked")
	private void doSearch() {
		int totalCount = contactService.getTotalCount(searchRequest
				.getSearchCriteria());
		int totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		currentListData = contactService
				.findPagableListByCriteria(searchRequest);
		view.displayContacts(currentListData, searchRequest.getCurrentPage(),
				totalPage);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		List<Integer> keyList = new ArrayList<Integer>();
		for (SimpleContact contact : selectionModel) {
			keyList.add(contact.getId());
		}

		if (keyList.size() > 0) {
			contactService.removeWithSession(keyList, AppContext.getUsername());
			doSearch();
		}
	}
}
