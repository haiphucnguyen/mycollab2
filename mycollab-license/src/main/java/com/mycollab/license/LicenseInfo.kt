package com.mycollab.license

import org.joda.time.LocalDateTime

import java.util.Date

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
class LicenseInfo {

    var customerId: String? = null

    var issueDate: Date? = null

    var expireDate: Date? = null

    var licenseOrg: String? = null

    var licenseType = LicenseType.PRO_TRIAL

    var maxUsers: Int = Integer.MAX_VALUE

    val isExpired: Boolean
        get() = expireDate!!.before(LocalDateTime().toDate())

    val isTrial: Boolean
        get() = LicenseType.PRO_TRIAL == licenseType

    val isInvalid: Boolean
        get() = LicenseType.INVALID == licenseType

    val isRequiredALicense: Boolean
        get() = isInvalid || isExpired && isTrial
}
