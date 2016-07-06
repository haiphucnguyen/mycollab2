package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.*;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.ProjectPreviewFormControlsGenerator;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.UserLink;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class RiskReadViewImpl extends AbstractPreviewItemComp<SimpleRisk> implements RiskReadView {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(RiskReadViewImpl.class);

    private TagViewComponent tagViewComponent;
    private ProjectActivityComponent activityComponent;
    private DateInfoComp dateInfoComp;
    private PeopleInfoComp peopleInfoComp;
    private ProjectFollowersComp<SimpleRisk> followerSheet;

    public RiskReadViewImpl() {
        super(AppContext.getMessage(RiskI18nEnum.DETAIL), ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleRisk> initPreviewForm() {
        return new RiskPreviewForm();
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new ProjectPreviewFormControlsGenerator<>(previewForm).createButtonControls(ProjectRolePermissionCollections.RISKS);
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getRiskname();
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.RISK, CurrentProjectVariables.getProjectId());
        dateInfoComp = new DateInfoComp();
        peopleInfoComp = new PeopleInfoComp();
        followerSheet = new ProjectFollowersComp<>(ProjectTypeConstants.RISK, ProjectRolePermissionCollections.RISKS);
        addToSideBar(dateInfoComp, peopleInfoComp, followerSheet);
    }

    @Override
    protected void onPreviewItem() {
        if (StatusI18nEnum.Closed.name().equals(beanItem.getStatus())) {
            addLayoutStyleName(UIConstants.LINK_COMPLETED);
        }

        if (beanItem.isOverdue()) {
            previewLayout.addTitleStyleName(UIConstants.LABEL_OVERDUE);
        }

        if (!SiteConfiguration.isCommunityEdition()) {
            tagViewComponent.display(ProjectTypeConstants.RISK, beanItem.getId());
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
            tagViewComponent = new TagViewComponent();
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

            Label peopleInfoHeader = new Label(FontAwesome.USER.getHtml() + " " +
                    AppContext.getMessage(ProjectCommonI18nEnum.SUB_INFO_PEOPLE), ContentMode.HTML);
            peopleInfoHeader.setStyleName("info-hdr");
            this.addComponent(peopleInfoHeader);

            GridLayout layout = new GridLayout(2, 2);
            layout.setSpacing(true);
            layout.setWidth("100%");
            layout.setMargin(new MarginInfo(false, false, false, true));
            try {
                Label createdLbl = new Label(AppContext.getMessage(ProjectCommonI18nEnum.ITEM_CREATED_PEOPLE));
                createdLbl.setSizeUndefined();
                layout.addComponent(createdLbl, 0, 0);

                String createdUserName = (String) PropertyUtils.getProperty(bean, "raisedbyuser");
                String createdUserAvatarId = (String) PropertyUtils.getProperty(bean, "raisedByUserAvatarId");
                String createdUserDisplayName = (String) PropertyUtils.getProperty(bean, "raisedByUserFullName");

                UserLink createdUserLink = new UserLink(createdUserName, createdUserAvatarId, createdUserDisplayName);
                layout.addComponent(createdUserLink, 1, 0);
                layout.setColumnExpandRatio(1, 1.0f);

                Label assigneeLbl = new Label(AppContext.getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
                assigneeLbl.setSizeUndefined();
                layout.addComponent(assigneeLbl, 0, 1);
                String assignUserName = (String) PropertyUtils.getProperty(bean, "assigntouser");
                String assignUserAvatarId = (String) PropertyUtils.getProperty(bean, "assignToUserAvatarId");
                String assignUserDisplayName = (String) PropertyUtils.getProperty(bean, "assignedToUserFullName");

                UserLink assignUserLink = new UserLink(assignUserName, assignUserAvatarId, assignUserDisplayName);
                layout.addComponent(assignUserLink, 1, 1);
            } catch (Exception e) {
                LOG.error("Can not build user link {} ", BeanUtility.printBeanObj(bean));
            }

            this.addComponent(layout);
        }
    }
}
