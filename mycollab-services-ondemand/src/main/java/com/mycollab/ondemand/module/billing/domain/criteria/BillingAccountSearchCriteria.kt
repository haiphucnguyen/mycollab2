package com.mycollab.ondemand.module.billing.domain.criteria

import com.mycollab.db.arguments.DateSearchField
import com.mycollab.db.arguments.RangeDateSearchField
import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.arguments.SetSearchField

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
class BillingAccountSearchCriteria : SearchCriteria() {
    var lastAccessTime: DateSearchField? = null

    var statuses: SetSearchField<String>? = null

    var registerTimeDuration: RangeDateSearchField? = null
}
