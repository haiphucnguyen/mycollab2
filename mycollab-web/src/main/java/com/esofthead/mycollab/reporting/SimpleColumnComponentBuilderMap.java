package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
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
public class SimpleColumnComponentBuilderMap {
	private static Map<Class, List<? extends ColumnFieldComponentBuilder>> mapInjection = new HashMap<Class, List<? extends ColumnFieldComponentBuilder>>();

	public static class TypeRender {
		public static final String HYPERLINK = "hyperLink";
		public static final String EMAILTYPE = "emailType";
		public static final String BUG_CUSTOM_TYPE = "bug_custom";
	}

	static {
		mapInjection.put(SimpleAccount.class, Arrays.asList(
				new FieldComponentBuilder("accountname",
						CrmTypeConstants.ACCOUNT, TypeRender.HYPERLINK),
				new FieldComponentBuilder("email", CrmTypeConstants.ACCOUNT,
						TypeRender.EMAILTYPE)));

		mapInjection.put(SimpleContact.class, Arrays.asList(
				new FieldComponentBuilder("contactName",
						CrmTypeConstants.CONTACT, TypeRender.HYPERLINK),
				new FieldComponentBuilder("email", CrmTypeConstants.CONTACT,
						TypeRender.EMAILTYPE)));

		mapInjection.put(SimpleCampaign.class, Arrays
				.asList(new FieldComponentBuilder("campaignname",
						CrmTypeConstants.CAMPAIGN, TypeRender.HYPERLINK)));

		mapInjection.put(SimpleLead.class, Arrays
				.asList(new FieldComponentBuilder("leadName",
						CrmTypeConstants.LEAD, TypeRender.HYPERLINK)));

		mapInjection.put(SimpleOpportunity.class, Arrays
				.asList(new FieldComponentBuilder("opportunityname",
						CrmTypeConstants.OPPORTUNITY, TypeRender.HYPERLINK)));

		mapInjection.put(SimpleCase.class, Arrays
				.asList(new FieldComponentBuilder("subject",
						CrmTypeConstants.CASE, TypeRender.HYPERLINK)));

		mapInjection.put(SimpleBug.class,
				Arrays.asList(new BugCustomLinkExpression()));

		mapInjection.put(SimpleRisk.class, Arrays
				.asList(new FieldComponentBuilder("name", "RISK",
						TypeRender.HYPERLINK)));
	}

	public static List<? extends ColumnFieldComponentBuilder> getListFieldBuilder(
			Class cls) {
		return mapInjection.get(cls);
	}

	public static interface HyperLinkFieldComponentBuilder extends
			ColumnFieldComponentBuilder {
	}

	public static class FieldComponentBuilder implements
			HyperLinkFieldComponentBuilder {

		private String field;
		private String classType;
		private String typeRender;

		public FieldComponentBuilder(String field, String classType,
				String typeRender) {
			this.field = field;
			this.classType = classType;
			this.typeRender = typeRender;
		}

		private class FieldComponetBuilderExpression extends
				AbstractSimpleExpression<String> {
			private static final long serialVersionUID = 1L;

			@Override
			public String evaluate(ReportParameters reportParameters) {
				Integer id = reportParameters.getFieldValue("id");
				Integer sAccountId = reportParameters
						.getFieldValue("saccountid");
				String fieldName = reportParameters.getFieldValue(field);
				String hyperLinkStr = "";

				if (typeRender.equals(TypeRender.HYPERLINK)) {
					hyperLinkStr = SiteConfiguration.getSiteUrl(sAccountId)
							+ "#"
							+ CrmLinkGenerator.generateCrmItemLink(classType,
									id);
				} else if (typeRender.equals(TypeRender.EMAILTYPE)) {
					hyperLinkStr = "mailto:" + fieldName;
				} else {
					hyperLinkStr = fieldName;
				}
				return hyperLinkStr;
			}
		}

		@Override
		public String getFieldName() {
			return field;
		}

		@Override
		public DRIExpression getDriExpression() {
			return new FieldComponetBuilderExpression();
		}

		@Override
		public ComponentBuilder getComponentBuilder() {
			HorizontalListBuilder componentBuilder = cmp.horizontalList();

			TextFieldBuilder textBuilder = cmp
					.text(new StringFieldExpression(field))
					.setHyperLink(hyperLink(this.getDriExpression()))
					.setStyle(Templates.underlineStyle);
			componentBuilder.add(textBuilder);
			return componentBuilder;
		}
	}

	public static interface ProjectFieldComponentBuilder extends
			ColumnFieldComponentBuilder {

	}

	private static class BugCustomLinkExpression extends
			AbstractSimpleExpression<ComponentBuilder> implements
			ProjectFieldComponentBuilder {
		private static final long serialVersionUID = 1L;

		@Override
		public ComponentBuilder evaluate(ReportParameters reportParameters) {

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

		@Override
		public String getFieldName() {
			return "summary";
		}

		@Override
		public DRIExpression getDriExpression() {
			return this;
		}

		@Override
		public ComponentBuilder getComponentBuilder() {
			// TODO Auto-generated method stub
			return null;
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

	public static class StringFieldExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		private String field;

		public StringFieldExpression(String field) {
			this.field = field;
		}

		@Override
		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue(field);
		}
	}
}
