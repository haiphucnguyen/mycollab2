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
package com.esofthead.mycollab.module.project.view.assignments;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.bug.BugAddWindow;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.esofthead.mycollab.module.project.view.task.TaskAddWindow;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class AssignmentAddWindow extends Window {

    public AssignmentAddWindow(Date date, final Integer prjId) {
        super("New assignment");
        final DateField dateSelection = new DateField(null, date);
        final ComboBox typeSelection = new ComboBox();
        typeSelection.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        typeSelection.addItems("Task", "Bug", "Milestone", "Risk");
        typeSelection.setItemIcon("Task", ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK));
        typeSelection.setItemIcon("Bug", ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG));
        typeSelection.setItemIcon("Milestone", ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE));
        typeSelection.setItemIcon("Risk", ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK));
        typeSelection.select("Task");
        this.setModal(true);
        this.setResizable(false);
        this.setWidth("400px");
        MVerticalLayout content = new MVerticalLayout().withMargin(true);
        this.setContent(content);

        Button okButton = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_OK), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String type = (String) typeSelection.getValue();
                if ("Task".equals(type)) {
                    close();
                    SimpleTask task = new SimpleTask();
                    task.setProjectid(prjId);
                    task.setSaccountid(AppContext.getAccountId());
                    task.setStartdate(dateSelection.getValue());
                    UI.getCurrent().addWindow(new TaskAddWindow(task));
                } else if ("Bug".equals(type)) {
                    close();
                    SimpleBug bug = new SimpleBug();
                    bug.setProjectid(prjId);
                    bug.setSaccountid(AppContext.getAccountId());
                    bug.setStartdate(dateSelection.getValue());
                    UI.getCurrent().addWindow(new BugAddWindow(bug));
                } else if ("Milestone".equals(type)) {
                    close();
                    SimpleMilestone milestone = new SimpleMilestone();
                    milestone.setSaccountid(AppContext.getAccountId());
                    milestone.setProjectid(prjId);
                    milestone.setStartdate(dateSelection.getValue());
                    UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
                } else {
                    close();
                    SimpleRisk risk = new SimpleRisk();
                    risk.setSaccountid(AppContext.getAccountId());
                    risk.setProjectid(prjId);
                    risk.setStartdate(dateSelection.getValue());

                }
            }
        });
        okButton.addStyleName(UIConstants.BUTTON_ACTION);
        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });
        cancelBtn.addStyleName(UIConstants.BUTTON_OPTION);
        MHorizontalLayout buttonControls = new MHorizontalLayout(okButton, cancelBtn);
        content.with(new MHorizontalLayout().with(new Label("Date: "), dateSelection, new Label("Type: "), typeSelection), buttonControls).withAlign
                (buttonControls, Alignment.TOP_RIGHT);
    }
}
