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
package com.esofthead.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountBillingEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

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
		this.withSpacing(true).withMargin(true).withWidth("100%");
		HorizontalLayout header = createHeader();
		this.addComponent(header);
		this.setComponentAlignment(header, Alignment.TOP_CENTER);
		this.addComponent(createBody());
	}

	protected HorizontalLayout createHeader() {
		MHorizontalLayout layout = new MHorizontalLayout();
		layout.setSizeUndefined();
		layout.addComponent(new Embedded(null, MyCollabResource
				.newResource("icons/sad_face.png")));
		VerticalLayout header = new VerticalLayout();
		header.setSpacing(true);
		header.addStyleName("cancelAccountHeader");

		Label headerTopLine = new Label(
				AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_FIRST_LINE));
		headerTopLine.addStyleName("first-line");
		header.addComponent(headerTopLine);
		header.setComponentAlignment(headerTopLine, Alignment.MIDDLE_CENTER);

		Label headerMsg = new Label(
				AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_MESSAGE),
				ContentMode.HTML);
		headerMsg.addStyleName("header-content");
		header.addComponent(headerMsg);
		header.setComponentAlignment(headerMsg, Alignment.MIDDLE_CENTER);

		Label headerNote = new Label(
				AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_NOTE));
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

		MVerticalLayout innerLayout = new MVerticalLayout();

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

		final OptionGroup optionGroup = (OptionGroup)layoutHelper.addComponentNoWrapper(optionGroupField,
				"Do any of these apply?", 0, 1);

		final TextField alternativeTool = (TextField)layoutHelper.addComponentNoWrapper(new TextField(),
				"Are you considering any other alternative tools?", 0, 2);

		final TextArea reasonToBack = (TextArea)layoutHelper.addComponentNoWrapper(new TextArea(),
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
						CustomerFeedbackWithBLOBs feedback = new CustomerFeedbackWithBLOBs();
						feedback.setOthertool(alternativeTool.getValue());
						feedback.setReasontoback(reasonToBack.getValue());
						if (optionGroup.getValue() != null) {
							feedback.setReasontoleave(optionGroup.getValue().toString());
						} else {
							feedback.setReasontoleave("");
						}

						BillingService billingService = ApplicationContextUtil
								.getSpringBean(BillingService.class);
						billingService.cancelAccount(AppContext.getAccountId(), feedback);
						UI.getCurrent().getPage()
								.setLocation("https://www.mycollab.com");
					}
				});
		submitBtn.addStyleName(UIConstants.THEME_RED_LINK);

		Button cancelBtn = new Button("Never mind, go back",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBusFactory.getInstance().post(
								new AccountBillingEvent.GotoSummary(
										CancelAccountViewImpl.this, null));
					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

		MHorizontalLayout formControls = new MHorizontalLayout().withMargin(true);
		formControls.setSizeUndefined();
		formControls.with(submitBtn, cancelBtn);

		innerLayout.addComponent(formControls);
		innerLayout
				.setComponentAlignment(formControls, Alignment.MIDDLE_CENTER);

		Label confirmNote = new Label(
				AppContext.getMessage(UserI18nEnum.CANCEL_ACCOUNT_CONFIRM_NOTE),
				ContentMode.HTML);
		confirmNote.setWidth("600px");
		innerLayout.addComponent(confirmNote);
		innerLayout.setComponentAlignment(confirmNote, Alignment.TOP_CENTER);
		layout.addComponent(innerLayout);
		return layout;
	}
}
