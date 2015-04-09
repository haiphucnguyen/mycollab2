package com.esofthead.mycollab.module.project.ui.form;

import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class ProjectItemViewField extends CustomField {
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
        String uid = UUID.randomUUID().toString();
        Text avatarLink = new Text(ProjectAssetsManager.getAsset(type).getHtml());
        A milestoneLink = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateProjectItemLink(project.getShortname(), project.getId(), type, typeId)).appendText(typeDisplayName);
        milestoneLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(uid, type, typeId + ""));
        milestoneLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));
        div.appendChild(avatarLink, DivLessFormatter.EMPTY_SPACE(), milestoneLink, DivLessFormatter.EMPTY_SPACE(), TooltipHelper.buildDivTooltipEnable(uid));
        return new Label(div.write(), ContentMode.HTML);
    }

    @Override
    public Class getType() {
        return Object.class;
    }
}
