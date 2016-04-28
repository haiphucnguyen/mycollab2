package com.esofthead.mycollab.module.project.view.settings.component;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.ComponentI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.ComponentDefaultFormLayoutFactory;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.IEditFormHandler;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.EditFormControlsGenerator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class ComponentAddWindow extends Window implements IEditFormHandler<Component> {
    public ComponentAddWindow() {
        super(AppContext.getMessage(ComponentI18nEnum.FORM_NEW_TITLE));
        this.setWidth("750px");
        this.setResizable(false);
        this.setModal(true);
        AdvancedEditBeanForm<Component> editForm = new AdvancedEditBeanForm<>();
        editForm.addFormHandler(this);
        editForm.setFormLayoutFactory(new DynaFormLayout(ProjectTypeConstants.BUG_COMPONENT,
                ComponentDefaultFormLayoutFactory.getForm(), "id"));
        editForm.setBeanFormFieldFactory(new ComponentEditFormFieldFactory(editForm));
        Component component = new Component();
        component.setProjectid(CurrentProjectVariables.getProjectId());
        component.setSaccountid(AppContext.getAccountId());
        component.setStatus(OptionI18nEnum.StatusI18nEnum.Open.name());
        editForm.setBean(component);
        ComponentContainer buttonControls = new EditFormControlsGenerator<>(editForm).createButtonControls(true, false, true);
        this.setContent(new MVerticalLayout(editForm, buttonControls).withAlign(buttonControls, Alignment.TOP_RIGHT));
        this.center();
    }

    @Override
    public void onSave(Component bean) {
        ComponentService componentService = ApplicationContextUtil.getSpringBean(ComponentService.class);
        componentService.saveWithSession(bean, AppContext.getUsername());
        close();
    }

    @Override
    public void onSaveAndNew(Component bean) {

    }

    @Override
    public void onCancel() {
        close();
    }
}
