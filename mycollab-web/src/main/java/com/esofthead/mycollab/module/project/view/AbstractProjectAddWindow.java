package com.esofthead.mycollab.module.project.view;

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
    public interface FormWizardStep extends WizardStep {
        boolean commit();
    }
}
