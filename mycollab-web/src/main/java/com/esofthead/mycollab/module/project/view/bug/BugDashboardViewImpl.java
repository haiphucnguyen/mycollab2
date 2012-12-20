package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class BugDashboardViewImpl extends AbstractView implements
		BugDashboardView {

	public BugDashboardViewImpl() {
		super();
		initUI();
	}

	private void initUI() {
		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		
		Label title = new Label("Bug Dashboard");
		title.setStyleName("h2");
		header.addComponent(title);
		header.setExpandRatio(title, 1.0f);
		
		Button createBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName("link");
		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		header.addComponent(createBtn);
		header.setComponentAlignment(createBtn, Alignment.MIDDLE_RIGHT);
		
		this.addComponent(header);
	}
}
