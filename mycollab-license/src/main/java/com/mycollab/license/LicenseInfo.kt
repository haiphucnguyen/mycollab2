package com.mycollab.license

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
class LicenseInfo {

    var customerId: String? = null

    var issueDate: LocalDate? = null

    var expireDate: LocalDate? = null

    var licenseOrg: String? = null

    var licenseType = LicenseType.PRO_TRIAL

    var maxUsers: Int = Integer.MAX_VALUE

    val isExpired: Boolean
        get() = expireDate!!.isBefore(LocalDate.now())

    val isTrial: Boolean
        get() = LicenseType.PRO_TRIAL == licenseType

    val isInvalid: Boolean
        get() = LicenseType.INVALID == licenseType

    val isRequiredALicense: Boolean
        get() = isInvalid || isExpired && isTrial
}
