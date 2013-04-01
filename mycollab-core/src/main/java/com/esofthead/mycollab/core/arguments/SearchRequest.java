package com.esofthead.mycollab.core.arguments;

public class SearchRequest<S extends SearchCriteria> {
	public static int DEFAULT_NUMBER_SEARCH_ITEMS = 2;
	
	private S searchCriteria;

	private int currentPage = 1;

	private int numberOfItems = 1;

	public SearchRequest(S searchCriteria, int currentPage, int numberOfItems) {
		this.searchCriteria = searchCriteria;
		this.currentPage = currentPage;
		this.numberOfItems = numberOfItems;
	}

	public S getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(S searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
}
