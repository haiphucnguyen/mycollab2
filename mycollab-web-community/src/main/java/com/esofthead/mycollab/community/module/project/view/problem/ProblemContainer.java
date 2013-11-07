package com.esofthead.mycollab.community.module.project.view.problem;

import com.esofthead.mycollab.module.project.view.problem.IProblemContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.vaadin.ui.Label;

public class ProblemContainer extends AbstractView implements IProblemContainer {
	private static final long serialVersionUID = 1L;

	public ProblemContainer() {
		this.addComponent(new Label(
				"Module is not presented for community edition"));
	}
}
