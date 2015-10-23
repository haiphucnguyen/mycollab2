package com.esofthead.mycollab.module.project.query;

import com.esofthead.mycollab.core.db.query.VariableInjecter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class CurrentProjectIdInjecter implements VariableInjecter {
    @Override
    public Object eval() {
        return Arrays.asList(CurrentProjectVariables.getProjectId());
    }
}
