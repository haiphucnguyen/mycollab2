package com.mycollab.license.service;

import com.mycollab.license.LicenseInfo;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public interface LicenseResolver {

    LicenseInfo getLicenseInfo();

    void checkAndSaveLicenseInfo(String licenseInputText);

    LicenseInfo checkLicenseInfo(byte[] licenseBytes, boolean isSave);
}
