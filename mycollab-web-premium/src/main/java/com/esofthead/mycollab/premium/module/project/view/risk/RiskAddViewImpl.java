package com.esofthead.mycollab.premium.module.project.view.risk;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
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
 * 
 */
@ViewComponent
public class RiskAddViewImpl extends AbstractEditItemComp<Risk> implements
		RiskAddView {

	private static final long serialVersionUID = 1L;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
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
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? "Create Risk" : beanItem
				.getRiskname();
	}

	@Override
	protected Resource initFormIconResource() {
		return MyCollabResource.newResource("icons/22/project/menu_risk.png");
	}

	@Override
	protected ComponentContainer createButtonControls() {
		final HorizontalLayout controlPanel = new HorizontalLayout();
		controlPanel.setMargin(true);
		controlPanel.addStyleName("control-buttons");
		final Layout controlButtons = (new EditFormControlsGenerator<Risk>(
				editForm)).createButtonControls();
		controlButtons.setSizeUndefined();
		controlPanel.addComponent(controlButtons);
		controlPanel.setWidth("100%");
		controlPanel.setComponentAlignment(controlButtons,
				Alignment.MIDDLE_CENTER);
		return controlPanel;
	}

	@Override
	protected AdvancedEditBeanForm<Risk> initPreviewForm() {
		return new AdvancedEditBeanForm<Risk>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new RiskFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Risk> initBeanFormFieldFactory() {
		return new RiskEditFormFieldFactory(editForm);
	}
}
