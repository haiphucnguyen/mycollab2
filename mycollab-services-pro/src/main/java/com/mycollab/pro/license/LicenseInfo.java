package com.mycollab.pro.license;

import org.joda.time.LocalDateTime;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class LicenseInfo {

    private String customerId;

    private Date issueDate;

    private Date expireDate;

    private String licenseOrg;

    private LicenseType licenseType = LicenseType.PRO_TRIAL;

    private Integer maxUsers = Integer.MAX_VALUE;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getLicenseOrg() {
        return licenseOrg;
    }

    public void setLicenseOrg(String licenseOrg) {
        this.licenseOrg = licenseOrg;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public boolean isExpired() {
        return expireDate.before(new LocalDateTime().toDate());
    }

    public boolean isTrial() {
        return LicenseType.PRO_TRIAL == licenseType;
    }

    public boolean isInvalid() {
        return LicenseType.INVALID == licenseType;
    }

    public boolean isRequiredALicense() {
        return isInvalid() || (isExpired() && isTrial());
    }
}
