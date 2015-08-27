package com.esofthead.mycollab.community.module.project.view.bug;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.bug.BugPopupFieldFactory;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanField;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@ViewComponent
public class BugPopupFieldFactoryImpl implements BugPopupFieldFactory {

    @Override
    public PopupView createBugCommentsPopupField(SimpleBug bug) {
        if (bug.getNumComments() != null) {
            return new PopupBeanField(FontAwesome.COMMENT_O.getHtml() + " " + bug.getNumComments());
        } else {
            return new PopupBeanField(FontAwesome.COMMENT_O.getHtml() + " 0");
        }
    }

    @Override
    public PopupView createBugMilestonePopupField(SimpleBug bug) {
        return new PopupBeanField(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() +
                " " + bug.getMilestoneName());
    }

    @Override
    public PopupView createBugStatusPopupField(SimpleBug bug) {
        return new PopupBeanField(FontAwesome.INFO_CIRCLE.getHtml() + " " + bug.getStatus());
    }

    @Override
    public PopupView createBugDeadlinePopupField(SimpleBug bug) {
        return new PopupBeanField(String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(),
                AppContext.formatPrettyTime(bug.getDueDateRoundPlusOne())));
    }
}
