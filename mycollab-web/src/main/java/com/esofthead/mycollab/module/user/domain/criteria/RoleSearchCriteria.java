package com.esofthead.mycollab.module.user.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class RoleSearchCriteria extends SearchCriteria {

    private StringSearchField username;
    private NumberSearchField sAccountid;

    public StringSearchField getUsername() {
        return username;
    }

    public void setUsername(StringSearchField username) {
        this.username = username;
    }

    public NumberSearchField getsAccountid() {
        return sAccountid;
    }

    public void setsAccountid(NumberSearchField sAccountid) {
        this.sAccountid = sAccountid;
    }
}
