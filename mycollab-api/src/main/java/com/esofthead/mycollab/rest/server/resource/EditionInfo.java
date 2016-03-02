package com.esofthead.mycollab.rest.server.resource;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class EditionInfo {
    private String version;
    private String communityDownloadLink;
    private String altCommunityDownloadLink;
    private String communityUpgradeLink;
    private String premiumDownloadLink;
    private String premiumUpgradeLink;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCommunityDownloadLink() {
        return communityDownloadLink;
    }

    public void setCommunityDownloadLink(String communityDownloadLink) {
        this.communityDownloadLink = communityDownloadLink;
    }

    public String getAltCommunityDownloadLink() {
        return altCommunityDownloadLink;
    }

    public void setAltCommunityDownloadLink(String altCommunityDownloadLink) {
        this.altCommunityDownloadLink = altCommunityDownloadLink;
    }

    public String getPremiumDownloadLink() {
        return premiumDownloadLink;
    }

    public void setPremiumDownloadLink(String premiumDownloadLink) {
        this.premiumDownloadLink = premiumDownloadLink;
    }

    public String getPremiumUpgradeLink() {
        return premiumUpgradeLink;
    }

    public void setPremiumUpgradeLink(String premiumUpgradeLink) {
        this.premiumUpgradeLink = premiumUpgradeLink;
    }

    public String getCommunityUpgradeLink() {
        return communityUpgradeLink;
    }

    public void setCommunityUpgradeLink(String communityUpgradeLink) {
        this.communityUpgradeLink = communityUpgradeLink;
    }
}
