package com.mycollab.module.crm.ui.format;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.formatter.HistoryFieldFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class AccountHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(AccountHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        return toString(value, true, UserUIContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (StringUtils.isBlank(value)) {
            return msgIfBlank;
        }

        try {
            Integer accountId = Integer.parseInt(value);
            AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
            SimpleAccount account = accountService.findById(accountId, AppUI.getAccountId());

            if (account != null) {
                if (displayAsHtml) {
                    A link = new A().setId("tag" + TOOLTIP_ID);
                    link.setHref(CrmLinkBuilder.generateAccountPreviewLinkFull(accountId))
                            .appendChild(new Text(account.getAccountname()));
                    link.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(CrmTypeConstants.INSTANCE.getACCOUNT(), accountId + ""));
                    link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
                    Div div = new DivLessFormatter().appendChild(new Text(CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getACCOUNT()).getHtml()),
                            DivLessFormatter.EMPTY_SPACE(), link);
                    return div.write();
                } else {
                    return account.getAccountname();
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }

        return value;
    }
}
