package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.module.project.view.AbstractProjectAddWindow;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class ProjectCustomizeFeatureStep implements AbstractProjectAddWindow.FormWizardStep {
    private Project project;

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
