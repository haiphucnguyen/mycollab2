package com.esofthead.mycollab.core.arguments;

import java.util.Date;

public class DateSearchField extends SearchField {

    public static String LESSTHAN = "<";
    public static String LESSTHANEQUAL = "<=";
    public static String GREATERTHAN = ">";
    public static String GRREATERTHANEQUAL = ">=";
    
    private Date value;
    private String comparision;

    public DateSearchField(String oper, Date value) {
        this(oper, DateSearchField.LESSTHAN, value);
    }

    public DateSearchField(String oper, String comparision, Date value) {
        this.operation = oper;
        this.value = value;
        this.comparision = comparision;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public String getComparision() {
        return comparision;
    }

    public void setComparision(String comparision) {
        this.comparision = comparision;
    }

}
