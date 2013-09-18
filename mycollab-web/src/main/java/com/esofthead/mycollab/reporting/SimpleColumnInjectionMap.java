package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.view.CrmLinkGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;

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

		mapInjection.put(SimpleCampaign.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("campaignname",
						new CampaignLinkExpression())));

		mapInjection.put(SimpleLead.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("leadName",
						new LeadLinkExpression())));

		mapInjection.put(SimpleOpportunity.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("opportunityname",
						new OpportunityLinkExpression())));

		mapInjection.put(SimpleCase.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("subject",
						new CaseLinkExpression())));

		mapInjection.put(SimpleBug.class, Arrays
				.asList(new BugCustomLinkRenderer("summary",
						new BugCustomLinkExpression())));

		mapInjection.put(SimpleRisk.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("name",
						new RiskLinkExpression())));
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
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ CrmLinkGenerator.generateContactPreviewLink(contactId);
		}

	}

	private static class CampaignLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer campaignId = reportParameters.getFieldValue("id");
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ CrmLinkGenerator.generateCampaignPreviewLink(campaignId);
		}

	}

	private static class LeadLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer leadId = reportParameters.getFieldValue("id");
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ CrmLinkGenerator.generateLeadPreviewLink(leadId);
		}

	}

	private static class OpportunityLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer opportunityId = reportParameters.getFieldValue("id");
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			return SiteConfiguration.getSiteUrl(sAccountId)
					+ "#"
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
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ CrmLinkGenerator.generateCasePreviewLink(caseId);
		}
	}

	private static class BugCustomLinkExpression extends
			AbstractSimpleExpression<HorizontalListBuilder> {
		private static final long serialVersionUID = 1L;

		@Override
		public HorizontalListBuilder evaluate(ReportParameters reportParameters) {

			String bugName = reportParameters.getFieldValue("summary");

			ImageBuilder image = cmp.image(new ImageBugExpression())
					.setFixedDimension(24, 24);
			HorizontalListBuilder itemComponent = cmp.horizontalList(
					image,
					cmp.verticalList(cmp
							.text(bugName)
							.setHyperLink(
									hyperLink(new BugHyperLinkExpression()))
							.setFixedHeight(24)));
			return itemComponent;
		}
	}

	private static class BugHyperLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer bugId = reportParameters.getFieldValue("id");
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			Integer projectId = reportParameters.getFieldValue("projectid");

			MailLinkGenerator linkGenerator = new MailLinkGenerator(projectId);

			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ linkGenerator.generateBugPreviewFullLink(bugId);
		}

	}

	private static class ImageBugExpression extends
			AbstractSimpleExpression<InputStream> {
		private static final long serialVersionUID = 1L;

		@Override
		public InputStream evaluate(ReportParameters reportParameters) {
			return Templates.class.getResourceAsStream("images/"
					+ reportParameters.getValue("image") + ".png");
		}
	}

	private static class RiskLinkExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		@Override
		public String evaluate(ReportParameters reportParameters) {
			Integer riskId = reportParameters.getFieldValue("id");
			Integer sAccountId = reportParameters.getFieldValue("saccountid");
			Integer projectId = reportParameters.getFieldValue("projectid");

			MailLinkGenerator linkGenerator = new MailLinkGenerator(projectId);

			return SiteConfiguration.getSiteUrl(sAccountId) + "#"
					+ linkGenerator.generateRiskPreviewFullLink(riskId);
		}

	}
}
