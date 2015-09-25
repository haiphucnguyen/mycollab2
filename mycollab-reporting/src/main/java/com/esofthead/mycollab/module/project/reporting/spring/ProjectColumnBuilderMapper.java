/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.reporting.spring;

import com.esofthead.mycollab.module.project.ProjectLinkGenerator;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.*;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.reporting.ColumnBuilderClassMapper;
import com.esofthead.mycollab.reporting.expression.*;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */
@Component
@SuppressWarnings("ucd")
public class ProjectColumnBuilderMapper implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectColumnBuilderMapper.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        ColumnBuilderClassMapper.put(SimpleTask.class, buildTaskMap());
        ColumnBuilderClassMapper.put(SimpleBug.class, buildBugMap());
        ColumnBuilderClassMapper.put(SimpleComponent.class, buildComponentMap());
        ColumnBuilderClassMapper.put(SimpleVersion.class, buildVersionMap());
        ColumnBuilderClassMapper.put(SimpleRisk.class, buildRiskMap());
        ColumnBuilderClassMapper.put(SimpleProblem.class, buildProblemMap());
        ColumnBuilderClassMapper.put(SimpleProjectRole.class, buildRoleMap());
        ColumnBuilderClassMapper.put(SimpleItemTimeLogging.class, buildTimeTrackingMap());
        ColumnBuilderClassMapper.put(FollowingTicket.class, buildTFollowingTicketMap());
    }

    private Map<String, MValue> buildTaskMap() {
        LOG.debug("Build report mapper for project::task module");
        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> taskNameTitleExpr = new StringExpression(Task.Field.taskname.name());
        DRIExpression<String> taskNameHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer taskKey = reportParameters.getFieldValue(Task.Field.taskkey.name());
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                String projectShortName = reportParameters.getFieldValue("projectShortName");
                return ProjectLinkGenerator.generateTaskPreviewFullLink(siteUrl, taskKey, projectShortName);
            }
        };
        map.put(Task.Field.taskkey.name(), new HyperlinkValue(taskNameTitleExpr, taskNameHrefExpr));
        map.put(Task.Field.startdate.name(), new DateExpression(Task.Field.startdate.name()));
        map.put(Task.Field.deadline.name(), new DateExpression(Task.Field.deadline.name()));

        DRIExpression<String> assigneeTitleExpr = new StringExpression(SimpleTask.Field.assignUserFullName.name());
        DRIExpression<String> assigneeHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("assignuser");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };

        map.put(SimpleTask.Field.assignUserFullName.name(), new HyperlinkValue(assigneeTitleExpr, assigneeHrefExpr));
        return map;
    }

    private Map<String, MValue> buildBugMap() {
        LOG.debug("Build report mapper for project::bug module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("summary");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer bugKey = reportParameters.getFieldValue("bugkey");
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                String projectShortName = reportParameters.getFieldValue("projectShortName");
                return ProjectLinkGenerator.generateBugPreviewFullLink(siteUrl, bugKey, projectShortName);
            }
        };
        map.put("summary", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));

        DRIExpression<String> assigneeTitleExpr = new StringExpression("assignuserFullName");
        DRIExpression<String> assigneeHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("assignuser");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };
//        map.put("resolution", new I18nExpression("resolution", OptionI18nEnum.BugResolution.class));
        map.put("assignuserFullName", new HyperlinkValue(assigneeTitleExpr, assigneeHrefExpr));
        map.put("duedate", new DateExpression("duedate"));
        return map;
    }

    private Map<String, MValue> buildComponentMap() {
        LOG.debug("Build report mapper for project::component module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("componentname");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer componentId = reportParameters.getFieldValue("id");
                Integer projectId = reportParameters.getFieldValue("projectId");
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                return ProjectLinkGenerator.generateBugComponentPreviewFullLink(siteUrl, projectId, componentId);
            }
        };
        map.put("componentname", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));

        DRIExpression<String> assigneeTitleExpr = new StringExpression("userLeadFullName");
        DRIExpression<String> assigneeHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("userlead");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };

        map.put("userLeadFullName", new HyperlinkValue(assigneeTitleExpr, assigneeHrefExpr));
        return map;
    }

    private Map<String, MValue> buildVersionMap() {
        LOG.debug("Build report mapper for project::version module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("versionname");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer versionid = reportParameters.getFieldValue("id");
                Integer projectId = reportParameters.getFieldValue("projectId");
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                return ProjectLinkGenerator.generateBugVersionPreviewFullLink(siteUrl, projectId, versionid);
            }
        };
        map.put("versionname", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));
        map.put("duedate", new DateExpression("duedate"));
        return map;
    }

    private Map<String, MValue> buildRiskMap() {
        LOG.debug("Build report mapper for project::risk module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("riskname");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer riskId = reportParameters.getFieldValue("id");
                Integer projectId = reportParameters.getFieldValue("projectId");
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                return ProjectLinkGenerator.generateRiskPreviewFullLink(siteUrl, projectId, riskId);
            }
        };
        map.put("riskname", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));

        DRIExpression<String> assigneeTitleExpr = new StringExpression("assignedToUserFullName");
        DRIExpression<String> assigneeHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("assigntouser");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };

        map.put("assignedToUserFullName", new HyperlinkValue(assigneeTitleExpr, assigneeHrefExpr));

        AbstractSimpleExpression<String> ratingExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters param) {
                Double level = param.getFieldValue("level");
                switch (level.intValue()) {
                    case 1:
                        return "images/1.png";
                    case 2:
                        return "images/2.png";
                    case 3:
                        return "images/3.png";
                    case 4:
                        return "images/4.png";
                    case 5:
                        return "images/5.png";
                    default:
                        return "images/severity_major.png";
                }

            }
        };
        HorizontalListBuilder ratingBuilder = cmp.horizontalList().setFixedWidth(120);
        ImageBuilder imgBuilder = cmp.image(ratingExpr).setFixedDimension(80, 15);
        ratingBuilder.add(imgBuilder);
        map.put("level", new CompBuilderValue(ratingBuilder));
        map.put("datedue", new DateExpression("datedue"));

        return map;
    }

    private Map<String, MValue> buildProblemMap() {
        LOG.debug("Build report mapper for project::problem module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("issuename");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer problemId = reportParameters.getFieldValue("id");
                Integer projectId = reportParameters.getFieldValue("projectId");
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                return ProjectLinkGenerator.generateProblemPreviewFullLink(siteUrl, projectId, problemId);
            }
        };
        map.put("issuename", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));

        DRIExpression<String> assigneeTitleExpr = new StringExpression("assignedUserFullName");
        DRIExpression<String> assigneeHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("assigntouser");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };

        map.put("assignedUserFullName", new HyperlinkValue(assigneeTitleExpr, assigneeHrefExpr));

        AbstractSimpleExpression<String> ratingExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters param) {
                Double level = param.getFieldValue("level");
                switch (level.intValue()) {
                    case 1:
                        return "images/1.png";
                    case 2:
                        return "images/2.png";
                    case 3:
                        return "images/3.png";
                    case 4:
                        return "images/4.png";
                    case 5:
                        return "images/5.png";
                    default:
                        return "images/severity_major.png";
                }

            }
        };
        HorizontalListBuilder ratingBuilder = cmp.horizontalList().setFixedWidth(120);
        ImageBuilder imgBuilder = cmp.image(ratingExpr).setFixedDimension(80, 15);
        ratingBuilder.add(imgBuilder);
        map.put("level", new CompBuilderValue(ratingBuilder));
        map.put("datedue", new DateExpression("datedue"));
        return map;
    }

    private Map<String, MValue> buildRoleMap() {
        LOG.debug("Build report mapper for project::role module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("rolename");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer roleId = reportParameters.getFieldValue("id");
                Integer projectId = reportParameters.getFieldValue("projectId");
                String siteUrl = reportParameters.getFieldValue("siteUrl");
                return ProjectLinkGenerator.generateRolePreviewFullLink(siteUrl, projectId, roleId);
            }
        };
        map.put("rolename", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));
        return map;
    }

    private Map<String, MValue> buildTimeTrackingMap() {
        LOG.debug("Build report mapper for project::timetracking module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> logUserTitleExpr = new StringExpression("logUserFullName");
        DRIExpression<String> logUserHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("loguser");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };

        map.put("logUserFullName", new HyperlinkValue(logUserTitleExpr, logUserHrefExpr));

        DRIExpression<String> projectTitleExpr = new StringExpression("projectName");
        DRIExpression<String> projectHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer projectId = reportParameters.getFieldValue("projectId");
                if (projectId != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return ProjectLinkGenerator.generateProjectFullLink(siteUrl, projectId);
                }

                return "";
            }
        };

        map.put("projectName", new HyperlinkValue(projectTitleExpr, projectHrefExpr));
        map.put("logforday", new DateExpression("logforday"));

        AbstractSimpleExpression<String> billingExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters param) {
                Boolean level = param.getFieldValue("isbillable");
                if (Boolean.TRUE.equals(level)) {
                    return "images/yes.png";
                } else {
                    return "images/no.png";
                }
            }
        };
        HorizontalListBuilder ratingBuilder = cmp.horizontalList().setFixedWidth(120);
        ImageBuilder imgBuilder = cmp.image(billingExpr).setFixedDimension(80, 15);
        ratingBuilder.add(imgBuilder);
        map.put("isbillable", new CompBuilderValue(ratingBuilder));

        DRIExpression<String> summaryTitleExpr = new StringExpression("summary");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String type = reportParameters.getFieldValue("type");
                Integer typeId = reportParameters.getFieldValue("typeid");
                Integer projectId = reportParameters.getFieldValue("projectId");
                String projectShortName = reportParameters.getFieldValue("projectShortName");
                String siteUrl = reportParameters.getFieldValue("siteUrl");

                if (type == null) {
                    return "";
                } else if (type.equals(ProjectTypeConstants.BUG)) {
                    return ProjectLinkGenerator.generateBugPreviewFullLink(siteUrl, typeId, projectShortName);
                } else if (type.equals(ProjectTypeConstants.TASK)) {
                    return ProjectLinkGenerator.generateTaskPreviewFullLink(siteUrl, typeId, projectShortName);
                } else if (type.equals(ProjectTypeConstants.PROBLEM)) {
                    return ProjectLinkGenerator.generateProblemPreviewFullLink(siteUrl, projectId, typeId);
                } else if (type.equals(ProjectTypeConstants.RISK)) {
                    return ProjectLinkGenerator.generateRiskPreviewFullLink(siteUrl, projectId, typeId);
                }
                return type;
            }
        };
        map.put("summary", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));
        return map;
    }

    private Map<String, MValue> buildTFollowingTicketMap() {
        LOG.debug("Build report mapper for project::following ticket module");

        Map<String, MValue> map = new HashMap<>();
        DRIExpression<String> summaryTitleExpr = new StringExpression("summary");
        DRIExpression<String> summaryHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String type = reportParameters.getFieldValue("type");
                Integer typeId = reportParameters.getFieldValue("typeId");
                String projectShortName = reportParameters.getFieldValue("projectShortName");
                String siteUrl = reportParameters.getFieldValue("siteUrl");

                if (type == null) {
                    return "";
                } else if (type.equals(ProjectTypeConstants.BUG)) {
                    return ProjectLinkGenerator.generateBugPreviewFullLink(siteUrl, typeId, projectShortName);
                } else if (type.equals(ProjectTypeConstants.TASK)) {
                    return ProjectLinkGenerator.generateTaskPreviewFullLink(siteUrl, typeId, projectShortName);
                }
                return type;
            }
        };
        map.put("summary", new HyperlinkValue(summaryTitleExpr, summaryHrefExpr));

        DRIExpression<String> projectTitleExpr = new StringExpression("projectName");
        DRIExpression<String> projectHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                Integer projectId = reportParameters.getFieldValue("projectId");
                if (projectId != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return ProjectLinkGenerator.generateProjectFullLink(siteUrl, projectId);
                }

                return "";
            }
        };

        map.put("projectName", new HyperlinkValue(projectTitleExpr, projectHrefExpr));

        DRIExpression<String> logUserTitleExpr = new StringExpression("assignUserFullName");
        DRIExpression<String> logUserHrefExpr = new AbstractSimpleExpression<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String evaluate(ReportParameters reportParameters) {
                String assignUser = reportParameters.getFieldValue("assignUser");
                if (assignUser != null) {
                    String siteUrl = reportParameters.getFieldValue("siteUrl");
                    return AccountLinkGenerator.generatePreviewFullUserLink(siteUrl, assignUser);
                }

                return "";
            }
        };

        map.put("assignUser", new HyperlinkValue(logUserTitleExpr, logUserHrefExpr));
        map.put("monitorDate", new DateExpression("monitorDate"));

        return map;
    }
}
