package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
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
import com.esofthead.mycollab.module.project.view.bug.BugPriorityStatusConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;

@SuppressWarnings("unchecked")
public class SimpleColumnComponentBuilderMap {
	private static Map<Class, List<? extends ColumnFieldComponentBuilder>> mapInjection = new HashMap<Class, List<? extends ColumnFieldComponentBuilder>>();

	public static class TypeRender {
		public static final String HYPERLINK = "hyperLink";
		public static final String EMAILTYPE = "emailType";
		public static final String BUG_CUSTOM_TYPE = "bug_custom";
		public static final String RATING = "rating";
	}

	static {
		mapInjection.put(SimpleAccount.class, Arrays.asList(
				new CrmFieldComponentBuilder("accountname",
						CrmTypeConstants.ACCOUNT, Arrays
								.asList(TypeRender.HYPERLINK)),
				new CrmFieldComponentBuilder("email", CrmTypeConstants.ACCOUNT,
						Arrays.asList(TypeRender.EMAILTYPE))));

		mapInjection.put(SimpleContact.class, Arrays.asList(
				new CrmFieldComponentBuilder("contactName",
						CrmTypeConstants.CONTACT, Arrays
								.asList(TypeRender.HYPERLINK)),
				new CrmFieldComponentBuilder("email", CrmTypeConstants.CONTACT,
						Arrays.asList(TypeRender.EMAILTYPE))));

		mapInjection.put(SimpleCampaign.class, Arrays
				.asList(new CrmFieldComponentBuilder("campaignname",
						CrmTypeConstants.CAMPAIGN, Arrays
								.asList(TypeRender.HYPERLINK))));

		mapInjection.put(SimpleLead.class, Arrays
				.asList(new CrmFieldComponentBuilder("leadName",
						CrmTypeConstants.LEAD, Arrays
								.asList(TypeRender.HYPERLINK))));

		mapInjection.put(SimpleOpportunity.class, Arrays
				.asList(new CrmFieldComponentBuilder("opportunityname",
						CrmTypeConstants.OPPORTUNITY, Arrays
								.asList(TypeRender.HYPERLINK))));

		mapInjection.put(SimpleCase.class, Arrays
				.asList(new CrmFieldComponentBuilder("subject",
						CrmTypeConstants.CASE, Arrays
								.asList(TypeRender.HYPERLINK))));

		mapInjection.put(SimpleBug.class, Arrays
				.asList(new BugCustomLinkExpression("summary", Arrays
						.asList(TypeRender.HYPERLINK))));

		mapInjection.put(SimpleRisk.class, Arrays.asList(
				new CrmFieldComponentBuilder("name", "RISK", Arrays
						.asList(TypeRender.HYPERLINK)),
				new RatingComponentBuilder("level", "RISK", Arrays
						.asList(TypeRender.RATING))));

		// mapInjection.put(SimpleProblem.class)
	}

	public static List<? extends ColumnFieldComponentBuilder> getListFieldBuilder(
			Class cls) {
		return mapInjection.get(cls);
	}

	/**
	 * ------------------------------------------------------------------------
	 * --------- CRM Field Factory ----------------------------
	 * ------------------------------------------
	 * ----------------------------------------
	 */
	public static class CrmFieldComponentBuilder implements
			ColumnFieldComponentBuilder {

		private String field;
		private String classType;
		private List<String> lstTypeRender;

		public CrmFieldComponentBuilder(String field, String classType,
				List<String> lstTypeRender) {
			this.field = field;
			this.classType = classType;
			this.lstTypeRender = lstTypeRender;
		}

		private class CrmFieldComponetBuilderExpression extends
				AbstractSimpleExpression<String> {
			private static final long serialVersionUID = 1L;

			@Override
			public String evaluate(ReportParameters reportParameters) {
				Integer id = reportParameters.getFieldValue("id");
				Integer sAccountId = reportParameters
						.getFieldValue("saccountid");
				String fieldName = reportParameters.getFieldValue(field);
				String hyperLinkStr = fieldName;

				for (String typeRender : lstTypeRender) {
					if (typeRender.equals(TypeRender.HYPERLINK)) {
						hyperLinkStr = SiteConfiguration.getSiteUrl(sAccountId)
								+ CrmLinkGenerator.generateCrmItemLink(
										classType, id);
					} else if (typeRender.equals(TypeRender.EMAILTYPE)) {
						hyperLinkStr = "mailto:" + fieldName;
					}
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
			return new CrmFieldComponetBuilderExpression();
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

	/**
	 * ------------------------------------------------------------------------
	 * --------- Project Field Factory ----------------------------
	 * ------------------------------------------
	 * ----------------------------------------
	 */

	private static class BugCustomLinkExpression implements
			ColumnFieldComponentBuilder {
		private static final long serialVersionUID = 1L;

		private String field;
		private List<String> lstTypeRender;

		public BugCustomLinkExpression(String field, List<String> lstTypeRender) {
			this.field = field;
			this.lstTypeRender = lstTypeRender;
		}

		@Override
		public String getFieldName() {
			return field;
		}

		@Override
		public DRIExpression getDriExpression() {
			return null;
		}

		@Override
		public ComponentBuilder getComponentBuilder() {
			HorizontalListBuilder lstBuilder = cmp.horizontalList();
			lstBuilder.add(cmp.image(new ImageBugExpression())
					.setFixedDimension(12, 12));

			ConditionalStyleBuilder overDueStyle = stl.conditionalStyle(
					new BugOverDueExpression()).setForegroundColor(Color.RED);
			ConditionalStyleBuilder isCompleteStyle = stl.conditionalStyle(
					new BugIsCompleteExpression()).setStrikeThrough(true);

			StyleBuilder bugStyleBuilder = stl.style()
					.addConditionalStyle(overDueStyle)
					.addConditionalStyle(isCompleteStyle);

			lstBuilder.add(cmp.text(new StringFieldExpression(field))
					.setHyperLink(hyperLink(new BugHyperLinkExpression()))
					.setStyle(bugStyleBuilder));
			return lstBuilder;
		}

		private class BugOverDueExpression extends
				AbstractSimpleExpression<Boolean> {
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean evaluate(ReportParameters param) {
				String status = param.getFieldValue("status");
				Date duedate = param.getFieldValue("duedate");
				Boolean isOverDue;
				if (BugStatusConstants.RESOLVED.equals(status)
						|| BugStatusConstants.VERIFIED.equals(status)) {
					isOverDue = false;
				}

				if (duedate != null) {
					Calendar today = Calendar.getInstance();
					today.set(Calendar.HOUR_OF_DAY, 0);
					Date todayDate = today.getTime();

					isOverDue = todayDate.after(duedate);
				} else {
					isOverDue = false;
				}
				return isOverDue;
			}
		}

		private class BugIsCompleteExpression extends
				AbstractSimpleExpression<Boolean> {
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean evaluate(ReportParameters param) {
				String status = param.getFieldValue("status");
				return BugStatusConstants.VERIFIED.equals(status);
			}
		}

		private static class BugHyperLinkExpression extends
				AbstractSimpleExpression<String> {
			private static final long serialVersionUID = 1L;

			@Override
			public String evaluate(ReportParameters reportParameters) {
				Integer bugId = reportParameters.getFieldValue("id");
				Integer projectId = reportParameters.getFieldValue("projectid");
				MailLinkGenerator linkGenerator = new MailLinkGenerator(
						projectId);
				return linkGenerator.generateBugPreviewFullLink(bugId);
			}
		}

		private static class ImageBugExpression extends
				AbstractSimpleExpression<InputStream> {
			private static final long serialVersionUID = 1L;

			@Override
			public InputStream evaluate(ReportParameters reportParameters) {
				String priority = reportParameters.getFieldValue("priority");

				if (priority.equals(BugPriorityStatusConstants.PRIORITY_MAJOR)) {
					return Templates.class.getClassLoader()
							.getResourceAsStream("images/priority_medium.png");
				} else if (priority
						.equals(BugPriorityStatusConstants.PRIORITY_MINOR)) {
					return Templates.class.getClassLoader()
							.getResourceAsStream("images/priority_low.png");
				} else if (priority
						.equals(BugPriorityStatusConstants.PRIORITY_CRITICAL)) {
					return Templates.class.getClassLoader()
							.getResourceAsStream("images/priority_high.png");
				} else
					return null;
			}
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

			return SiteConfiguration.getSiteUrl(sAccountId)
					+ linkGenerator.generateRiskPreviewFullLink(riskId);
		}

	}

	public static class RatingComponentBuilder implements
			ColumnFieldComponentBuilder {
		private static final long serialVersionUID = 1L;
		private String field;
		private String classType;
		private List<String> lstTypeRender;

		public RatingComponentBuilder(String field, String classType,
				List<String> lstTypeRender) {
			this.field = field;
			this.classType = classType;
			this.lstTypeRender = lstTypeRender;
		}

		private class RatingComponentBuilderExpression extends
				AbstractSimpleExpression<String> {

			@Override
			public String evaluate(ReportParameters param) {
				Double level = param.getFieldValue(field);
				// TODO : must comlish rating
				return null;
			}
		}

		@Override
		public String getFieldName() {
			return field;
		}

		@Override
		public DRIExpression getDriExpression() {
			return new RatingComponentBuilderExpression();
		}

		@Override
		public ComponentBuilder getComponentBuilder() {
			HorizontalListBuilder componentBuilder = cmp.horizontalList();
			ImageBuilder imgBuilder = cmp.image(this.getDriExpression())
					.setFixedDimension(24, 24);
			;
			componentBuilder.add(imgBuilder);
			return componentBuilder;
		}

	}

	/**
	 * ------------------------------------------------------------------------
	 * --------- Common Field Factory ----------------------------
	 * ------------------------------------------
	 * ----------------------------------------
	 */
	public static class StringFieldExpression extends
			AbstractSimpleExpression<String> {
		private static final long serialVersionUID = 1L;

		private String field;

		public StringFieldExpression(String field) {
			this.field = field;
		}

		@Override
		public String evaluate(ReportParameters reportParameters) {
			return reportParameters.getValue(field).toString();
		}
	}

}
