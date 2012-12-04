package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class ActivityCalendarViewImpl extends AbstractView implements
		ActivityCalendarView {
	private static final long serialVersionUID = 1L;

	private PopupButton calendarActionBtn;

	public ActivityCalendarViewImpl() {
		super();

		MenuActionListener listener = new MenuActionListener();

		calendarActionBtn = new PopupButton("Create");

		VerticalLayout actionBtnLayout = new VerticalLayout();
		actionBtnLayout.setMargin(true);
		actionBtnLayout.setSpacing(true);
		actionBtnLayout.setWidth("200px");

		actionBtnLayout.addComponent(new ButtonLink("Create Todo", listener));
		actionBtnLayout.addComponent(new ButtonLink("Create Call", listener));
		actionBtnLayout
				.addComponent(new ButtonLink("Create Meeting", listener));

		calendarActionBtn.addComponent(actionBtnLayout);

		this.addComponent(calendarActionBtn);

		Calendar calendar = new Calendar();
		this.addComponent(calendar);
	}

	private class MenuActionListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String caption = event.getButton().getCaption();
			if (caption.equals("Create Todo")) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.TaskAdd(this, null));
			} else if (caption.equals("Create Call")) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.CallAdd(this, null));
			} else if (caption.equals("Create Meeting")) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.MeetingAdd(this, null));
			}
		}
	}

}
