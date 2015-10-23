package com.esofthead.mycollab.core.db.query;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class ConstantValueInjecter implements VariableInjecter {
    private Object value;

    public ConstantValueInjecter(Object value) {
        this.value = value;
    }

    @Override
    public Object eval() {
        return value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
