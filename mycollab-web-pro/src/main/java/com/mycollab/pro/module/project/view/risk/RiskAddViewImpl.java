package com.mycollab.pro.module.project.view.risk;

import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskRate;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mycollab.vaadin.web.ui.utils.FormControlsGenerator.generateEditFormControls;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class RiskAddViewImpl extends AbstractEditItemComp<SimpleRisk> implements RiskAddView {
    private static final long serialVersionUID = 1L;

    private static Map<Integer, RiskRate> valueCaptions = new HashMap<>(5);

    static {
        valueCaptions.put(1, RiskRate.Epic_Fail);
        valueCaptions.put(2, RiskRate.Poor);
        valueCaptions.put(3, RiskRate.OK);
        valueCaptions.put(4, RiskRate.Good);
        valueCaptions.put(5, RiskRate.Excellent);
    }

    public static String[] getValueCaptions() {
        return valueCaptions.values().stream().map(item -> UserUIContext.getMessage(item)).collect(Collectors.toList())
                .toArray(new String[0]);
    }

    @Override
    protected String initFormHeader() {
        return (beanItem.getId() == null) ? UserUIContext.getMessage(RiskI18nEnum.NEW) :
                UserUIContext.getMessage(RiskI18nEnum.DETAIL);
    }

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? null : beanItem.getRiskname();
    }

    @Override
    protected Resource initFormIconResource() {
        return ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK);
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return generateEditFormControls(editForm);
    }

    @Override
    public AttachmentUploadField getAttachUploadField() {
        return ((RiskEditFormFieldFactory) editForm.getFieldFactory()).getAttachmentUploadField();
    }

    @Override
    protected AdvancedEditBeanForm<SimpleRisk> initPreviewForm() {
        return new AdvancedEditBeanForm<>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DefaultDynaFormLayout(ProjectTypeConstants.RISK, RiskDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleRisk> initBeanFormFieldFactory() {
        return new RiskEditFormFieldFactory(editForm);
    }
}
