package com.esofthead.mycollab.module.project.view.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.Gantt.ResizeEvent;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.Step.CaptionMode;
import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

class TaskGanttChart extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private Gantt gantt;
	private List<SimpleTask> taskList;
	List<Step> stepList;
	private NativeSelect reso;

	private DateField start;
	private DateField end;

	public TaskGanttChart() {
		constructGanttChart();
		Panel controls = createControls();
		this.addComponent(controls);
		this.addComponent(gantt);
	}

	@SuppressWarnings("unchecked")
	private void constructGanttChart() {
		stepList = new ArrayList<Step>();
		final ProjectTaskService taskService = ApplicationContextUtil
				.getSpringBean(ProjectTaskService.class);
		TaskSearchCriteria criteria = new TaskSearchCriteria();
		taskList = taskService
				.findPagableListByCriteria(new SearchRequest<TaskSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		gantt = new Gantt();
		gantt.setWidth(100, Unit.PERCENTAGE);
		gantt.setHeight(350, Unit.PIXELS);
		gantt.setResizableSteps(false);
		gantt.setMovableSteps(false);

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -7);

		gantt.setStartDate(cal.getTime());
		cal.add(Calendar.DATE, 37);
		gantt.setEndDate(cal.getTime());

		

		updateStepList();
	}

	private void updateStepList() {

		/* Clear current Gantt chart */
		if (!stepList.isEmpty()) {
			for (Step step : stepList) {
				gantt.removeStep(step);
			}
			stepList.clear();
		}

		/* Add steps */
		if (!taskList.isEmpty()) {

			for (SimpleTask task : taskList) {

				Date startDate = null;
				Date endDate = null;

				/* Check for date */
				if (task.getActualstartdate() != null) {
					startDate = task.getActualstartdate();
				} else if (task.getStartdate() != null) {
					startDate = task.getStartdate();
				}

				if (task.getDeadline() != null) {
					endDate = task.getDeadline();
				} else if (task.getActualenddate() != null) {
					endDate = task.getActualenddate();
				} else if (endDate == null && startDate != null) {
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(startDate);
					gc.add(Calendar.YEAR, 1);
					endDate = gc.getTime();
				}

				/* Add row block if both stardate and endate avalable */
				if (startDate != null && gantt.getStartDate().before(startDate)
						&& gantt.getEndDate().after(startDate)) {
					Step step = new Step();
					step.setCaption(tooltipGenerate(task));
					step.setCaptionMode(CaptionMode.HTML);
					step.setStartDate(startDate.getTime());
					step.setEndDate(endDate.getTime());

					/* Add style for row block */
					if (task.getPercentagecomplete() != null
							&& 100d == task.getPercentagecomplete()) {
						step.setBackgroundColor("7df968");
						step.setStyleName("completed");
					} else {
						if ("Pending".equals(task.getStatus())) {
							step.setBackgroundColor("e2f852");
						} else if ("Open".equals(task.getStatus())
								&& endDate.before(new GregorianCalendar()
										.getTime())) {
							step.setBackgroundColor("fb4e5a");
						}
					}

					stepList.add(step);
				}

			}
		}

		if (!stepList.isEmpty()) {
			for (Step step : stepList) {
				gantt.addStep(step);
			}
		}

	}

	private String tooltipGenerate(SimpleTask task) {
		String content = "";

		// --------------Item hidden div tooltip----------------
		String randomStrId = UUID.randomUUID().toString();
		String idDivSeverData = "projectOverViewserverdata" + randomStrId + "";
		String idToopTipDiv = "projectOverViewtooltip" + randomStrId + "";
		String idStickyToolTipDiv = "projectOverViewmystickyTooltip"
				+ randomStrId;
		String idtagA = "projectOverViewtagA" + randomStrId;

		String arg0 = ProjectResources
				.getResourceLink(ProjectTypeConstants.TASK);
		String arg1 = idtagA;
		String arg2 = ProjectLinkBuilder.generateProjectItemLink(
				task.getProjectid(), ProjectTypeConstants.TASK, task.getId());
		String arg3 = "'" + randomStrId + "'";
		String arg4 = "'" + ProjectTypeConstants.TASK + "'";
		String arg5 = "'" + task.getId() + "'";
		String arg6 = "'" + AppContext.getSiteUrl() + "tooltip/'";
		String arg7 = "'" + task.getSaccountid() + "'";
		String arg8 = "'" + AppContext.getSiteUrl() + "'";
		String arg9 = AppContext.getSession().getTimezone();
		String arg10 = task.getTaskname();

		String arg11 = idStickyToolTipDiv;
		String arg12 = idToopTipDiv;
		String arg13 = idDivSeverData;

		content = AppContext.getMessage(
				ProjectCommonI18nEnum.GANTT_CHART_TITLE, arg0, arg1, arg2,
				arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12,
				arg13);
		return content;
	}

	private Panel createControls() {

		Panel panel = new Panel();
		panel.setWidth(100, Unit.PERCENTAGE);

		HorizontalLayout controls = new HorizontalLayout();
		controls.setSpacing(true);
		controls.setMargin(true);
		panel.setContent(controls);

		start = new DateField("Start date");
		start.setResolution(Resolution.SECOND);
		start.setImmediate(true);
		start.addValueChangeListener(startDateValueChangeListener);

		end = new DateField("End date");
		end.setResolution(Resolution.SECOND);
		end.setImmediate(true);
		end.addValueChangeListener(endDateValueChangeListener);

		reso = new NativeSelect("Resolution");
		reso.setNullSelectionAllowed(false);
		reso.addItem(org.tltv.gantt.client.shared.Resolution.Hour);
		reso.addItem(org.tltv.gantt.client.shared.Resolution.Day);
		reso.addItem(org.tltv.gantt.client.shared.Resolution.Week);
		reso.setValue(gantt.getResolution());
		reso.setImmediate(true);
		reso.addValueChangeListener(resolutionValueChangeListener);

		controls.addComponent(start);
		controls.addComponent(end);
		controls.addComponent(reso);
		panel.setStyleName(UIConstants.THEME_NO_BORDER);

		return panel;
	}

	private ValueChangeListener startDateValueChangeListener = new ValueChangeListener() {

		@Override
		public void valueChange(ValueChangeEvent event) {
			gantt.setStartDate((Date) event.getProperty().getValue());
			updateStepList();
		}
	};

	private ValueChangeListener endDateValueChangeListener = new ValueChangeListener() {

		@Override
		public void valueChange(ValueChangeEvent event) {
			gantt.setEndDate((Date) event.getProperty().getValue());
			updateStepList();
		}
	};

	private ValueChangeListener resolutionValueChangeListener = new ValueChangeListener() {

		@Override
		public void valueChange(ValueChangeEvent event) {
			org.tltv.gantt.client.shared.Resolution res = (org.tltv.gantt.client.shared.Resolution) event
					.getProperty().getValue();
			if (validateResolutionChange(res)) {
				gantt.setResolution(res);
			}
		}
	};

	private boolean validateResolutionChange(
			final org.tltv.gantt.client.shared.Resolution res) {
		long max = 4 * 7 * 24 * 60 * 60000L;
		if (res == org.tltv.gantt.client.shared.Resolution.Hour
				&& (gantt.getEndDate().getTime() - gantt.getStartDate()
						.getTime()) > max) {

			// revert to previous resolution
			setResolution(gantt.getResolution());

			// make user to confirm hour resolution, if the timeline range is
			// more than one week long.

			ConfirmDialogExt
					.show(UI.getCurrent(),
							"Please Confirm:",
							"Timeline range is a quite long for hour resolution. Rendering may be slow. Continue anyway?",
							AppContext
									.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
							AppContext
									.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
							new ConfirmDialog.Listener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void onClose(final ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {

										setResolution(res);
										gantt.setResolution(res);

									}
								}
							});
			return false;
		}
		return true;
	}

	private void setResolution(
			org.tltv.gantt.client.shared.Resolution resolution) {
		reso.removeValueChangeListener(resolutionValueChangeListener);
		try {
			reso.setValue(resolution);
		} finally {
			reso.addValueChangeListener(resolutionValueChangeListener);
		}
	}

}