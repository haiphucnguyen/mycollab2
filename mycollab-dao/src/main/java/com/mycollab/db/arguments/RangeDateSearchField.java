package com.mycollab.db.arguments;

import java.util.Date;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RangeDateSearchField extends SearchField {
    private static final long serialVersionUID = 1L;

    private Date from;
    private Date to;

    public RangeDateSearchField() {
    }

    public RangeDateSearchField(Date from, Date to) {
        this(SearchField.AND, from, to);
    }

    public RangeDateSearchField(final String oper, final Date from, final Date to) {
        this.operation = oper;
        this.from = from;
        this.to = to;
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
