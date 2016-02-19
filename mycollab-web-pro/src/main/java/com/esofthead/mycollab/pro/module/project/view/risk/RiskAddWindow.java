package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.events.AssignmentEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class RiskAddWindow extends Window {
    public RiskAddWindow(SimpleRisk risk) {
        if (risk.getId() == null) {
            setCaption("New risk");
        } else {
            setCaption("Edit risk");
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

        class FormLayoutFactory implements IFormLayoutFactory {
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

                Button updateAllBtn = new Button("Update other fields", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, EditForm.this.bean));
                        close();
                    }
                });
                updateAllBtn.addStyleName(UIConstants.BUTTON_LINK);

                Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        if (EditForm.this.validateForm()) {
                            RiskService riskService = ApplicationContextUtil.getSpringBean(RiskService.class);
                            Integer riskId;
                            if (bean.getId() == null) {
                                riskId = riskService.saveWithSession(bean, AppContext.getUsername());
                            } else {
                                riskService.updateWithSession(bean, AppContext.getUsername());
                                riskId = bean.getId();
                            }
                            close();
                            EventBusFactory.getInstance().post(new AssignmentEvent.NewAssignmentAdd(this,
                                    ProjectTypeConstants.RISK, riskId));
                        }
                    }
                });
                saveBtn.setIcon(FontAwesome.SAVE);
                saveBtn.setStyleName(UIConstants.BUTTON_ACTION);

                Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        close();
                    }
                });
                cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
                buttonControls.with(updateAllBtn, saveBtn, cancelBtn);

                layout.addComponent(buttonControls);
                layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            public void attachField(Object propertyId, Field<?> field) {
                if (Risk.Field.riskname.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_NAME), 0, 0, 2, "100%");
                } else if (Risk.Field.assigntouser.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 1);
                } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_PHASE), 1, 1);
                } else if (Risk.Field.datedue.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_DATE_DUE), 0, 2);
                } else if (Risk.Field.consequence.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE), 1, 2);
                } else if (Risk.Field.probalitity.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY), 0, 3);
                } else if (Risk.Field.level.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_RATING), 1, 3);
                } else if (Risk.Field.description.equalTo(propertyId)) {
                    informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 4, 2, "100%");
                }
            }
        }
    }
}