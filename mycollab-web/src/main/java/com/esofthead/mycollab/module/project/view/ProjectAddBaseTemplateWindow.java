package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.db.query.NumberParam;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
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
        this.setWidth("450px");
        MVerticalLayout content = new MVerticalLayout();
        TemplateProjectComboBox templateProjectComboBox = new TemplateProjectComboBox();
        MHorizontalLayout bodyPanel = new MHorizontalLayout().with(new ELabel("Template: ").withWidthUndefined(),
                templateProjectComboBox).expand(templateProjectComboBox);
        MHorizontalLayout buttonControls = new MHorizontalLayout();
        content.with(bodyPanel, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
        Button okBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_OK), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });
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
            for (SimpleProject prjTemplate: projectTemplates) {
                this.addItem(prjTemplate);
                this.setItemCaption(prjTemplate, StringUtils.trim(prjTemplate.getName(), 50, true));
            }
        }
    }
}
