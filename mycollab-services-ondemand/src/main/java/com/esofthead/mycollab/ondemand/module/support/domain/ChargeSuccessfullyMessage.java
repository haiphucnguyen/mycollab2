package com.esofthead.mycollab.ondemand.module.support.domain;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class ChargeSuccessfullyMessage {
    private Integer accountId;

    public ChargeSuccessfullyMessage(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountId() {
        return accountId;
    }
}
