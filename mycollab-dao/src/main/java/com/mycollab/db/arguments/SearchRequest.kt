package com.mycollab.db.arguments

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
open class SearchRequest(val currentPage: Int, val numberOfItems: Int, requestedUser: String = GroupIdProvider.requestedUser) {
    companion object {
        val DEFAULT_NUMBER_SEARCH_ITEMS = 25
    }
}