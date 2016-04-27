package com.esofthead.mycollab.core.arguments;

import java.io.Serializable;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class SearchRequest implements Serializable {
    public static final int DEFAULT_NUMBER_SEARCH_ITEMS = 25;

    private Integer currentPage = 1;
    private Integer numberOfItems = 1;
    private String requestedUser;

    public SearchRequest(int currentPage, int numberOfItems) {
        this.currentPage = currentPage;
        this.numberOfItems = numberOfItems;
        this.requestedUser = GroupIdProvider.getRequestedUser();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(String requestedUser) {
        this.requestedUser = requestedUser;
    }
}
