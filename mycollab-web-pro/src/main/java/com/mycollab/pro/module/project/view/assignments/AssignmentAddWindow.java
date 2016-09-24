package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.i18n.*;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.bug.BugEditForm;
import com.mycollab.module.project.view.task.TaskEditForm;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.pro.module.project.view.risk.RiskEditForm;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class AssignmentAddWindow extends MWindow {

    private ComboBox typeSelection;
    private CssLayout formLayout;

    public AssignmentAddWindow(Date date, final Integer prjId, final Integer milestoneId, boolean isIncludeMilestone) {
        super(UserUIContext.getMessage(TicketI18nEnum.NEW));
        MVerticalLayout content = new MVerticalLayout();
        withModal(true).withResizable(false).withCenter().withWidth("1200px").withContent(content);

        typeSelection = new ComboBox();
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
        typeSelection.addValueChangeListener(valueChangeEvent -> doChange(date, prjId, milestoneId));

        GridFormLayoutHelper formLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
        formLayoutHelper.addComponent(typeSelection, UserUIContext.getMessage(GenericI18Enum.FORM_TYPE), 0, 0);
        formLayout = new CssLayout();
        formLayout.setWidth("100%");
        content.with(formLayoutHelper.getLayout(), formLayout);
        doChange(date, prjId, milestoneId);
    }

    private void doChange(Date dateValue, final Integer prjId, final Integer milestoneId) {
        formLayout.removeAllComponents();
        String value = (String) typeSelection.getValue();
        if (UserUIContext.getMessage(TaskI18nEnum.SINGLE).equals(value)) {
            SimpleTask task = new SimpleTask();
            task.setProjectid(prjId);
            task.setMilestoneid(milestoneId);
            task.setSaccountid(MyCollabUI.getAccountId());
            task.setCreateduser(UserUIContext.getUsername());
            task.setStartdate(dateValue);
            TaskEditForm editForm = new TaskEditForm() {
                @Override
                protected void postExecution() {
                    close();
                }
            };
            editForm.setBean(task);
            formLayout.addComponent(editForm);
        } else if (UserUIContext.getMessage(BugI18nEnum.SINGLE).equals(value)) {
            SimpleBug bug = new SimpleBug();
            bug.setProjectid(prjId);
            bug.setSaccountid(MyCollabUI.getAccountId());
            bug.setStartdate(dateValue);
            bug.setMilestoneid(milestoneId);
            bug.setCreateduser(UserUIContext.getUsername());
            BugEditForm editForm = new BugEditForm() {
                @Override
                protected void postExecution() {
                    close();
                }
            };
            editForm.setBean(bug);
            formLayout.addComponent(editForm);
        } else if (UserUIContext.getMessage(RiskI18nEnum.SINGLE).equals(value)) {
            SimpleRisk risk = new SimpleRisk();
            risk.setSaccountid(MyCollabUI.getAccountId());
            risk.setProjectid(prjId);
            risk.setStartdate(dateValue);
            risk.setCreateduser(UserUIContext.getUsername());
            risk.setMilestoneid(milestoneId);
            RiskEditForm editForm = new RiskEditForm() {
                @Override
                protected void postExecution() {
                    close();
                }
            };
            editForm.setBean(risk);
            formLayout.addComponent(editForm);
        }
    }
}
