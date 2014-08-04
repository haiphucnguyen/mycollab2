package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.ui.format.ProjectMemberHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFomatter;
import com.esofthead.mycollab.utils.FieldGroupFomatter.I18nHistoryFieldFormat;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProblemHistoryList extends HistoryLogComponent {
	private static final long serialVersionUID = 1L;

	public static final FieldGroupFomatter problemFormatter;

	static {
		problemFormatter = new FieldGroupFomatter();

		problemFormatter.generateFieldDisplayHandler("issuename",
				ProblemI18nEnum.FORM_NAME);
		problemFormatter.generateFieldDisplayHandler("description",
				GenericI18Enum.FORM_DESCRIPTION);
		problemFormatter.generateFieldDisplayHandler("raisedbyuser",
				ProblemI18nEnum.FORM_RAISED_BY,
				new ProjectMemberHistoryFieldFormat());
		problemFormatter.generateFieldDisplayHandler("assigntouser",
				GenericI18Enum.FORM_ASSIGNEE,
				new ProjectMemberHistoryFieldFormat());
		problemFormatter.generateFieldDisplayHandler("impact",
				ProblemI18nEnum.FORM_IMPACT);

		problemFormatter.generateFieldDisplayHandler("datedue",
				ProblemI18nEnum.FORM_DATE_DUE, FieldGroupFomatter.DATE_FIELD);
		problemFormatter.generateFieldDisplayHandler("priority",
				ProblemI18nEnum.FORM_PRIORITY);
		problemFormatter.generateFieldDisplayHandler("status",
				ProblemI18nEnum.FORM_STATUS, new I18nHistoryFieldFormat(
						StatusI18nEnum.class));
		problemFormatter.generateFieldDisplayHandler("level",
				ProblemI18nEnum.FORM_RATING);
		problemFormatter.generateFieldDisplayHandler("resolution",
				ProblemI18nEnum.FORM_RESOLUTION);
	}

	public ProblemHistoryList(String module, String type) {
		super(module, type);
	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return problemFormatter;
	}

}
