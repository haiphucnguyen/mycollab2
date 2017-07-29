package com.mycollab.module.project.view.kanban;

import com.mycollab.common.domain.OptionVal;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.service.OptionValService;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.view.IKanbanView;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.awt.*;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class AddNewColumnWindow extends MWindow {
    private static final Color DEFAULT_COLOR = Color.decode("#fdde86");

    public AddNewColumnWindow(final IKanbanView kanbanView, final String type, final String fieldGroup) {
        super(UserUIContext.getMessage(TaskI18nEnum.ACTION_NEW_COLUMN));
        this.withModal(true).withResizable(false).withWidth("800px").withCenter();
        MVerticalLayout layout = new MVerticalLayout().withMargin(new MarginInfo(false, false, true, false));
        GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 4);
        this.setContent(layout);

        final TextField stageField = new TextField();
        final CheckBox defaultProject = new CheckBox();
        defaultProject.setVisible(UserUIContext.canBeYes(RolePermissionCollections.GLOBAL_PROJECT_SETTINGS));
        final ColorPicker colorPicker = new ColorPicker("", new com.vaadin.shared.ui.colorpicker.Color(DEFAULT_COLOR.getRed(),
                DEFAULT_COLOR.getGreen(), DEFAULT_COLOR.getBlue()));
        final TextArea description = new TextArea();

        gridFormLayoutHelper.addComponent(stageField, UserUIContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0);
        gridFormLayoutHelper.addComponent(defaultProject, UserUIContext.getMessage(TaskI18nEnum.FORM_COLUMN_DEFAULT_FOR_NEW_PROJECT), 0, 1);
        gridFormLayoutHelper.addComponent(colorPicker, UserUIContext.getMessage(TaskI18nEnum.FORM_COLUMN_COLOR), 0, 2);
        gridFormLayoutHelper.addComponent(description, UserUIContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 3);

        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            OptionVal optionVal = new OptionVal();
            optionVal.setCreatedtime(new GregorianCalendar().getTime());
            optionVal.setCreateduser(UserUIContext.getUsername());
            optionVal.setDescription(description.getValue());
            com.vaadin.shared.ui.colorpicker.Color color = colorPicker.getColor();
            String cssColor = color.getCSS();
            if (cssColor.startsWith("#")) {
                cssColor = cssColor.substring(1);
            }
            optionVal.setColor(cssColor);
            if (defaultProject.getValue()) {
                optionVal.setIsdefault(true);
            } else {
                optionVal.setIsdefault(false);
                optionVal.setExtraid(CurrentProjectVariables.getProjectId());
            }
            optionVal.setSaccountid(MyCollabUI.getAccountId());
            optionVal.setType(type);
            optionVal.setTypeval(stageField.getValue());
            optionVal.setFieldgroup(fieldGroup);
            OptionValService optionService = AppContextUtil.getSpringBean(OptionValService.class);
            int optionValId = optionService.saveWithSession(optionVal, UserUIContext.getUsername());

            if (optionVal.getIsdefault()) {
                optionVal.setId(null);
                optionVal.setIsdefault(false);
                optionVal.setRefoption(optionValId);
                optionVal.setExtraid(CurrentProjectVariables.getProjectId());
                optionService.saveWithSession(optionVal, UserUIContext.getUsername());
            }
            kanbanView.addColumn(optionVal);
            close();
        }).withIcon(FontAwesome.SAVE).withStyleName(WebThemes.BUTTON_ACTION);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);

        MHorizontalLayout controls = new MHorizontalLayout().with(cancelBtn, saveBtn).withMargin(
                new MarginInfo(false, true, false, false));
        layout.with(gridFormLayoutHelper.getLayout(), controls).withAlign(controls, Alignment.BOTTOM_RIGHT);
    }
}
