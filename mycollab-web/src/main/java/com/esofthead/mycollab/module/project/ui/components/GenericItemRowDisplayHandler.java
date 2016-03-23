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
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.SafeHtmlLabel;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.layouts.MVerticalLayout;

import static com.esofthead.mycollab.utils.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class GenericItemRowDisplayHandler implements AbstractBeanPagedList.RowDisplayHandler<ProjectGenericItem> {
    @Override
    public Component generateRow(AbstractBeanPagedList host, ProjectGenericItem item, int rowIndex) {
        MVerticalLayout layout = new MVerticalLayout().withWidth("100%").withStyleName("border-bottom", UIConstants.HOVER_EFFECT_NOT_BOX);
        ELabel link = ELabel.h3("");
        if (ProjectTypeConstants.BUG.equals(item.getType()) || ProjectTypeConstants.TASK.equals(item.getType())) {
            link.setValue(ProjectLinkBuilder.generateProjectItemHtmlLinkAndTooltip(item.getProjectShortName(),
                    item.getProjectId(), item.getSummary(), item.getType(), item.getExtraTypeId() + ""));
        } else {
            link.setValue(ProjectLinkBuilder.generateProjectItemHtmlLinkAndTooltip(item.getProjectShortName(),
                    item.getProjectId(), item.getSummary(), item.getType(), item.getTypeId()));
        }

        String desc = (StringUtils.isBlank(item.getDescription())) ? "&lt;&lt;No description&gt;&gt;" : item.getDescription();
        SafeHtmlLabel descLbl = new SafeHtmlLabel(desc);

        Div div = new Div().setStyle("width:100%");
        Text createdByTxt = new Text("Created by: ");
        Div lastUpdatedOn = new Div().appendChild(new Text("Modified: " + AppContext.formatPrettyTime(item.getLastUpdatedTime
                ()))).setTitle(AppContext.formatDateTime(item.getLastUpdatedTime())).setStyle("float:right;" +
                "margin-right:5px");

        if (StringUtils.isBlank(item.getCreatedUser())) {
            div.appendChild(createdByTxt, DivLessFormatter.EMPTY_SPACE(), new Text("None"), lastUpdatedOn);
        } else {
            Img userAvatar = new Img("", StorageFactory.getInstance().getAvatarPath(item.getCreatedUserAvatarId(), 16));
            A userLink = new A().setId("tag" + TOOLTIP_ID).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(item.getProjectId(), item
                    .getCreatedUser())).appendText(item.getCreatedUserDisplayName());
            userLink.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(item.getCreatedUser()));
            userLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());

            div.appendChild(createdByTxt, DivLessFormatter.EMPTY_SPACE(), userAvatar, DivLessFormatter.EMPTY_SPACE(),
                    userLink, TooltipHelper.buildDivTooltipEnable(),
                    lastUpdatedOn);
        }

        ELabel footer = new ELabel(div.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO)
                .withWidth("100%");
        layout.with(link, descLbl, footer);
        return layout;
    }
}
