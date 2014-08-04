package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.ui.format.ProjectMemberHistoryFieldFormat;
import com.esofthead.mycollab.utils.FieldGroupFomatter;
import com.esofthead.mycollab.utils.FieldGroupFomatter.I18nHistoryFieldFormat;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class RiskHistoryList extends HistoryLogComponent {
	private static final long serialVersionUID = 1L;

	public static final FieldGroupFomatter riskFormatter;

	static {
		riskFormatter = new FieldGroupFomatter();

		riskFormatter.generateFieldDisplayHandler("riskname",
				AppContext.getMessage(RiskI18nEnum.FORM_NAME));
		riskFormatter.generateFieldDisplayHandler("description",
				AppContext.getMessage(RiskI18nEnum.FORM_DESCRIPTION));
		riskFormatter.generateFieldDisplayHandler("raisedbyuser",
				AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY),
				new ProjectMemberHistoryFieldFormat());
		riskFormatter.generateFieldDisplayHandler("assigntouser",
				AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE),
				new ProjectMemberHistoryFieldFormat());
		riskFormatter.generateFieldDisplayHandler("consequence",
				AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE));

		riskFormatter.generateFieldDisplayHandler("datedue",
				AppContext.getMessage(RiskI18nEnum.FORM_DATE_DUE),
				FieldGroupFomatter.DATE_FIELD);
		riskFormatter.generateFieldDisplayHandler("probalitity",
				AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY));
		riskFormatter.generateFieldDisplayHandler("status",
				AppContext.getMessage(RiskI18nEnum.FORM_STATUS),
				new I18nHistoryFieldFormat(StatusI18nEnum.class));
		riskFormatter.generateFieldDisplayHandler("level",
				AppContext.getMessage(RiskI18nEnum.FORM_RATING));
		riskFormatter.generateFieldDisplayHandler("response",
				AppContext.getMessage(RiskI18nEnum.FORM_RESPONSE));
	}

	public RiskHistoryList(String module, String type) {
		super(module, type);

	}

	@Override
	protected FieldGroupFomatter buildFormatter() {
		return riskFormatter;
	}

}
