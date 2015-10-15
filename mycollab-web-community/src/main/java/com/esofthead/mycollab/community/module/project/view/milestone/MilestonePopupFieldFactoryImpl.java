package com.esofthead.mycollab.community.module.project.view.milestone;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePopupFieldFactory;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupFieldBuilder;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.PopupView;
import org.vaadin.teemu.VaadinIcons;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class MilestonePopupFieldFactoryImpl implements MilestonePopupFieldFactory {
    @Override
    public PopupView createMilestoneAssigneePopupField(SimpleMilestone milestone) {
        String avatarLink = StorageFactory.getInstance().getAvatarPath(milestone.getOwnerAvatarId(), 16);
        Img img = new Img(milestone.getOwnerFullName(), avatarLink).setTitle(milestone.getOwnerFullName());
        return new PopupFieldBuilder().withCaption(img.write() + " " + StringUtils.trim(milestone.getOwnerFullName(), 20, true))
                .withDescription(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).build();
    }

    @Override
    public PopupView createStartDatePopupField(SimpleMilestone milestone) {
        if (milestone.getStartdate() == null) {
            Div divHint = new Div().setCSSClass("nonValue");
            divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
            divHint.appendChild(new Span().appendText(" Click to edit").setCSSClass("hide"));
            return new PopupFieldBuilder().withCaption(divHint.write()).withDescription("Start date").build();
        } else {
            return new PopupFieldBuilder().withCaption(String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(),
                    AppContext.formatDate(milestone.getStartdate()))).withDescription("Start date").build();
        }
    }

    @Override
    public PopupView createEndDatePopupField(SimpleMilestone milestone) {
        if (milestone.getEnddate() == null) {
            Div divHint = new Div().setCSSClass("nonValue");
            divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
            divHint.appendChild(new Span().appendText(" Click to edit").setCSSClass("hide"));
            return new PopupFieldBuilder().withCaption(divHint.write()).withDescription("End date").build();
        } else {
            return new PopupFieldBuilder().withCaption(String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(),
                    AppContext.formatDate(milestone.getEnddate()))).withDescription("End date").build();
        }
    }

    @Override
    public PopupView createBillableHoursPopupField(SimpleMilestone milestone) {
        return new PopupFieldBuilder().withCaptionAndIcon(FontAwesome.MONEY, "" + (milestone.getTotalBugBillableHours() + milestone.getTotalTaskBillableHours()))
                .withDescription("Billable hours").build();
    }

    @Override
    public PopupView createNonBillableHoursPopupField(SimpleMilestone milestone) {
        return new PopupFieldBuilder().withCaptionAndIcon(FontAwesome.GIFT, "" + (milestone.getTotalBugNonBillableHours() + milestone.getTotalTaskNonBillableHours()))
                .withDescription("Billable hours").build();
    }
}
