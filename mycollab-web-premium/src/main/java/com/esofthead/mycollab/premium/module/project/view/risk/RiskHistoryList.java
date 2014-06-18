package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.ui.format.ProjectMemberHistoryFieldFormat;
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

	public RiskHistoryList(String module, String type) {
		super(module, type);

		this.generateFieldDisplayHandler("riskname",
				AppContext.getMessage(RiskI18nEnum.FORM_NAME));
		this.generateFieldDisplayHandler("description",
				AppContext.getMessage(RiskI18nEnum.FORM_DESCRIPTION));
		this.generateFieldDisplayHandler("raisedbyuser",
				AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY),
				new ProjectMemberHistoryFieldFormat());
		this.generateFieldDisplayHandler("assigntouser",
				AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE),
				new ProjectMemberHistoryFieldFormat());
		this.generateFieldDisplayHandler("consequence",
				AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE));

		this.generateFieldDisplayHandler("datedue",
				AppContext.getMessage(RiskI18nEnum.FORM_DATE_DUE),
				HistoryLogComponent.DATE_FIELD);
		this.generateFieldDisplayHandler("probalitity",
				AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY));
		this.generateFieldDisplayHandler("status",
				AppContext.getMessage(RiskI18nEnum.FORM_STATUS));
		this.generateFieldDisplayHandler("level",
				AppContext.getMessage(RiskI18nEnum.FORM_RATING));
		this.generateFieldDisplayHandler("response",
				AppContext.getMessage(RiskI18nEnum.FORM_RESPONSE));
	}

}
