/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.Date;
import java.util.GregorianCalendar;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.rits.cloning.Cloner;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class BugChartComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private final String[] reportDashboard = { "BugTrend", "BugsByPriority",
			"BugsByStatus", "BugByResolution" };
	private int currentReportIndex = 0;

	private final BugSearchCriteria baseSearchCriteria;

	public BugChartComponent(final BugSearchCriteria baseSearchCriteria,
			final int headerWidth, final int titleWidth) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHARTS_WIDGET_TITLE),
				null, new VerticalLayout(), headerWidth
						+ "px", titleWidth + "px");
		this.baseSearchCriteria = baseSearchCriteria;
		initUI();
	}

	public BugChartComponent(final BugSearchCriteria baseSearchCriteria,
			final String headerWidth, final String titleWidth) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHARTS_WIDGET_TITLE),
				null, new VerticalLayout(), headerWidth, titleWidth);
		this.baseSearchCriteria = baseSearchCriteria;
		initUI();
	}

	public void displayReport() {
		final String reportName = reportDashboard[currentReportIndex];

		final VerticalLayout bodyContent = (VerticalLayout) this.bodyContent;
		bodyContent.removeAllComponents();

		if ("BugTrend".equals(reportName)) {
			BugTrendReportChartWidget bugTrendWidget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				bugTrendWidget = new BugTrendReportChartWidget(300, 200);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				bugTrendWidget = new BugTrendReportChartWidget();
			}
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(bugTrendWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			final BugSearchCriteria trendSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			final Date last30Days = DateTimeUtils.subtractOrAddDayDuration(
					new GregorianCalendar().getTime(), -30);
			trendSearchCriteria.setUpdatedDate(new DateTimeSearchField(
					SearchField.AND, DateTimeSearchField.GREATERTHANEQUAL,
					last30Days));
			bugTrendWidget.setSearchCriteria(trendSearchCriteria);
		} else if ("BugsByPriority".equals(reportName)) {
			PrioritySummaryChartWidget prioritySummaryChartWidget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				prioritySummaryChartWidget = new PrioritySummaryChartWidget(
						300, 220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				prioritySummaryChartWidget = new PrioritySummaryChartWidget();
			}
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					prioritySummaryChartWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			final BugSearchCriteria prioritySearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			prioritySummaryChartWidget
					.setSearchCriteria(prioritySearchCriteria);
		} else if ("BugsByStatus".equals(reportName)) {
			StatusSummaryChartWidget statusSummaryChartWidget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				statusSummaryChartWidget = new StatusSummaryChartWidget(300,
						220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				statusSummaryChartWidget = new StatusSummaryChartWidget();
			}
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					statusSummaryChartWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			final BugSearchCriteria statusSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			statusSummaryChartWidget.setSearchCriteria(statusSearchCriteria);
		} else if ("BugByResolution".equals(reportName)) {
			BugResolutionSummaryChartWidget resolutionSummaryWdiget = null;
			if (ScreenSize.hasSupport1024Pixels()) {
				resolutionSummaryWdiget = new BugResolutionSummaryChartWidget(
						300, 220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				resolutionSummaryWdiget = new BugResolutionSummaryChartWidget();
			}
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					resolutionSummaryWdiget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			final BugSearchCriteria statusSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			resolutionSummaryWdiget.setSearchCriteria(statusSearchCriteria);
		}
	}

	private void initUI() {
//		final HorizontalLayout headerContainer = (HorizontalLayout) headerContent;
		// headerContainer.setWidth("100%");
		// headerContainer.setSpacing(true);
//		final Label emptySpace = new Label();
//		headerContainer.addComponent(emptySpace);
//		headerContainer.setExpandRatio(emptySpace, 1.0f);

		final PopupButton bugChartPopup = new PopupButton("Bug Trend");
		bugChartPopup.addStyleName("link");

		final VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");

		final Button btnBugTrend = new Button("Bugs Trend",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs Trend");
						currentReportIndex = 0;
						displayReport();
					}
				});
		btnBugTrend.setStyleName("link");
		filterBtnLayout.addComponent(btnBugTrend);

		final Button btnBugByPriority = new Button("Bugs By Priority",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs By Priority");
						currentReportIndex = 1;
						displayReport();
					}
				});
		btnBugByPriority.setStyleName("link");
		filterBtnLayout.addComponent(btnBugByPriority);

		final Button btnBugByStatus = new Button("Bugs By Status",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs By Status");
						currentReportIndex = 2;
						displayReport();
					}
				});
		btnBugByStatus.setStyleName("link");
		filterBtnLayout.addComponent(btnBugByStatus);

		final Button btnBugByResolution = new Button("Bugs By Resolution",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs By Resolution");
						currentReportIndex = 3;
						displayReport();
					}
				});
		btnBugByResolution.setStyleName("link");
		filterBtnLayout.addComponent(btnBugByResolution);

		displayReport();

		bugChartPopup.addComponent(filterBtnLayout);
		this.addHeaderElement(bugChartPopup);
		this.setHeaderColor(true);
	}
}
