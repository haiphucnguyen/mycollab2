/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.mycollab.common.i18n.ShellI18nEnum;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.mycollab.ondemand.module.billing.service.BillingService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.WebResourceIds;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class CancelAccountViewImpl extends AbstractPageView implements CancelAccountView {
    private static final long serialVersionUID = 1L;

    public CancelAccountViewImpl() {
        this.withSpacing(true).withMargin(true).withFullWidth();
        ComponentContainer header = createHeader();
        this.with(header, createBody()).withAlign(header, Alignment.TOP_CENTER);
    }

    protected ComponentContainer createHeader() {
        MVerticalLayout header = new MVerticalLayout().withWidth("-1px");
        header.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        ELabel headerTopLine = ELabel.h2(AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_FIRST_LINE)).withWidthUndefined();

        Label headerMsg = ELabel.html(AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_MESSAGE));

        ELabel headerNote = new ELabel(AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_NOTE))
                .withStyleName(UIConstants.META_INFO).withWidthUndefined();

        header.with(new Image(null, new ExternalResource(StorageFactory.generateAssetRelativeLink(WebResourceIds._sad_face))),
                headerTopLine, headerMsg, headerNote);
        return header;
    }

    private CssLayout createBody() {
        CssLayout layout = new CssLayout();
        layout.setWidth("100%");

        MVerticalLayout innerLayout = new MVerticalLayout();
        innerLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        ELabel helpNoteLbl = ELabel.h3(AppContext.getMessage(BillingI18nEnum.OPT_FEEDBACK_TITLE)).withWidthUndefined();
        innerLayout.with(helpNoteLbl);

        GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 4);
        layoutHelper.getLayout().setWidth("800px");

        final TextArea whyLeaving = new TextArea();
        layoutHelper.addComponent(whyLeaving, AppContext.getMessage(BillingI18nEnum.OPT_WHY_YOU_LEAVE), 0, 0);

        final OptionGroup optionGroupField = new OptionGroup();
        optionGroupField.addItem(AppContext.getMessage(BillingI18nEnum.OPT_CANCEL_AND_OPEN_NEW_ACCOUNT));
        optionGroupField.addItem(AppContext.getMessage(BillingI18nEnum.OPT_MISSING_IMPORTANT_FEATURE));
        optionGroupField.addItem(AppContext.getMessage(BillingI18nEnum.OPT_TOO_EXPENSIVE));
        optionGroupField.addItem(AppContext.getMessage(BillingI18nEnum.OPT_NONE_OF_ABOVE));

        layoutHelper.addComponent(optionGroupField, AppContext.getMessage(BillingI18nEnum.OPT_ANY_APPLY), 0, 1);

        final TextArea alternativeTool = new TextArea();
        layoutHelper.addComponent(alternativeTool, AppContext.getMessage(BillingI18nEnum.OPT_CONSIDER_OTHER_TOOL), 0, 2);

        final TextArea reasonToBack = new TextArea();
        layoutHelper.addComponent(reasonToBack, AppContext.getMessage(BillingI18nEnum.OPT_HOW_TO_BACK), 0, 3);

        innerLayout.with(layoutHelper.getLayout());

        MButton submitBtn = new MButton(AppContext.getMessage(BillingI18nEnum.ACTION_SUBMIT_CANCEL_ACCOUNT), clickEvent -> {
            CustomerFeedbackWithBLOBs feedback = new CustomerFeedbackWithBLOBs();
            String whyLeavingMsg = whyLeaving.getValue();
            feedback.setUsername(AppContext.getUsername());
            feedback.setSaccountid(AppContext.getAccountId());
            feedback.setOthertool(alternativeTool.getValue());
            feedback.setReasontoback(reasonToBack.getValue());
            if (optionGroupField.getValue() != null) {
                feedback.setReasontoleave(optionGroupField.getValue().toString() + ": " + whyLeavingMsg);
            } else {
                feedback.setReasontoleave(whyLeavingMsg);
            }

            BillingService billingService = AppContextUtil.getSpringBean(BillingService.class);
            billingService.cancelAccount(AppContext.getAccountId(), feedback);
            UI.getCurrent().getPage().setLocation("https://www.mycollab.com");
        }).withStyleName(WebUIConstants.BUTTON_DANGER);

        MButton cancelBtn = new MButton(AppContext.getMessage(BillingI18nEnum.ACTION_NOT_CANCEL_ACCOUNT),
                clickEvent -> EventBusFactory.getInstance().post(new AccountBillingEvent.GotoSummary(this, null)))
                .withStyleName(WebUIConstants.BUTTON_ACTION);

        innerLayout.with(new MHorizontalLayout(submitBtn, cancelBtn).withMargin(new MarginInfo(false, true, false, true)));
        innerLayout.with(ELabel.html(AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_CONFIRM_NOTE)).withWidth("600px"));
        layout.addComponent(innerLayout);
        return layout;
    }
}
