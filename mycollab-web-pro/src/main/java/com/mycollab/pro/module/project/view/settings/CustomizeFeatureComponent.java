package com.mycollab.pro.module.project.view.settings;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectCustomizeView;
import com.mycollab.module.project.events.CustomizeUIEvent;
import com.mycollab.module.project.service.ProjectCustomizeViewService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.pro.module.project.ui.components.FeatureSelectionBox;
import com.mycollab.module.project.i18n.*;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.web.ui.BlockWidget;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class CustomizeFeatureComponent extends BlockWidget {
    private static final long serialVersionUID = 1L;

    public CustomizeFeatureComponent() {
        super(AppContext.getMessage(ProjectSettingI18nEnum.WIDGET_CUSTOMIZE_FEATURES));
        constructBody();
    }

    public void constructBody() {
        final ProjectCustomizeView customizeView = CurrentProjectVariables.getFeatures();

        MVerticalLayout body = new MVerticalLayout().withFullWidth();

        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withFullWidth();

        VerticalLayout leftColLayout = new VerticalLayout();
        leftColLayout.setSpacing(true);
        leftColLayout.setWidth("100%");

        final FeatureSelectionBox displayMsgSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE),
                AppContext.getMessage(MessageI18nEnum.LIST),
                customizeView.getDisplaymessage());
        leftColLayout.addComponent(displayMsgSelection);

        final FeatureSelectionBox displayPhaseSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE),
                AppContext.getMessage(MilestoneI18nEnum.LIST),
                customizeView.getDisplaymilestone());
        leftColLayout.addComponent(displayPhaseSelection);

        final FeatureSelectionBox displayTaskSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK),
                AppContext.getMessage(TaskI18nEnum.LIST),
                customizeView.getDisplaytask());
        leftColLayout.addComponent(displayTaskSelection);

        final FeatureSelectionBox displayBugSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG),
                AppContext.getMessage(BugI18nEnum.LIST),
                customizeView.getDisplaybug());
        leftColLayout.addComponent(displayBugSelection);

        final FeatureSelectionBox displayPageSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PAGE),
                AppContext.getMessage(PageI18nEnum.LIST),
                customizeView.getDisplaypage());
        leftColLayout.addComponent(displayPageSelection);

        layout.addComponent(leftColLayout);

        MVerticalLayout rightColLayout = new MVerticalLayout().withFullWidth().withMargin(false);

        final FeatureSelectionBox displayFileSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.FILE),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
                customizeView.getDisplayfile());
        rightColLayout.addComponent(displayFileSelection);

        final FeatureSelectionBox displayRiskSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK),
                AppContext.getMessage(RiskI18nEnum.LIST),
                customizeView.getDisplayrisk());
        rightColLayout.addComponent(displayRiskSelection);

        final FeatureSelectionBox displayTimeSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                customizeView.getDisplaytimelogging());
        rightColLayout.addComponent(displayTimeSelection);

        final FeatureSelectionBox displayStandupSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.STANDUP),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDUP),
                customizeView.getDisplaystandup());
        rightColLayout.addComponent(displayStandupSelection);

        final FeatureSelectionBox displayInvoiceSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE),
                AppContext.getMessage(InvoiceI18nEnum.LIST),
                customizeView.getDisplayinvoice());
        rightColLayout.addComponent(displayInvoiceSelection);

        layout.addComponent(rightColLayout);
        body.addComponent(layout);

        MButton updateFeaturesBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), clickEvent -> {
            customizeView.setDisplaymessage(displayMsgSelection.getSelected());
            customizeView.setDisplaymilestone(displayPhaseSelection.getSelected());
            customizeView.setDisplaytask(displayTaskSelection.getSelected());
            customizeView.setDisplaybug(displayBugSelection.getSelected());
            customizeView.setDisplaypage(displayPageSelection.getSelected());
            customizeView.setDisplayfile(displayFileSelection.getSelected());
            customizeView.setDisplayrisk(displayRiskSelection.getSelected());
            customizeView.setDisplaytimelogging(displayTimeSelection.getSelected());
            customizeView.setDisplaystandup(displayStandupSelection.getSelected());
            customizeView.setDisplayinvoice(displayInvoiceSelection.getSelected());

            ProjectCustomizeViewService projectCustomizeService = AppContextUtil.getSpringBean(ProjectCustomizeViewService.class);
            if (customizeView.getId() == null) {
                projectCustomizeService.saveWithSession(customizeView, AppContext.getUsername());
            } else {
                projectCustomizeService.updateWithSession(customizeView, AppContext.getUsername());
            }

            CurrentProjectVariables.getProject().setCustomizeView(customizeView);
            EventBusFactory.getInstance().post(new CustomizeUIEvent.UpdateFeaturesList(CustomizeFeatureComponent.this));
        }).withStyleName(WebUIConstants.BUTTON_ACTION).withIcon(FontAwesome.REFRESH).withVisible(CurrentProjectVariables.isAdmin());

        body.addComponent(updateFeaturesBtn);
        this.addToBody(body);
    }

}
