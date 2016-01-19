package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
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

        Button saveBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        DynaSection section = new DynaSection();
                        section.setDeletedSection(false);
                        section.setHeader(sectionName.getValue());
                        section.setLayoutType((LayoutType) sectionLayoutComboBox
                                .getValue());

                        customView.addActiveSection(section);
                        CreateSectionWindow.this.close();
                    }
                });
        saveBtn.setStyleName(UIConstants.BUTTON_ACTION);
        saveBtn.setIcon(FontAwesome.SAVE);


        Button cancelBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        CreateSectionWindow.this.close();
                    }
                });
        cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);

        controlLayout.with(saveBtn, cancelBtn);

        contentLayout.with(controlLayout).withAlign(controlLayout, Alignment.MIDDLE_RIGHT);
    }

    private static class SectionLayoutComboBox extends ComboBox {
        private static final long serialVersionUID = 1L;

        public SectionLayoutComboBox() {
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
            this.setNullSelectionAllowed(false);
            this.addItem(LayoutType.ONE_COLUMN);
            this.setItemCaption(LayoutType.ONE_COLUMN, "One Column");

            this.addItem(LayoutType.TWO_COLUMN);
            this.setItemCaption(LayoutType.TWO_COLUMN, "Two Columns");
            this.select(LayoutType.ONE_COLUMN);
        }
    }

}
