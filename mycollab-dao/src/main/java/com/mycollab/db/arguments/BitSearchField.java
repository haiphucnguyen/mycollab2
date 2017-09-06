package com.mycollab.db.arguments;

import com.mycollab.core.utils.BeanUtility;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class BitSearchField extends NumberSearchField {
    private static final long serialVersionUID = 1L;

    public static final BitSearchField TRUE = new BitSearchField(AND, 1);
    public static final BitSearchField FALSE = new BitSearchField(AND, 0);

    public BitSearchField() {
        this(AND, 0);
    }

    public BitSearchField(String oper, Number value) {
        this(oper, value, NumberSearchField.EQUAL());
    }

    public BitSearchField(String oper, Number value, String compareOperator) {
        super(oper, value, compareOperator);
    }

    public String toString() {
        return BeanUtility.printBeanObj(this);
    }
}
