package com.mycollab.pro.module.project.view;

import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.domain.ProjectCustomizeView;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.service.ProjectCustomizeViewService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.AbstractProjectAddWindow;
import com.mycollab.pro.module.project.ui.components.FeatureSelectionBox;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class ProjectCustomizeFeatureStep implements AbstractProjectAddWindow.FormWizardStep {
    private Project project;
    private FeatureSelectionBox displayMsgSelection, displayPhaseSelection,
            displayTicketSelection, displayPageSelection, displayFileSelection,
            displayTimeSelection,
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
        return UserUIContext.getMessage(ProjectI18nEnum.OPT_FEATURES);
    }

    @Override
    public Component getContent() {
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(true).withFullWidth();

        VerticalLayout leftColLayout = new VerticalLayout();
        leftColLayout.setSpacing(true);
        leftColLayout.setWidth("100%");

        displayMsgSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE),
                UserUIContext.getMessage(MessageI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayMsgSelection);

        displayPhaseSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE),
                UserUIContext.getMessage(MilestoneI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayPhaseSelection);

        displayTicketSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TICKET),
                UserUIContext.getMessage(TicketI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayTicketSelection);

        displayPageSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PAGE),
                UserUIContext.getMessage(PageI18nEnum.LIST),
                true);
        leftColLayout.addComponent(displayPageSelection);

        layout.addComponent(leftColLayout);

        VerticalLayout rightColLayout = new VerticalLayout();
        rightColLayout.setWidth("100%");
        rightColLayout.setSpacing(true);

        displayFileSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.FILE),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
                true);
        rightColLayout.addComponent(displayFileSelection);

        displayTimeSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                true);
        rightColLayout.addComponent(displayTimeSelection);

        displayStandupSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.STANDUP),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDUP),
                true);
        rightColLayout.addComponent(displayStandupSelection);

        displayInvoiceSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE),
                UserUIContext.getMessage(InvoiceI18nEnum.LIST),
                true);
        rightColLayout.addComponent(displayInvoiceSelection);

        layout.addComponent(rightColLayout);
        return layout;
    }

    void saveProjectFeatures() {
        ProjectCustomizeView features = new ProjectCustomizeView();
        features.setProjectid(project.getId());
        features.setDisplayinvoice(getFeatureSelected(displayInvoiceSelection));
        features.setDisplayticket(getFeatureSelected(displayTicketSelection));
        features.setDisplayfile(getFeatureSelected(displayFileSelection));
        features.setDisplaymessage(getFeatureSelected(displayMsgSelection));
        features.setDisplaymilestone(getFeatureSelected(displayPhaseSelection));
        features.setDisplaypage(getFeatureSelected(displayPageSelection));
        features.setDisplaystandup(getFeatureSelected(displayStandupSelection));
        features.setDisplaytimelogging(getFeatureSelected(displayTimeSelection));
        ProjectCustomizeViewService projectCustomizeViewService = AppContextUtil.getSpringBean(ProjectCustomizeViewService.class);
        projectCustomizeViewService.saveWithSession(features, UserUIContext.getUsername());
    }

    private Boolean getFeatureSelected(FeatureSelectionBox comp) {
        return (comp != null) ? comp.getSelected() : Boolean.TRUE;
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
