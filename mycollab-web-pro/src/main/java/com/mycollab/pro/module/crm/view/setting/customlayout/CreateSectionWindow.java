package com.mycollab.pro.module.crm.view.setting.customlayout;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.mycollab.module.crm.view.setting.ICrmCustomView;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
// TODO
public class CreateSectionWindow extends Window {
    private static final long serialVersionUID = 1L;

    public CreateSectionWindow(final ICrmCustomView customView) {
        super("New Section");
        center();
        this.setWidth("600px");
        this.setResizable(false);
        this.setModal(true);

        MVerticalLayout contentLayout = new MVerticalLayout().withMargin(false).withSpacing(false);
        this.setContent(contentLayout);

        GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 2);

        final TextField sectionName = new TextField();
        layoutHelper.addComponent(sectionName, "Name", 0, 0);

        final SectionLayoutComboBox sectionLayoutComboBox = new SectionLayoutComboBox();
        layoutHelper.addComponent(sectionLayoutComboBox, "Column Layout", 0, 1);

        contentLayout.addComponent(layoutHelper.getLayout());

        MHorizontalLayout controlLayout = new MHorizontalLayout().withMargin(true);

        Button saveBtn = new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), event -> {
            DynaSection section = new DynaSection();
            section.setDeletedSection(false);
//                        section.setHeader(sectionName.getValue());
            section.setLayoutType((LayoutType) sectionLayoutComboBox
                    .getValue());

            customView.addActiveSection(section);
            close();
        });
        saveBtn.setStyleName(WebThemes.BUTTON_ACTION);
        saveBtn.setIcon(VaadinIcons.PENCIL);


        Button cancelBtn = new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), event -> close());
        cancelBtn.setStyleName(WebThemes.BUTTON_OPTION);

        controlLayout.with(saveBtn, cancelBtn);
        contentLayout.with(controlLayout).withAlign(controlLayout, Alignment.MIDDLE_RIGHT);
    }

    private static class SectionLayoutComboBox extends ComboBox {
        private static final long serialVersionUID = 1L;

        SectionLayoutComboBox() {
//            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//            this.setNullSelectionAllowed(false);
//            this.addItem(LayoutType.ONE_COLUMN);
//            this.setItemCaption(LayoutType.ONE_COLUMN, "One Column");
//
//            this.addItem(LayoutType.TWO_COLUMN);
//            this.setItemCaption(LayoutType.TWO_COLUMN, "Two Columns");
//            this.select(LayoutType.ONE_COLUMN);
        }
    }

}
