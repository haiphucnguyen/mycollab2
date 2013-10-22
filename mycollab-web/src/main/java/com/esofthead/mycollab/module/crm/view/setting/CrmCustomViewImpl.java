package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@ViewComponent
public class CrmCustomViewImpl extends AbstractView implements CrmCustomView {
    private static final long serialVersionUID = 1L;

    private Label headerLbl;
    private ModuleSelectionComboBox moduleComboBox;
    private CustomLayoutDDComp layoutComp;

    public CrmCustomViewImpl() {
        this.setSpacing(true);
        headerLbl = new Label();
        this.addComponent(headerLbl);

        Label descLbl = new Label(
                "Customize the page layout by changing the order of the columns and fields, marking fields as mandatory, adding or removing the fields and sections. You can drag and drop the section header to reorder the sections. You need to drag and drop the fields to move them to the List of Removed Fields");
        this.addComponent(descLbl);

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setSpacing(true);
        moduleComboBox = new ModuleSelectionComboBox();

        Label moduleLbl = new Label("Module");
        controlLayout.addComponent(moduleLbl);
        controlLayout.setComponentAlignment(moduleLbl, Alignment.MIDDLE_LEFT);
        controlLayout.addComponent(moduleComboBox);
        controlLayout.setComponentAlignment(moduleComboBox,
                Alignment.MIDDLE_LEFT);

        Button createCustomFieldBtn = new Button("New Custom Field",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        // TODO Auto-generated method stub

                    }
                });
        createCustomFieldBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
        createCustomFieldBtn.setIcon(MyCollabResource
                .newResource("icons/16/addRecord.png"));
        controlLayout.addComponent(createCustomFieldBtn);
        controlLayout.setComponentAlignment(createCustomFieldBtn,
                Alignment.MIDDLE_LEFT);

        Button createSectionBtn = new Button("Create Section",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        CreateSectionWindow createSectionWindow = new CreateSectionWindow(
                                CrmCustomViewImpl.this);
                        getWindow().addWindow(createSectionWindow);

                    }
                });
        createSectionBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
        createSectionBtn.setIcon(MyCollabResource
                .newResource("icons/16/addRecord.png"));
        controlLayout.addComponent(createSectionBtn);
        controlLayout.setComponentAlignment(createSectionBtn,
                Alignment.MIDDLE_LEFT);
        this.addComponent(controlLayout);

        layoutComp = new CustomLayoutDDComp();
        this.addComponent(layoutComp);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
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
        buttonsLayout.addComponent(saveBtn);

        Button cancelBtn = new Button(
                LocalizationHelper
                        .getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        // TODO Auto-generated method stub

                    }
                });
        cancelBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
        buttonsLayout.addComponent(cancelBtn);

        this.addComponent(buttonsLayout);
        this.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void display(String moduleName) {
        headerLbl.setCaption(moduleName + ": Edit Page Layout");
        moduleComboBox.select(moduleName);
        DynaForm form;

        if (CrmTypeConstants.ACCOUNT.equals(moduleName)) {
            form = AccountDefaultDynaFormFactory.getForm(AppContext
                    .getAccountId());
        } else {
            throw new MyCollabException(
                    "Do not support custom layout of module " + moduleName);
        }

        layoutComp.displayLayoutCustom(form);
    }

    private class ModuleSelectionComboBox extends ValueComboBox {
        private static final long serialVersionUID = 1L;

        public ModuleSelectionComboBox() {
            super(false, CrmTypeConstants.ACCOUNT, CrmTypeConstants.CONTACT,
                    CrmTypeConstants.CAMPAIGN, CrmTypeConstants.LEAD,
                    CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE,
                    CrmTypeConstants.TASK, CrmTypeConstants.CALL,
                    CrmTypeConstants.MEETING);
        }
    }

}
