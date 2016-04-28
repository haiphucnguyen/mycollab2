package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.i18n.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.events.ClientEvent;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsUtil;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.base.MoreObjects;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientListViewImpl extends AbstractPageView implements ClientListView {

    private AccountSearchCriteria searchCriteria;
    private ClientSearchPanel accountSearchPanel;
    private CssLayout content;

    public ClientListViewImpl() {
        this.withSpacing(true).withMargin(true);
        accountSearchPanel = new ClientSearchPanel();
        this.addComponent(accountSearchPanel);
        content = new CssLayout();
        content.setSizeFull();
        content.addStyleName(UIConstants.FLEX_DISPLAY);
        this.addComponent(content);
    }

    @Override
    public HasSearchHandlers<AccountSearchCriteria> getSearchHandlers() {
        return accountSearchPanel;
    }

    @Override
    public void display(AccountSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        content.removeAllComponents();
        AccountService accountService = ApplicationContextUtil.getSpringBean(AccountService.class);
        List<SimpleAccount> clients = accountService.findPagableListByCriteria(new BasicSearchRequest<>(searchCriteria, 0,
                Integer.MAX_VALUE));
        for (SimpleAccount client : clients) {
            content.addComponent(generateClientBlock(client));
        }
    }

    private Component generateClientBlock(final SimpleAccount client) {
        VerticalLayout blockContent = new VerticalLayout();
        blockContent.setStyleName("member-block");
        blockContent.setWidth("350px");

        MHorizontalLayout buttonControls = new MHorizontalLayout();
        Button editBtn = new Button("", FontAwesome.EDIT);
        editBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBusFactory.getInstance().post(new ClientEvent.GotoEdit(this, client));
            }
        });
        editBtn.setVisible(AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));
        editBtn.setDescription("Edit client '" + client.getAccountname() + "' information");
        editBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
        blockContent.addComponent(editBtn);
        blockContent.setComponentAlignment(editBtn, Alignment.TOP_RIGHT);

        Button deleteBtn = new Button("", FontAwesome.TRASH_O);
        deleteBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppContext.getSiteName()),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                        AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                        new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    AccountService accountService = ApplicationContextUtil.getSpringBean(AccountService.class);
                                    accountService.removeWithSession(client, AppContext.getUsername(), AppContext.getAccountId());
                                    EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
                                }
                            }
                        });
            }
        });
        deleteBtn.setDescription("Remove client '" + client.getAccountname() + "'");
        deleteBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
        deleteBtn.setVisible(AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));

        buttonControls.with(editBtn, deleteBtn);
        blockContent.addComponent(buttonControls);
        blockContent.setComponentAlignment(buttonControls, Alignment.TOP_RIGHT);

        MHorizontalLayout blockTop = new MHorizontalLayout().withWidth("100%");
        Component clientAvatar = ProjectAssetsUtil.buildClientLogo(client, 100);
        blockTop.addComponent(clientAvatar);

        A clientLink = new A(ProjectLinkBuilder.generateClientPreviewFullLink(client.getId())).appendText(client
                .getAccountname()).setTitle(client.getAccountname());
        ELabel clientLinkLbl = ELabel.h3(clientLink.write()).withStyleName(UIConstants.TEXT_ELLIPSIS).withWidth("100%");

        MVerticalLayout clientInfo = new MVerticalLayout().withMargin(false).with(clientLinkLbl, ELabel.hr());
        Div websiteDiv = new Div().appendText(AppContext.getMessage(AccountI18nEnum.FORM_WEBSITE) + ": " +
                MoreObjects.firstNonNull(client.getWebsite(), "None"));
        clientInfo.addComponent(new ELabel(websiteDiv.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO));

        Div addressDiv = new Div().appendText(AppContext.getMessage(AccountI18nEnum.FORM_BILLING_ADDRESS) + ": "
                + MoreObjects.firstNonNull(client.getBillingaddress(), "None") +
                ", " + MoreObjects.firstNonNull(client.getCity(), "None") +
                ", " + MoreObjects.firstNonNull(client.getBillingcountry(), "None"));
        clientInfo.addComponent(new ELabel(addressDiv.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO));
        Div assignUserDiv = new Div().appendText(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE) + " : ").
                appendChild(new Img("", StorageFactory.getInstance().getAvatarPath(client.getAssignUserAvatarId(), 16)).setCSSClass(UIConstants.CIRCLE_BOX),
                        new A(AccountLinkGenerator.generatePreviewFullUserLink(AppContext.getSiteUrl(), client.getAssignuser())).
                                appendText(client.getAssignUserFullName()));
        clientInfo.addComponent(new ELabel(assignUserDiv.write(), ContentMode.HTML).withStyleName(UIConstants
                .LABEL_META_INFO, UIConstants.TEXT_ELLIPSIS));
        Div numProjectsDiv = new Div().appendText("Num projects: " + client.getNumProjects());
        clientInfo.addComponent(new ELabel(numProjectsDiv.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO));

        blockTop.with(clientInfo).expand(clientInfo);
        blockContent.addComponent(blockTop);
        return blockContent;
    }
}
