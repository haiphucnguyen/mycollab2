package com.esofthead.mycollab.core.db.query;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class DateRangeInjecter implements VariableInjecter {
    private Date from, to;

    public DateRangeInjecter(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Object eval() {
        return (from != null && to != null) ? new Date[]{from, to} : null;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
