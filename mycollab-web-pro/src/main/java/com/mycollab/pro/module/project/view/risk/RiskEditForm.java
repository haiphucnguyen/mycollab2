package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.ui.WrappedFormLayoutFactory;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.data.HasValue;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

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

    class FormLayoutFactory extends WrappedFormLayoutFactory {

        @Override
        public AbstractComponent getLayout() {
            MVerticalLayout layout = new MVerticalLayout().withMargin(false);
            wrappedLayoutFactory = new DefaultDynaFormLayout(ProjectTypeConstants.RISK, RiskDefaultFormLayoutFactory.getForm());
            AbstractComponent gridLayout = wrappedLayoutFactory.getLayout();
            gridLayout.addStyleName(WebThemes.SCROLLABLE_CONTAINER);
            gridLayout.addStyleName("window-max-height");

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
                    String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(), bean.getProjectid(),
                            ProjectTypeConstants.RISK, "" + riskId);
                    uploadField.saveContentsToRepo(attachPath);

                    postExecution();
                    EventBusFactory.getInstance().post(new TicketEvent.NewTicketAdded(this, ProjectTypeConstants.RISK, riskId));
                }
            }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.CLIPBOARD).withClickShortcut(ShortcutAction.KeyCode.ENTER);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> postExecution())
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MCssLayout buttonControls = new MCssLayout(new MHorizontalLayout(cancelBtn, saveBtn).withStyleName(WebThemes.ALIGN_RIGHT)
                    .withMargin(new MarginInfo(true, false, false, false))).withFullWidth().withStyleName(WebThemes.BORDER_TOP);

            layout.with(gridLayout, buttonControls).expand(gridLayout).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
            return layout;
        }
    }
}
