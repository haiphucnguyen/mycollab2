package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.events.InvoiceEvent;
import com.esofthead.mycollab.module.project.service.InvoiceService;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceAddWindow extends Window {
    InvoiceAddWindow(final SimpleInvoice invoice) {
        if (invoice.getId() == null) {
            setCaption("New invoice");
        } else {
            setCaption("Edit invoice");
        }
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        VerticalLayout content = new VerticalLayout();
        this.setContent(content);
        final AdvancedEditBeanForm<SimpleInvoice> editBeanForm = new AdvancedEditBeanForm<>();
        content.addComponent(editBeanForm);
        editBeanForm.setFormLayoutFactory(new DynaFormLayout(ProjectTypeConstants.INVOICE,
                InvoiceDefaultFormLayoutFactory.getForm()));
        final InvoiceEditFormFieldFactory invoiceEditFormFieldFactory = new InvoiceEditFormFieldFactory(editBeanForm);
        editBeanForm.setBeanFormFieldFactory(invoiceEditFormFieldFactory);
        editBeanForm.setBean(invoice);

        MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(true, true, true, false));
        buttonControls.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

        Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (editBeanForm.validateForm()) {
                    InvoiceService invoiceService = AppContextUtil.getSpringBean(InvoiceService.class);

                    if (invoice.getId() == null) {
                        invoiceService.saveWithSession(invoice, AppContext.getUsername());
                        EventBusFactory.getInstance().post(new InvoiceEvent.NewInvoiceAdded(this, invoice));
                    } else {
                        invoiceService.updateWithSession(invoice, AppContext.getUsername());
                        EventBusFactory.getInstance().post(new InvoiceEvent.InvoiceUpdateAdded(this, invoice));
                    }
                    AttachmentUploadField uploadField = invoiceEditFormFieldFactory.getAttachmentUploadField();
                    String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(AppContext.getAccountId(), invoice.getProjectid(),
                            ProjectTypeConstants.INVOICE, "" + invoice.getId());
                    uploadField.saveContentsToRepo(attachPath);
                    close();
                }
            }
        });
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.setStyleName(UIConstants.BUTTON_ACTION);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });
        cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
        buttonControls.with(cancelBtn, saveBtn);
        content.addComponent(buttonControls);
        content.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
    }
}
