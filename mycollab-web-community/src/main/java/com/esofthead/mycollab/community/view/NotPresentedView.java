package com.esofthead.mycollab.community.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class NotPresentedView extends AbstractView {
	private static final long serialVersionUID = 1L;

	public NotPresentedView() {
		this.setHeight("370px");
		this.setWidth("100%");
		VerticalLayout layoutWapper = new VerticalLayout();
		layoutWapper.setWidth("100%");

		VerticalLayout layout = new VerticalLayout();
		final Embedded titleIcon = new Embedded();
		titleIcon.setSource(MyCollabResource
				.newResource("icons/not_present.png"));
		layout.addComponent(titleIcon);
		layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_CENTER);

		Label label = new Label("Module is not presented for community edition");
		label.setStyleName("h2_community");
		layout.addComponent(label);
		layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

		layoutWapper.addComponent(layout);
		layoutWapper.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
		this.addComponent(layoutWapper);
		this.setComponentAlignment(layoutWapper, Alignment.MIDDLE_CENTER);
	}
}
