package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class BugRowRenderer implements AbstractBeanPagedList.RowDisplayHandler<SimpleBug> {
    @Override
    public Component generateRow(AbstractBeanPagedList host, SimpleBug bug, int rowIndex) {
        ToggleBugSummaryField toggleBugSummaryField = new ToggleBugSummaryField(bug);

        MHorizontalLayout rowComp = new MHorizontalLayout().withStyleName(UIConstants.HOVER_EFFECT_NOT_BOX);
        rowComp.addStyleName("margin-bottom");
        rowComp.setDefaultComponentAlignment(Alignment.TOP_LEFT);

        String bugPriority = bug.getPriority();
        Span priorityLink = new Span().appendText(ProjectAssetsManager.getBugPriorityHtml(bugPriority)).setTitle(bugPriority);

        Span statusSpan = new Span().appendText(AppContext.getMessage(OptionI18nEnum.BugStatus.class,
                bug.getStatus())).setCSSClass(UIConstants.FIELD_NOTE);

        String avatarLink = StorageFactory.getInstance().getAvatarPath(bug.getAssignUserAvatarId(), 16);
        Img img = new Img(bug.getAssignuserFullName(), avatarLink).setTitle(bug.getAssignuserFullName());

        rowComp.with(new ELabel(ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG).getHtml(), ContentMode.HTML)
                        .withWidthUndefined(), new ELabel(priorityLink.write(), ContentMode.HTML).withWidthUndefined(),
                new ELabel(statusSpan.write(), ContentMode.HTML).withWidthUndefined(),
                new ELabel(img.write(), ContentMode.HTML).withWidthUndefined(),
                toggleBugSummaryField).expand(toggleBugSummaryField);
        return rowComp;
    }
}
