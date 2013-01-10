package com.esofthead.mycollab.core.arguments;

public class NumberSearchField extends SearchField {

    public static final String EQUAL = "=";
    public static final String NOTEQUAL = "<>";
    private Number value;
    private String compareOperator;

    public NumberSearchField(Number value) {
        this(SearchField.AND, value, EQUAL);
    }

    public NumberSearchField(String oper, Number value) {
        this(oper, value, EQUAL);
    }
    
    public NumberSearchField(Number value, String compareOperator) {
        this(SearchField.AND, value, compareOperator);
    }

    public NumberSearchField(String oper, Number value, String compareOperator) {
        this.operation = oper;
        this.value = value;
        this.compareOperator = compareOperator;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public String getCompareOperator() {
        return compareOperator;
    }

    public void setCompareOperator(String compareOperator) {
        this.compareOperator = compareOperator;
    }
}
