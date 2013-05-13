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
	private String[] reportDashboard = { "BugTrend", "BugsByPriority",
			"BugsByStatus", "BugByResolution" };
	private int currentReportIndex = 0;

	private BugSearchCriteria baseSearchCriteria;

	public BugChartComponent(BugSearchCriteria baseSearchCriteria,
			int headerWidth, int titleWidth) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHARTS_WIDGET_TITLE),
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
		
		final PopupButton bugChartPopup = new PopupButton("Bug Trend");
		bugChartPopup.addStyleName("link");
		
		VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("200px");
		
		Button btnBugTrend = new Button("Bugs Trend",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs Trend");
						currentReportIndex = 0;
						displayReport();
					}
				});
		btnBugTrend.setStyleName("link");
		filterBtnLayout.addComponent(btnBugTrend);
		
		Button btnBugByPriority = new Button("Bugs By Priority",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs By Priority");
						currentReportIndex = 1;
						displayReport();
					}
				});
		btnBugByPriority.setStyleName("link");
		filterBtnLayout.addComponent(btnBugByPriority);
		
		Button btnBugByStatus = new Button("Bugs By Status",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						bugChartPopup.setPopupVisible(false);
						bugChartPopup.setCaption("Bugs By Status");
						currentReportIndex = 2;
						displayReport();
					}
				});
		btnBugByStatus.setStyleName("link");
		filterBtnLayout.addComponent(btnBugByStatus);
		
		Button btnBugByResolution = new Button("Bugs By Resolution",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
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
		headerContainer.addComponent(bugChartPopup);
		
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
				prioritySummaryChartWidget = new PrioritySummaryChartWidget(
						300, 220);
			} else if (ScreenSize.hasSupport1280Pixels()) {
				prioritySummaryChartWidget = new PrioritySummaryChartWidget();
			}
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					prioritySummaryChartWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			BugSearchCriteria prioritySearchCriteria = new Cloner()
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
			LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					statusSummaryChartWidget);
			bodyContent.addComponent(lazyComp);
			bodyContent.setComponentAlignment(lazyComp, Alignment.MIDDLE_RIGHT);

			BugSearchCriteria statusSearchCriteria = new Cloner()
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
