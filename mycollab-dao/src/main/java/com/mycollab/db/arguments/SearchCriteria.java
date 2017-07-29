package com.mycollab.db.arguments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class SearchCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    private List<OrderField> orderFields;
    private NumberSearchField saccountid;
    private List<SearchField> extraFields;

    public SearchCriteria() {
        saccountid = new NumberSearchField(GroupIdProvider.getAccountId());
    }

    public NumberSearchField getSaccountid() {
        return saccountid;
    }

    public void setSaccountid(NumberSearchField saccountid) {
        this.saccountid = saccountid;
    }

    public List<SearchField> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(List<SearchField> extraFields) {
        this.extraFields = extraFields;
    }

    public SearchCriteria addExtraField(SearchField extraField) {
        if (extraField == null) {
            return this;
        }
        if (extraFields == null) {
            extraFields = new ArrayList<>();
        }
        extraFields.add(extraField);
        return this;
    }

    public void addOrderField(OrderField orderField) {
        if (orderFields == null) {
            orderFields = new ArrayList<>();
        }
        orderFields.add(orderField);
    }

    public List<OrderField> getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(List<OrderField> orderFields) {
        this.orderFields = orderFields;
    }

    public static class OrderField implements Serializable {
        private String field;
        private String direction;

        public OrderField(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }

        public String getField() {
            return field;
        }

        public String getDirection() {
            return direction;
        }
    }
}
