package com.esofthead.mycollab.pro.module.project.view.settings;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.events.CustomizeUIEvent;
import com.esofthead.mycollab.module.project.i18n.*;
import com.esofthead.mycollab.module.project.service.ProjectCustomizeViewService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.web.ui.BlockWidget;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.base.MoreObjects;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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

        MVerticalLayout body = new MVerticalLayout().withWidth("100%");

        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withWidth("100%");

        VerticalLayout leftColLayout = new VerticalLayout();
        leftColLayout.setSpacing(true);
        leftColLayout.setWidth("100%");

        final SelectionBox displayMsgSelection = new SelectionBox(ProjectAssetsManager.getAsset(ProjectTypeConstants.MESSAGE),
                AppContext.getMessage(MessageI18nEnum.LIST),
                customizeView.getDisplaymessage());
        leftColLayout.addComponent(displayMsgSelection);

        final SelectionBox displayPhaseSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE),
                AppContext.getMessage(MilestoneI18nEnum.LIST),
                customizeView.getDisplaymilestone());
        leftColLayout.addComponent(displayPhaseSelection);

        final SelectionBox displayTaskSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK),
                AppContext.getMessage(TaskI18nEnum.LIST),
                customizeView.getDisplaytask());
        leftColLayout.addComponent(displayTaskSelection);

        final SelectionBox displayBugSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG),
                AppContext.getMessage(BugI18nEnum.LIST),
                customizeView.getDisplaybug());
        leftColLayout.addComponent(displayBugSelection);

        final SelectionBox displayPageSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PAGE),
                AppContext.getMessage(PageI18nEnum.LIST),
                customizeView.getDisplaypage());
        leftColLayout.addComponent(displayPageSelection);

        layout.addComponent(leftColLayout);

        VerticalLayout rightColLayout = new VerticalLayout();
        rightColLayout.setWidth("100%");
        rightColLayout.setSpacing(true);

        final SelectionBox displayFileSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.FILE),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_FILE),
                customizeView.getDisplayfile());
        rightColLayout.addComponent(displayFileSelection);

        final SelectionBox displayRiskSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK),
                AppContext.getMessage(RiskI18nEnum.LIST),
                customizeView.getDisplayrisk());
        rightColLayout.addComponent(displayRiskSelection);

        final SelectionBox displayTimeSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME),
                customizeView.getDisplaytimelogging());
        rightColLayout.addComponent(displayTimeSelection);

        final SelectionBox displayStandupSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.STANDUP),
                AppContext.getMessage(ProjectCommonI18nEnum.VIEW_STANDUP),
                customizeView.getDisplaystandup());
        rightColLayout.addComponent(displayStandupSelection);

        final SelectionBox displayInvoiceSelection = new SelectionBox(
                ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE),
                AppContext.getMessage(InvoiceI18nEnum.LIST),
                customizeView.getDisplayinvoice());
        rightColLayout.addComponent(displayInvoiceSelection);

        layout.addComponent(rightColLayout);

        body.addComponent(layout);

        Button updateFeaturesBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
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

                ProjectCustomizeViewService projectCustomizeService = ApplicationContextUtil
                        .getSpringBean(ProjectCustomizeViewService.class);
                if (customizeView.getId() == null) {
                    projectCustomizeService.saveWithSession(customizeView, AppContext.getUsername());
                } else {
                    projectCustomizeService.updateWithSession(customizeView, AppContext.getUsername());
                }

                CurrentProjectVariables.getProject().setCustomizeView(customizeView);
                EventBusFactory.getInstance().post(new CustomizeUIEvent.UpdateFeaturesList(CustomizeFeatureComponent.this));
            }
        });
        updateFeaturesBtn.setEnabled(CurrentProjectVariables.isAdmin());
        updateFeaturesBtn.setStyleName(UIConstants.BUTTON_ACTION);
        updateFeaturesBtn.setIcon(FontAwesome.REFRESH);
        body.addComponent(updateFeaturesBtn);
        this.addToBody(body);
    }

    private static class SelectionBox extends MHorizontalLayout {
        private static final long serialVersionUID = 1L;

        private CheckBox checkbox;

        SelectionBox(FontAwesome iconResource, String caption, Boolean selected) {
            this.withMargin(true).withWidth("100%").withStyleName("feature-select-box");
            Label captionLbl = new Label(iconResource.getHtml() + " " + caption, ContentMode.HTML);
            this.addComponent(captionLbl);
            this.setExpandRatio(captionLbl, 1.0f);
            this.setComponentAlignment(captionLbl, Alignment.MIDDLE_LEFT);
            checkbox = new CheckBox("", MoreObjects.firstNonNull(selected, Boolean.FALSE));
            this.addComponent(checkbox);
            this.setComponentAlignment(checkbox, Alignment.MIDDLE_LEFT);
        }

        public Boolean getSelected() {
            return checkbox.getValue();
        }
    }

}
