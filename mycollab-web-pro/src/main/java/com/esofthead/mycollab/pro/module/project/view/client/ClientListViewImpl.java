package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.i18n.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.base.MoreObjects;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientListViewImpl extends AbstractPageView implements ClientListView {

    private ClientSearchPanel accountSearchPanel;
    private CssLayout content;

    public ClientListViewImpl() {
        this.setMargin(true);
    }

    @Override
    public void display(AccountSearchCriteria searchCriteria) {
        accountSearchPanel = new ClientSearchPanel();
        this.addComponent(accountSearchPanel);

        content = new CssLayout();
        content.setSizeFull();
        content.addStyleName(UIConstants.FLEX_DISPLAY);
        this.addComponent(content);

        AccountService accountService = ApplicationContextUtil.getSpringBean(AccountService.class);
        List<SimpleAccount> clients = accountService.findPagableListByCriteria(new SearchRequest<>(searchCriteria, 0,
                Integer.MAX_VALUE));
        for (SimpleAccount client : clients) {
            content.addComponent(new ClientBlock(client));
        }
    }

    private static class ClientBlock extends VerticalLayout {
        ClientBlock(SimpleAccount client) {
            this.setWidth("400px");
            this.addStyleName("clientblock");
            this.addComponent(new ELabel(client.getAccountname()).withStyleName("header"));

            Div websiteDiv = new Div().appendText(AppContext.getMessage(AccountI18nEnum.FORM_WEBSITE) + ": " +
                    MoreObjects.firstNonNull(client.getWebsite(), "None"));
            this.addComponent(new ELabel(websiteDiv.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO));

            Div addressDiv = new Div().appendText(AppContext.getMessage(AccountI18nEnum.FORM_BILLING_ADDRESS) + ": "
                    + MoreObjects.firstNonNull(client.getBillingaddress(), "None") +
                    ", " + MoreObjects.firstNonNull(client.getCity(), "None") +
                    ", " + MoreObjects.firstNonNull(client.getBillingcountry(), "None"));
            this.addComponent(new ELabel(addressDiv.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO));
            Div assignUserDiv = new Div().appendText(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE) + " : ").
                    appendChild(new Img("", StorageFactory.getInstance().getAvatarPath(client.getAssignUserAvatarId(), 16)),
                            new A(AccountLinkGenerator.generatePreviewFullUserLink(AppContext.getSiteUrl(), client.getAssignuser())).
                                    appendText(client.getAssignUserFullName()));
            this.addComponent(new ELabel(assignUserDiv.write(), ContentMode.HTML).withStyleName(UIConstants.LABEL_META_INFO));
        }
    }
}
