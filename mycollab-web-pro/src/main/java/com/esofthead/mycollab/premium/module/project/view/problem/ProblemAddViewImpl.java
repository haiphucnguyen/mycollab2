package com.esofthead.mycollab.premium.module.project.view.problem;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.esofthead.mycollab.module.project.ui.components.DynaFormLayout;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Layout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class ProblemAddViewImpl extends AbstractEditItemComp<Problem> implements
		ProblemAddView, IFormAddView<Problem> {

	private static final long serialVersionUID = 1L;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);

	static {
		ProblemAddViewImpl.getValueCaptions().put(1, "Epic Fail");
		ProblemAddViewImpl.getValueCaptions().put(2, "Poor");
		ProblemAddViewImpl.getValueCaptions().put(3, "OK");
		ProblemAddViewImpl.getValueCaptions().put(4, "Good");
		ProblemAddViewImpl.getValueCaptions().put(5, "Excellent");
	}

	public static Map<Integer, String> getValueCaptions() {
		return valueCaptions;
	}

	@Override
	protected String initFormHeader() {
		return (beanItem.getId() == null) ? AppContext
				.getMessage(ProblemI18nEnum.VIEW_NEW_TITLE) : AppContext
				.getMessage(ProblemI18nEnum.VIEW_EDIT_TITLE);
	}

	@Override
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? null : beanItem.getIssuename();
	}

	@Override
	protected Resource initFormIconResource() {
		return MyCollabResource
				.newResource("icons/22/project/problem_selected.png");
	}

	@Override
	protected ComponentContainer createButtonControls() {
		final Layout controlButtons = (new EditFormControlsGenerator<Problem>(
				editForm)).createButtonControls();
		controlButtons.setSizeUndefined();
		return controlButtons;
	}

	@Override
	protected AdvancedEditBeanForm<Problem> initPreviewForm() {
		return new AdvancedEditBeanForm<Problem>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(ProjectTypeConstants.PROBLEM,
				ProblemDefaultFormLayoutFactory.getForm());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Problem> initBeanFormFieldFactory() {
		return new ProblemEditFormFieldFactory(editForm);
	}
}
