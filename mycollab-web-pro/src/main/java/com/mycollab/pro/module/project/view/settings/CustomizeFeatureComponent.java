package com.mycollab.pro.module.project.view.settings;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectCustomizeView;
import com.mycollab.module.project.event.CustomizeUIEvent;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.service.ProjectCustomizeViewService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.pro.module.project.ui.components.FeatureSelectionBox;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.BlockWidget;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
class CustomizeFeatureComponent extends BlockWidget {
    private static final long serialVersionUID = 1L;

    CustomizeFeatureComponent() {
        super(UserUIContext.getMessage(ProjectSettingI18nEnum.WIDGET_CUSTOMIZE_FEATURES));
        constructBody();
    }

    private void constructBody() {
        ProjectCustomizeView customizeView = CurrentProjectVariables.getFeatures();

        MVerticalLayout body = new MVerticalLayout().withFullWidth().withMargin(false);

        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withFullWidth();

        MVerticalLayout leftColLayout = new MVerticalLayout().withFullWidth().withMargin(false);

        FeatureSelectionBox displayMsgSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE),
                UserUIContext.getMessage(MessageI18nEnum.LIST),
                customizeView.getDisplaymessage());
        leftColLayout.addComponent(displayMsgSelection);

        FeatureSelectionBox displayPhaseSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE),
                UserUIContext.getMessage(MilestoneI18nEnum.LIST),
                customizeView.getDisplaymilestone());
        leftColLayout.addComponent(displayPhaseSelection);

        FeatureSelectionBox displayTicketSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TICKET),
                UserUIContext.getMessage(TicketI18nEnum.LIST),
                customizeView.getDisplayticket());
        leftColLayout.addComponent(displayTicketSelection);

        FeatureSelectionBox displayPageSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PAGE),
                UserUIContext.getMessage(PageI18nEnum.LIST),
                customizeView.getDisplaypage());
        leftColLayout.addComponent(displayPageSelection);

        layout.addComponent(leftColLayout);

        MVerticalLayout rightColLayout = new MVerticalLayout().withFullWidth().withMargin(false);

        FeatureSelectionBox displayTimeSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                customizeView.getDisplaytimelogging());
        rightColLayout.addComponent(displayTimeSelection);

        FeatureSelectionBox displayFileSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.FILE),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
                customizeView.getDisplayfile());
        rightColLayout.addComponent(displayFileSelection);

        FeatureSelectionBox displayInvoiceSelection = new FeatureSelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE),
                UserUIContext.getMessage(InvoiceI18nEnum.LIST),
                customizeView.getDisplayinvoice());
        rightColLayout.addComponent(displayInvoiceSelection);

        layout.addComponent(rightColLayout);
        body.addComponent(layout);

        MButton updateFeaturesBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            customizeView.setDisplaymessage(displayMsgSelection.getSelected());
            customizeView.setDisplaymilestone(displayPhaseSelection.getSelected());
            customizeView.setDisplayticket(displayTicketSelection.getSelected());
            customizeView.setDisplaypage(displayPageSelection.getSelected());
            customizeView.setDisplaytimelogging(displayTimeSelection.getSelected());
            customizeView.setDisplayfile(displayFileSelection.getSelected());
            customizeView.setDisplayinvoice(displayInvoiceSelection.getSelected());

            ProjectCustomizeViewService projectCustomizeService = AppContextUtil.getSpringBean(ProjectCustomizeViewService.class);
            if (customizeView.getId() == null) {
                projectCustomizeService.saveWithSession(customizeView, UserUIContext.getUsername());
            } else {
                projectCustomizeService.updateWithSession(customizeView, UserUIContext.getUsername());
            }

            CurrentProjectVariables.getProject().setCustomizeView(customizeView);
            EventBusFactory.getInstance().post(new CustomizeUIEvent.UpdateFeaturesList(CustomizeFeatureComponent.this));
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.CLIPBOARD)
                .withVisible(CurrentProjectVariables.isSuperUser() && !CurrentProjectVariables.isProjectArchived());

        body.addComponent(updateFeaturesBtn);
        this.addToBody(body);
    }

}
