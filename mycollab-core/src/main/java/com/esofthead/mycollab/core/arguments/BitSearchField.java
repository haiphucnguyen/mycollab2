/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.core.arguments;

import com.esofthead.mycollab.core.utils.BeanUtility;

/**
 *
 * @author haiphucnguyen
 */
public class BitSearchField extends NumberSearchField {
    public static final BitSearchField TRUE = new BitSearchField(AND, 1);
    public static final BitSearchField FALSE = new BitSearchField(AND, 0);
    
    public BitSearchField(Number value) {
        this(SearchField.AND, value, EQUAL);
    }

    public BitSearchField(String oper, Number value) {
        this(oper, value, EQUAL);
    }
    
    public BitSearchField(Number value, String compareOperator) {
        this(SearchField.AND, value, compareOperator);
    }

    public BitSearchField(String oper, Number value, String compareOperator) {
        super(oper, value, compareOperator);
    }
    
    public String toString() {
		return BeanUtility.printBeanObj(this);
	}
}
