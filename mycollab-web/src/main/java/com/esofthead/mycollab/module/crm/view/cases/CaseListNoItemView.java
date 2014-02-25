package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@ViewComponent
public class CaseListNoItemView extends AbstractPageView {
	private static final long serialVersionUID = 1L;

	public CaseListNoItemView() {
		/*VerticalLayout header = new  VerticalLayout();
		header.setMargin(new MarginInfo(true, false, false, false));
		header.setSpacing(true);
		header.setWidth("100%");
		*/
		
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("case-noitem");
		layout.setWidth("800px");
		layout.setSpacing(true);
		layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		layout.setMargin(true);
		
		Image image = new Image(null,
			   MyCollabResource.newResource("icons/48/crm/case.png"));
		layout.addComponent(image);
		
		Label title = new Label("Maintance Your Cases");
		title.addStyleName("h2");
		title.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		layout.addComponent(title);
		
		Label contact = new Label("Contact are the people in a company with whom you communicat and interact in pursuit of the business opportunity.");
		contact.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		layout.addComponent(contact);
		
		
		Button btCreateContact = new Button("Create Case", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new CaseEvent.GotoAdd(this, null));
			}
		});
		
		HorizontalLayout links = new HorizontalLayout();
		
		links.addComponent(btCreateContact);
		btCreateContact.addStyleName(UIConstants.THEME_BLUE_LINK);
		
		/*
		Label or = new Label("Or");
		or.setStyleName("h2");
		links.addComponent(or);
		
		Button btImportContact = new Button("Import Contacts", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				UI.getCurrent().addWindow(new CaseImportWindow());
			}
		});

		btImportContact.addStyleName(UIConstants.THEME_GRAY_LINK);
		
		
		links.addComponent(btImportContact);*/
		links.setSpacing(true);
		
		layout.addComponent(links);
		this.addComponent(layout);
		this.setComponentAlignment(layout, Alignment.TOP_CENTER);
	}
}
