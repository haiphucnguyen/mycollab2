package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.bug.BugAddWindow;
import com.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.mycollab.module.project.view.task.TaskAddWindow;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.pro.module.project.view.risk.RiskAddWindow;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class AssignmentAddWindow extends MWindow {

    public AssignmentAddWindow(Date date, final Integer prjId, boolean isIncludeMilestone) {
        super(UserUIContext.getMessage(ProjectCommonI18nEnum.ACTION_NEW_ASSIGNMENT));
        withModal(true).withResizable(false).withCenter().withWidth("500px");
        final PopupDateFieldExt dateSelection = new PopupDateFieldExt(date);
        final ComboBox typeSelection = new ComboBox();
        typeSelection.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
        typeSelection.addItem(UserUIContext.getMessage(TaskI18nEnum.SINGLE));
        typeSelection.setItemIcon(UserUIContext.getMessage(TaskI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK));
        typeSelection.addItem(UserUIContext.getMessage(BugI18nEnum.SINGLE));
        typeSelection.setItemIcon(UserUIContext.getMessage(BugI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG));
        if (isIncludeMilestone) {
            typeSelection.addItem(UserUIContext.getMessage(MilestoneI18nEnum.SINGLE));
            typeSelection.setItemIcon(UserUIContext.getMessage(MilestoneI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE));
        }

        typeSelection.addItem(UserUIContext.getMessage(RiskI18nEnum.SINGLE));
        typeSelection.setItemIcon(UserUIContext.getMessage(RiskI18nEnum.SINGLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK));
        typeSelection.select(UserUIContext.getMessage(TaskI18nEnum.SINGLE));
        typeSelection.setNullSelectionAllowed(false);
        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);

        MButton okButton = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_OK), clickEvent -> {
            String type = (String) typeSelection.getValue();
            Date dateValue = dateSelection.getValue();
            if (UserUIContext.getMessage(TaskI18nEnum.SINGLE).equals(type)) {
                close();
                SimpleTask task = new SimpleTask();
                task.setProjectid(prjId);
                task.setSaccountid(MyCollabUI.getAccountId());
                task.setLogby(UserUIContext.getUsername());
                task.setStartdate(dateValue);
                UI.getCurrent().addWindow(new TaskAddWindow(task));
            } else if (UserUIContext.getMessage(BugI18nEnum.SINGLE).equals(type)) {
                close();
                SimpleBug bug = new SimpleBug();
                bug.setProjectid(prjId);
                bug.setSaccountid(MyCollabUI.getAccountId());
                bug.setStartdate(dateValue);
                bug.setLogby(UserUIContext.getUsername());
                UI.getCurrent().addWindow(new BugAddWindow(bug));
            } else if (UserUIContext.getMessage(MilestoneI18nEnum.SINGLE).equals(type)) {
                close();
                SimpleMilestone milestone = new SimpleMilestone();
                milestone.setSaccountid(MyCollabUI.getAccountId());
                milestone.setProjectid(prjId);
                milestone.setStartdate(dateValue);
                UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
            } else {
                close();
                SimpleRisk risk = new SimpleRisk();
                risk.setSaccountid(MyCollabUI.getAccountId());
                risk.setProjectid(prjId);
                risk.setStartdate(dateValue);
                risk.setRaisedbyuser(UserUIContext.getUsername());
                UI.getCurrent().addWindow(new RiskAddWindow(risk));
            }
        }).withStyleName(WebUIConstants.BUTTON_ACTION);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebUIConstants.BUTTON_OPTION);
        MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, okButton);
        GridFormLayoutHelper formLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(2, 1, "60px");
        formLayoutHelper.addComponent(dateSelection, UserUIContext.getMessage(DayI18nEnum.OPT_DATE), 0, 0);
        formLayoutHelper.addComponent(typeSelection, UserUIContext.getMessage(GenericI18Enum.FORM_TYPE), 1, 0);
        content.with(formLayoutHelper.getLayout(), buttonControls).withAlign(buttonControls, Alignment.TOP_RIGHT);
    }
}
