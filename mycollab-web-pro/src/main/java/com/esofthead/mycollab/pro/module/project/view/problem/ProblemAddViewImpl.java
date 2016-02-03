package com.esofthead.mycollab.pro.module.project.view.problem;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.EditFormControlsGenerator;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Layout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProblemAddViewImpl extends AbstractEditItemComp<Problem> implements ProblemAddView, IFormAddView<Problem> {
    private static final long serialVersionUID = 1L;

    private static Map<Integer, String> valueCaptions = new HashMap<>(5);

    static {
        valueCaptions.put(1, "Epic Fail");
        valueCaptions.put(2, "Poor");
        valueCaptions.put(3, "OK");
        valueCaptions.put(4, "Good");
        valueCaptions.put(5, "Excellent");
    }

    public static Map<Integer, String> getValueCaptions() {
        return valueCaptions;
    }

    @Override
    protected String initFormHeader() {
        return (beanItem.getId() == null) ? AppContext.getMessage(ProblemI18nEnum.VIEW_NEW_TITLE) : AppContext
                .getMessage(ProblemI18nEnum.VIEW_EDIT_TITLE);
    }

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? null : beanItem.getIssuename();
    }

    @Override
    protected Resource initFormIconResource() {
        return ProjectAssetsManager.getAsset(ProjectTypeConstants.PROBLEM);
    }

    @Override
    protected ComponentContainer createButtonControls() {
        final Layout controlButtons = (new EditFormControlsGenerator<>(editForm)).createButtonControls();
        controlButtons.setSizeUndefined();
        return controlButtons;
    }

    @Override
    protected AdvancedEditBeanForm<Problem> initPreviewForm() {
        return new AdvancedEditBeanForm<>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.PROBLEM, ProblemDefaultFormLayoutFactory.getForm());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<Problem> initBeanFormFieldFactory() {
        return new ProblemEditFormFieldFactory(editForm);
    }
}
