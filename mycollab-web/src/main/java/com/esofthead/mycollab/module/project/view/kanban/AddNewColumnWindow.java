package com.esofthead.mycollab.module.project.view.kanban;

import com.esofthead.mycollab.common.dao.OptionValMapper;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.view.IKanbanView;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class AddNewColumnWindow extends Window {

    public AddNewColumnWindow(final IKanbanView kanbanView, final String type) {
        super("Add column");
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        this.center();
        MVerticalLayout layout = new MVerticalLayout().withMargin(new MarginInfo(false, false, true, false));
        GridFormLayoutHelper gridFormLayoutHelper = new GridFormLayoutHelper(1, 3, "100%", "250px", Alignment.TOP_LEFT);
        gridFormLayoutHelper.getLayout().setWidth("100%");
        gridFormLayoutHelper.getLayout().addStyleName("colored-gridlayout");
        gridFormLayoutHelper.getLayout().setMargin(false);
        this.setContent(layout);

        final TextField stageField = new TextField();
        final CheckBox defaultProject = new CheckBox();
        final TextArea description = new TextArea();

        gridFormLayoutHelper.addComponent(stageField, "Stage name", 0, 0);
        gridFormLayoutHelper.addComponent(defaultProject, "Default for new Projects", 0, 1);
        gridFormLayoutHelper.addComponent(description, "Description", 0, 2);

        Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                OptionVal optionVal = new OptionVal();
                optionVal.setCreatedtime(new GregorianCalendar().getTime());
                optionVal.setCreateduser(AppContext.getUsername());
                optionVal.setDescription(description.getValue());
                if (defaultProject.getValue()) {
                    optionVal.setIsdefault(true);
                } else {
                    optionVal.setIsdefault(false);
                    optionVal.setExtraid(CurrentProjectVariables.getProjectId());
                }
                optionVal.setSaccountid(AppContext.getAccountId());
                optionVal.setType(type);
                optionVal.setTypeval(stageField.getValue());
                OptionValMapper optionMapper = ApplicationContextUtil.getSpringBean(OptionValMapper.class);
                optionMapper.insert(optionVal);
                kanbanView.addColumn(optionVal);
                AddNewColumnWindow.this.close();
            }
        });
        saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddNewColumnWindow.this.close();
            }
        });
        cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

        MHorizontalLayout controls = new MHorizontalLayout().with(saveBtn, cancelBtn).withMargin(
                new MarginInfo(false, true, false, false));
        layout.with(gridFormLayoutHelper.getLayout(), controls).withAlign(controls, Alignment.BOTTOM_RIGHT);
    }
}
