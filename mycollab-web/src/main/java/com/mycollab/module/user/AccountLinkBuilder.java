package com.mycollab.module.user;

import com.mycollab.vaadin.MyCollabUI;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */
public class AccountLinkBuilder {
    public static String generatePreviewFullUserLink(String username) {
        return AccountLinkGenerator.generatePreviewFullUserLink(MyCollabUI.getSiteUrl(), username);
    }

    public static String generatePreviewFullRoleLink(Integer userRoleId) {
        return AccountLinkGenerator.generatePreviewFullRoleLink(MyCollabUI.getSiteUrl(), userRoleId);
    }
}
