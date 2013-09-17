package com.esofthead.mycollab.reporting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.view.CrmLinkGenerator;
import com.esofthead.mycollab.web.AppContext;

@SuppressWarnings("unchecked")
public class SimpleColumnInjectionMap {
	private static Map<Class, List<? extends ColumnInjectionRenderer>> mapInjection = new HashMap<Class, List<? extends ColumnInjectionRenderer>>();

	static {
		mapInjection.put(SimpleAccount.class, Arrays.asList(
				new HyperLinkColumnInjectionRenderer("accountname",
						new AccountLinkExpression()),
				new EmailColumnInjectionRenderer("email")));

		mapInjection.put(SimpleContact.class, Arrays.asList(
				new HyperLinkColumnInjectionRenderer("contactName",
						new ContactLinkExpression()),
				new HyperLinkColumnInjectionRenderer("accountName",
						new AccountLinkExpression()),
				new EmailColumnInjectionRenderer("email")));

		mapInjection.put(SimpleCampaign.class, Arrays.asList(
				new HyperLinkColumnInjectionRenderer("campaignname",
						new CampaignLinkExpression()),
				new HyperLinkColumnInjectionRenderer("assignuser",
						new AccountLinkExpression())));

		mapInjection.put(SimpleLead.class, Arrays.asList(
				new HyperLinkColumnInjectionRenderer("leadName",
						new LeadLinkExpression()),
				new HyperLinkColumnInjectionRenderer("assignuser",
						new AccountLinkExpression())));

		mapInjection.put(SimpleOpportunity.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("opportunityname",
						new OpportunityLinkExpression())));

		mapInjection.put(SimpleCase.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("subject",
						new CaseLinkExpression())));
	}

	public static List<? extends ColumnInjectionRenderer> getRenderers(Class cls) {
		return mapInjection.get(cls);
	}

	private static class AccountLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer id = reportParameters.getFieldValue("id");
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ CrmLinkGenerator.generateAccountPreviewLink(id);
		}

	}

	private static class ContactLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer contactId = reportParameters.getFieldValue("id");
			return AppContext.getSiteUrl() + "/"
					+ CrmLinkGenerator.generateContactPreviewLink(contactId);
		}

	}

	private static class CampaignLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer campaignId = reportParameters.getFieldValue("id");
			return AppContext.getSiteUrl() + "/"
					+ CrmLinkGenerator.generateCampaignPreviewLink(campaignId);
		}

	}

	private static class LeadLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer leadId = reportParameters.getFieldValue("id");
			return AppContext.getSiteUrl() + "/"
					+ CrmLinkGenerator.generateLeadPreviewLink(leadId);
		}

	}

	private static class OpportunityLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer opportunityId = reportParameters.getFieldValue("id");
			return AppContext.getSiteUrl()
					+ "/"
					+ CrmLinkGenerator
							.generateOpportunityPreviewLink(opportunityId);
		}

	}

	private static class CaseLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer caseId = reportParameters.getFieldValue("id");
			return AppContext.getSiteUrl() + "/"
					+ CrmLinkGenerator.generateCasePreviewLink(caseId);
		}

	}

}
