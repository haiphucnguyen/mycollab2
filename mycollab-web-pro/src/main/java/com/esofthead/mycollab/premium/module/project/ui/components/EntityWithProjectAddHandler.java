package com.esofthead.mycollab.premium.module.project.ui.components;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.task.TaskAddWindow;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
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
            prjSelectionWindow.setCaption("New Task");
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
            UI.getCurrent().addWindow(new TaskAddWindow(newTask));
        }
    }

    private class ProjectSelectionLayout extends MVerticalLayout {
        private UserInvolvedProjectsSelection projectCombo;

        ProjectSelectionLayout() {
            this.withMargin(false);
            GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
            projectCombo = new UserInvolvedProjectsSelection();
            layoutHelper.addComponent(projectCombo, "Project", 0, 0);
            MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(false, true, true, false));
            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    prjSelectionWindow.close();
                }
            });
            cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

            Button nextBtn = new Button("Next", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    SimpleProject selectedProject = (SimpleProject) projectCombo.getValue();
                    if (selectedProject != null) {
                        prjSelectionWindow.close();
                        displayEntityEditForm(selectedProject.getId());
                    }
                }
            });
            nextBtn.setStyleName(UIConstants.BUTTON_ACTION);
            buttonControls.with(cancelBtn, nextBtn);
            this.with(layoutHelper.getLayout(), buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
        }
    }

    private static class UserInvolvedProjectsSelection extends ComboBox {
        UserInvolvedProjectsSelection() {
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
            ProjectService projectService = ApplicationContextUtil.getSpringBean(ProjectService.class);
            List<SimpleProject> projects = projectService.getProjectsUserInvolved(AppContext.getUsername(), AppContext
                    .getAccountId());
            for (SimpleProject project : projects) {
                this.addItem(project);
                this.setItemCaption(project, project.getName());
            }

        }
    }
}
