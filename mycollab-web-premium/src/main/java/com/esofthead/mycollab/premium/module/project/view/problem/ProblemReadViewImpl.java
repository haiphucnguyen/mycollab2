package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.SimpleProblem;
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
public class ProblemReadViewImpl extends AbstractPageView implements
		ProblemReadView {

	private static final long serialVersionUID = 1L;
	private ProblemReadComp problemReadComp;

	public ProblemReadViewImpl() {
		super();
		this.setMargin(true);
		problemReadComp = new ProblemReadComp();
		this.addComponent(this.problemReadComp);
	}

	@Override
	public void previewItem(final SimpleProblem item) {
		problemReadComp.previewItem(item);
	}

	@Override
	public SimpleProblem getItem() {
		return problemReadComp.getBeanItem();
	}

	@Override
	public HasPreviewFormHandlers<SimpleProblem> getPreviewFormHandlers() {
		return problemReadComp.getPreviewForm();
	}
}
