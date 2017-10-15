package com.mycollab.license.service

import com.mycollab.license.LicenseInfo

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
interface LicenseResolver {

    val licenseInfo: LicenseInfo?

    fun checkAndSaveLicenseInfo(licenseInputText: String)

    fun checkLicenseInfo(licenseBytes: ByteArray, isSave: Boolean): LicenseInfo
}
