package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;

@ViewComponent
public class StandupListViewImpl extends AbstractView implements
		StandupListView {
	private static final long serialVersionUID = 1L;

	public StandupListViewImpl() {
		super();
		constructHeader();
	}

	private void constructHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true);
		header.setSpacing(true);
		header.setWidth("100%");

		Label titleLbl = new Label("StandUp Reports");
		titleLbl.addStyleName("h2");
		header.addComponent(titleLbl);
		header.setComponentAlignment(titleLbl, Alignment.MIDDLE_RIGHT);
		header.setExpandRatio(titleLbl, 1.0f);

		Button addNewReport = new Button("Add New Report",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new StandUpEvent.GotoAdd(
										StandupListViewImpl.class, null));

					}
				});
		addNewReport.setStyleName(UIConstants.THEME_BLUE_LINK);
		addNewReport.setIcon(new ThemeResource("icons/16/addRecord.png"));
		
		header.addComponent(addNewReport);

		this.addComponent(header);
	}
}
