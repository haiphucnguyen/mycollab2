package com.esofthead.mycollab.common;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.db.query.SearchFieldInfo;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class QueryAnalyzer {

    public static String toQueryParams(List<SearchFieldInfo> searchFieldInfos) {
        StringBuilder result = new StringBuilder();
        for (SearchFieldInfo searchFieldInfo : searchFieldInfos) {

        }
        return result.toString();
    }

    public static SearchCriteria fromQueryParams(String query, Class<? extends SearchCriteria> cls) {
        return null;
    }
}
