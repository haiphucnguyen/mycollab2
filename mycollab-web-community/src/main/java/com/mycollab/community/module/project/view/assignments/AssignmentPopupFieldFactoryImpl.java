package com.mycollab.community.module.project.view.assignments;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.i18n.FollowerI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.community.vaadin.web.ui.field.MetaFieldBuilder;
import com.mycollab.core.utils.NumberUtils;
import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.assignments.AssignmentPopupFieldFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractComponent;
import org.vaadin.teemu.VaadinIcons;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@ViewComponent
public class AssignmentPopupFieldFactoryImpl implements AssignmentPopupFieldFactory {
    @Override
    public AbstractComponent createStartDatePopupField(ProjectAssignment assignment) {
        if (assignment.getStartDate() == null) {
            Div divHint = new Div().setCSSClass("nonValue");
            divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
            divHint.appendChild(new Span().appendText(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)).setCSSClass("hide"));
            return new MetaFieldBuilder().withCaption(divHint.write())
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE)).build();
        } else {
            return new MetaFieldBuilder().withCaption(String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(),
                    UserUIContext.formatDate(assignment.getStartDate())))
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE)).build();
        }
    }

    @Override
    public AbstractComponent createEndDatePopupField(ProjectAssignment assignment) {
        if (assignment.getEndDate() == null) {
            Div divHint = new Div().setCSSClass("nonValue");
            divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
            divHint.appendChild(new Span().appendText(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)).setCSSClass("hide"));
            return new MetaFieldBuilder().withCaption(divHint.write()).withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE)).build();
        } else {
            return new MetaFieldBuilder().withCaption(String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(),
                    UserUIContext.formatDate(assignment.getEndDate()))).withDescription(UserUIContext.getMessage
                    (GenericI18Enum.FORM_END_DATE)
            ).build();
        }
    }

    @Override
    public AbstractComponent createDueDatePopupField(ProjectAssignment assignment) {
        if (assignment.getDueDatePlusOne() == null) {
            Div divHint = new Div().setCSSClass("nonValue");
            divHint.appendText(FontAwesome.CLOCK_O.getHtml());
            divHint.appendChild(new Span().appendText(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)).setCSSClass("hide"));
            return new MetaFieldBuilder().withCaption(divHint.write())
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE)).build();
        } else {
            return new MetaFieldBuilder().withCaption(String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(),
                    UserUIContext.formatPrettyTime(assignment.getDueDatePlusOne())))
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE)).build();
        }
    }

    @Override
    public AbstractComponent createPriorityPopupField(ProjectAssignment assignment) {
        return new MetaFieldBuilder().withCaption(ProjectAssetsManager.getTaskPriorityHtml(assignment.getPriority()))
                .withDescription(UserUIContext.getMessage(GenericI18Enum.FORM_PRIORITY_HELP)).build();
    }

    @Override
    public AbstractComponent createBillableHoursPopupField(ProjectAssignment task) {
        return null;
    }

    @Override
    public AbstractComponent createNonBillableHoursPopupField(ProjectAssignment task) {
        return null;
    }

    @Override
    public AbstractComponent createFollowersPopupField(ProjectAssignment assignment) {
        return new MetaFieldBuilder().withCaptionAndIcon(FontAwesome.EYE, "" + NumberUtils.zeroIfNull(assignment.getNumFollowers()))
                .withDescription(UserUIContext.getMessage(FollowerI18nEnum.OPT_SUB_INFO_WATCHERS)).build();
    }

    @Override
    public AbstractComponent createCommentsPopupField(ProjectAssignment assignment) {
        return new MetaFieldBuilder().withCaption(FontAwesome.COMMENT_O.getHtml() + " " + NumberUtils.zeroIfNull(assignment.getNumComments()))
                .withDescription(UserUIContext.getMessage(GenericI18Enum.OPT_COMMENTS)).build();
    }

    @Override
    public AbstractComponent createStatusPopupField(ProjectAssignment assignment) {
        return new MetaFieldBuilder().withCaptionAndIcon(FontAwesome.INFO_CIRCLE, assignment.getStatus()).withDescription
                (UserUIContext.getMessage(GenericI18Enum.FORM_STATUS)).build();
    }
}
