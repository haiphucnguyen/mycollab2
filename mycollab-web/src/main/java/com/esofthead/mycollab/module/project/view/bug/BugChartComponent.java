/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.Date;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.localization.BugI18Enum;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.rits.cloning.Cloner;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class BugChartComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private String[] reportDashboard = { "BugTrend", "BugsByPriority",
			"BugsByStatus", "BugByResolution" };
	private int currentReportIndex = 0;

	private BugSearchCriteria baseSearchCriteria;

	public BugChartComponent(BugSearchCriteria baseSearchCriteria,
			int headerWidth, int titleWidth) {
		super(AppContext.getMessage(BugI18Enum.CHARTS_WIDGET_TITLE),
				new HorizontalLayout(), new VerticalLayout(), headerWidth
						+ "px", titleWidth + "px");
		this.baseSearchCriteria = baseSearchCriteria;
		initUI();
	}

	private void initUI() {
		HorizontalLayout headerContainer = (HorizontalLayout) this.headerContent;
		headerContainer.setWidth("100%");
		headerContainer.setSpacing(true);
		Label emptySpace = new Label();
		headerContainer.addComponent(emptySpace);
		headerContainer.setExpandRatio(emptySpace, 1.0f);

		Button prevButton = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				if (currentReportIndex == 0) {
					currentReportIndex = reportDashboard.length - 1;
				} else {
					currentReportIndex--;
				}

				displayReport();
			}
		});
		prevButton.setIcon(new ThemeResource("icons/16/previous_gray.png"));
		prevButton.setStyleName("link");
		headerContainer.addComponent(prevButton);

		Button nextBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				if (currentReportIndex >= (reportDashboard.length - 1)) {
					currentReportIndex = 0;
				} else {
					currentReportIndex++;
				}
				displayReport();
			}
		});
		nextBtn.setIcon(new ThemeResource("icons/16/next_gray.png"));
		nextBtn.setStyleName("link");
		headerContainer.addComponent(nextBtn);

		displayReport();
	}

	public void displayReport() {
		String reportName = reportDashboard[currentReportIndex];

		VerticalLayout bodyContent = (VerticalLayout) this.bodyContent;
		bodyContent.removeAllComponents();

		if ("BugTrend".equals(reportName)) {
			BugTrendReportChartWidget bugTrendWidget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				bugTrendWidget = new BugTrendReportChartWidget(300, 200);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				bugTrendWidget = new BugTrendReportChartWidget();
			}
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(bugTrendWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			BugSearchCriteria trendSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			Date last30Days = DateTimeUtils.subtractOrAddDayDuration(
					new GregorianCalendar().getTime(), -30);
			trendSearchCriteria.setUpdatedDate(new DateTimeSearchField(
					SearchField.AND, DateTimeSearchField.GREATERTHANEQUAL,
					last30Days));
			bugTrendWidget.setSearchCriteria(trendSearchCriteria);
		} else if ("BugsByPriority".equals(reportName)) {
			PrioritySummaryChartWidget prioritySummaryChartWidget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				prioritySummaryChartWidget = new PrioritySummaryChartWidget(300, 220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				prioritySummaryChartWidget = new PrioritySummaryChartWidget();
			}
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					prioritySummaryChartWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			BugSearchCriteria prioritySearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			prioritySummaryChartWidget.setSearchCriteria(prioritySearchCriteria);
		} else if ("BugsByStatus".equals(reportName)) {
			StatusSummaryChartWidget statusSummaryChartWidget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				statusSummaryChartWidget = new StatusSummaryChartWidget(300, 220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				statusSummaryChartWidget = new StatusSummaryChartWidget();
			}
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(statusSummaryChartWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			BugSearchCriteria statusSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			statusSummaryChartWidget.setSearchCriteria(statusSearchCriteria);
		} else if ("BugByResolution".equals(reportName)) {
			BugResolutionSummaryChartWidget resolutionSummaryWdiget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				resolutionSummaryWdiget = new BugResolutionSummaryChartWidget(300,
						220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				resolutionSummaryWdiget = new BugResolutionSummaryChartWidget();
			}
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					resolutionSummaryWdiget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			BugSearchCriteria statusSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			resolutionSummaryWdiget.setSearchCriteria(statusSearchCriteria);
		}
	}
}
