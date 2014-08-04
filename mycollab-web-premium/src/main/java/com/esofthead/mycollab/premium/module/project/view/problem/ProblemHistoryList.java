package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.module.project.view.problem.ProblemFormatter;
import com.esofthead.mycollab.utils.FieldGroupFomatter;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProblemHistoryList extends HistoryLogComponent {
	private static final long serialVersionUID = 1L;

	public ProblemHistoryList(String module, String type) {
		super(module, type);
	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return ProblemFormatter.instance;
	}

}
