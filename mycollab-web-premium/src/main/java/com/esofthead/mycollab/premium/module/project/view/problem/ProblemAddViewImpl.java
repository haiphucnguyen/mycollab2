package com.esofthead.mycollab.premium.module.project.view.problem;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
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
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? "Create Problem" : beanItem
				.getIssuename();
	}

	@Override
	protected Resource initFormIconResource() {
		return MyCollabResource
				.newResource("icons/22/project/menu_problem.png");
	}

	@Override
	protected ComponentContainer createButtonControls() {
		final HorizontalLayout controlPanel = new HorizontalLayout();
		controlPanel.setMargin(true);
		final Layout controlButtons = (new EditFormControlsGenerator<Problem>(
				editForm)).createButtonControls();
		controlButtons.setSizeUndefined();
		controlPanel.addComponent(controlButtons);
		controlPanel.setWidth("100%");
		controlPanel.setComponentAlignment(controlButtons,
				Alignment.MIDDLE_CENTER);
		return controlPanel;
	}

	@Override
	protected AdvancedEditBeanForm<Problem> initPreviewForm() {
		return new AdvancedEditBeanForm<Problem>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ProblemFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Problem> initBeanFormFieldFactory() {
		return new ProblemEditFormFieldFactory(editForm);
	}
}
