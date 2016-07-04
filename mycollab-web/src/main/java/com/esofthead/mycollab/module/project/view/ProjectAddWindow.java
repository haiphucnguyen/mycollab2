/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.*;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectAddWindow extends Window implements WizardProgressListener {
    private static final long serialVersionUID = 1L;

    private Project project;
    private ProjectAddWizard wizard;
    private ProjectGeneralInfoStep infoStep;
    private ProjectBillingAccountStep billingAccountStep;
    private CustomizeFeatureStep customizeFeatureStep;

    public ProjectAddWindow() {
        this(new Project());
    }

    public ProjectAddWindow(Project valuePrj) {
        setCaption(AppContext.getMessage(ProjectI18nEnum.NEW));
        this.setWidth("900px");
        this.center();
        this.setResizable(false);
        this.setModal(true);

        MVerticalLayout contentLayout = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false, false, true, false));
        setContent(contentLayout);

        project = valuePrj;

        wizard = new ProjectAddWizard();
        infoStep = new ProjectGeneralInfoStep(project);
        billingAccountStep = new ProjectBillingAccountStep(project);
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
            project.setSaccountid(AppContext.getAccountId());
            ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
            projectService.saveWithSession(project, AppContext.getUsername());

            EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this,
                    new PageActionChain(new ProjectScreenData.Goto(project.getId()))));
            if (project.getLead() != null && !AppContext.getUsername().equals(project.getLead())) {
                ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
                projectMemberService.inviteProjectMembers(new String[]{project.getLead()}, CurrentProjectVariables.getProjectId(),
                        -1, AppContext.getUsername(), AppContext.getMessage(ProjectMemberI18nEnum
                                .MSG_DEFAULT_INVITATION_COMMENT), AppContext.getAccountId());
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
            this.getCancelButton().setStyleName(UIConstants.BUTTON_OPTION);
            this.getBackButton().setStyleName(UIConstants.BUTTON_OPTION);
            this.getNextButton().setStyleName(UIConstants.BUTTON_ACTION);
            this.getFinishButton().setStyleName(UIConstants.BUTTON_ACTION);
            this.footer.setMargin(new MarginInfo(true, true, false, false));

            Button newProjectFromTemplateBtn = new Button(AppContext.getMessage(ProjectI18nEnum.OPT_CREATE_PROJECT_FROM_TEMPLATE), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    close();
                    UI.getCurrent().addWindow(new ProjectAddBaseTemplateWindow());
                }
            });
            newProjectFromTemplateBtn.addStyleName(UIConstants.BUTTON_ACTION);
            footer.addComponent(newProjectFromTemplateBtn, 0);
        }

        @Override
        public void finish() {
            if (this.currentStep.onAdvance()) {
                this.fireEvent(new WizardCompletedEvent(this));
            }
        }
    }

    interface FormWizardStep extends WizardStep {
        boolean commit();
    }

    private class CustomizeFeatureStep implements FormWizardStep {
        @Override
        public boolean commit() {
            return false;
        }

        @Override
        public String getCaption() {
            return null;
        }

        @Override
        public Component getContent() {
            return null;
        }

        @Override
        public boolean onAdvance() {
            return false;
        }

        @Override
        public boolean onBack() {
            return false;
        }
    }
}
