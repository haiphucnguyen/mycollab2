package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.esofthead.mycollab.module.project.ui.components.DynaFormLayout;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class RiskAddViewImpl extends AbstractEditItemComp<Risk> implements
		RiskAddView {

	private static final long serialVersionUID = 1L;

	private static Map<Integer, String> valueCaptions = new HashMap<>(
			5);

	static {
		RiskAddViewImpl.getValueCaptions().put(1, "Epic Fail");
		RiskAddViewImpl.getValueCaptions().put(2, "Poor");
		RiskAddViewImpl.getValueCaptions().put(3, "OK");
		RiskAddViewImpl.getValueCaptions().put(4, "Good");
		RiskAddViewImpl.getValueCaptions().put(5, "Excellent");
	}

	public static Map<Integer, String> getValueCaptions() {
		return valueCaptions;
	}

	@Override
	protected String initFormHeader() {
		return (beanItem.getId() == null) ? AppContext
				.getMessage(RiskI18nEnum.VIEW_NEW_TITLE) : AppContext
				.getMessage(RiskI18nEnum.VIEW_EDIT_TITLE);
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
		return (new EditFormControlsGenerator<>(editForm)).createButtonControls();
	}

	@Override
	protected AdvancedEditBeanForm<Risk> initPreviewForm() {
		return new AdvancedEditBeanForm<>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(ProjectTypeConstants.RISK,
				RiskDefaultFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Risk> initBeanFormFieldFactory() {
		return new RiskEditFormFieldFactory(editForm);
	}
}
