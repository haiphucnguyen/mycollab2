package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.view.task.TaskAddWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class EntityWithProjectAddHandler {
    private Object entity;
    private Window prjSelectionWindow;

    public Window buildWindow(SimpleTask task) {
        this.entity = task;
        if (task.getProjectid() == null) {
            prjSelectionWindow = new MWindow(UserUIContext.getMessage(TicketI18nEnum.NEW)).withResizable(false).withModal(true)
                    .withWidth("500px").withContent(new ProjectSelectionLayout());
            return prjSelectionWindow;
        } else {
            return new TaskAddWindow(task);
        }
    }

    private void displayEntityEditForm(Integer projectId) {
        if (entity instanceof SimpleTask) {
            SimpleTask newTask = (SimpleTask) entity;
            newTask.setProjectid(projectId);
            newTask.setSaccountid(AppUI.getAccountId());
            newTask.setCreateduser(UserUIContext.getUsername());
            UI.getCurrent().addWindow(new TaskAddWindow(newTask));
        }
    }

    private class ProjectSelectionLayout extends MVerticalLayout {
        private UserInvolvedProjectsSelection projectCombo;

        ProjectSelectionLayout() {
            this.withMargin(false);
            GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
            projectCombo = new UserInvolvedProjectsSelection();
            layoutHelper.addComponent(projectCombo, UserUIContext.getMessage(ProjectI18nEnum.SINGLE), 0, 0);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> prjSelectionWindow.close())
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MButton nextBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_NEXT), clickEvent -> {
                SimpleProject selectedProject = (SimpleProject) projectCombo.getValue();
                if (selectedProject != null) {
                    prjSelectionWindow.close();
                    displayEntityEditForm(selectedProject.getId());
                }
            }).withStyleName(WebThemes.BUTTON_ACTION);
            MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, nextBtn).withMargin(new MarginInfo(false, true, true, false));
            this.with(layoutHelper.getLayout(), buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
        }
    }

    private static class UserInvolvedProjectsSelection extends ComboBox {
        UserInvolvedProjectsSelection() {
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
            ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
            List<SimpleProject> projects = projectService.getProjectsUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
            for (SimpleProject project : projects) {
                this.addItem(project);
                this.setItemCaption(project, project.getName());
            }
        }
    }
}
