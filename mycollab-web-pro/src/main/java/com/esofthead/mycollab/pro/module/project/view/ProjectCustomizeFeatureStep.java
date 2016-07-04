package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.i18n.*;
import com.esofthead.mycollab.module.project.service.ProjectCustomizeViewService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.AbstractProjectAddWindow;
import com.esofthead.mycollab.pro.module.project.ui.components.FeatureSelectionBox;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class ProjectCustomizeFeatureStep implements AbstractProjectAddWindow.FormWizardStep {
    private Project project;
    private FeatureSelectionBox displayMsgSelection, displayPhaseSelection, displayTaskSelection,
            displayBugSelection, displayPageSelection, displayFileSelection, displayRiskSelection, displayTimeSelection,
            displayStandupSelection, displayInvoiceSelection;

    ProjectCustomizeFeatureStep(Project project) {
        this.project = project;
    }

    @Override
    public boolean commit() {
        return false;
    }

    @Override
    public String getCaption() {
        return AppContext.getMessage(ProjectI18nEnum.OPT_FEATURES);
    }

    @Override
    public Component getContent() {
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(true).withFullWidth();

        VerticalLayout leftColLayout = new VerticalLayout();
        leftColLayout.setSpacing(true);
        leftColLayout.setWidth("100%");

        displayMsgSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE),
                AppContext.getMessage(MessageI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayMsgSelection);

        displayPhaseSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE),
                AppContext.getMessage(MilestoneI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayPhaseSelection);

        displayTaskSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK),
                AppContext.getMessage(TaskI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayTaskSelection);

        displayBugSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG),
                AppContext.getMessage(BugI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayBugSelection);

        displayPageSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PAGE),
                AppContext.getMessage(PageI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayPageSelection);

        layout.addComponent(leftColLayout);

        VerticalLayout rightColLayout = new VerticalLayout();
        rightColLayout.setWidth("100%");
        rightColLayout.setSpacing(true);

        displayFileSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.FILE),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
                true);
        rightColLayout.addComponent(displayFileSelection);

        displayRiskSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK),
                AppContext.getMessage(RiskI18nEnum.LIST),
                true);
        rightColLayout.addComponent(displayRiskSelection);

        displayTimeSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                true);
        rightColLayout.addComponent(displayTimeSelection);

        displayStandupSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.STANDUP),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDUP),
                true);
        rightColLayout.addComponent(displayStandupSelection);

        displayInvoiceSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE),
                AppContext.getMessage(InvoiceI18nEnum.LIST),
                true);
        rightColLayout.addComponent(displayInvoiceSelection);

        layout.addComponent(rightColLayout);
        return layout;
    }

    void saveProjectFeatures() {
        ProjectCustomizeView features = new ProjectCustomizeView();
        features.setProjectid(project.getId());
        features.setDisplayinvoice(displayInvoiceSelection.getSelected());
        features.setDisplaybug(displayBugSelection.getSelected());
        features.setDisplayfile(displayFileSelection.getSelected());
        features.setDisplaymessage(displayMsgSelection.getSelected());
        features.setDisplaymilestone(displayPhaseSelection.getSelected());
        features.setDisplaypage(displayPageSelection.getSelected());
        features.setDisplaystandup(displayStandupSelection.getSelected());
        features.setDisplaytask(displayTaskSelection.getSelected());
        features.setDisplayrisk(displayRiskSelection.getSelected());
        features.setDisplaytimelogging(displayTimeSelection.getSelected());
        ProjectCustomizeViewService projectCustomizeViewService = AppContextUtil.getSpringBean(ProjectCustomizeViewService.class);
        projectCustomizeViewService.saveWithSession(features, AppContext.getUsername());
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
        return true;
    }
}
