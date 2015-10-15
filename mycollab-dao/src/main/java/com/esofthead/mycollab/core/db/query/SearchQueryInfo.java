package com.esofthead.mycollab.core.db.query;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class SearchQueryInfo {
    private String queryName;
    private List<SearchFieldInfo> searchFieldInfos;

    public SearchQueryInfo(String queryName, SearchFieldInfo... searchFieldInfoArr) {
        this.queryName = queryName;
        searchFieldInfos = Arrays.asList(searchFieldInfoArr);
    }

    public SearchQueryInfo(String queryName, List<SearchFieldInfo> searchFieldInfos) {
        this.queryName = queryName;
        this.searchFieldInfos = searchFieldInfos;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public List<SearchFieldInfo> getSearchFieldInfos() {
        return searchFieldInfos;
    }

    public void setSearchFieldInfos(List<SearchFieldInfo> searchFieldInfos) {
        this.searchFieldInfos = searchFieldInfos;
    }
}
