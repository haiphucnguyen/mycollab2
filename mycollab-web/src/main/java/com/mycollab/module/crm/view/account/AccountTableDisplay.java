/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.account;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.mycollab.common.GridFieldMeta;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.crm.CrmLinkGenerator;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.i18n.OptionI18nEnum;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.module.crm.ui.components.CrmAssetsUtil;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.CheckBoxDecor;
import com.mycollab.vaadin.web.ui.UrlLink;
import com.mycollab.vaadin.web.ui.UserLink;
import com.mycollab.vaadin.web.ui.table.DefaultPagedGrid;
import com.vaadin.server.*;
import com.vaadin.shared.Registration;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.Renderer;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import org.vaadin.grid.cellrenderers.editoraware.CheckboxRenderer;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
// TODO
public class AccountTableDisplay extends DefaultPagedGrid<AccountService, AccountSearchCriteria, SimpleAccount> {
    private static final long serialVersionUID = 1L;

    public AccountTableDisplay(List<GridFieldMeta> displayColumns) {
        this(null, displayColumns);
    }

    public AccountTableDisplay(GridFieldMeta requiredColumn, List<GridFieldMeta> displayColumns) {
        this(null, requiredColumn, displayColumns);
    }

    public AccountTableDisplay(String viewId, GridFieldMeta requiredColumn, List<GridFieldMeta> displayColumns) {
        super(AppContextUtil.getSpringBean(AccountService.class),
                SimpleAccount.class, viewId, requiredColumn, displayColumns);

        addGeneratedColumn("selected", new CheckboxRenderer((Setter) (bean, fieldVal) -> {
            System.out.println("PRINT: " + bean + "=--" + fieldVal);
        }));
//        addGeneratedColumn("selected", (source, itemId, columnId) -> {
//            final SimpleAccount account = getBeanByIndex(itemId);
//            final CheckBoxDecor cb = new CheckBoxDecor("", account.isSelected());
//            cb.addValueChangeListener(valueChangeEvent -> {
//                fireSelectItemEvent(account);
//                fireTableEvent(new TableClickEvent(AccountTableDisplay.this, account, "selected"));
//            });
//            account.setExtraData(cb);
//            return cb;
//        });
//
//        addGeneratedColumn("email", (source, itemId, columnId) -> {
//            SimpleAccount account = getBeanByIndex(itemId);
//            return ELabel.email(account.getEmail());
//        });
//
//        addGeneratedColumn("accountname", (source, itemId, columnId) -> {
//            SimpleAccount account = getBeanByIndex(itemId);
//            A accountLink = new A(CrmLinkGenerator.generateAccountPreviewLink(account.getId())).appendText(account.getAccountname());
//            accountLink.setAttribute("onmouseover", TooltipHelper.crmHoverJsFunction(CrmTypeConstants.ACCOUNT,
//                    account.getId() + ""));
//            accountLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
//            A url;
//            if (StringUtils.isNotBlank(account.getWebsite())) {
//                url = new A(account.getWebsite(), "_blank").appendText(account.getWebsite()).setCSSClass(UIConstants.META_INFO);
//            } else {
//                url = new A("").appendText("").setCSSClass(UIConstants.META_INFO);
//            }
//            Div accountDiv = new Div().appendChild(accountLink, new Br(), url);
//            ELabel b = ELabel.html(accountDiv.write());
//            return new MHorizontalLayout(CrmAssetsUtil.accountLogoComp(account, 32), b)
//                    .expand(b).alignAll(Alignment.MIDDLE_LEFT).withMargin(false);
//        });
//
//        addGeneratedColumn("assignUserFullName", (source, itemId, columnId) -> {
//            SimpleAccount account = getBeanByIndex(itemId);
//            return new UserLink(account.getAssignuser(), account.getAssignUserAvatarId(), account.getAssignUserFullName());
//        });
//
//        addGeneratedColumn("industry", (source, itemId, columnId) -> {
//            SimpleAccount account = getBeanByIndex(itemId);
//            return ELabel.i18n(account.getIndustry(), OptionI18nEnum.AccountIndustry.class);
//        });
//
//        addGeneratedColumn("type", (source, itemId, columnId) -> {
//            SimpleAccount account = getBeanByIndex(itemId);
//            return ELabel.i18n(account.getType(), OptionI18nEnum.AccountType.class);
//        });
//
//        addGeneratedColumn("website", (source, itemId, columnId) -> {
//            SimpleAccount account = getBeanByIndex(itemId);
//            if (account.getWebsite() != null) {
//                return new UrlLink(account.getWebsite());
//            } else {
//                return new Label("");
//            }
//        });

        this.setWidth("100%");
    }
}
