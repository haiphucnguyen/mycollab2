/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.utils.NumberUtils;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.mobile.ui.DefaultPagedBeanList;
import com.esofthead.mycollab.mobile.ui.UIConstants;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class ProjectListDisplay extends DefaultPagedBeanList<ProjectService, ProjectSearchCriteria, SimpleProject> {
    private static final long serialVersionUID = -3362055893248919249L;

    public ProjectListDisplay() {
        super(ApplicationContextUtil.getSpringBean(ProjectService.class), new ProjectRowDisplayHandler());
    }

    public static class ProjectRowDisplayHandler implements RowDisplayHandler<SimpleProject> {

        @Override
        public Component generateRow(final SimpleProject project, int rowIndex) {
            MVerticalLayout layout = new MVerticalLayout();
            A prjLink = new A(ProjectLinkBuilder.generateProjectFullLink(project.getId())).appendText(String.format
                    ("[%s] %s", project.getShortname(), project.getName()));
            layout.with(new ELabel(prjLink.write(), ContentMode.HTML).withStyleName(UIConstants.TRUNCATE));

            MHorizontalLayout metaInfo = new MHorizontalLayout();
            metaInfo.setDefaultComponentAlignment(Alignment.TOP_LEFT);

            Div activeMembersDiv = new Div().appendText(FontAwesome.USERS.getHtml() + " " + project.getNumActiveMembers()).setTitle("Active " +
                    "members");

            Div createdTimeDiv = new Div().appendText(FontAwesome.CLOCK_O.getHtml() + " " + AppContext
                    .formatPrettyTime(project.getCreatedtime())).setTitle("Created time");

            Div billableHoursDiv = new Div().appendText(FontAwesome.MONEY.getHtml() + " " + NumberUtils.roundDouble
                    (2, project.getTotalBillableHours())).setTitle("Billable hours");

            Div nonBillableHoursDiv = new Div().appendText(FontAwesome.GIFT.getHtml() + " " + NumberUtils.roundDouble(2, project.getTotalNonBillableHours()));
            nonBillableHoursDiv.setTitle("Non billable hours");

            Div metaDiv = new Div().appendChild(activeMembersDiv, DivLessFormatter.EMPTY_SPACE(), createdTimeDiv, DivLessFormatter.EMPTY_SPACE(),
                    billableHoursDiv, DivLessFormatter.EMPTY_SPACE(), nonBillableHoursDiv);
            if (project.getLead() != null) {
                Div leadDiv = new Div().appendChild(new Img("", StorageFactory.getInstance().getAvatarPath(project
                        .getLeadAvatarId(), 16)), new A(ProjectLinkBuilder.generateProjectMemberFullLink(project.getId(), project.getLead()))
                        .appendText(project.getLeadFullName())).setTitle("Manager");
                metaDiv.appendChild(0, leadDiv);
                metaDiv.appendChild(1, DivLessFormatter.EMPTY_SPACE());
            }
            metaInfo.addComponent(new ELabel(metaDiv.write(), ContentMode.HTML).withStyleName(UIConstants.META_INFO));
            layout.addComponent(metaInfo);

            int openAssignments = project.getNumOpenBugs() + project.getNumOpenTasks() + project.getNumOpenRisks() + project.getNumOpenRisks();
            int totalAssignments = project.getNumBugs() + project.getNumTasks() + project.getNumRisks();
            ELabel progressInfoLbl;
            if (totalAssignments > 0) {
                progressInfoLbl = new ELabel(String.format("%d of %d issue(s) resolved. Progress (%d%%)",
                        (totalAssignments - openAssignments), totalAssignments, (totalAssignments - openAssignments)
                                * 100 / totalAssignments)).withStyleName(UIConstants.META_INFO);
            } else {
                progressInfoLbl = new ELabel("No issue").withStyleName(UIConstants.META_INFO);
            }
            layout.addComponent(progressInfoLbl);

            return layout;
        }
    }
}
