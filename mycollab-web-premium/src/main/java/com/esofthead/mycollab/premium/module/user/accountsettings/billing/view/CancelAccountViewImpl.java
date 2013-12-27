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
package com.esofthead.mycollab.premium.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.common.dao.CustomerFeedbackMapper;
import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class CancelAccountViewImpl extends AbstractPageView implements
		CancelAccountView {
	private static final long serialVersionUID = 1L;

	public CancelAccountViewImpl() {
		this.setWidth("100%");
		this.setMargin(true);
		this.setSpacing(true);
		HorizontalLayout header = createHeader();
		this.addComponent(header);
		this.setComponentAlignment(header, Alignment.TOP_CENTER);
		this.addComponent(createBody());
	}

	protected HorizontalLayout createHeader() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeUndefined();
		layout.setSpacing(true);
		layout.addComponent(new Embedded(null, MyCollabResource
				.newResource("icons/sad_face.png")));
		VerticalLayout header = new VerticalLayout();
		header.setSpacing(true);
		header.addStyleName("cancelAccountHeader");

		Label headerTopLine = new Label(
				LocalizationHelper
						.getMessage(UserI18nEnum.CANCEL_ACCOUNT_FIRST_LINE));
		headerTopLine.addStyleName("first-line");
		header.addComponent(headerTopLine);
		header.setComponentAlignment(headerTopLine, Alignment.MIDDLE_CENTER);

		Label headerMsg = new Label(
				LocalizationHelper
						.getMessage(UserI18nEnum.CANCEL_ACCOUNT_MESSAGE),
				ContentMode.HTML);
		headerMsg.addStyleName("header-content");
		header.addComponent(headerMsg);
		header.setComponentAlignment(headerMsg, Alignment.MIDDLE_CENTER);

		Label headerNote = new Label(
				LocalizationHelper.getMessage(UserI18nEnum.CANCEL_ACCOUNT_NOTE));
		headerNote.addStyleName("header-note");
		header.addComponent(headerNote);
		header.setComponentAlignment(headerNote, Alignment.MIDDLE_CENTER);
		layout.addComponent(header);

		return layout;
	}

	protected CssLayout createBody() {
		CssLayout layout = new CssLayout();
		layout.setWidth("100%");
		layout.addStyleName("cancelAccountBody");

		VerticalLayout innerLayout = new VerticalLayout();
		innerLayout.setSpacing(true);
		innerLayout.setMargin(true);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 4,
				"100%", "200px");

		layoutHelper.addComponentNoWrapper(new TextField(),
				"Why are you leaving us?", 0, 0);

		OptionGroup optionGroupField = new OptionGroup();
		optionGroupField
				.addItem("I'm cancelling this account to join or open a new MyCollab account");
		optionGroupField.addItem("I'm missing an important integration");
		optionGroupField
				.addItem("MyCollab doesn't have all the features I'm looking for");
		optionGroupField.addItem("Too expensive");
		optionGroupField.addItem("None of the above");

		layoutHelper.addComponentNoWrapper(optionGroupField,
				"Do any of these apply?", 0, 1);

		layoutHelper
				.addComponentNoWrapper(
						new TextField(),
						"Are you considering any other online CRM or sales tracking tools?",
						0, 2);

		layoutHelper.addComponentNoWrapper(new TextArea(),
				"What would it take to have you back?", 0, 3);

		layoutHelper.getLayout().setWidth("800px");
		layoutHelper.getLayout().setSpacing(true);
		innerLayout.addComponent(layoutHelper.getLayout());
		innerLayout.setComponentAlignment(layoutHelper.getLayout(),
				Alignment.MIDDLE_CENTER);

		Button submitBtn = new Button("Submit and delete my account",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// Save cancel account reason
						CustomerFeedbackMapper customerFeedbackMapper = ApplicationContextUtil
								.getSpringBean(CustomerFeedbackMapper.class);
						CustomerFeedbackWithBLOBs feedback = new CustomerFeedbackWithBLOBs();

						BillingService billingService = ApplicationContextUtil
								.getSpringBean(BillingService.class);
						billingService.cancelAccount(AppContext.getAccountId());
						UI.getCurrent().getPage()
								.setLocation("http://www.mycollab.com");
					}
				});
		submitBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

		Button cancelBtn = new Button("Never mind, go back",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new AccountBillingEvent.GotoSummary(
										CancelAccountViewImpl.this, null));
					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);

		HorizontalLayout formControls = new HorizontalLayout();
		formControls.setSpacing(true);
		formControls.setMargin(true);
		formControls.setSizeUndefined();
		formControls.addComponent(submitBtn);
		formControls.addComponent(cancelBtn);

		innerLayout.addComponent(formControls);
		innerLayout
				.setComponentAlignment(formControls, Alignment.MIDDLE_CENTER);

		Label confirmNote = new Label(
				LocalizationHelper
						.getMessage(UserI18nEnum.CANCEL_ACCOUNT_CONFIRM_NOTE),
				ContentMode.HTML);
		confirmNote.setWidth("600px");
		innerLayout.addComponent(confirmNote);
		innerLayout.setComponentAlignment(confirmNote, Alignment.TOP_CENTER);
		layout.addComponent(innerLayout);
		return layout;
	}
}
