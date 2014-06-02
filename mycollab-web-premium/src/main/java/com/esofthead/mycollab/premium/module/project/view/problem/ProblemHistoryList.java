package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.module.project.localization.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.ui.format.ProjectMemberHistoryFieldFormat;
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

		this.generateFieldDisplayHandler("issuename",
				AppContext.getMessage(ProblemI18nEnum.FORM_NAME));
		this.generateFieldDisplayHandler("description",
				AppContext.getMessage(ProblemI18nEnum.FORM_DESCRIPTION));
		this.generateFieldDisplayHandler("raisedbyuser",
				AppContext.getMessage(ProblemI18nEnum.FORM_RAISED_BY),
				new ProjectMemberHistoryFieldFormat());
		this.generateFieldDisplayHandler("assigntouser",
				AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
				new ProjectMemberHistoryFieldFormat());
		this.generateFieldDisplayHandler("impact",
				AppContext.getMessage(ProblemI18nEnum.FORM_IMPACT));

		this.generateFieldDisplayHandler("datedue",
				AppContext.getMessage(ProblemI18nEnum.FORM_DATE_DUE),
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("priority",
				AppContext.getMessage(ProblemI18nEnum.FORM_PRIORITY));
		this.generateFieldDisplayHandler("status",
				AppContext.getMessage(ProblemI18nEnum.FORM_STATUS));
		this.generateFieldDisplayHandler("level",
				AppContext.getMessage(ProblemI18nEnum.FORM_RATING));
		this.generateFieldDisplayHandler("resolution",
				AppContext.getMessage(ProblemI18nEnum.FORM_RESOLUTION));
	}

}
