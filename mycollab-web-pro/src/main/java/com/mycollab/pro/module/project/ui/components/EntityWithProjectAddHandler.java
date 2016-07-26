package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.view.task.TaskAddWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

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
            prjSelectionWindow = new Window();
            prjSelectionWindow.setResizable(false);
            prjSelectionWindow.setModal(true);
            prjSelectionWindow.setWidth("500px");
            prjSelectionWindow.setCaption(AppContext.getMessage(ProjectCommonI18nEnum.ACTION_NEW_ASSIGNMENT));
            prjSelectionWindow.setContent(new ProjectSelectionLayout());
            return prjSelectionWindow;
        } else {
            return new TaskAddWindow(task);
        }
    }

    private void displayEntityEditForm(Integer projectId) {
        if (entity instanceof SimpleTask) {
            SimpleTask newTask = (SimpleTask) entity;
            newTask.setProjectid(projectId);
            newTask.setSaccountid(AppContext.getAccountId());
            newTask.setLogby(AppContext.getUsername());
            UI.getCurrent().addWindow(new TaskAddWindow(newTask));
        }
    }

    private class ProjectSelectionLayout extends MVerticalLayout {
        private UserInvolvedProjectsSelection projectCombo;

        ProjectSelectionLayout() {
            this.withMargin(false);
            GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
            projectCombo = new UserInvolvedProjectsSelection();
            layoutHelper.addComponent(projectCombo, AppContext.getMessage(ProjectI18nEnum.SINGLE), 0, 0);

            MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> prjSelectionWindow.close())
                    .withStyleName(WebUIConstants.BUTTON_OPTION);

            MButton nextBtn = new MButton(AppContext.getMessage(GenericI18Enum.ACTION_NEXT), clickEvent -> {
                SimpleProject selectedProject = (SimpleProject) projectCombo.getValue();
                if (selectedProject != null) {
                    prjSelectionWindow.close();
                    displayEntityEditForm(selectedProject.getId());
                }
            }).withStyleName(WebUIConstants.BUTTON_ACTION);
            MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, nextBtn).withMargin(new MarginInfo(false, true, true, false));
            this.with(layoutHelper.getLayout(), buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
        }
    }

    private static class UserInvolvedProjectsSelection extends ComboBox {
        UserInvolvedProjectsSelection() {
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
            ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
            List<SimpleProject> projects = projectService.getProjectsUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
            for (SimpleProject project : projects) {
                this.addItem(project);
                this.setItemCaption(project, project.getName());
            }
        }
    }
}
