/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 *
 * @author haiphucnguyen
 */
public class AuditLogSearchCriteria extends SearchCriteria {
    private StringSearchField type;
    
    private NumberSearchField typeid;
    
    private NumberSearchField sAccountId;

    public StringSearchField getType() {
        return type;
    }

    public void setType(StringSearchField type) {
        this.type = type;
    }

    public NumberSearchField getTypeid() {
        return typeid;
    }

    public void setTypeid(NumberSearchField typeid) {
        this.typeid = typeid;
    }

    public NumberSearchField getsAccountId() {
        return sAccountId;
    }

    public void setsAccountId(NumberSearchField sAccountId) {
        this.sAccountId = sAccountId;
    }
}
