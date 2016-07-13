package com.mycollab.premium.license.service;

import com.mycollab.pro.license.LicenseInfo;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public interface LicenseResolver {
    LicenseInfo getLicenseInfo();

    void checkAndSaveLicenseInfo(String licenseInputText);

    void checkLicenseInfo(byte[] licenseBytes, boolean isSave);
}
