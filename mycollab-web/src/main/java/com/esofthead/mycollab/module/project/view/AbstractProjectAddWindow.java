package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.vaadin.ui.Window;
import org.vaadin.teemu.wizards.WizardStep;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public abstract class AbstractProjectAddWindow extends Window implements CacheableComponent {
    protected Project project;

    public AbstractProjectAddWindow(Project valuePrj) {
        setCaption(AppContext.getMessage(ProjectI18nEnum.NEW));
        this.setWidth("900px");
        this.center();
        this.setResizable(false);
        this.setModal(true);
        this.project = valuePrj;
    }

    public interface FormWizardStep extends WizardStep {
        boolean commit();
    }
}
