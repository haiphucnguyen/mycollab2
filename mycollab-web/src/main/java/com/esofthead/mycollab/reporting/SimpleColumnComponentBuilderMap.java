package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import org.jfree.util.Log;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.view.CrmLinkGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
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
		public static final String BUG_CUSTOM_TYPE = "bug_custom_type";
		public static final String RATING = "rating";
		public static final String RISK_NAME_TYPE = "risk_name_type";
		public static final String PROBLEM_NAME_TYPE = "problem_name_type";
	}

	public static class ProjectMoulde {
		public static final String BUG = "bug";
		public static final String TASKLIST = "taskList";
		public static final String RISK = "risk";
		public static final String PROBLEM = "problem";
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
				.asList(new ProjectFieldBuilderFactory("summary",
						ProjectMoulde.BUG, TypeRender.HYPERLINK)));

		mapInjection.put(SimpleRisk.class, Arrays.asList(
				new ProjectFieldBuilderFactory("riskname", ProjectMoulde.RISK,
						TypeRender.HYPERLINK), new RatingComponentBuilder(
						"level", ProjectMoulde.RISK)));

		mapInjection.put(SimpleProblem.class, Arrays.asList(
				new ProjectFieldBuilderFactory("issuename",
						ProjectMoulde.PROBLEM, TypeRender.HYPERLINK),
				new RatingComponentBuilder("level", ProjectMoulde.PROBLEM)));
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

	private static class ProjectFieldBuilderFactory implements
			ColumnFieldComponentBuilder {
		private static final long serialVersionUID = 1L;

		private String field;
		private String typeRender;
		private String projectModule;

		public ProjectFieldBuilderFactory(String field, String projectModule,
				String typeRender) {
			this.field = field;
			this.typeRender = typeRender;
			this.projectModule = projectModule;
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
			if (projectModule.equals(ProjectMoulde.BUG)) {
				if (typeRender.equals(TypeRender.HYPERLINK)) {
					Log.debug("HyperLink for name field ------" + field);
					lstBuilder.add(cmp.image(new ImageBugExpression())
							.setFixedDimension(12, 12));

					ConditionalStyleBuilder overDueStyle = stl
							.conditionalStyle(
									new OverDueExpression(projectModule))
							.setForegroundColor(Color.RED);
					ConditionalStyleBuilder isCompleteStyle = stl
							.conditionalStyle(
									new IsCompleteExpression(projectModule))
							.setStrikeThrough(true);

					StyleBuilder bugStyleBuilder = stl.style()
							.addConditionalStyle(overDueStyle)
							.addConditionalStyle(isCompleteStyle);

					lstBuilder.add(cmp
							.text(new StringFieldExpression(field))
							.setHyperLink(
									hyperLink(new ProjectHyperLinkExpression(
											projectModule)))
							.setStyle(bugStyleBuilder));
				}
			} else {
				Log.debug("Start make field builder component-------RISK, PROBLEM");
				ConditionalStyleBuilder overDueStyle = stl.conditionalStyle(
						new OverDueExpression(projectModule))
						.setForegroundColor(Color.RED);
				ConditionalStyleBuilder isCompleteStyle = stl.conditionalStyle(
						new IsCompleteExpression(projectModule))
						.setStrikeThrough(true);

				StyleBuilder styleBuilder = stl.style()
						.addConditionalStyle(overDueStyle)
						.addConditionalStyle(isCompleteStyle);

				lstBuilder
						.add(cmp.text(new StringFieldExpression(field))
								.setHyperLink(
										hyperLink(new ProjectHyperLinkExpression(
												projectModule)))
								.setStyle(styleBuilder));

			}
			return lstBuilder;
		}

		private class OverDueExpression extends
				AbstractSimpleExpression<Boolean> {
			private static final long serialVersionUID = 1L;
			private String projectModule;

			public OverDueExpression(String projectModule) {
				this.projectModule = projectModule;
			}

			@Override
			public Boolean evaluate(ReportParameters param) {
				if (projectModule.equals(ProjectMoulde.BUG)) {
					String status = param.getFieldValue("status");
					Date duedate = param.getFieldValue("duedate");
					if (BugStatusConstants.RESOLVED.equals(status)
							|| BugStatusConstants.VERIFIED.equals(status)) {
						return false;
					}
					if (duedate != null) {
						Calendar today = Calendar.getInstance();
						today.set(Calendar.HOUR_OF_DAY, 0);
						Date todayDate = today.getTime();

						return todayDate.after(duedate);
					} else {
						return false;
					}
				} else {
					Date datedue = param.getFieldValue("datedue");
					if (datedue != null
							&& (datedue.before(new GregorianCalendar()
									.getTime()))) {
						return true;
					} else
						return false;
				}
			}

		}

		private class IsCompleteExpression extends
				AbstractSimpleExpression<Boolean> {
			private static final long serialVersionUID = 1L;

			private String projectModule;

			public IsCompleteExpression(String projectModule) {
				this.projectModule = projectModule;
			}

			@Override
			public Boolean evaluate(ReportParameters param) {
				String status = param.getFieldValue("status");
				if (projectModule.equals(ProjectMoulde.BUG)) {
					return BugStatusConstants.VERIFIED.equals(status);
				} else {
					return "Closed".equals(status);
				}
			}
		}

		private static class ProjectHyperLinkExpression extends
				AbstractSimpleExpression<String> {
			private static final long serialVersionUID = 1L;

			private String projectModule;

			public ProjectHyperLinkExpression(String projectModule) {
				this.projectModule = projectModule;
			}

			@Override
			public String evaluate(ReportParameters reportParameters) {
				Integer id = reportParameters.getFieldValue("id");
				Integer projectId = reportParameters.getFieldValue("projectid");
				MailLinkGenerator linkGenerator = new MailLinkGenerator(
						projectId);

				if (projectModule.equals(ProjectMoulde.BUG)) {
					return linkGenerator.generateBugPreviewFullLink(id);
				} else if (projectModule.equals(ProjectMoulde.RISK)) {
					return linkGenerator.generateRiskPreviewFullLink(id);
				} else if (projectModule.equals(ProjectMoulde.PROBLEM)) {
					return linkGenerator.generateProblemPreviewFullLink(id);
				}
				return "";
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

	public static class RatingComponentBuilder implements
			ColumnFieldComponentBuilder {
		private static final long serialVersionUID = 1L;
		private String field;
		private String classType;

		public RatingComponentBuilder(String field, String classType) {
			this.field = field;
			this.classType = classType;
		}

		private class RatingComponentBuilderExpression extends
				AbstractSimpleExpression<String> {

			@Override
			public String evaluate(ReportParameters param) {
				Double level = param.getFieldValue(field);
				// TODO : must comlish rating
				return "images/severity_major.png";
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
					.setFixedDimension(12, 12);
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
