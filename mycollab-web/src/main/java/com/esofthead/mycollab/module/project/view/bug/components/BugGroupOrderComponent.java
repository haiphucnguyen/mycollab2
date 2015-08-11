package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.vaadin.ui.CssLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public abstract class BugGroupOrderComponent extends CssLayout {
    abstract public void insertBugs(List<SimpleBug> tasks);
}
