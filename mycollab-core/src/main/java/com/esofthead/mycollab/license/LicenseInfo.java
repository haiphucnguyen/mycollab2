package com.esofthead.mycollab.license;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class LicenseInfo {
    private String edition;

    private String customerId;

    private Date issueDate;

    private Date expireDate;

    private String licenseOrg;

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

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
}
