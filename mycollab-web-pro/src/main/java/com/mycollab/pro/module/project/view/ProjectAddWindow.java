package com.mycollab.pro.module.project.view;

import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.view.AbstractProjectAddWindow;
import com.mycollab.module.project.view.ProjectAddBaseTemplateWindow;
import com.mycollab.module.project.view.ProjectGeneralInfoStep;
import com.mycollab.module.project.view.parameters.ProjectScreenData;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.PageActionChain;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.event.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectAddWindow extends AbstractProjectAddWindow implements WizardProgressListener {
    private static final long serialVersionUID = 1L;

    private ProjectAddWizard wizard;
    private ProjectGeneralInfoStep infoStep;
    private ProjectBillingAccountStep billingAccountStep;
    private ProjectCustomizeFeatureStep customizeFeatureStep;

    public ProjectAddWindow() {
        this(new Project());
    }

    public ProjectAddWindow(Project valuePrj) {
        super(valuePrj);
        MVerticalLayout contentLayout = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false, false, true, false));
        setContent(contentLayout);

        wizard = new ProjectAddWizard();
        infoStep = new ProjectGeneralInfoStep(project);
        billingAccountStep = new ProjectBillingAccountStep(project);
        customizeFeatureStep = new ProjectCustomizeFeatureStep(project);
        wizard.addStep(infoStep);
        wizard.addStep(customizeFeatureStep);
        wizard.addStep(billingAccountStep);
        wizard.getFinishButton().setEnabled(true);
        wizard.addListener(this);
        contentLayout.with(wizard).withAlign(wizard, Alignment.TOP_CENTER);
    }

    @Override
    public void activeStepChanged(WizardStepActivationEvent wizardStepActivationEvent) {
        wizard.getFinishButton().setEnabled(true);
        if (wizard.isActive(infoStep)) {
            billingAccountStep.commit();
        } else {
            infoStep.commit();
        }
    }

    @Override
    public void stepSetChanged(WizardStepSetChangedEvent wizardStepSetChangedEvent) {
        wizard.getFinishButton().setEnabled(true);
    }


    @Override
    public void wizardCompleted(WizardCompletedEvent wizardCompletedEvent) {
        boolean isInfoValid = infoStep.commit();

        boolean isBillingValid = true;
        if (wizard.isActive(billingAccountStep)) {
            isBillingValid = billingAccountStep.commit();
        }

        if (!isInfoValid || !isBillingValid) {
            return;
        }

        Validator validator = AppContextUtil.getValidator();
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if (CollectionUtils.isNotEmpty(violations)) {
            StringBuilder errorMsg = new StringBuilder();

            for (ConstraintViolation violation : violations) {
                errorMsg.append(violation.getMessage()).append("<br/>");
            }
            NotificationUtil.showErrorNotification(errorMsg.toString());
        } else {
            project.setSaccountid(AppUI.getAccountId());
            ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
            projectService.saveWithSession(project, UserUIContext.getUsername());
            customizeFeatureStep.saveProjectFeatures();

            EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this,
                    new PageActionChain(new ProjectScreenData.Goto(project.getId()))));
            if (project.getMemlead() != null && !UserUIContext.getUsername().equals(project.getMemlead())) {
                ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
                projectMemberService.inviteProjectMembers(new String[]{project.getMemlead()}, CurrentProjectVariables.getProjectId(),
                        -1, UserUIContext.getUsername(), UserUIContext.getMessage(ProjectMemberI18nEnum
                                .MSG_DEFAULT_INVITATION_COMMENT), AppUI.getAccountId());
            }
            close();
        }
    }

    @Override
    public void wizardCancelled(WizardCancelledEvent wizardCancelledEvent) {
        this.close();
    }

    private class ProjectAddWizard extends Wizard {
        ProjectAddWizard() {
            this.getCancelButton().setStyleName(WebThemes.BUTTON_OPTION);
            this.getBackButton().setStyleName(WebThemes.BUTTON_OPTION);
            this.getNextButton().setStyleName(WebThemes.BUTTON_ACTION);
            this.getFinishButton().setStyleName(WebThemes.BUTTON_ACTION);
            footer.setMargin(new MarginInfo(true, true, false, false));

            if (!SiteConfiguration.isCommunityEdition()) {
                MButton newProjectFromTemplateBtn = new MButton(UserUIContext.getMessage(ProjectI18nEnum.OPT_CREATE_PROJECT_FROM_TEMPLATE), clickEvent -> {
                            close();
                            UI.getCurrent().addWindow(new ProjectAddBaseTemplateWindow());
                        }).withStyleName(WebThemes.BUTTON_ACTION);
                footer.addComponent(newProjectFromTemplateBtn, 0);
            }
        }

        @Override
        public void finish() {
            if (this.currentStep.onAdvance()) {
                this.fireEvent(new WizardCompletedEvent(this));
            }
        }
    }
}
