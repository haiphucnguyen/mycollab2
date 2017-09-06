package com.mycollab.db.query;

import java.util.Arrays;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class SearchQueryInfo {
    private String queryId;
    private String queryName;
    private List<SearchFieldInfo> searchFieldInfos;

    public SearchQueryInfo(String queryName, SearchFieldInfo... searchFieldInfoArr) {
        this(queryName, Arrays.asList(searchFieldInfoArr));
    }

    public SearchQueryInfo(String queryName, List<SearchFieldInfo> searchFieldInfos) {
        this("", queryName, searchFieldInfos);
    }

    public SearchQueryInfo(String queryId, String queryName, SearchFieldInfo... searchFieldInfoArr) {
        this(queryId, queryName, Arrays.asList(searchFieldInfoArr));
    }

    public SearchQueryInfo(String queryId, String queryName, List<SearchFieldInfo> searchFieldInfos) {
        this.queryId = queryId;
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

    public String getQueryId() {
        return queryId;
    }
}
