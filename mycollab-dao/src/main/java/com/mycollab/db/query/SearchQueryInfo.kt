package com.mycollab.db.query

import java.util.Arrays

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
class SearchQueryInfo(val queryId: String, var queryName: String?, var searchFieldInfos: List<SearchFieldInfo<*>>?) {

    constructor(queryName: String, vararg searchFieldInfoArr: SearchFieldInfo<*>) : this(queryName, Arrays.asList(*searchFieldInfoArr)) {}

    constructor(queryName: String, searchFieldInfos: List<SearchFieldInfo<*>>) : this("", queryName, searchFieldInfos) {}

    constructor(queryId: String, queryName: String, vararg searchFieldInfoArr: SearchFieldInfo<*>) : this(queryId, queryName, Arrays.asList(*searchFieldInfoArr)) {}
}
