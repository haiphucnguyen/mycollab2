package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CreateSectionWindow extends Window {
    private static final long serialVersionUID = 1L;

    private CrmCustomView customView;

    public CreateSectionWindow(CrmCustomView customView) {
        super("Create Section");
        center();
        this.setWidth("600px");
        VerticalLayout windowLayout = (VerticalLayout) this.getContent();
        windowLayout.setMargin(false);

        this.customView = customView;

        GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 2,
                "100%", "167px", Alignment.MIDDLE_LEFT);
        layoutHelper.getLayout().setMargin(false);
        layoutHelper.getLayout().setWidth("100%");
        layoutHelper.getLayout().addStyleName(UIConstants.COLORED_GRIDLAYOUT);

        TextField sectionName = new TextField();
        layoutHelper.addComponent(sectionName, "Name", 0, 0);

        SectionLayoutComboBox sectionLayoutComboBox = new SectionLayoutComboBox();
        layoutHelper.addComponent(sectionLayoutComboBox, "Column Layout", 0, 1);

        this.addComponent(layoutHelper.getLayout());

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setWidth("100%");
        controlLayout.setMargin(true);
        controlLayout.setSpacing(true);

        Button saveBtn = new Button(
                LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        // TODO Auto-generated method stub

                    }
                });
        saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
        controlLayout.addComponent(saveBtn);
        controlLayout.setComponentAlignment(saveBtn, Alignment.MIDDLE_LEFT);

        Button cancelBtn = new Button(
                LocalizationHelper
                        .getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        CreateSectionWindow.this.close();

                    }
                });
        cancelBtn.addStyleName("link");
        controlLayout.addComponent(cancelBtn);
        controlLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_LEFT);
        controlLayout.setExpandRatio(cancelBtn, 1.0f);

        this.addComponent(controlLayout);
    }

    private static class SectionLayoutComboBox extends ValueComboBox {
        private static final long serialVersionUID = 1L;

        public SectionLayoutComboBox() {
            super(false, "One Column", "Two Columns");
        }
    }

}
