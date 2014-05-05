package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.module.project.ui.components.ProjectMemberHistoryFieldFormat;
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

		this.generateFieldDisplayHandler("issuename", "Name");
		this.generateFieldDisplayHandler("description", "Description");
		this.generateFieldDisplayHandler("raisedbyuser", "Raised by",
				new ProjectMemberHistoryFieldFormat());
		this.generateFieldDisplayHandler("assigntouser", AppContext
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
				new ProjectMemberHistoryFieldFormat());
		this.generateFieldDisplayHandler("impact", "Impact");

		this.generateFieldDisplayHandler("datedue", "Date due",
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("priority", "Priority");
		this.generateFieldDisplayHandler("status", "Status");
		this.generateFieldDisplayHandler("level", "Rating");
		this.generateFieldDisplayHandler("resolution", "Resolution");
	}

}
