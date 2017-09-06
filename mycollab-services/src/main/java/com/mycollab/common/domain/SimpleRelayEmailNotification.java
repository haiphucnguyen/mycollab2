package com.mycollab.common.domain;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.user.domain.SimpleUser;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SimpleRelayEmailNotification extends RelayEmailNotificationWithBLOBs {
    private static final long serialVersionUID = 1L;

    private String accountLogo;
    private String changeByUserFullName;
    private List<SimpleUser> notifyUsers;

    public String getChangeByUserFullName() {
        if (StringUtils.isBlank(changeByUserFullName)) {
            return StringUtils.extractNameFromEmail(getChangeby());
        }
        return changeByUserFullName;
    }

    public void setChangeByUserFullName(String changeByUserFullName) {
        this.changeByUserFullName = changeByUserFullName;
    }

    public List<SimpleUser> getNotifyUsers() {
        return notifyUsers;
    }

    public void setNotifyUsers(List<SimpleUser> notifyUsers) {
        this.notifyUsers = notifyUsers;
    }

    public String getAccountLogo() {
        return accountLogo;
    }

    public void setAccountLogo(String accountLogo) {
        this.accountLogo = accountLogo;
    }
}
