package com.esofthead.mycollab.module.crm.view.lead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@ViewComponent
public class LeadConvertReadViewImpl extends AbstractPageView implements
		LeadConvertReadView {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(LeadConvertReadViewImpl.class);

	@Override
	public void displayConvertLeadInfo(SimpleLead lead) {
		this.removeAllComponents();

		AddViewLayout2 previewLayout = new AddViewLayout2(
				"You can not access the Lead, it is converted already",
				MyCollabResource.newResource("icons/22/crm/lead.png"));

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.addStyleName("main-info");
		Button gotoLeadListBtn = new Button("Go to Leads",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoList(this, null));
					}
				});
		gotoLeadListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(gotoLeadListBtn);

		previewLayout.addBody(buttonControls);

		VerticalLayout convertLeadInfoLayout = new VerticalLayout();
		previewLayout.addComponent(convertLeadInfoLayout);

		Label header = new Label("<h2>Conversion Details</h2>",
				ContentMode.HTML);
		convertLeadInfoLayout.addComponent(header);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 3);
		layoutHelper.getLayout().setWidth("100%");
		layoutHelper.getLayout().setMargin(false);
		layoutHelper.getLayout().addStyleName("colored-gridlayout");

		log.debug("Display associate account");
		layoutHelper.addComponent(new Label("Account"), "Account", 0, 0);

		log.debug("Display associate contact");
		layoutHelper.addComponent(new Label("Contact"), "Contact", 0, 1);

		log.debug("Display associate opportunity");
		layoutHelper
				.addComponent(new Label("Opportunity"), "Opportunity", 0, 2);

		convertLeadInfoLayout.addComponent(layoutHelper.getLayout());
		this.addComponent(previewLayout);

	}

}
