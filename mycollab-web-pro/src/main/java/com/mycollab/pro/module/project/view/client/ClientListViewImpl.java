package com.mycollab.pro.module.project.view.client;

import com.google.common.base.MoreObjects;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.i18n.AccountI18nEnum;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.module.user.AccountLinkGenerator;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientListViewImpl extends AbstractVerticalPageView implements ClientListView {

    private ClientSearchPanel accountSearchPanel;
    private MCssLayout content;

    public ClientListViewImpl() {
        this.withSpacing(true).withMargin(true);
        accountSearchPanel = new ClientSearchPanel();
        content = new MCssLayout().withStyleName(WebThemes.FLEX_DISPLAY);
        this.with(accountSearchPanel, content);
    }

    @Override
    public HasSearchHandlers<AccountSearchCriteria> getSearchHandlers() {
        return accountSearchPanel;
    }

    @Override
    public void display(AccountSearchCriteria searchCriteria) {
        content.removeAllComponents();
        AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
        List<SimpleAccount> clients = accountService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleAccount client : clients) {
            content.addComponent(generateClientBlock(client));
        }
    }

    private Component generateClientBlock(final SimpleAccount client) {
        MVerticalLayout blockContent = new MVerticalLayout().withStyleName("member-block").withWidth("350px").withHeightUndefined()
                .withMargin(false).withSpacing(false);

        MButton editBtn = new MButton("", clickEvent -> EventBusFactory.getInstance().post(new ClientEvent.GotoEdit(this, client)))
                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ICON_ONLY)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_ACCOUNT()));

        MButton deleteBtn = new MButton("", clickEvent -> {
            ConfirmDialogExt.show(UI.getCurrent(),
                    UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                    UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                    UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                    UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                    confirmDialog -> {
                        if (confirmDialog.isConfirmed()) {
                            AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                            accountService.removeWithSession(client, UserUIContext.getUsername(), AppUI.getAccountId());
                            EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
                        }
                    });
        }).withIcon(FontAwesome.TRASH_O).withStyleName(WebThemes.BUTTON_ICON_ONLY)
                .withVisible(UserUIContext.canAccess(RolePermissionCollections.INSTANCE.getCRM_ACCOUNT()));
        deleteBtn.setDescription(UserUIContext.getMessage(ClientI18nEnum.OPT_REMOVE_CLIENT, client.getAccountname()));

        MHorizontalLayout buttonControls = new MHorizontalLayout(editBtn, deleteBtn);
        blockContent.addComponent(buttonControls);
        blockContent.setComponentAlignment(buttonControls, Alignment.TOP_RIGHT);

        MHorizontalLayout blockTop = new MHorizontalLayout().withFullWidth();
        Component clientAvatar = ProjectAssetsUtil.clientLogoComp(client, 100);
        blockTop.addComponent(clientAvatar);

        A clientLink = new A(ProjectLinkBuilder.generateClientPreviewFullLink(client.getId())).appendText(client
                .getAccountname()).setTitle(client.getAccountname());
        ELabel clientLinkLbl = ELabel.h3(clientLink.write()).withStyleName(UIConstants.TEXT_ELLIPSIS).withFullWidth();

        MVerticalLayout clientInfo = new MVerticalLayout().withMargin(false).with(clientLinkLbl, ELabel.hr());
        Div websiteDiv = new Div().appendText(UserUIContext.getMessage(AccountI18nEnum.FORM_WEBSITE) + ": " +
                MoreObjects.firstNonNull(client.getWebsite(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)));
        clientInfo.addComponent(ELabel.html(websiteDiv.write()).withStyleName(UIConstants.META_INFO));

        Div addressDiv = new Div().appendText(UserUIContext.getMessage(AccountI18nEnum.FORM_BILLING_ADDRESS) + ": "
                + MoreObjects.firstNonNull(client.getBillingaddress(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)) +
                ", " + MoreObjects.firstNonNull(client.getCity(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)) +
                ", " + MoreObjects.firstNonNull(client.getBillingcountry(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)));
        clientInfo.addComponent(new ELabel(addressDiv.write(), ContentMode.HTML).withStyleName(UIConstants.META_INFO));
        Div assignUserDiv = new Div().appendText(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE) + " : ").
                appendChild(new Img("", StorageUtils.INSTANCE.getAvatarPath(client.getAssignUserAvatarId(), 16)).setCSSClass(UIConstants.CIRCLE_BOX),
                        new A(AccountLinkGenerator.generatePreviewFullUserLink(AppUI.getSiteUrl(), client.getAssignuser())).
                                appendText(client.getAssignUserFullName()));
        clientInfo.addComponent(new ELabel(assignUserDiv.write(), ContentMode.HTML).withStyleName(UIConstants.META_INFO,
                UIConstants.TEXT_ELLIPSIS));
        Div numProjectsDiv = new Div().appendText(UserUIContext.getMessage(ClientI18nEnum.OPT_NUM_PROJECTS, client.getNumProjects()));
        clientInfo.addComponent(new ELabel(numProjectsDiv.write(), ContentMode.HTML).withStyleName(UIConstants.META_INFO));

        blockTop.with(clientInfo).expand(clientInfo);
        blockContent.addComponent(blockTop);
        return blockContent;
    }
}
