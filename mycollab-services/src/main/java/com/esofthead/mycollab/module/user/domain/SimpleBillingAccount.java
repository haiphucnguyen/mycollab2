/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.NotBindable;
import com.esofthead.mycollab.core.utils.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SimpleBillingAccount extends BillingAccount {
    private static final long serialVersionUID = 1L;

    @NotBindable
    private BillingPlan billingPlan;

    @NotBindable
    private SimpleDateFormat dateTimeFormat;

    @NotBindable
    private SimpleDateFormat dateFormatInstance;

    @NotBindable
    private SimpleDateFormat shortDateFormatInstance;

    @NotBindable
    private SimpleDateFormat humanDateFormatInstance;

    @NotBindable
    private Locale localeInstance;

    public BillingPlan getBillingPlan() {
        return billingPlan;
    }

    public void setBillingPlan(BillingPlan billingPlan) {
        this.billingPlan = billingPlan;
    }

    @Override
    public void setDefaultyymmddformat(String defaultyymmddformat) {
        if (StringUtils.isBlank(defaultyymmddformat)) {
            super.setDefaultyymmddformat("MM/dd/yyyy");
        } else {
            super.setDefaultyymmddformat(defaultyymmddformat);
        }
        dateFormatInstance = new SimpleDateFormat(getDefaultyymmddformat());
        dateTimeFormat = new SimpleDateFormat(getDefaultyymmddformat() + "HH:mm:ss z");
    }

    @Override
    public void setDefaultmmddformat(String defaultmmddformat) {
        if (StringUtils.isBlank(defaultmmddformat)) {
            super.setDefaultmmddformat("MM/dd");
        } else {
            super.setDefaultmmddformat(defaultmmddformat);
        }
        shortDateFormatInstance = new SimpleDateFormat(getDefaultmmddformat());
    }

    @Override
    public void setDefaulthumandateformat(String defaulthumandateformat) {
        if (StringUtils.isBlank(defaulthumandateformat)) {
            super.setDefaulthumandateformat("E, dd MMM yyyy");
        } else {
            super.setDefaulthumandateformat(defaulthumandateformat);
        }
        humanDateFormatInstance = new SimpleDateFormat(getDefaulthumandateformat());
    }

    @Override
    public void setDefaultlanguagetag(String defaultlanguagetag) {
        super.setDefaultlanguagetag(defaultlanguagetag);
    }

    public SimpleDateFormat getDateFormatInstance() {
        return dateFormatInstance;
    }

    public SimpleDateFormat getShortDateFormatInstance() {
        return shortDateFormatInstance;
    }

    public SimpleDateFormat getHumanDateFormatInstance() {
        return humanDateFormatInstance;
    }

    public SimpleDateFormat getDateTimeFormatInstance() {
        return dateTimeFormat;
    }

    public Locale getLocaleInstance() {
        return localeInstance;
    }
}
