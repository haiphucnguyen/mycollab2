package com.esofthead.mycollab.module.crm.view.activity;

import com.vaadin.ui.CustomField;

import com.esofthead.mycollab.core.schedule.recurring.DailyEvent;
import com.esofthead.mycollab.core.schedule.recurring.MonthlyEventFollowDay;
import com.esofthead.mycollab.core.schedule.recurring.MonthlyEventFollowKindDay;
import com.esofthead.mycollab.core.schedule.recurring.WeeklyEvent;
import com.esofthead.mycollab.core.schedule.recurring.YearlyEventFollowAdvanceSettingMonth;
import com.esofthead.mycollab.core.schedule.recurring.YearlyEventFollowEveryMonth;
import com.esofthead.mycollab.core.utils.JsonDeSerializer;
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs;
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
	private DateTimePicker<MeetingWithBLOBs> startDate;
	private DateTimePicker<MeetingWithBLOBs> endDate;
	private RepeatTypeComboBox repeatTypeComboBox;
	private VerticalLayout layout;
	private HorizontalLayout startDateHorizontalLayout;
	private HorizontalLayout endDateHorizontalLayout;
	private HorizontalLayout repeatTypeHorizontalLayout;
	private CheckBox checkbox;

	public RecurringActivityCustomField(final MeetingWithBLOBs meeting) {
		layout = new VerticalLayout();
		layout.setSpacing(true);
		checkbox = new CheckBox();
		checkbox.setImmediate(true);
		checkbox.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				if ((Boolean) checkbox.getValue()) {
					meeting.setIsrecurrence(true);
					startDateHorizontalLayout = new HorizontalLayout();
					startDateHorizontalLayout.setSpacing(true);
					Label startDateLbl = new Label("Start Date:");
					startDateLbl.addStyleName("label-padding-left-5px");
					startDateLbl.setWidth("100px");
					startDateHorizontalLayout.addComponent(startDateLbl);
					startDateHorizontalLayout.setComponentAlignment(
							startDateLbl, Alignment.MIDDLE_LEFT);
					startDate = new DateTimePicker<MeetingWithBLOBs>(
							"recurrencestartdate", meeting);
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
					endDate = new DateTimePicker<MeetingWithBLOBs>(
							"recurrenceenddate", meeting);
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
					meeting.setIsrecurrence(false);
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
				"Monthly", "Yearly" };

		private VerticalLayout layoutForCurrentRepeatType;

		public RepeatTypeComboBox(final MeetingWithBLOBs meeting) {
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
					} else if (arg0.getProperty().getValue().equals("Yearly")) {
						RecurringActivityCustomField.this.layout
								.removeComponent(layoutForCurrentRepeatType);
						layoutForCurrentRepeatType = constructLayoutForYearly(meeting);
						RecurringActivityCustomField.this.layout
								.addComponent(layoutForCurrentRepeatType);
					}
				}
			});
			layoutForCurrentRepeatType = constructLayoutForDaily(meeting);
		}

		private VerticalLayout constructLayoutForDaily(
				final MeetingWithBLOBs meeting) {
			meeting.setRecurrencetype("DailyEvent");
			final DailyEvent dailyEvent = new DailyEvent();
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
					repeatFromInputDaycheckbOx.setValue(false);
					if ((Boolean) everydaycheckBox.getValue()) {
						dailyEvent.setRepeatInDay(1);
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(dailyEvent));
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
			horizontalRepeatFromInputDay
					.addComponent(repeatFromInputDaycheckbOx);
			horizontalRepeatFromInputDay.setComponentAlignment(
					repeatFromInputDaycheckbOx, Alignment.MIDDLE_LEFT);
			horizontalRepeatFromInputDay.setSpacing(true);
			Label lablerow2 = new Label("Repeat for every ");
			horizontalRepeatFromInputDay.addComponent(lablerow2);
			horizontalRepeatFromInputDay.setComponentAlignment(lablerow2,
					Alignment.MIDDLE_LEFT);
			final TextField inputRepeatTextField = new TextField();
			inputRepeatTextField.setWidth("50px");
			inputRepeatTextField.setValue("1");
			inputRepeatTextField.setImmediate(true);
			inputRepeatTextField.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					try {
						dailyEvent.setRepeatInDay(Integer
								.parseInt((String) inputRepeatTextField
										.getValue()));
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(dailyEvent));
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});
			horizontalRepeatFromInputDay.addComponent(inputRepeatTextField);
			horizontalRepeatFromInputDay.setComponentAlignment(
					inputRepeatTextField, Alignment.MIDDLE_LEFT);
			repeatFromInputDaycheckbOx.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					try {
						everydaycheckBox.setValue(false);
						if ((Boolean) repeatFromInputDaycheckbOx.getValue()) {
							Integer day = Integer
									.parseInt((String) inputRepeatTextField
											.getValue());
							dailyEvent.setRepeatInDay(day);
							meeting.setRecurrenceinfo(JsonDeSerializer
									.toJson(dailyEvent));
						}
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});

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

		private VerticalLayout constructLayoutForWeekly(
				final MeetingWithBLOBs meeting) {
			final WeeklyEvent weeklyEvent = new WeeklyEvent();
			meeting.setRecurrencetype("WeeklyEvent");
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
			final TextField tf = new TextField();
			tf.setWidth("50px");
			tf.setImmediate(true);
			tf.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					try {
						weeklyEvent.setRepeatInWeekNum(Integer
								.parseInt((String) tf.getValue()));
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});

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
			final CheckBox sundayck = new CheckBox();
			sundayck.setImmediate(true);
			sundayck.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) sundayck.getValue()) {
						weeklyEvent.setHappenInSunday(true);
					} else {
						weeklyEvent.setHappenInSunday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});
			Label sunLbl = new Label("Sunday");
			sundayLayout.addComponent(sundayck);
			sundayLayout.addComponent(sunLbl);
			sundayLayout.setComponentAlignment(sunLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(sundayLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					sundayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout mondayLayout = new HorizontalLayout();
			final CheckBox mondayck = new CheckBox();
			mondayck.setImmediate(true);
			mondayck.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) mondayck.getValue()) {
						weeklyEvent.setHappenInMonday(true);
					} else {
						weeklyEvent.setHappenInMonday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});

			Label mondayLbl = new Label("Monday");
			mondayLayout.addComponent(mondayck);
			mondayLayout.addComponent(mondayLbl);
			mondayLayout
					.setComponentAlignment(mondayLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(mondayLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					mondayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout tuedayLayout = new HorizontalLayout();
			final CheckBox tuedayck = new CheckBox();
			tuedayck.setImmediate(true);
			tuedayck.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) tuedayck.getValue()) {
						weeklyEvent.setHappenInTuesday(true);
					} else {
						weeklyEvent.setHappenInTuesday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});
			Label tuedayLbl = new Label("Tuesday");
			tuedayLayout.addComponent(tuedayck);
			tuedayLayout.addComponent(tuedayLbl);
			tuedayLayout
					.setComponentAlignment(tuedayLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayout.addComponent(tuedayLayout);
			weeklydatepickerHorizontalLayout.setComponentAlignment(
					tuedayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout wednesLayout = new HorizontalLayout();
			final CheckBox wednesCk = new CheckBox();
			wednesCk.setImmediate(true);
			wednesCk.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) wednesCk.getValue()) {
						weeklyEvent.setHappenInWednesday(true);
					} else {
						weeklyEvent.setHappenInWednesday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});
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
			final CheckBox thursCk = new CheckBox();
			thursCk.setImmediate(true);
			thursCk.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) thursCk.getValue()) {
						weeklyEvent.setHappenInThursday(true);
					} else {
						weeklyEvent.setHappenInThursday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});
			Label thurLbl = new Label("Thursday");
			thursLayout.addComponent(thursCk);
			thursLayout.addComponent(thurLbl);
			thursLayout.setComponentAlignment(thurLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayoutRow2.addComponent(thursLayout);
			weeklydatepickerHorizontalLayoutRow2.setComponentAlignment(
					thursLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout fridayLayout = new HorizontalLayout();
			final CheckBox fridayCk = new CheckBox();
			fridayCk.setImmediate(true);
			fridayCk.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) fridayCk.getValue()) {
						weeklyEvent.setHappenInFriday(true);
					} else {
						weeklyEvent.setHappenInFriday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});
			Label friLbl = new Label("Friday");
			fridayLayout.addComponent(fridayCk);
			fridayLayout.addComponent(friLbl);
			fridayLayout.setComponentAlignment(friLbl, Alignment.MIDDLE_LEFT);
			weeklydatepickerHorizontalLayoutRow2.addComponent(fridayLayout);
			weeklydatepickerHorizontalLayoutRow2.setComponentAlignment(
					fridayLayout, Alignment.MIDDLE_LEFT);

			HorizontalLayout staturdayLayout = new HorizontalLayout();
			final CheckBox staturdayCk = new CheckBox();
			staturdayCk.setImmediate(true);
			staturdayCk.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					if ((Boolean) staturdayCk.getValue()) {
						weeklyEvent.setHappenInSaturday(true);
					} else {
						weeklyEvent.setHappenInSaturday(false);
					}
					meeting.setRecurrenceinfo(JsonDeSerializer
							.toJson(weeklyEvent));
				}
			});
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

		private VerticalLayout constructLayoutForMonthly(
				final MeetingWithBLOBs meeting) {
			final MonthlyEventFollowDay monthDayEvent = new MonthlyEventFollowDay();
			final MonthlyEventFollowKindDay monthKindDayEvent = new MonthlyEventFollowKindDay();
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
			opptionFollowDayLayout.addComponent(followDayCheckbox);
			Label ondayLbl = new Label("On day");
			ondayLbl.setWidth("50px");
			opptionFollowDayLayout.addComponent(ondayLbl);
			opptionFollowDayLayout.setComponentAlignment(ondayLbl,
					Alignment.MIDDLE_LEFT);
			final DateInMonth dateInMonthComboBox = new DateInMonth();
			dateInMonthComboBox.setImmediate(true);
			dateInMonthComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) followDayCheckbox.getValue()) {
						monthDayEvent.setNumday(Integer
								.parseInt((String) dateInMonthComboBox
										.getValue()));
						meeting.setRecurrencetype("MonthlyEventFollowDay");
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(monthDayEvent));
					}
				}
			});
			opptionFollowDayLayout.addComponent(dateInMonthComboBox);
			Label ofEveryLbl = new Label("Of every");
			opptionFollowDayLayout.addComponent(ofEveryLbl);
			opptionFollowDayLayout.setComponentAlignment(ofEveryLbl,
					Alignment.MIDDLE_LEFT);
			final TextField tf = new TextField();
			tf.setImmediate(true);
			tf.setWidth("50px");
			tf.setValue("1");
			tf.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					try {
						if ((Boolean) followDayCheckbox.getValue()) {
							monthDayEvent.setMonthStep(Integer
									.parseInt((String) tf.getValue()));
							meeting.setRecurrencetype("MonthlyEventFollowDay");
							meeting.setRecurrenceinfo(JsonDeSerializer
									.toJson(monthDayEvent));
						}
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});
			followDayCheckbox.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					followKindDateCk.setValue(false);
					try {
						if ((Boolean) followDayCheckbox.getValue()) {
							meeting.setRecurrencetype("MonthlyEventFollowDay");
							monthDayEvent.setNumday(Integer
									.parseInt((String) dateInMonthComboBox
											.getValue()));
							monthDayEvent.setMonthStep(Integer
									.parseInt((String) (tf.getValue())));
							meeting.setRecurrenceinfo(JsonDeSerializer
									.toJson(monthDayEvent));
						}
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});
			opptionFollowDayLayout.addComponent(tf);
			Label monthLbl = new Label("months");
			opptionFollowDayLayout.addComponent(monthLbl);
			opptionFollowDayLayout.setComponentAlignment(monthLbl,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(opptionFollowDayLayout);

			HorizontalLayout opptionFollowKindDateLayout = new HorizontalLayout();
			opptionFollowKindDateLayout.setSpacing(true);
			opptionFollowKindDateLayout.addComponent(followKindDateCk);
			Label onLbel = new Label("On");
			onLbel.setWidth("50px");
			opptionFollowKindDateLayout.addComponent(onLbel);
			opptionFollowKindDateLayout.setComponentAlignment(onLbel,
					Alignment.MIDDLE_LEFT);

			final ValueComboBox sttComboBox = new ValueComboBox();
			sttComboBox.setCaption(null);
			sttComboBox.setWidth("60px");
			sttComboBox.setNullSelectionAllowed(false);
			sttComboBox.setImmediate(true);
			sttComboBox.loadData(new String[] { "First", "Second", "Third",
					"Fourth", "Fifth" });
			sttComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) followKindDateCk.getValue()) {
						if (arg0.getProperty().getValue().equals("First")) {
							monthKindDayEvent.setHappenIn(1);
						} else if (arg0.getProperty().getValue()
								.equals("Second")) {
							monthKindDayEvent.setHappenIn(2);
						} else if (arg0.getProperty().getValue()
								.equals("Third")) {
							monthKindDayEvent.setHappenIn(3);
						} else if (arg0.getProperty().getValue()
								.equals("Fourth")) {
							monthKindDayEvent.setHappenIn(4);
						} else if (arg0.getProperty().getValue()
								.equals("Fifth")) {
							monthKindDayEvent.setHappenIn(5);
						}
						meeting.setRecurrencetype("MonthlyEventFollowKindDay");
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(monthKindDayEvent));
					}
				}
			});

			opptionFollowKindDateLayout.addComponent(sttComboBox);

			final KindDateInWeek kindDateInWeek = new KindDateInWeek();
			kindDateInWeek.setImmediate(true);
			kindDateInWeek.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) followKindDateCk.getValue()) {
						monthKindDayEvent.setKindOfDay((String) kindDateInWeek
								.getValue());
						meeting.setRecurrencetype("MonthlyEventFollowKindDay");
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(monthKindDayEvent));
					}
				}
			});
			opptionFollowKindDateLayout.addComponent(kindDateInWeek);
			Label ofEveryLbl2 = new Label("Of every");
			opptionFollowKindDateLayout.addComponent(ofEveryLbl2);
			opptionFollowKindDateLayout.setComponentAlignment(ofEveryLbl2,
					Alignment.MIDDLE_LEFT);
			final TextField tfMonths = new TextField();
			tfMonths.setImmediate(true);
			tfMonths.setWidth("50px");
			tfMonths.setValue("1");
			tfMonths.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					try {
						if ((Boolean) followKindDateCk.getValue()) {
							monthKindDayEvent.setMonthStep(Integer
									.parseInt((String) tfMonths.getValue()));
							meeting.setRecurrencetype("MonthlyEventFollowKindDay");
							meeting.setRecurrenceinfo(JsonDeSerializer
									.toJson(monthKindDayEvent));
						}
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});
			followKindDateCk.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					followDayCheckbox.setValue(false);
					try {
						if ((Boolean) followKindDateCk.getValue()) {
							meeting.setRecurrencetype("MonthlyEventFollowKindDay");
							if (sttComboBox.getValue().equals("Frist")) {
								monthKindDayEvent.setHappenIn(1);
							} else if (sttComboBox.getValue().equals("Second")) {
								monthKindDayEvent.setHappenIn(2);
							} else if (sttComboBox.getValue().equals("Third")) {
								monthKindDayEvent.setHappenIn(3);
							} else if (sttComboBox.getValue().equals("Fourth")) {
								monthKindDayEvent.setHappenIn(4);
							} else if (sttComboBox.getValue().equals("Fifth")) {
								monthKindDayEvent.setHappenIn(5);
							}
							monthKindDayEvent
									.setKindOfDay((String) kindDateInWeek
											.getValue());
							monthKindDayEvent.setMonthStep(Integer
									.parseInt((String) tfMonths.getValue()));
							meeting.setRecurrenceinfo(JsonDeSerializer
									.toJson(monthKindDayEvent));
						}
					} catch (Exception e) {
						RecurringActivityCustomField.this.getWindow()
								.showNotification("Please input numberic");
					}
				}
			});

			opptionFollowKindDateLayout.addComponent(tfMonths);
			Label monthLbl2 = new Label("months");
			opptionFollowKindDateLayout.addComponent(monthLbl2);
			opptionFollowKindDateLayout.setComponentAlignment(monthLbl2,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(opptionFollowKindDateLayout);

			return layout;
		}

		private VerticalLayout constructLayoutForYearly(
				final MeetingWithBLOBs meeting) {
			final YearlyEventFollowEveryMonth yearlyFollowEveryMonthEvent = new YearlyEventFollowEveryMonth();
			final YearlyEventFollowAdvanceSettingMonth yearlyAdSttFollowEveryMonthEvent = new YearlyEventFollowAdvanceSettingMonth();
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			final CheckBox kindMonthCheckBox = new CheckBox();
			kindMonthCheckBox.setImmediate(true);
			kindMonthCheckBox.setValue(false);
			final CheckBox kindAdvanceSettingMonthCheckBox = new CheckBox();
			kindAdvanceSettingMonthCheckBox.setImmediate(true);
			kindAdvanceSettingMonthCheckBox.setValue(false);

			HorizontalLayout kindMonthLayout = new HorizontalLayout();
			kindMonthLayout.setSpacing(true);
			kindMonthLayout.addComponent(kindMonthCheckBox);
			kindMonthLayout.setComponentAlignment(kindMonthCheckBox,
					Alignment.MIDDLE_LEFT);
			Label row1Labl = new Label("Of every");
			kindMonthLayout.addComponent(row1Labl);
			kindMonthLayout.setComponentAlignment(row1Labl,
					Alignment.MIDDLE_LEFT);
			final KindMonth kindMonthComboBox = new KindMonth();
			kindMonthComboBox.setImmediate(true);
			kindMonthComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) kindMonthCheckBox.getValue()) {
						yearlyFollowEveryMonthEvent
								.setMonth((String) kindMonthComboBox.getValue());
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyFollowEveryMonthEvent));
					}
				}
			});
			kindMonthLayout.addComponent(kindMonthComboBox);
			kindMonthLayout.setComponentAlignment(kindMonthComboBox,
					Alignment.MIDDLE_LEFT);
			final DateInMonth dateInMonth = new DateInMonth();
			dateInMonth.setImmediate(true);
			dateInMonth.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) kindMonthCheckBox.getValue()) {
						yearlyFollowEveryMonthEvent.setDay(Integer
								.parseInt((String) dateInMonth.getValue()));
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyFollowEveryMonthEvent));
					}
				}
			});
			kindMonthLayout.addComponent(dateInMonth);
			kindMonthLayout.setComponentAlignment(dateInMonth,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(kindMonthLayout);

			kindMonthCheckBox.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					kindAdvanceSettingMonthCheckBox.setValue(false);
					if ((Boolean) kindMonthCheckBox.getValue()) {
						meeting.setRecurrencetype("YearlyEventFollowEveryMonth");
						yearlyFollowEveryMonthEvent.setDay(Integer
								.parseInt((String) dateInMonth.getValue()));
						yearlyFollowEveryMonthEvent
								.setMonth((String) kindMonthComboBox.getValue());
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyFollowEveryMonthEvent));
					}
				}
			});
			HorizontalLayout kindAdvanceSettingMonthLayout = new HorizontalLayout();
			kindAdvanceSettingMonthLayout.setSpacing(true);
			kindAdvanceSettingMonthLayout
					.addComponent(kindAdvanceSettingMonthCheckBox);
			kindAdvanceSettingMonthLayout.setComponentAlignment(
					kindAdvanceSettingMonthCheckBox, Alignment.MIDDLE_LEFT);
			Label onLbl = new Label("On");
			kindAdvanceSettingMonthLayout.addComponent(onLbl);
			kindAdvanceSettingMonthLayout.setComponentAlignment(onLbl,
					Alignment.MIDDLE_LEFT);

			final ValueComboBox sttComboBox = new ValueComboBox();
			sttComboBox.setCaption(null);
			sttComboBox.setWidth("60px");
			sttComboBox.setNullSelectionAllowed(false);
			sttComboBox.setImmediate(true);
			sttComboBox.loadData(new String[] { "First", "Second", "Third",
					"Fourth", "Fifth" });
			sttComboBox.setValue("First");
			sttComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) kindAdvanceSettingMonthCheckBox.getValue()) {
						if (arg0.getProperty().getValue().equals("First")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(1);
						} else if (arg0.getProperty().getValue()
								.equals("Second")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(2);
						} else if (arg0.getProperty().getValue()
								.equals("Third")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(3);
						} else if (arg0.getProperty().getValue()
								.equals("Fourth")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(4);
						} else if (arg0.getProperty().getValue()
								.equals("Fifth")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(5);
						}
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyAdSttFollowEveryMonthEvent));
					}
				}
			});
			kindAdvanceSettingMonthLayout.addComponent(sttComboBox);
			kindAdvanceSettingMonthLayout.setComponentAlignment(sttComboBox,
					Alignment.MIDDLE_LEFT);
			final KindDateInWeek kindInDate = new KindDateInWeek();
			kindInDate.setImmediate(true);
			kindInDate.setNullSelectionAllowed(false);
			kindInDate.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) kindAdvanceSettingMonthCheckBox.getValue()) {
						yearlyAdSttFollowEveryMonthEvent
								.setKindOfDay((String) kindInDate.getValue());
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyAdSttFollowEveryMonthEvent));
					}
				}
			});
			kindAdvanceSettingMonthLayout.addComponent(kindInDate);
			kindAdvanceSettingMonthLayout.setComponentAlignment(kindInDate,
					Alignment.MIDDLE_LEFT);
			Label ofLbl = new Label("of");
			kindAdvanceSettingMonthLayout.addComponent(ofLbl);
			kindAdvanceSettingMonthLayout.setComponentAlignment(ofLbl,
					Alignment.MIDDLE_LEFT);

			final KindMonth kindMonthRow2 = new KindMonth();
			kindMonthRow2.setImmediate(true);
			kindMonthRow2.setNullSelectionAllowed(false);
			kindMonthRow2.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent arg0) {
					if ((Boolean) kindAdvanceSettingMonthCheckBox.getValue()) {
						yearlyAdSttFollowEveryMonthEvent
								.setMonth((String) kindMonthRow2.getValue());
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyAdSttFollowEveryMonthEvent));
					}
				}
			});
			kindAdvanceSettingMonthCheckBox.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent arg0) {
					kindMonthCheckBox.setValue(false);
					if ((Boolean) kindAdvanceSettingMonthCheckBox.getValue()) {
						meeting.setRecurrencetype("YearlyEventFollowAdvanceSettingMonth");
						yearlyAdSttFollowEveryMonthEvent
								.setMonth((String) kindMonthRow2.getValue());
						yearlyAdSttFollowEveryMonthEvent
								.setKindOfDay((String) kindInDate.getValue());
						System.out.print(sttComboBox.getValue());
						if (((String) sttComboBox.getValue()).equals("First")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(1);
						} else if (sttComboBox.getValue().equals("Second")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(2);
						} else if (sttComboBox.getValue().equals("Third")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(3);
						} else if (sttComboBox.getValue().equals("Fourth")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(4);
						} else if (sttComboBox.getValue().equals("Fifth")) {
							yearlyAdSttFollowEveryMonthEvent.setHappenIn(5);
						}
						meeting.setRecurrenceinfo(JsonDeSerializer
								.toJson(yearlyAdSttFollowEveryMonthEvent));
					}
				}
			});

			kindAdvanceSettingMonthLayout.addComponent(kindMonthRow2);
			kindAdvanceSettingMonthLayout.setComponentAlignment(kindMonthRow2,
					Alignment.MIDDLE_LEFT);
			layout.addComponent(kindAdvanceSettingMonthLayout);

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
				this.setNullSelectionAllowed(false);
				this.setWidth("60px");
				this.loadData(DATEINMONTHDATA);
				this.setValue("1");
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
				this.setNullSelectionAllowed(false);
				this.setWidth("90px");
				this.loadData(KINDDATEINWEEK);
				this.setValue("Sunday");
			}
		}

		private class KindMonth extends ValueComboBox {
			private static final long serialVersionUID = 1L;
			private String[] DATA = new String[] { "January", "February",
					"March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };

			public KindMonth() {
				super();
				setCaption(null);
				this.setNullSelectionAllowed(false);
				this.setWidth("90px");
				this.loadData(DATA);
				this.setValue("January");
			}
		}

	}

}
