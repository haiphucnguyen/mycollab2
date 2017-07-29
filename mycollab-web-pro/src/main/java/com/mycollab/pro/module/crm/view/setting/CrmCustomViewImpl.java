package com.mycollab.pro.module.crm.view.setting;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.form.service.MasterFormService;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.activity.AssignmentDefaultFormLayoutFactory;
import com.mycollab.module.crm.view.activity.CallDefaultFormLayoutFactory;
import com.mycollab.module.crm.view.activity.MeetingDefaultFormLayoutFactory;
import com.mycollab.module.crm.view.campaign.CampaignDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.cases.CasesDefaultFormLayoutFactory;
import com.mycollab.module.crm.view.contact.ContactDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.lead.LeadDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.opportunity.OpportunityDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.setting.ICrmCustomView;
import com.mycollab.pro.module.crm.view.setting.customlayout.CreateCustomFieldWindow;
import com.mycollab.pro.module.crm.view.setting.customlayout.CreateSectionWindow;
import com.mycollab.pro.module.crm.view.setting.customlayout.CustomLayoutDDComp;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.web.ui.ValueComboBox;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class CrmCustomViewImpl extends AbstractVerticalPageView implements ICrmCustomView {
    private static final long serialVersionUID = 1L;

    private Label headerLbl;
    private ModuleSelectionComboBox moduleComboBox;
    private CustomLayoutDDComp layoutComp;
    private String moduleName;

    public CrmCustomViewImpl() {
        this.withMargin(true);
        MVerticalLayout headerBox = new MVerticalLayout().withFullWidth();

        headerLbl = new Label("", ContentMode.HTML);

        MHorizontalLayout headerTitle = new MHorizontalLayout().withFullWidth().withSpacing(false)
                .withMargin(new MarginInfo(true, false, true, false));
        headerTitle.addStyleName(WebThemes.HEADER_VIEW);
        headerTitle.with(headerLbl).withAlign(headerLbl, Alignment.MIDDLE_LEFT).expand(headerLbl);

        headerBox.addComponent(headerTitle);

        MHorizontalLayout controlLayout = new MHorizontalLayout();
        moduleComboBox = new ModuleSelectionComboBox();

        Label moduleLbl = new Label("Module: ");
        controlLayout.addComponent(moduleLbl);
        controlLayout.setComponentAlignment(moduleLbl, Alignment.MIDDLE_LEFT);
        controlLayout.addComponent(moduleComboBox);
        controlLayout.setComponentAlignment(moduleComboBox, Alignment.MIDDLE_LEFT);

        MButton createCustomFieldBtn = new MButton("New Custom Field", clickEvent -> {
            CreateCustomFieldWindow createCustomFieldWindow = new CreateCustomFieldWindow(CrmCustomViewImpl.this);
            UI.getCurrent().addWindow(createCustomFieldWindow);
        }).withIcon(FontAwesome.PLUS).withStyleName(WebThemes.BUTTON_ACTION);
        controlLayout.addComponent(createCustomFieldBtn);
        controlLayout.setComponentAlignment(createCustomFieldBtn, Alignment.MIDDLE_LEFT);

        MButton createSectionBtn = new MButton("New Section", clickEvent -> {
            CreateSectionWindow createSectionWindow = new CreateSectionWindow(CrmCustomViewImpl.this);
            UI.getCurrent().addWindow(createSectionWindow);
        }).withIcon(FontAwesome.PLUS).withStyleName(WebThemes.BUTTON_ACTION);

        controlLayout.addComponent(createSectionBtn);
        controlLayout.setComponentAlignment(createSectionBtn, Alignment.MIDDLE_LEFT);
        headerTitle.addComponent(controlLayout);

        MVerticalLayout headerContent = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false,
                true, true, false)).withFullWidth();
        Label descLbl = new Label(
                "Customize the page layout by changing the order of the columns and fields, marking fields as mandatory, adding or removing the fields and sections. You can drag and drop the originSection header to reorder the sections. You need to drag and drop the fields to move them to the List of Removed Fields");
        descLbl.setStyleName("instructionLbl");
        headerContent.addComponent(descLbl);
        headerBox.addComponent(headerContent);
        addComponent(headerBox);

        layoutComp = new CustomLayoutDDComp();
        addComponent(layoutComp);

        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            DynaForm rebuildForm = layoutComp.rebuildForm();
            MasterFormService formService = AppContextUtil.getSpringBean(MasterFormService.class);
            formService.saveCustomForm(MyCollabUI.getAccountId(), moduleName, rebuildForm);
        }).withIcon(FontAwesome.SAVE).withStyleName(WebThemes.BUTTON_ACTION);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> display(moduleName))
                .withStyleName(WebThemes.BUTTON_OPTION);

        MHorizontalLayout buttonsLayout = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(new MarginInfo(true, false, true, false));
        headerContent.with(buttonsLayout).withAlign(buttonsLayout, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void display(String moduleName) {
        this.moduleName = moduleName;
        headerLbl.setValue(FontAwesome.MAGIC.getHtml() + " " + moduleName + ": Edit Page Layout (Beta)");
        headerLbl.setStyleName(ValoTheme.LABEL_H2);
        moduleComboBox.select(moduleName);
        layoutComp.displayLayoutCustom(getDynaForm(moduleName));
    }

    private static DynaForm getDynaForm(String moduleName) {
        MasterFormService formService = AppContextUtil.getSpringBean(MasterFormService.class);
        DynaForm form = formService.findCustomForm(MyCollabUI.getAccountId(), moduleName);

        if (form == null) {
            if (CrmTypeConstants.ACCOUNT.equals(moduleName)) {
                form = AccountDefaultDynaFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.CONTACT.equals(moduleName)) {
                form = ContactDefaultDynaFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.CAMPAIGN.equals(moduleName)) {
                form = CampaignDefaultDynaFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.LEAD.equals(moduleName)) {
                form = LeadDefaultDynaFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.OPPORTUNITY.equals(moduleName)) {
                form = OpportunityDefaultDynaFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.CASE.equals(moduleName)) {
                form = CasesDefaultFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.CALL.equals(moduleName)) {
                form = CallDefaultFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.MEETING.equals(moduleName)) {
                form = MeetingDefaultFormLayoutFactory.getForm();
            } else if (CrmTypeConstants.TASK.equals(moduleName)) {
                form = AssignmentDefaultFormLayoutFactory.getForm();
            } else {
                throw new MyCollabException("Do not support custom layout of module " + moduleName);
            }
        }

        return form;
    }

    @Override
    public void addActiveSection(DynaSection section) {
        layoutComp.addActiveSection(section);
    }

    @Override
    public DynaSection[] getActiveSections() {
        return layoutComp.getActiveSections();
    }

    private class ModuleSelectionComboBox extends ValueComboBox {
        private static final long serialVersionUID = 1L;

        ModuleSelectionComboBox() {
            super(false, CrmTypeConstants.ACCOUNT, CrmTypeConstants.CONTACT,
                    CrmTypeConstants.CAMPAIGN, CrmTypeConstants.LEAD,
                    CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE,
                    CrmTypeConstants.TASK, CrmTypeConstants.CALL,
                    CrmTypeConstants.MEETING);

            this.addValueChangeListener(valueChangeEvent -> {
                String module = (String) ModuleSelectionComboBox.this.getValue();
                display(module);
            });
        }
    }

    @Override
    public String getCandidateTextFieldName() {
        return layoutComp.getCandidateTextFieldName();
    }

    @Override
    public void refreshSectionLayout(DynaSection section) {
        layoutComp.refreshSectionLayout(section);
    }
}
