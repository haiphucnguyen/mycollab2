package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RecurringActivityCustomField extends CustomField {
	private static final long serialVersionUID = 1L;
	private DateTimePicker<Meeting> startDate;
	private DateTimePicker<Meeting> endDate;
	private RepeatTypeComboBox repeatTypeComboBox;
	private VerticalLayout layout;
	private HorizontalLayout startDateHorizontalLayout;
	private HorizontalLayout endDateHorizontalLayout;
	private HorizontalLayout repeatTypeHorizontalLayout;
	private CheckBox checkbox;

	public RecurringActivityCustomField(final Meeting meeting) {
		layout = new VerticalLayout();
		layout.setSpacing(true);
		checkbox = new CheckBox();
		checkbox.setImmediate(true);
		checkbox.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				if ((Boolean) checkbox.getValue()) {
					startDateHorizontalLayout = new HorizontalLayout();
					startDateHorizontalLayout.setSpacing(true);
					Label startDateLbl = new Label("Start Date:");
					startDateLbl.addStyleName("label-padding-left-5px");
					startDateLbl.setWidth("100px");
					startDateHorizontalLayout.addComponent(startDateLbl);
					startDateHorizontalLayout.setComponentAlignment(
							startDateLbl, Alignment.MIDDLE_LEFT);
					startDate = new DateTimePicker<Meeting>("startdate",
							meeting);
					startDateHorizontalLayout.addComponent(startDate);
					startDateHorizontalLayout.setComponentAlignment(startDate,
							Alignment.MIDDLE_LEFT);

					endDateHorizontalLayout = new HorizontalLayout();
					endDateHorizontalLayout.setSpacing(true);
					Label endDateLbl = new Label("End Date:");
					endDateLbl.addStyleName("label-padding-left-5px");
					endDateLbl.setWidth("100px");
					endDateHorizontalLayout.addComponent(endDateLbl);
					endDateHorizontalLayout.setComponentAlignment(endDateLbl,
							Alignment.MIDDLE_LEFT);
					endDate = new DateTimePicker<Meeting>("enddate", meeting);
					endDateHorizontalLayout.addComponent(endDate);
					endDateHorizontalLayout.setComponentAlignment(endDate,
							Alignment.MIDDLE_LEFT);

					repeatTypeHorizontalLayout = new HorizontalLayout();
					repeatTypeHorizontalLayout.setSpacing(true);
					Label repeatTypeLbl = new Label("Repeat type:");
					repeatTypeLbl.addStyleName("label-padding-left-5px");
					repeatTypeLbl.setWidth("100px");
					repeatTypeHorizontalLayout.addComponent(repeatTypeLbl);
					repeatTypeHorizontalLayout.setComponentAlignment(
							repeatTypeLbl, Alignment.MIDDLE_LEFT);
					repeatTypeComboBox = new RepeatTypeComboBox(meeting);
					repeatTypeHorizontalLayout.addComponent(repeatTypeComboBox);
					repeatTypeHorizontalLayout.setComponentAlignment(
							repeatTypeComboBox, Alignment.MIDDLE_LEFT);

					layout.addComponent(startDateHorizontalLayout);
					layout.addComponent(endDateHorizontalLayout);
					layout.addComponent(repeatTypeHorizontalLayout);
					layout.addComponent(repeatTypeComboBox
							.getLayoutForCurrentRepeatType());
				} else {
					layout.removeComponent(startDateHorizontalLayout);
					layout.removeComponent(endDateHorizontalLayout);
					layout.removeComponent(repeatTypeHorizontalLayout);
					layout.removeComponent(repeatTypeComboBox
							.getLayoutForCurrentRepeatType());
				}
			}
		});
		layout.addComponent(checkbox);
		this.setCompositionRoot(layout);
	}

	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}

	private class RepeatTypeComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;
		private String[] REPEATTYPEDATA = new String[] { "Daily", "Weekly",
				"Monthly" };

		private VerticalLayout layoutForCurrentRepeatType;

		public RepeatTypeComboBox(final Meeting meeting) {
			super();
			setCaption(null);
			this.setNullSelectionAllowed(false);
			this.setImmediate(true);
			this.setWidth("129px");
			this.loadData(REPEATTYPEDATA);
			this.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if (arg0.getProperty().getValue().equals("Daily")) {
						RecurringActivityCustomField.this.layout
								.removeComponent(layoutForCurrentRepeatType);
						layoutForCurrentRepeatType = constructLayoutForDaily(meeting);
						RecurringActivityCustomField.this.layout
								.addComponent(layoutForCurrentRepeatType);
					} else if (arg0.getProperty().getValue().equals("Weekly")) {
						RecurringActivityCustomField.this.layout
								.removeComponent(layoutForCurrentRepeatType);
						layoutForCurrentRepeatType = constructLayoutForWeekly(meeting);
						RecurringActivityCustomField.this.layout
								.addComponent(layoutForCurrentRepeatType);
					} else if (arg0.getProperty().getValue().equals("Monthly")) {
						RecurringActivityCustomField.this.layout
								.removeComponent(layoutForCurrentRepeatType);
						layoutForCurrentRepeatType = constructLayoutForMonthly(meeting);
						RecurringActivityCustomField.this.layout
								.addComponent(layoutForCurrentRepeatType);
					}
				}
			});
			layoutForCurrentRepeatType = constructLayoutForDaily(meeting);
		}

		private VerticalLayout constructLayoutForDaily(final Meeting meeting) {
			final CheckBox repeatFromInputDaycheckbOx = new CheckBox();
			repeatFromInputDaycheckbOx.setImmediate(true);
			repeatFromInputDaycheckbOx.setValue(false);

			HorizontalLayout horLayoutRepeatEveryDay = new HorizontalLayout();
			horLayoutRepeatEveryDay.setSpacing(true);
			final CheckBox everydaycheckBox = new CheckBox();
			everydaycheckBox.setImmediate(true);
			everydaycheckBox.setValue(false);
			everydaycheckBox.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) repeatFromInputDaycheckbOx.getValue()) {
						everydaycheckBox.setValue(true);
						repeatFromInputDaycheckbOx.setValue(false);
						// TODO;
					}
				}
			});
			horLayoutRepeatEveryDay.addComponent(everydaycheckBox);
			horLayoutRepeatEveryDay.setComponentAlignment(everydaycheckBox,
					Alignment.MIDDLE_LEFT);
			Label lblrow1 = new Label("Every day");
			horLayoutRepeatEveryDay.addComponent(lblrow1);
			horLayoutRepeatEveryDay.setComponentAlignment(lblrow1,
					Alignment.MIDDLE_LEFT);

			HorizontalLayout horizontalRepeatFromInputDay = new HorizontalLayout();
			repeatFromInputDaycheckbOx.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) everydaycheckBox.getValue()) {
						everydaycheckBox.setValue(false);
						// TODO;
					}
				}
			});
			horizontalRepeatFromInputDay
					.addComponent(repeatFromInputDaycheckbOx);
			horizontalRepeatFromInputDay.setComponentAlignment(
					repeatFromInputDaycheckbOx, Alignment.MIDDLE_LEFT);
			horizontalRepeatFromInputDay.setSpacing(true);
			Label lablerow2 = new Label("Repeat for every ");
			horizontalRepeatFromInputDay.addComponent(lablerow2);
			horizontalRepeatFromInputDay.setComponentAlignment(lablerow2,
					Alignment.MIDDLE_LEFT);
			TextField inputRepeatTextField = new TextField();
			inputRepeatTextField.setWidth("50px");
			horizontalRepeatFromInputDay.addComponent(inputRepeatTextField);
			horizontalRepeatFromInputDay.setComponentAlignment(
					inputRepeatTextField, Alignment.MIDDLE_LEFT);
			Label dayLbl = new Label("days");
			horizontalRepeatFromInputDay.addComponent(dayLbl);
			horizontalRepeatFromInputDay.setComponentAlignment(dayLbl,
					Alignment.MIDDLE_LEFT);

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			layout.setHeight("50px");
			layout.addComponent(horLayoutRepeatEveryDay);
			layout.addComponent(horizontalRepeatFromInputDay);
			return layout;
		}

		private VerticalLayout constructLayoutForWeekly(final Meeting meeting) {
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			HorizontalLayout repeatWeekHorizontalLayout = new HorizontalLayout();
			repeatWeekHorizontalLayout.setSpacing(true);
			Label lbl = new Label("Repeat for every");
			lbl.setWidth("100px");
			lbl.addStyleName("label-padding-left-5px");
			repeatWeekHorizontalLayout.addComponent(lbl);
			repeatWeekHorizontalLayout.setComponentAlignment(lbl,
					Alignment.MIDDLE_LEFT);
			TextField tf = new TextField();
			tf.setWidth("50px");
			repeatWeekHorizontalLayout.addComponent(tf);
			repeatWeekHorizontalLayout.setComponentAlignment(tf,
					Alignment.MIDDLE_LEFT);
			Label lblWeek = new Label("weeks");
			repeatWeekHorizontalLayout.addComponent(lblWeek);
			repeatWeekHorizontalLayout.setComponentAlignment(lblWeek,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(repeatWeekHorizontalLayout);

			HorizontalLayout weeklydatepickerHorizontalLayout = new HorizontalLayout();
			weeklydatepickerHorizontalLayout.setSpacing(true);
			layout.addComponent(weeklydatepickerHorizontalLayout);

			HorizontalLayout sundayLayout = new HorizontalLayout();
			CheckBox sundayck = new CheckBox();
			Label sunLbl = new Label("Sunday");
			sundayLayout.addComponent(sundayck);
			sundayLayout.addComponent(sunLbl);
			sundayLayout.setComponentAlignment(sunLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(sundayLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					sundayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout mondayLayout = new HorizontalLayout();
			CheckBox mondayck = new CheckBox();
			Label mondayLbl = new Label("Monday");
			mondayLayout.addComponent(mondayck);
			mondayLayout.addComponent(mondayLbl);
			mondayLayout
					.setComponentAlignment(mondayLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(mondayLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					mondayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout tuedayLayout = new HorizontalLayout();
			CheckBox tuedayck = new CheckBox();
			Label tuedayLbl = new Label("Tuesday");
			tuedayLayout.addComponent(tuedayck);
			tuedayLayout.addComponent(tuedayLbl);
			tuedayLayout
					.setComponentAlignment(tuedayLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(tuedayLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					tuedayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout wednesLayout = new HorizontalLayout();
			CheckBox wednesCk = new CheckBox();
			Label wednesLbl = new Label("Wednesday");
			wednesLayout.addComponent(wednesCk);
			wednesLayout.addComponent(wednesLbl);
			wednesLayout
					.setComponentAlignment(wednesLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(wednesLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					wednesLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout weeklydatepickerHorizontalLayoutRow2 = new HorizontalLayout();
			weeklydatepickerHorizontalLayoutRow2.setSpacing(true);
			layout.addComponent(weeklydatepickerHorizontalLayoutRow2);

			HorizontalLayout thursLayout = new HorizontalLayout();
			CheckBox thursCk = new CheckBox();
			Label thurLbl = new Label("Thursday");
			thursLayout.addComponent(thursCk);
			thursLayout.addComponent(thurLbl);
			thursLayout.setComponentAlignment(thurLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayoutRow2.addComponent(thursLayout);
			weeklydatepickerHorizontalLayoutRow2.setComponentAlignment(
					thursLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout fridayLayout = new HorizontalLayout();
			CheckBox fridayCk = new CheckBox();
			Label friLbl = new Label("Friday");
			fridayLayout.addComponent(fridayCk);
			fridayLayout.addComponent(friLbl);
			fridayLayout.setComponentAlignment(friLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayoutRow2.addComponent(fridayLayout);
			weeklydatepickerHorizontalLayoutRow2.setComponentAlignment(
					fridayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout staturdayLayout = new HorizontalLayout();
			CheckBox staturdayCk = new CheckBox();
			Label staturdayLbl = new Label("Saturday");
			staturdayLayout.addComponent(staturdayCk);
			staturdayLayout.addComponent(staturdayLbl);
			staturdayLayout.setComponentAlignment(staturdayLbl,
					Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayoutRow2.addComponent(staturdayLayout);
			weeklydatepickerHorizontalLayoutRow2.setComponentAlignment(
					staturdayLayout, Alignment.MIDDLE_LEFT);

			return layout;
		}

		private VerticalLayout constructLayoutForMonthly(final Meeting meeting) {
			final CheckBox followKindDateCk = new CheckBox();
			followKindDateCk.setImmediate(true);
			followKindDateCk.setValue(false);
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			HorizontalLayout opptionFollowDayLayout = new HorizontalLayout();
			opptionFollowDayLayout.setSpacing(true);
			final CheckBox followDayCheckbox = new CheckBox();
			followDayCheckbox.setValue(false);
			followDayCheckbox.setImmediate(true);
			followDayCheckbox.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) followKindDateCk.getValue()) {
						followKindDateCk.setValue(false);
						followDayCheckbox.setValue(true);
					}

				}
			});
			opptionFollowDayLayout.addComponent(followDayCheckbox);
			Label ondayLbl = new Label("On day");
			ondayLbl.setWidth("50px");
			opptionFollowDayLayout.addComponent(ondayLbl);
			opptionFollowDayLayout.setComponentAlignment(ondayLbl,
					Alignment.MIDDLE_LEFT);
			opptionFollowDayLayout.addComponent(new DateInMonth());
			Label ofEveryLbl = new Label("Of every");
			opptionFollowDayLayout.addComponent(ofEveryLbl);
			opptionFollowDayLayout.setComponentAlignment(ofEveryLbl,
					Alignment.MIDDLE_LEFT);
			TextField tf = new TextField();
			tf.setImmediate(true);
			tf.setWidth("50px");
			opptionFollowDayLayout.addComponent(tf);
			Label monthLbl = new Label("months");
			opptionFollowDayLayout.addComponent(monthLbl);
			opptionFollowDayLayout.setComponentAlignment(monthLbl,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(opptionFollowDayLayout);

			HorizontalLayout opptionFollowKindDateLayout = new HorizontalLayout();
			opptionFollowKindDateLayout.setSpacing(true);
			followKindDateCk.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) followDayCheckbox.getValue()) {
						followDayCheckbox.setValue(false);
						followKindDateCk.setValue(true);
					}
				}
			});
			opptionFollowKindDateLayout.addComponent(followKindDateCk);
			Label onLbel = new Label("On");
			onLbel.setWidth("50px");
			opptionFollowKindDateLayout.addComponent(onLbel);
			opptionFollowKindDateLayout.setComponentAlignment(onLbel,
					Alignment.MIDDLE_LEFT);

			ValueComboBox sttComboBox = new ValueComboBox();
			sttComboBox.setCaption(null);
			sttComboBox.setWidth("60px");
			sttComboBox.setNullSelectionAllowed(false);
			sttComboBox.loadData(new String[] { "First", "Second", "Third",
					"Fourth", "Fifth" });

			opptionFollowKindDateLayout.addComponent(sttComboBox);
			opptionFollowKindDateLayout.addComponent(new KindDateInWeek());
			Label ofEveryLbl2 = new Label("Of every");
			opptionFollowKindDateLayout.addComponent(ofEveryLbl2);
			opptionFollowKindDateLayout.setComponentAlignment(ofEveryLbl2,
					Alignment.MIDDLE_LEFT);
			TextField tfMonths = new TextField();
			tfMonths.setImmediate(true);
			tfMonths.setWidth("50px");
			opptionFollowKindDateLayout.addComponent(tfMonths);
			Label monthLbl2 = new Label("months");
			opptionFollowKindDateLayout.addComponent(monthLbl2);
			opptionFollowKindDateLayout.setComponentAlignment(monthLbl2,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(opptionFollowKindDateLayout);

			return layout;
		}

		public VerticalLayout getLayoutForCurrentRepeatType() {
			return layoutForCurrentRepeatType;
		}

		private class DateInMonth extends ValueComboBox {
			private static final long serialVersionUID = 1L;
			private String[] DATEINMONTHDATA = new String[] { "1", "2", "3",
					"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
					"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30", "31" };

			public DateInMonth() {
				super();
				setCaption(null);
				this.setWidth("60px");
				this.loadData(DATEINMONTHDATA);
			}
		}

		private class KindDateInWeek extends ValueComboBox {
			private static final long serialVersionUID = 1L;
			private String[] KINDDATEINWEEK = new String[] { "Sunday",
					"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
					"Saturday" };

			public KindDateInWeek() {
				super();
				setCaption(null);
				this.setWidth("90px");
				this.loadData(KINDDATEINWEEK);
			}
		}

	}

}
