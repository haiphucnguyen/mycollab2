package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.module.project.view.bug.BugDisplayWidget;
import com.esofthead.mycollab.module.project.view.parameters.BugFilterParameter;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class WaitingApprovalBugsWidget  extends BugDisplayWidget {
    private static final long serialVersionUID = 1L;

    public WaitingApprovalBugsWidget() {
        super("Waiting Approval", false, BugRowDisplayHandler.class);
    }

    @Override
    protected BugFilterParameter constructMoreDisplayFilter() {
        return new BugFilterParameter("Waiting Approval", searchCriteria);
    }
}
