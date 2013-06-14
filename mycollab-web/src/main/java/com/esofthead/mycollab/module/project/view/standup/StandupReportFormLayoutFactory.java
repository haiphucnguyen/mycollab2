package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class StandupReportFormLayoutFactory implements
		IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private LazyLoadWrapper whatTodayField;

	private LazyLoadWrapper whatYesterdayField;

	private LazyLoadWrapper whatProblemField;

	private final String title;

	public StandupReportFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout reportAddLayout = new AddViewLayout(this.title,
				new ThemeResource("icons/24/project/standup.png"));

		reportAddLayout.addTopControls(this.createTopPanel());

		final HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSpacing(true);
		mainLayout.setWidth("100%");

		final VerticalLayout layoutField = new VerticalLayout();
		layoutField.addStyleName("standup-edit-layout");
		layoutField.setWidth("500px");
		final Label whatYesterdayLbl = new Label(
				"What I did in the last day/week");
		whatYesterdayLbl.setStyleName("h2");
		layoutField.addComponent(whatYesterdayLbl);
		this.whatYesterdayField = new LazyLoadWrapper();
		layoutField.addComponent(this.whatYesterdayField);

		final Label whatTodayLbl = new Label("What I will do today/week");
		whatTodayLbl.setStyleName("h2");
		layoutField.addComponent(whatTodayLbl);
		this.whatTodayField = new LazyLoadWrapper();
		layoutField.addComponent(this.whatTodayField);

		final Label roadblockLbl = new Label(
				"Do you have roadblocks? If you have questions or you need help, please write your questions or needs here");
		roadblockLbl.addStyleName("h2");
		roadblockLbl.addStyleName(UIConstants.WORD_WRAP);
		layoutField.addComponent(roadblockLbl);
		this.whatProblemField = new LazyLoadWrapper();
		layoutField.addComponent(this.whatProblemField);

		mainLayout.addComponent(layoutField);

		final VerticalLayout instructionLayout = new VerticalLayout();
		instructionLayout.setStyleName("instructionStandupBox");
		instructionLayout.setSpacing(true);

		final Label instruct1Lbl = new Label(
				"A stand-up meeting (or simply \"stand-up\") is a daily team-meeting held to provide a status update to the team members. The \"semi-real-time\" status allows participants to know about potential challenges as well as to coordinate efforts to resolve difficult and/or time-consuming issues.");

		instruct1Lbl.addStyleName(UIConstants.WORD_WRAP);
		instructionLayout.addComponent(instruct1Lbl);

		final Label instruct1Lbl2 = new Label(
				"With MyCollab standup meeting page, you do not need to attend a real time meeting involve all project members at once, this tool is convenient for distributed team works in different timezone where people can put his standup report at their appropriate time.");
		instruct1Lbl2.addStyleName(UIConstants.WORD_WRAP);
		instructionLayout.addComponent(instruct1Lbl2);

		if (ScreenSize.hasSupport1280Pixels()) {
			instructionLayout.setWidth("300px");
			instructionLayout.setHeight("300px");
		} else if (ScreenSize.hasSupport1024Pixels()) {
			instructionLayout.setWidth("150px");
		}

		instruct1Lbl.setWidth("85%");
		instruct1Lbl2.setWidth("85%");

		mainLayout.addComponent(instructionLayout);
		mainLayout.setComponentAlignment(instructionLayout,
				Alignment.MIDDLE_CENTER);

		reportAddLayout.addBody(mainLayout);

		reportAddLayout.addBottomControls(this.createBottomPanel());

		return reportAddLayout;
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		if (propertyId.equals("whatlastday")) {
			this.whatYesterdayField.setLazyLoadComponent(field);
		} else if (propertyId.equals("whattoday")) {
			this.whatTodayField.setLazyLoadComponent(field);
		} else if (propertyId.equals("whatproblem")) {
			this.whatProblemField.setLazyLoadComponent(field);
		}
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();
}
