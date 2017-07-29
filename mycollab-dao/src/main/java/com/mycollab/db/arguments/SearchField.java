package com.mycollab.db.arguments;

import com.mycollab.core.utils.BeanUtility;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SearchField implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String OR = "OR";
    public static final String AND = "AND";

    protected String operation = AND;

    public SearchField() {
    }

    public SearchField(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return BeanUtility.printBeanObj(this);
    }
}
