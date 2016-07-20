package com.mycollab.pro.module.project.view.milestone;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.milestone.MilestonePopupFieldFactory;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class MilestonePopupFieldFactoryImpl implements MilestonePopupFieldFactory {
    @Override
    public PopupView createMilestoneAssigneePopupField(final SimpleMilestone milestone, final boolean isDisplayName) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                String avatarLink = StorageFactory.getAvatarPath(milestone.getOwnerAvatarId(), 16);
                Img img = new Img(milestone.getOwnerFullName(), avatarLink).setCSSClass(UIConstants.CIRCLE_BOX);
                if (isDisplayName) {
                    img.setTitle(milestone.getOwnerFullName());
                }
                return img.write();
            }

            @Override
            protected String generateSmallAsHtmlAfterUpdate() {
                MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                SimpleMilestone newMilestone = milestoneService.findById(milestone.getId(), AppContext.getAccountId());
                String avatarLink = StorageFactory.getAvatarPath(newMilestone.getOwnerAvatarId(), 16);
                Img img = new Img(newMilestone.getOwnerFullName(), avatarLink).setCSSClass(UIConstants.CIRCLE_BOX);
                if (isDisplayName) {
                    img.setTitle(newMilestone.getOwnerFullName());
                }
                return img.write();
            }

            @Override
            protected String generateDescription() {
                return milestone.getOwnerFullName();
            }
        };
        builder.withBean(milestone).withBindProperty("owner").withDescription(milestone.getOwnerFullName())
                .withCaption(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)).withField(new ProjectMemberSelectionField())
                .withService(AppContextUtil.getSpringBean(MilestoneService.class)).withValue(milestone.getOwner())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));
        return builder.build();
    }

    @Override
    public PopupView createStartDatePopupField(final SimpleMilestone milestone) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (milestone.getStartdate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), AppContext.formatDate(milestone.getStartdate()));
                }

            }
        };
        builder.withBean(milestone).withBindProperty("startdate").withCaption(AppContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new PopupDateFieldExt()).withService(AppContextUtil.getSpringBean(MilestoneService.class))
                .withValue(milestone.getStartdate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));
        return builder.build();
    }

    @Override
    public PopupView createEndDatePopupField(final SimpleMilestone milestone) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (milestone.getEnddate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" Click to edit " + caption).setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), AppContext.formatDate(milestone.getEnddate()));
                }

            }
        };
        builder.withBean(milestone).withBindProperty("enddate").withCaption(AppContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new PopupDateFieldExt()).withService(AppContextUtil.getSpringBean(MilestoneService.class))
                .withValue(milestone.getEnddate())
                .withHasPermission(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));
        return builder.build();
    }

    @Override
    public PopupView createBillableHoursPopupField(final SimpleMilestone milestone) {
        PopupView popupView = new PopupView(new PopupView.Content() {
            @Override
            public String getMinimizedValueAsHTML() {
                return FontAwesome.MONEY.getHtml() + " " + (milestone.getTotalBugBillableHours() + milestone.getTotalTaskBillableHours());
            }

            @Override
            public Component getPopupComponent() {
                MVerticalLayout layout = new MVerticalLayout();
                layout.with(new ELabel(ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG).getHtml() + ": " +
                        milestone.getTotalBugBillableHours(), ContentMode.HTML));
                layout.with(new ELabel(ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK).getHtml() + ": " +
                        milestone.getTotalTaskBillableHours(), ContentMode.HTML));
                return layout;
            }
        });
        popupView.setStyleName("block-popupedit");
        return popupView;
    }

    @Override
    public PopupView createNonBillableHoursPopupField(final SimpleMilestone milestone) {
        PopupView popupView = new PopupView(new PopupView.Content() {
            @Override
            public String getMinimizedValueAsHTML() {
                return FontAwesome.GIFT.getHtml() + " " + (milestone.getTotalBugNonBillableHours() + milestone
                        .getTotalTaskNonBillableHours());
            }

            @Override
            public Component getPopupComponent() {
                MVerticalLayout layout = new MVerticalLayout();
                layout.with(new ELabel(ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG).getHtml() + ": " +
                        milestone.getTotalBugNonBillableHours(), ContentMode.HTML));
                layout.with(new ELabel(ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK).getHtml() + ": " +
                        milestone.getTotalTaskNonBillableHours(), ContentMode.HTML));
                return layout;
            }
        });
        popupView.setStyleName("block-popupedit");
        return popupView;
    }
}
