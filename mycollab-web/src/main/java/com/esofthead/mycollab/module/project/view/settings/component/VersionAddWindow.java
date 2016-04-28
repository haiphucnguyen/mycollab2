package com.esofthead.mycollab.module.project.view.settings.component;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.VersionI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.VersionDefaultFormLayoutFactory;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.VersionService;
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
public class VersionAddWindow extends Window implements IEditFormHandler<Version> {
    public VersionAddWindow() {
        super(AppContext.getMessage(VersionI18nEnum.VIEW_NEW_TITLE));
        this.setWidth("750px");
        this.setResizable(false);
        this.setModal(true);
        AdvancedEditBeanForm<Version> editForm = new AdvancedEditBeanForm<>();
        editForm.addFormHandler(this);
        editForm.setFormLayoutFactory(new DynaFormLayout(ProjectTypeConstants.BUG_VERSION,
                VersionDefaultFormLayoutFactory.getForm(), "id"));
        editForm.setBeanFormFieldFactory(new VersionEditFormFieldFactory(editForm));
        Version version = new Version();
        version.setProjectid(CurrentProjectVariables.getProjectId());
        version.setSaccountid(AppContext.getAccountId());
        version.setStatus(OptionI18nEnum.StatusI18nEnum.Open.name());
        editForm.setBean(version);
        ComponentContainer buttonControls = new EditFormControlsGenerator<>(editForm).createButtonControls(true, false, true);
        this.setContent(new MVerticalLayout(editForm, buttonControls).withAlign(buttonControls, Alignment.TOP_RIGHT));
        this.center();
    }

    @Override
    public void onSave(Version bean) {
        VersionService versionService = ApplicationContextUtil.getSpringBean(VersionService.class);
        versionService.saveWithSession(bean, AppContext.getUsername());
        close();
    }

    @Override
    public void onSaveAndNew(Version bean) {

    }

    @Override
    public void onCancel() {
        close();
    }
}
