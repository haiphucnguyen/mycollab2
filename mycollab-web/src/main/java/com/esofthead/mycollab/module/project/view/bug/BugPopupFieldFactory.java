package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public interface BugPopupFieldFactory extends CacheableComponent {
    PopupView createBugStatusPopupField(SimpleBug bug);

    PopupView createBugMilestonePopupField(SimpleBug bug);

    PopupView createBugDeadlinePopupField(SimpleBug bug);
}
