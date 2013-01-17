package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ComponentSearchCriteria extends SearchCriteria {

    private NumberSearchField projectid;
    
    private StringSearchField componentName;

    public NumberSearchField getProjectid() {
        return projectid;
    }

    public void setProjectid(NumberSearchField projectid) {
        this.projectid = projectid;
    }

    public StringSearchField getComponentName() {
        return componentName;
    }

    public void setComponentName(StringSearchField componentName) {
        this.componentName = componentName;
    }
}
