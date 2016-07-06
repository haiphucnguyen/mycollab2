package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.events.AssignmentEvent;
import com.mycollab.module.project.events.RiskEvent;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class RiskAddWindow extends Window {
    public RiskAddWindow(SimpleRisk risk) {
        if (risk.getId() == null) {
            setCaption(AppContext.getMessage(RiskI18nEnum.NEW));
        } else {
            setCaption(AppContext.getMessage(RiskI18nEnum.EDIT));
        }
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);

        EditForm editForm = new EditForm();
        editForm.setBean(risk);
        this.setContent(editForm);
    }

    private class EditForm extends AdvancedEditBeanForm<SimpleRisk> {
        @Override
        public void setBean(final SimpleRisk item) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new RiskEditFormFieldFactory(this));
            super.setBean(item);
        }

        class FormLayoutFactory extends AbstractFormLayoutFactory {
            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public ComponentContainer getLayout() {
                VerticalLayout layout = new VerticalLayout();
                informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 5);
                informationLayout.getLayout().setMargin(false);
                informationLayout.getLayout().setSpacing(false);
                layout.addComponent(informationLayout.getLayout());

                MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(true, true, true, false));
                buttonControls.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

                MButton updateAllBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_OTHER_FIELDS), clickEvent -> {
                    EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, EditForm.this.bean));
                    close();
                }).withStyleName(UIConstants.BUTTON_LINK);

                MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                    if (EditForm.this.validateForm()) {
                        RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                        Integer riskId;
                        if (bean.getId() == null) {
                            riskId = riskService.saveWithSession(bean, AppContext.getUsername());
                        } else {
                            riskService.updateWithSession(bean, AppContext.getUsername());
                            riskId = bean.getId();
                        }
                        AttachmentUploadField uploadField = ((RiskEditFormFieldFactory) getFieldFactory()).getAttachmentUploadField();
                        String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(AppContext.getAccountId(), bean.getProjectid(),
                                ProjectTypeConstants.RISK, "" + riskId);
                        uploadField.saveContentsToRepo(attachPath);

                        close();
                        EventBusFactory.getInstance().post(new AssignmentEvent.NewAssignmentAdd(this,
                                ProjectTypeConstants.RISK, riskId));
                    }
                }).withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.SAVE);

                MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                        .withStyleName(UIConstants.BUTTON_OPTION);
                cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
                buttonControls.with(updateAllBtn, saveBtn, cancelBtn);

                layout.addComponent(buttonControls);
                layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            protected Component onAttachField(Object propertyId, Field<?> field) {
                if (Risk.Field.riskname.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0, 2, "100%");
                } else if (Risk.Field.assigntouser.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 1);
                } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_PHASE), 1, 1);
                } else if (Risk.Field.datedue.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DUE_DATE), 0, 2);
                } else if (Risk.Field.consequence.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE), 1, 2);
                } else if (Risk.Field.probalitity.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY), 0, 3);
                } else if (Risk.Field.level.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_RATING), 1, 3);
                } else if (Risk.Field.description.equalTo(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 4, 2, "100%");
                }
                return null;
            }
        }
    }
}
