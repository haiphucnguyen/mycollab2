package com.mycollab.pro.module.project.view.client;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.mycollab.common.TableViewField;
import com.mycollab.common.domain.SimpleClient;
import com.mycollab.common.domain.criteria.ClientSearchCriteria;
import com.mycollab.common.i18n.ClientI18nEnum.ClientIndustry;
import com.mycollab.common.i18n.ClientI18nEnum.ClientType;
import com.mycollab.common.service.ClientService;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.CheckBoxDecor;
import com.mycollab.vaadin.web.ui.UrlLink;
import com.mycollab.vaadin.web.ui.UserLink;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.List;

public class ClientTableDisplay extends DefaultPagedBeanTable<ClientService, ClientSearchCriteria, SimpleClient> {
    private static final long serialVersionUID = 1L;

    public ClientTableDisplay(List<TableViewField> displayColumns) {
        this(null, displayColumns);
    }

    public ClientTableDisplay(TableViewField requiredColumn, List<TableViewField> displayColumns) {
        this(null, requiredColumn, displayColumns);
    }

    public ClientTableDisplay(String viewId, TableViewField requiredColumn, List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(ClientService.class),
                SimpleClient.class, viewId, requiredColumn, displayColumns);

        addGeneratedColumn("selected", (source, itemId, columnId) -> {
            final SimpleClient account = getBeanByIndex(itemId);
            final CheckBoxDecor cb = new CheckBoxDecor("", account.isSelected());
            cb.addValueChangeListener(valueChangeEvent -> {
                fireSelectItemEvent(account);
                fireTableEvent(new TableClickEvent(ClientTableDisplay.this, account, "selected"));
            });
            account.setExtraData(cb);
            return cb;
        });

        addGeneratedColumn("email", (source, itemId, columnId) -> {
            SimpleClient account = getBeanByIndex(itemId);
            return ELabel.email(account.getEmail());
        });

        addGeneratedColumn("name", (source, itemId, columnId) -> {
            SimpleClient client = getBeanByIndex(itemId);
            A clientLink = new A(ProjectLinkGenerator.generateClientPreviewLink(client.getId())).appendText(client.getName());
            clientLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(ProjectTypeConstants.CLIENT,
                    client.getId() + ""));
            clientLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
            A url;
            if (StringUtils.isNotBlank(client.getWebsite())) {
                url = new A(client.getWebsite(), "_blank").appendText(client.getWebsite()).setCSSClass(UIConstants.META_INFO);
            } else {
                url = new A("").appendText("").setCSSClass(UIConstants.META_INFO);
            }
            Div accountDiv = new Div().appendChild(clientLink, new Br(), url);
            ELabel b = ELabel.html(accountDiv.write());
            return new MHorizontalLayout(ProjectAssetsUtil.clientLogoComp(client, 32), b)
                    .expand(b).alignAll(Alignment.MIDDLE_LEFT).withMargin(false);
        });

        addGeneratedColumn("assignUserFullName", (source, itemId, columnId) -> {
            SimpleClient account = getBeanByIndex(itemId);
            return new UserLink(account.getAssignuser(), account.getAssignUserAvatarId(), account.getAssignUserFullName());
        });

        addGeneratedColumn("industry", (source, itemId, columnId) -> {
            SimpleClient account = getBeanByIndex(itemId);
            return ELabel.i18n(account.getIndustry(), ClientIndustry.class);
        });

        addGeneratedColumn("type", (source, itemId, columnId) -> {
            SimpleClient account = getBeanByIndex(itemId);
            return ELabel.i18n(account.getType(), ClientType.class);
        });

        addGeneratedColumn("website", (source, itemId, columnId) -> {
            SimpleClient account = getBeanByIndex(itemId);
            if (account.getWebsite() != null) {
                return new UrlLink(account.getWebsite());
            } else {
                return new Label("");
            }
        });

        this.setWidth("100%");
    }
}
