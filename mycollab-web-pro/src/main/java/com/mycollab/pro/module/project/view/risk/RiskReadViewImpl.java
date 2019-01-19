package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.*;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.risk.IRiskReadView;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.AbstractPreviewItemComp;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.UserLink;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addons.stackpanel.StackPanel;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class RiskReadViewImpl extends AbstractPreviewItemComp<SimpleRisk> implements IRiskReadView {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(RiskReadViewImpl.class);

    private TagViewComponent tagViewComponent;
    private ProjectActivityComponent activityComponent;
    private RiskTimeLogSheet timeLogComp;
    private DateInfoComp dateInfoComp;
    private PeopleInfoComp peopleInfoComp;
    private ProjectFollowersComp<SimpleRisk> followerSheet;

    public RiskReadViewImpl() {
        super(UserUIContext.getMessage(RiskI18nEnum.DETAIL), ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleRisk> initPreviewForm() {
        return new RiskPreviewForm();
    }

    @Override
    protected HorizontalLayout createButtonControls() {
        return new ProjectPreviewFormControlsGenerator<>(previewForm).createButtonControls(ProjectRolePermissionCollections.RISKS);
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getName();
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.RISK, CurrentProjectVariables.getProjectId());
        dateInfoComp = new DateInfoComp();
        peopleInfoComp = new PeopleInfoComp();
        followerSheet = new ProjectFollowersComp<>(ProjectTypeConstants.RISK, ProjectRolePermissionCollections.RISKS);

        ProjectView projectView = UIUtils.getRoot(this, ProjectView.class);
        MVerticalLayout detailLayout = new MVerticalLayout().withMargin(new MarginInfo(false, true, false, true));

        if (SiteConfiguration.isCommunityEdition()) {
            detailLayout.with(dateInfoComp, peopleInfoComp, followerSheet);
        } else {
            timeLogComp = ViewManager.getCacheComponent(RiskTimeLogSheet.class);
            detailLayout.with(dateInfoComp, peopleInfoComp, timeLogComp, followerSheet);
        }
        Panel detailPanel = new Panel(UserUIContext.getMessage(GenericI18Enum.OPT_DETAILS), detailLayout);
        StackPanel.extend(detailPanel);
        projectView.addComponentToRightBar(detailPanel);
    }

    @Override
    protected void onPreviewItem() {
        if (StatusI18nEnum.Closed.name().equals(beanItem.getStatus())) {
            addLayoutStyleName(WebThemes.LINK_COMPLETED);
        }

        if (beanItem.isOverdue()) {
            previewLayout.addTitleStyleName(WebThemes.LABEL_OVERDUE);
        }

        if (!SiteConfiguration.isCommunityEdition()) {
            tagViewComponent.display(ProjectTypeConstants.RISK, beanItem.getId());
        }

        if (timeLogComp != null) {
            timeLogComp.displayTime(beanItem);
        }
        activityComponent.loadActivities("" + beanItem.getId());
        dateInfoComp.displayEntryDateTime(beanItem);
        peopleInfoComp.displayEntryPeople(beanItem);
        followerSheet.displayFollowers(beanItem);
    }

    @Override
    protected ComponentContainer createExtraControls() {
        if (SiteConfiguration.isCommunityEdition()) {
            return null;
        } else {
            tagViewComponent = new TagViewComponent(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
            return tagViewComponent;
        }
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return activityComponent;
    }

    @Override
    protected String getType() {
        return ProjectTypeConstants.RISK;
    }


    @Override
    public SimpleRisk getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers() {
        return previewForm;
    }

    private static class PeopleInfoComp extends MVerticalLayout {
        private static final long serialVersionUID = 1L;

        void displayEntryPeople(ValuedBean bean) {
            this.removeAllComponents();
            this.withMargin(false);

            Label peopleInfoHeader = ELabel.html(VaadinIcons.USER.getHtml() + " " +
                    UserUIContext.getMessage(ProjectCommonI18nEnum.SUB_INFO_PEOPLE)).withStyleName("info-hdr");
            this.addComponent(peopleInfoHeader);

            GridLayout layout = new GridLayout(2, 2);
            layout.setSpacing(true);
            layout.setWidth("100%");
            layout.setMargin(new MarginInfo(false, false, false, true));
            try {
                Label createdLbl = new Label(UserUIContext.getMessage(ProjectCommonI18nEnum.ITEM_CREATED_PEOPLE));
                createdLbl.setSizeUndefined();
                layout.addComponent(createdLbl, 0, 0);

                String createdUserName = (String) PropertyUtils.getProperty(bean, "createduser");
                String createdUserAvatarId = (String) PropertyUtils.getProperty(bean, "raisedByUserAvatarId");
                String createdUserDisplayName = (String) PropertyUtils.getProperty(bean, "raisedByUserFullName");

                UserLink createdUserLink = new UserLink(createdUserName, createdUserAvatarId, createdUserDisplayName);
                layout.addComponent(createdUserLink, 1, 0);
                layout.setColumnExpandRatio(1, 1.0f);

                Label assigneeLbl = new Label(UserUIContext.getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
                assigneeLbl.setSizeUndefined();
                layout.addComponent(assigneeLbl, 0, 1);
                String assignUserName = (String) PropertyUtils.getProperty(bean, "assignuser");
                String assignUserAvatarId = (String) PropertyUtils.getProperty(bean, "assignToUserAvatarId");
                String assignUserDisplayName = (String) PropertyUtils.getProperty(bean, "assignedToUserFullName");

                UserLink assignUserLink = new UserLink(assignUserName, assignUserAvatarId, assignUserDisplayName);
                layout.addComponent(assignUserLink, 1, 1);
            } catch (Exception e) {
                LOG.error("Can not build user link {} ", e);
            }

            this.addComponent(layout);
        }
    }
}
