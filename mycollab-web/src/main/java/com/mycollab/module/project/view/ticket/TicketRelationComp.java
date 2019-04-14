/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.ticket;

import com.hp.gagawa.java.elements.A;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.domain.SimpleTicketRelation;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.AbstractToggleSummaryField;
import com.mycollab.vaadin.web.ui.WebThemes;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class TicketRelationComp extends AbstractToggleSummaryField {

    public TicketRelationComp(SimpleTicketRelation ticketRelation) {
        titleLinkLbl = ELabel.html(buildTicketLink(ticketRelation)).withStyleName(WebThemes.LABEL_WORD_WRAP).withUndefinedWidth();
        this.addComponent(titleLinkLbl);

//        toggleBugSummaryField = new ToggleBugSummaryField(relatedBug);
//        MButton unlinkBtn = new MButton("", clickEvent -> {
//            ConfirmDialogExt.show(UI.getCurrent(), UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE,
//                    AppUI.getSiteName()),
//                    UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
//                    UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
//                    UserUIContext.getMessage(GenericI18Enum.ACTION_NO), confirmDialog -> {
//                        TicketRelationExample ex = new TicketRelationExample();
//                        ex.or().andTicketidEqualTo(hostBug.getId()).andTickettypeEqualTo(ProjectTypeConstants.BUG).andTypeidEqualTo(relatedBug.getId());
//                        ex.or().andTicketidEqualTo(relatedBug.getId()).andTypeidEqualTo(hostBug.getId()).andTypeEqualTo(ProjectTypeConstants.BUG);
//
//                        TicketRelationMapper ticketRelationMapper = AppContextUtil.getSpringBean(TicketRelationMapper.class);
//                        ticketRelationMapper.deleteByExample(ex);
//                        UIUtils.removeChildAssociate(toggleBugSummaryField, RemoveInlineComponentMarker.class);
//                    });
//        }).withIcon(VaadinIcons.UNLINK).withStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP, ValoTheme.BUTTON_ICON_ONLY)
//                .withDescription(UserUIContext.getMessage(BugI18nEnum.OPT_REMOVE_RELATIONSHIP));
//        toggleBugSummaryField.addControl(unlinkBtn);
    }

    private String buildTicketLink(SimpleTicketRelation ticketRelation) {
        if (ticketRelation.getLtr()) {
            A ticketLink = new A(ProjectLinkGenerator.generateProjectItemLink(CurrentProjectVariables.getShortName(),
                    CurrentProjectVariables.getProjectId(), ticketRelation.getType(), ticketRelation.getTypeKey() + ""))
                    .appendText(ticketRelation.getTypeName()).setId("tag" + TooltipHelper.TOOLTIP_ID);
            ticketLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(ticketRelation.getType(), "" + ticketRelation.getTypeid()));
            ticketLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
            return ticketLink.write();
        } else {
            A ticketLink = new A(ProjectLinkGenerator.generateProjectItemLink(CurrentProjectVariables.getShortName(),
                    CurrentProjectVariables.getProjectId(), ticketRelation.getTickettype(), ticketRelation.getTicketKey() + ""))
                    .appendText(ticketRelation.getTicketName()).setId("tag" + TooltipHelper.TOOLTIP_ID);
            ticketLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(ticketRelation.getTickettype(), "" + ticketRelation.getTicketid()));
            ticketLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
            return ticketLink.write();
        }
    }
}
