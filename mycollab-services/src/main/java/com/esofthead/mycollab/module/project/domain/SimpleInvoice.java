package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.module.crm.domain.Account;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class SimpleInvoice extends Invoice {
    private Account client;
    private Currency currency;

    public Account getClient() {
        return client;
    }

    public void setClient(Account client) {
        this.client = client;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
