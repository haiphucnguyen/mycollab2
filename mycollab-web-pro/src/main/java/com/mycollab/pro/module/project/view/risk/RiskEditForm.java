package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class RiskEditForm extends AdvancedEditBeanForm<SimpleRisk> {
    @Override
    public void setBean(final SimpleRisk item) {
        this.setFormLayoutFactory(new FormLayoutFactory());
        this.setBeanFormFieldFactory(new RiskEditFormFieldFactory(this));
        super.setBean(item);
    }

    protected void postExecution() {

    }

    class FormLayoutFactory extends AbstractFormLayoutFactory {
        private IFormLayoutFactory formLayoutFactory;

        @Override
        public AbstractComponent getLayout() {
            VerticalLayout layout = new VerticalLayout();
            formLayoutFactory = new DefaultDynaFormLayout(ProjectTypeConstants.RISK, RiskDefaultFormLayoutFactory.getForm());
            AbstractComponent gridLayout = formLayoutFactory.getLayout();
            gridLayout.addStyleName(WebThemes.SCROLLABLE_CONTAINER);
            new Restrain(gridLayout).setMaxHeight((UIUtils.getBrowserHeight() - 180) + "px");
            layout.addComponent(gridLayout);
            layout.setExpandRatio(gridLayout, 1.0f);

            MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                if (validateForm()) {
                    RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                    Integer riskId;
                    if (bean.getId() == null) {
                        riskId = riskService.saveWithSession(bean, UserUIContext.getUsername());
                    } else {
                        riskService.updateWithSession(bean, UserUIContext.getUsername());
                        riskId = bean.getId();
                    }
                    AttachmentUploadField uploadField = ((RiskEditFormFieldFactory) getFieldFactory()).getAttachmentUploadField();
                    String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(MyCollabUI.getAccountId(), bean.getProjectid(),
                            ProjectTypeConstants.RISK, "" + riskId);
                    uploadField.saveContentsToRepo(attachPath);

                    postExecution();
                    EventBusFactory.getInstance().post(new TicketEvent.NewTicketAdded(this, ProjectTypeConstants.RISK, riskId));
                }
            }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.SAVE).withClickShortcut(ShortcutAction.KeyCode.ENTER);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> postExecution())
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(new MarginInfo(true, false, false, false));

            layout.addComponent(buttonControls);
            layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
            return layout;
        }

        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            return formLayoutFactory.attachField(propertyId, field);
        }
    }
}
