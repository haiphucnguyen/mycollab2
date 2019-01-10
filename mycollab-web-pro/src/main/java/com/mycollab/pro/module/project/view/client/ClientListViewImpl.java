package com.mycollab.pro.module.project.view.client;

import com.google.common.base.MoreObjects;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.mycollab.common.domain.SimpleClient;
import com.mycollab.common.domain.criteria.ClientSearchCriteria;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.service.ClientService;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.module.user.AccountLinkGenerator;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.HasSearchHandlers;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
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
    public HasSearchHandlers<ClientSearchCriteria> getSearchHandlers() {
        return accountSearchPanel;
    }

    @Override
    public void display(ClientSearchCriteria searchCriteria) {
        content.removeAllComponents();
        ClientService accountService = AppContextUtil.getSpringBean(ClientService.class);
        List<SimpleClient> clients = (List<SimpleClient>) accountService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        for (SimpleClient client : clients) {
            content.addComponent(generateClientBlock(client));
        }
    }

    private Component generateClientBlock(final SimpleClient client) {
        MVerticalLayout blockContent = new MVerticalLayout().withStyleName("member-block").withWidth("350px").withUndefinedWidth()
                .withMargin(false).withSpacing(false);

        MButton editBtn = new MButton("", clickEvent -> EventBusFactory.getInstance().post(new ClientEvent.GotoEdit(this, client)))
                .withIcon(VaadinIcons.EDIT).withStyleName(WebThemes.BUTTON_ICON_ONLY)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.CLIENT));

        MButton deleteBtn = new MButton("", clickEvent -> {
            ConfirmDialogExt.show(UI.getCurrent(),
                    UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                    UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                    UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
                    UserUIContext.getMessage(GenericI18Enum.ACTION_NO),
                    confirmDialog -> {
                        if (confirmDialog.isConfirmed()) {
                            ClientService clientService = AppContextUtil.getSpringBean(ClientService.class);
                            clientService.removeWithSession(client, UserUIContext.getUsername(), AppUI.getAccountId());
                            EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
                        }
                    });
        }).withIcon(VaadinIcons.TRASH).withStyleName(WebThemes.BUTTON_ICON_ONLY)
                .withVisible(UserUIContext.canAccess(RolePermissionCollections.CLIENT));
        deleteBtn.setDescription(UserUIContext.getMessage(ClientI18nEnum.OPT_REMOVE_CLIENT, client.getName()));

        MHorizontalLayout buttonControls = new MHorizontalLayout(editBtn, deleteBtn);
        blockContent.addComponent(buttonControls);
        blockContent.setComponentAlignment(buttonControls, Alignment.TOP_RIGHT);

        MHorizontalLayout blockTop = new MHorizontalLayout().withFullWidth();
        Component clientAvatar = ProjectAssetsUtil.clientLogoComp(client, 100);
        blockTop.addComponent(clientAvatar);

        A clientLink = new A(ProjectLinkGenerator.generateClientPreviewLink(client.getId())).appendText(client
                .getName()).setTitle(client.getName());
        ELabel clientLinkLbl = ELabel.h3(clientLink.write()).withStyleName(UIConstants.TEXT_ELLIPSIS).withFullWidth();

        MVerticalLayout clientInfo = new MVerticalLayout().withMargin(false).with(clientLinkLbl, ELabel.hr());
        Div websiteDiv = new Div().appendText(UserUIContext.getMessage(ClientI18nEnum.FORM_WEBSITE) + ": " +
                MoreObjects.firstNonNull(client.getWebsite(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)));
        clientInfo.addComponent(ELabel.html(websiteDiv.write()).withStyleName(UIConstants.META_INFO));

        Div addressDiv = new Div().appendText(UserUIContext.getMessage(ClientI18nEnum.FORM_BILLING_ADDRESS) + ": "
                + MoreObjects.firstNonNull(client.getBillingaddress(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)) +
                ", " + MoreObjects.firstNonNull(client.getCity(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)) +
                ", " + MoreObjects.firstNonNull(client.getBillingcountry(), UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)));
        clientInfo.addComponent(new ELabel(addressDiv.write(), ContentMode.HTML).withStyleName(UIConstants.META_INFO));
        Div assignUserDiv = new Div().appendText(UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE) + " : ").
                appendChild(new Img("", StorageUtils.getAvatarPath(client.getAssignUserAvatarId(), 16)).setCSSClass(UIConstants.CIRCLE_BOX),
                        new A(AccountLinkGenerator.generateUserLink(client.getAssignuser())).
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
