package com.mycollab.module.project.view.settings.component;

import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.ComponentI18nEnum;
import com.mycollab.module.project.view.settings.ComponentDefaultFormLayoutFactory;
import com.mycollab.module.tracker.domain.Component;
import com.mycollab.module.tracker.service.ComponentService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import static com.mycollab.vaadin.web.ui.utils.FormControlsGenerator.generateEditFormControls;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
class ComponentAddWindow extends MWindow implements IEditFormHandler<Component> {
    ComponentAddWindow() {
        super(UserUIContext.getMessage(ComponentI18nEnum.NEW));
        AdvancedEditBeanForm<Component> editForm = new AdvancedEditBeanForm<>();
        editForm.addFormHandler(this);
        editForm.setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.INSTANCE.getBUG_COMPONENT(),
                ComponentDefaultFormLayoutFactory.getForm(), "id"));
        editForm.setBeanFormFieldFactory(new ComponentEditFormFieldFactory(editForm));
        Component component = new Component();
        component.setProjectid(CurrentProjectVariables.getProjectId());
        component.setSaccountid(AppUI.getAccountId());
        component.setStatus(OptionI18nEnum.StatusI18nEnum.Open.name());
        editForm.setBean(component);
        ComponentContainer buttonControls = generateEditFormControls(editForm, true, false, true);
        this.setContent(new MVerticalLayout(editForm, buttonControls).withAlign(buttonControls, Alignment.TOP_RIGHT));
        this.withWidth("750px").withModal(true).withResizable(false).withCenter();
    }

    @Override
    public void onSave(Component bean) {
        ComponentService componentService = AppContextUtil.getSpringBean(ComponentService.class);
        componentService.saveWithSession(bean, UserUIContext.getUsername());
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
