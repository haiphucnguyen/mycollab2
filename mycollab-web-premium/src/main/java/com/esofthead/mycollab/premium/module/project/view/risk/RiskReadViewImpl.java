package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class RiskReadViewImpl extends AbstractPageView implements RiskReadView {

	private static final long serialVersionUID = 1L;

	private RiskReadComp riskReadComp;

	public RiskReadViewImpl() {
		super();
		riskReadComp = new RiskReadComp();
		this.addComponent(riskReadComp);
	}

	@Override
	public SimpleRisk getItem() {
		return riskReadComp.getBeanItem();
	}

	@Override
	public void previewItem(SimpleRisk item) {
		riskReadComp.previewItem(item);
	}

	@Override
	public HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers() {
		return riskReadComp.getPreviewForm();
	}
}
