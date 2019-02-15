package com.mycollab.pro.module.project.view.finance;

import com.google.common.collect.Sets;
import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.SearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.mycollab.module.project.event.InvoiceEvent;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.service.InvoiceService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.ProjectActivityComponent;
import com.mycollab.module.project.view.finance.IInvoiceContainer;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ApplicationEventListener;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.reporting.FormReportLayout;
import com.mycollab.vaadin.reporting.PrintButton;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.IBeanList;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Collections;
import java.util.HashSet;

import static com.mycollab.module.project.i18n.OptionI18nEnum.InvoiceStatus;
import static com.mycollab.module.project.i18n.OptionI18nEnum.InvoiceStatus.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class InvoiceContainerImpl extends AbstractVerticalPageView implements IInvoiceContainer {

    private ApplicationEventListener<InvoiceEvent.NewInvoiceAdded> newInvoiceAddedHandler = new
            ApplicationEventListener<InvoiceEvent.NewInvoiceAdded>() {
                @Override
                @Subscribe
                public void handle(InvoiceEvent.NewInvoiceAdded event) {
                    SimpleInvoice newInvoice = event.getData();
                    insertNewInvoiceAdded(newInvoice);
                }
            };

    private ApplicationEventListener<InvoiceEvent.InvoiceUpdateAdded> updateInvoiceAddedHandler = new
            ApplicationEventListener<InvoiceEvent.InvoiceUpdateAdded>() {
                @Override
                @Subscribe
                public void handle(InvoiceEvent.InvoiceUpdateAdded event) {
                    SimpleInvoice newInvoice = event.getData();
                    updateInvoiceAdded(newInvoice);
                }
            };

    private ApplicationEventListener<InvoiceEvent.InvoiceDelete> deleteInvoiceHandler = new
            ApplicationEventListener<InvoiceEvent.InvoiceDelete>() {
                @Override
                @Subscribe
                public void handle(InvoiceEvent.InvoiceDelete event) {
                    SimpleInvoice newInvoice = event.getData();
                    displayInvoices(invoiceStatus);
                }
            };

    private InvoiceStatus invoiceStatus;
    private InvoiceListComp invoiceListComp;
    private InvoiceStatusComboBox statusComboBox;

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(newInvoiceAddedHandler);
        EventBusFactory.getInstance().register(updateInvoiceAddedHandler);
        EventBusFactory.getInstance().register(deleteInvoiceHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(newInvoiceAddedHandler);
        EventBusFactory.getInstance().unregister(updateInvoiceAddedHandler);
        EventBusFactory.getInstance().unregister(deleteInvoiceHandler);
        super.detach();
    }

    @Override
    public void display() {
        removeAllComponents();
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.INVOICE)) {
            ELabel invoiceIcon = ELabel.h2(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE).getHtml()).withUndefinedWidth();

            Component headerRightLayout = createHeaderRight();
            statusComboBox = new InvoiceStatusComboBox();
            MHorizontalLayout header = new MHorizontalLayout(new MHorizontalLayout(invoiceIcon, statusComboBox), headerRightLayout).withSpacing(false)
                    .withMargin(true).withFullWidth()
                    .withAlign(headerRightLayout, Alignment.MIDDLE_RIGHT);

            MHorizontalLayout bodyLayout = new MHorizontalLayout().withMargin(new MarginInfo(false, true, true, true));
            with(header, bodyLayout).expand(bodyLayout);

            invoiceListComp = new InvoiceListComp();
            InvoiceReadView invoiceReadView = new InvoiceReadView();
            bodyLayout.with(invoiceListComp, invoiceReadView).expand(invoiceReadView);
            statusComboBox.addValueChangeListener(valueChangeEvent -> {
                InvoiceStatus value = statusComboBox.getValue();
                displayInvoices(value);
            });
            displayInvoices(All);
        } else {
            this.with(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION))).alignAll(Alignment.MIDDLE_CENTER);
        }
    }

    private HorizontalLayout createHeaderRight() {
        MButton createBtn = new MButton(UserUIContext.getMessage(InvoiceI18nEnum.BUTTON_NEW_INVOICE), clickEvent -> {
            SimpleInvoice invoice = new SimpleInvoice();
            invoice.setSaccountid(AppUI.getAccountId());
            invoice.setProjectid(CurrentProjectVariables.getProjectId());
            invoice.setStatus(Scheduled.name());
            invoice.setCreateduser(UserUIContext.getUsername());
            UI.getCurrent().addWindow(new InvoiceAddWindow(invoice));
        }).withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION)
                .withClickShortcut(KeyCode.ENTER)
                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INVOICE));
        return new MHorizontalLayout(createBtn);
    }

    private void displayInvoices(InvoiceStatus status) {
        invoiceStatus = status;
        InvoiceSearchCriteria searchCriteria = new InvoiceSearchCriteria();
        if (status != All) {
            searchCriteria.addExtraField(InvoiceSearchCriteria.p_status.buildPropertyParamInList(SearchField.AND,
                    Sets.newHashSet(status.name())));
        }
        searchCriteria.addExtraField(InvoiceSearchCriteria.p_projectIds.buildPropertyParamInList(SearchField.AND,
                new HashSet<>(Collections.singletonList(CurrentProjectVariables.getProjectId()))));
        int count = invoiceListComp.setSearchCriteria(searchCriteria);
        if (count > 0) {
            SimpleInvoice invoice = invoiceListComp.getItemAt(0);
            if (invoice != null) {
                EventBusFactory.getInstance().post(new InvoiceEvent.DisplayInvoiceView(this, invoice));
            }
            Component firstRow = invoiceListComp.getRowAt(0);
            if (firstRow != null) {
                invoiceListComp.setSelectedRow(firstRow);
            }
        }
    }

    private void insertNewInvoiceAdded(SimpleInvoice invoice) {
        if (invoice.getStatus().equals(invoiceStatus.name()) || invoiceStatus.name().equals(All.name())) {
            Component newRow = invoiceListComp.insertRowAt(invoice, 0);
            EventBusFactory.getInstance().post(new InvoiceEvent.DisplayInvoiceView(this, invoice));
            invoiceListComp.setSelectedRow(newRow);
        }
    }

    private void updateInvoiceAdded(SimpleInvoice invoice) {
        if (invoice.getStatus().equals(invoiceStatus.name()) || invoiceStatus.name().equals(All.name())) {
            displayInvoices(invoiceStatus);
            EventBusFactory.getInstance().post(new InvoiceEvent.DisplayInvoiceView(this, invoice));
        }
    }

    private static class InvoiceListComp extends DefaultBeanPagedList<InvoiceService, InvoiceSearchCriteria, SimpleInvoice> {
        InvoiceListComp() {
            super(AppContextUtil.getSpringBean(InvoiceService.class), new InvoiceRowDisplayHandler(), Integer.MAX_VALUE);
            setWidth("250px");
            addStyleName(WebThemes.BORDER_LIST);
        }

        @Override
        protected String stringWhenEmptyList() {
            return UserUIContext.getMessage(InvoiceI18nEnum.OPT_NO_INVOICE);
        }
    }

    private static class InvoiceRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleInvoice> {
        @Override
        public Component generateRow(final IBeanList<SimpleInvoice> host, final SimpleInvoice invoice, int rowIndex) {
            final MVerticalLayout layout = new MVerticalLayout().withStyleName(WebThemes.BORDER_LIST_ROW)
                    .withStyleName(WebThemes.CURSOR_POINTER);
            InvoiceStatus invoiceStatus = InvoiceStatus.valueOf(invoice.getStatus());
            ELabel statusLbl = new ELabel(UserUIContext.getMessage(invoiceStatus)).withUndefinedWidth();
            if (invoiceStatus == Paid) {
                statusLbl.withStyleName("invoice", "paid");
            } else if (invoiceStatus == Scheduled) {
                statusLbl.withStyleName("invoice", "scheduled");
            } else if (invoiceStatus == Sent) {
                statusLbl.withStyleName("invoice", "sent");
            }
            ELabel headerLbl = new ELabel(invoice.getNoid());
            layout.addComponent(new MHorizontalLayout(statusLbl, headerLbl).expand(headerLbl));
            if (StringUtils.isNotBlank(invoice.getNote())) {
                Label noteLbl = new Label(invoice.getNote());
                layout.with(noteLbl);
            }
            layout.addLayoutClickListener(layoutClickEvent -> {
                EventBusFactory.getInstance().post(new InvoiceEvent.DisplayInvoiceView(this, invoice));
                ((AbstractBeanPagedList) host).setSelectedRow(layout);
            });
            return layout;
        }
    }

    private static class InvoiceStatusComboBox extends I18nValueComboBox<InvoiceStatus> {
        InvoiceStatusComboBox() {
            super(InvoiceStatus.class, All, Paid, Sent, Scheduled);
        }
    }

    private static class InvoiceReadView extends VerticalLayout {
        private ApplicationEventListener<InvoiceEvent.DisplayInvoiceView> displayInvoiceHandler = new
                ApplicationEventListener<InvoiceEvent.DisplayInvoiceView>() {
                    @Override
                    @Subscribe
                    public void handle(InvoiceEvent.DisplayInvoiceView event) {
                        SimpleInvoice invoice = event.getData();
                        showInvoice(invoice);
                    }
                };

        private AdvancedPreviewBeanForm<SimpleInvoice> previewForm;
        private ELabel headerLbl;
        private ProjectActivityComponent activityComponent;

        @Override
        public void attach() {
            EventBusFactory.getInstance().register(displayInvoiceHandler);
            super.attach();
        }

        @Override
        public void detach() {
            EventBusFactory.getInstance().unregister(displayInvoiceHandler);
            super.detach();
        }

        InvoiceReadView() {
            this.setMargin(new MarginInfo(false, false, false, true));
        }

        void showInvoice(final SimpleInvoice invoice) {
            removeAllComponents();
            MHorizontalLayout header = new MHorizontalLayout().withFullWidth();
            header.setDefaultComponentAlignment(Alignment.TOP_LEFT);
            addComponent(header);

            headerLbl = ELabel.h2("");

            PrintButton<SimpleInvoice> printBtn = new PrintButton<>();
            printBtn.setStyleName(WebThemes.BUTTON_OPTION);
            printBtn.setVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.INVOICE));
            printBtn.doPrint(invoice, new FormReportLayout(ProjectTypeConstants.INVOICE, Invoice.Field.noid.name(),
                    InvoiceDefaultFormLayoutFactory.getForm(), Invoice.Field.id.name()));

            MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                    clickEvent -> UI.getCurrent().addWindow(new InvoiceAddWindow(invoice)))
                    .withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.EDIT)
                    .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INVOICE));

            MButton deleteBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_DELETE), clickEvent -> {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                InvoiceService invoiceService = AppContextUtil.getSpringBean(InvoiceService.class);
                                invoiceService.removeWithSession(invoice, UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new InvoiceEvent.InvoiceDelete(this, invoice));
                            }
                        });
            }).withStyleName(WebThemes.BUTTON_DANGER).withIcon(VaadinIcons.TRASH)
                    .withVisible(CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.INVOICE));

            MHorizontalLayout buttonControls = new MHorizontalLayout(printBtn, editBtn, deleteBtn);
            header.with(headerLbl, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
            previewForm = new AdvancedPreviewBeanForm<>();
            addComponent(previewForm);

            activityComponent = new ProjectActivityComponent(ProjectTypeConstants.INVOICE, CurrentProjectVariables.getProjectId());
            addComponent(activityComponent);

            if (StringUtils.isBlank(invoice.getNote())) {
                headerLbl.setValue(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE).getHtml() + " " + invoice.getNoid());
            } else {
                headerLbl.setValue(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE).getHtml() + " " +
                        invoice.getNoid() + " - " + invoice.getNote());
            }

            previewForm.setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.INVOICE,
                    InvoiceDefaultFormLayoutFactory.getForm()));
            previewForm.setBeanFormFieldFactory(new InvoiceReadFormFieldFactory(previewForm));
            previewForm.setBean(invoice);

            activityComponent.loadActivities("" + invoice.getId());
        }
    }
}
