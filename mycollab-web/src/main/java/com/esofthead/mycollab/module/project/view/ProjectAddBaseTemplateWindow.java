package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.service.ProjectTemplateService;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class ProjectAddBaseTemplateWindow extends Window {
    public ProjectAddBaseTemplateWindow() {
        super("Create project from template ...");
        this.setModal(true);
        this.setClosable(true);
        this.setResizable(false);
        this.setWidth("550px");
        MVerticalLayout content = new MVerticalLayout();
        GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 3);
        final TemplateProjectComboBox templateProjectComboBox = new TemplateProjectComboBox();
        Button helpBtn = new Button("");
        helpBtn.setIcon(FontAwesome.QUESTION_CIRCLE);
        helpBtn.addStyleName(UIConstants.BUTTON_ACTION);
        helpBtn.setDescription("To mark a template project, select a project and choose 'Mark as template'");
        gridFormLayoutHelper.addComponent(new MHorizontalLayout().withFullWidth().with(templateProjectComboBox,
                helpBtn).expand(templateProjectComboBox), "Template", 0, 0);
        final TextField prjNameField = new TextField();
        gridFormLayoutHelper.addComponent(prjNameField, AppContext.getMessage(ProjectI18nEnum.FORM_NAME), 0, 1);
        final TextField prjKeyField = new TextField();
        gridFormLayoutHelper.addComponent(prjKeyField, AppContext.getMessage(ProjectI18nEnum.FORM_SHORT_NAME), 0, 2);
        MHorizontalLayout buttonControls = new MHorizontalLayout();
        content.with(gridFormLayoutHelper.getLayout(), buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
        Button okBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_OK), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SimpleProject templatePrj = (SimpleProject) templateProjectComboBox.getValue();
                if (templatePrj == null) {
                    NotificationUtil.showErrorNotification("You must choose a template project");
                    return;
                }
                String newPrjName = prjNameField.getValue();
                if (newPrjName.length() == 0) {
                    NotificationUtil.showErrorNotification("Project name must be not null");
                    return;
                }
                String newPrjKey = prjKeyField.getValue();
                if (newPrjKey.length() > 3 || newPrjKey.length() == 0) {
                    NotificationUtil.showErrorNotification("Project key must be not null and less than 3 characters");
                    return;
                }
                ProjectTemplateService projectTemplateService = ApplicationContextUtil.getSpringBean
                        (ProjectTemplateService.class);
                if (projectTemplateService != null) {
                    Integer newProjectId = projectTemplateService.cloneProject(templatePrj.getId(), newPrjName, newPrjKey,
                            AppContext.getAccountId(), AppContext.getUsername());
                    EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this,
                            new PageActionChain(new ProjectScreenData.Goto(newProjectId))));
                    close();
                }
            }
        });
        okBtn.setIcon(FontAwesome.SAVE);
        okBtn.addStyleName(UIConstants.BUTTON_ACTION);
        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });
        cancelBtn.addStyleName(UIConstants.BUTTON_OPTION);
        buttonControls.with(okBtn, cancelBtn);
        this.setContent(content);
    }

    private static class TemplateProjectComboBox extends ComboBox {
        TemplateProjectComboBox() {
            ProjectService projectService = ApplicationContextUtil.getSpringBean(ProjectService.class);
            ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
            searchCriteria.addExtraField(ProjectSearchCriteria.p_template.buildParamIsEqual(SearchField.AND, 1));
            List<SimpleProject> projectTemplates = projectService.findPagableListByCriteria(new SearchRequest<>
                    (searchCriteria, 0, Integer.MAX_VALUE));
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
            for (SimpleProject prjTemplate : projectTemplates) {
                this.addItem(prjTemplate);
                this.setItemCaption(prjTemplate, StringUtils.trim(String.format("[%s] %s", prjTemplate.getShortname(),
                        prjTemplate.getName()), 50, true));
            }
        }
    }
}
