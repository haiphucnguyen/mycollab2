package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class SimpleListOrderComponent extends BugGroupOrderComponent {
    public SimpleListOrderComponent() {
        this.setStyleName("tasklist");
    }

    @Override
    public void insertBugs(List<SimpleBug> bugs) {
        for (SimpleBug bug : bugs) {
            this.addComponent(new BugRowRenderer(bug));
        }
    }
}
