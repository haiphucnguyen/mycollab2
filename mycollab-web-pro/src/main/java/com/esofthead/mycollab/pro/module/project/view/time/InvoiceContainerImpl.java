package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.esofthead.mycollab.module.project.events.InvoiceEvent;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.InvoiceService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.ProjectActivityComponent;
import com.esofthead.mycollab.module.project.ui.format.InvoiceFieldFormatter;
import com.esofthead.mycollab.module.project.view.time.IInvoiceContainer;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.*;
import com.esofthead.vaadin.floatingcomponent.FloatingComponent;
import com.google.common.eventbus.Subscribe;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class InvoiceContainerImpl extends AbstractPageView implements IInvoiceContainer {

    private ApplicationEventListener<InvoiceEvent.NewInvoiceAdded> newInvoiceAddedHandler = new
            ApplicationEventListener<InvoiceEvent.NewInvoiceAdded>() {
                @Override
                @Subscribe
                public void handle(InvoiceEvent.NewInvoiceAdded event) {
                    SimpleInvoice newInvoice = (SimpleInvoice) event.getData();
                    updateNewInvoice(newInvoice);
                }
            };

    private String invoiceStatus;
    private InvoiceListComp invoiceListComp;
    private InvoiceReadView invoiceReadView;
    private InvoiceStatusComboBox statusComboBox;
    private InvoiceSearchCriteria searchCriteria;

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(newInvoiceAddedHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(newInvoiceAddedHandler);
        super.detach();
    }

    @Override
    public void display() {
        removeAllComponents();
        ELabel invoiceIcon = ELabel.fontIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE))
                .withStyleName(ValoTheme.LABEL_H2, ValoTheme.LABEL_NO_MARGIN).withWidthUndefined();

        Component headerRightLayout = createHeaderRight();
        statusComboBox = new InvoiceStatusComboBox();
        MHorizontalLayout header = new MHorizontalLayout(new MHorizontalLayout(invoiceIcon, statusComboBox), headerRightLayout).withSpacing(false)
                .withStyleName("hdr-view").withWidth("100%").withMargin(true)
                .withAlign(headerRightLayout, Alignment.MIDDLE_RIGHT);

        MHorizontalLayout bodyLayout = new MHorizontalLayout().withStyleName("hdr-view");
        bodyLayout.setId("invoice-body");
        with(header, bodyLayout).expand(bodyLayout);

        invoiceListComp = new InvoiceListComp();
        FloatingComponent floatingComponent = FloatingComponent.floatThis(invoiceListComp);
        floatingComponent.setContainerId("invoice-body");
        invoiceReadView = new InvoiceReadView();
        bodyLayout.with(invoiceListComp, invoiceReadView).expand(invoiceReadView);
        statusComboBox.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Object value = statusComboBox.getValue();
                displayInvoices((String) value);
            }
        });
        displayInvoices(OptionI18nEnum.InvoiceStatus.All.name());
    }

    private HorizontalLayout createHeaderRight() {
        MHorizontalLayout layout = new MHorizontalLayout();
        Button createBtn = new Button(AppContext.getMessage(InvoiceI18nEnum.BUTTON_NEW_INVOICE), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                SimpleInvoice invoice = new SimpleInvoice();
                invoice.setSaccountid(AppContext.getAccountId());
                invoice.setProjectid(CurrentProjectVariables.getProjectId());
                invoice.setStatus(OptionI18nEnum.InvoiceStatus.Scheduled.name());
                UI.getCurrent().addWindow(new InvoiceAddWindow(invoice));
            }
        });
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setStyleName(UIConstants.BUTTON_ACTION);
        createBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INVOICE));
        layout.with(createBtn);
        return layout;
    }

    private void displayInvoices(String status) {
        invoiceStatus = status;
        searchCriteria = new InvoiceSearchCriteria();
        if (!OptionI18nEnum.InvoiceStatus.All.name().equals(status)) {
            searchCriteria.addExtraField(InvoiceSearchCriteria.p_status().buildPropertyParamInList(SearchField.AND,
                    Arrays.asList(status)));
        }
        searchCriteria.addExtraField(InvoiceSearchCriteria.p_projectIds().buildPropertyParamInList(SearchField.AND,
                Arrays.asList(CurrentProjectVariables.getProjectId())));
        int count = invoiceListComp.setSearchCriteria(searchCriteria);
        statusComboBox.setItemCaption(status, status + " (" + count + ")");
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

    private void updateNewInvoice(SimpleInvoice invoice) {
        if (invoice.getStatus().equals(invoiceStatus) || invoiceStatus.equals(OptionI18nEnum.InvoiceStatus.All.name())) {
            Component newRow = invoiceListComp.insertRowAt(invoice, 0);
            EventBusFactory.getInstance().post(new InvoiceEvent.DisplayInvoiceView(this, invoice));
            invoiceListComp.setSelectedRow(newRow);
        }
    }

    private static class InvoiceListComp extends DefaultBeanPagedList<InvoiceService, InvoiceSearchCriteria, SimpleInvoice> {
        InvoiceListComp() {
            super(ApplicationContextUtil.getSpringBean(InvoiceService.class), new InvoiceRowDisplayHandler(), Integer.MAX_VALUE);
            setWidth("300px");
            new Restrain(this).setMinHeight("50px").setMaxHeight((Page.getCurrent()
                    .getBrowserWindowHeight() - 320) + "px");
            addStyleName(UIConstants.BORDER_LIST);
        }

        @Override
        protected String stringWhenEmptyList() {
            return "No item";
        }
    }

    private static class InvoiceRowDisplayHandler implements AbstractBeanPagedList.RowDisplayHandler<SimpleInvoice> {
        @Override
        public Component generateRow(final AbstractBeanPagedList host, final SimpleInvoice invoice, int rowIndex) {
            final MVerticalLayout layout = new MVerticalLayout().withStyleName(UIConstants.BORDER_LIST_ROW);
            Button invoiceLink = new ButtonLink(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE).getHtml()
                    + " " + invoice.getNoid() + " (" + AppContext.getMessage(OptionI18nEnum
                    .InvoiceStatus.class, invoice.getStatus()) + ")", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new InvoiceEvent.DisplayInvoiceView(this, invoice));
                    host.setSelectedRow(layout);
                }
            });
            invoiceLink.setCaptionAsHtml(true);
            layout.with(invoiceLink);
            if (StringUtils.isNotBlank(invoice.getNote())) {
                Label noteLbl = new Label(invoice.getNote());
                layout.with(noteLbl);
            }
            return layout;
        }
    }

    private static class InvoiceStatusComboBox extends I18nValueComboBox {
        public InvoiceStatusComboBox() {
            super();
            this.setNullSelectionAllowed(false);
            this.setCaption(null);
            this.loadData(Arrays.asList(OptionI18nEnum.InvoiceStatus.All, OptionI18nEnum.InvoiceStatus.Paid,
                    OptionI18nEnum.InvoiceStatus.Sent, OptionI18nEnum.InvoiceStatus.Scheduled));
        }
    }

    private static class InvoiceReadView extends VerticalLayout {
        private ApplicationEventListener<InvoiceEvent.DisplayInvoiceView> displayInvoiceHandler = new
                ApplicationEventListener<InvoiceEvent.DisplayInvoiceView>() {
                    @Override
                    @Subscribe
                    public void handle(InvoiceEvent.DisplayInvoiceView event) {
                        SimpleInvoice invoice = (SimpleInvoice) event.getData();
                        if (invoice != null) {
                            showInvoice(invoice);
                        }
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
            MHorizontalLayout header = new MHorizontalLayout().withSpacing(false).withWidth("100%");
            header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            addComponent(header);

            headerLbl = ELabel.h3("");
            headerLbl.setSizeUndefined();
            header.with(headerLbl);
            previewForm = new AdvancedPreviewBeanForm<>();
            addComponent(previewForm);

            activityComponent = new ProjectActivityComponent(ProjectTypeConstants.INVOICE, CurrentProjectVariables.getProjectId(),
                    InvoiceFieldFormatter.instance());
            addComponent(activityComponent);
        }

        void showInvoice(SimpleInvoice invoice) {
            if (StringUtils.isBlank(invoice.getNote())) {
                headerLbl.setValue(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE).getHtml() + " " + invoice.getNoid());
            } else {
                headerLbl.setValue(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE).getHtml() + " " +
                        invoice.getNoid() + " - " + invoice.getNote());
            }

            previewForm.setFormLayoutFactory(new DynaFormLayout(ProjectTypeConstants.INVOICE,
                    InvoiceDefaultFormLayoutFactory.getForm()));
            previewForm.setBeanFormFieldFactory(new InvoiceReadFormFieldFactory(previewForm));
            previewForm.setBean(invoice);

            activityComponent.loadActivities("" + invoice.getId());
        }
    }
}
