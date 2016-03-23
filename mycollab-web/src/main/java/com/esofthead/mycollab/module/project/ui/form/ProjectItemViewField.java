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
package com.esofthead.mycollab.module.project.ui.form;

import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import static com.esofthead.mycollab.utils.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class ProjectItemViewField extends CustomField<String> {
    private String type;
    private String typeId;
    private String typeDisplayName;

    public ProjectItemViewField(String type, String typeId, String typeDisplayName) {
        this.type = type;
        this.typeId = typeId;
        this.typeDisplayName = typeDisplayName;
    }

    @Override
    protected Component initContent() {
        if (typeId.equals("null")) {
            return new Label();
        }

        SimpleProject project = CurrentProjectVariables.getProject();
        DivLessFormatter div = new DivLessFormatter();

        A milestoneLink = new A().setId("tag" + TOOLTIP_ID).setHref(ProjectLinkBuilder.generateProjectItemLink(project.getShortname(),
                project.getId(), type, typeId)).appendText(typeDisplayName);
        milestoneLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(type, typeId + ""));
        milestoneLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
        div.appendChild(milestoneLink);
        ELabel label = new ELabel(div.write(), ContentMode.HTML).withStyleName(UIConstants.TEXT_ELLIPSIS);
        return new MHorizontalLayout(new ELabel(ProjectAssetsManager.getAsset(type).getHtml(), ContentMode.HTML).withWidthUndefined(),
                label).expand(label);
    }

    @Override
    public Class getType() {
        return String.class;
    }
}
