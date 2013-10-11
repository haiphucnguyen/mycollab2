/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.rits.cloning.Cloner;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class BugChartComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private final String[] reportDashboard = { "BugsByPriority",
			"BugsByStatus", "BugByResolution" };
	private int currentReportIndex = 0;

	private final BugSearchCriteria baseSearchCriteria;

	public BugChartComponent(final BugSearchCriteria baseSearchCriteria,
			final int headerWidth, final int titleWidth) {
		super(LocalizationHelper.getMessage(BugI18nEnum.CHARTS_WIDGET_TITLE),
				null, new CssLayout(), headerWidth + "px", titleWidth + "px");
		this.setSizeFull();
		this.baseSearchCriteria = baseSearchCriteria;
		this.setContentBorder(true);
		this.bodyContent.setSizeFull();
		initUI();
	}

	public void displayReport() {
		final String reportName = reportDashboard[currentReportIndex];

		final CssLayout bodyContent = (CssLayout) this.bodyContent;
		bodyContent.removeAllComponents();

		if ("BugsByPriority".equals(reportName)) {
			PrioritySummaryChartWidget prioritySummaryChartWidget = null;
			prioritySummaryChartWidget = new PrioritySummaryChartWidget();
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					prioritySummaryChartWidget);
			bodyContent.addComponent(lazyComp);

			final BugSearchCriteria prioritySearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			prioritySummaryChartWidget
					.setSearchCriteria(prioritySearchCriteria);
		} else if ("BugsByStatus".equals(reportName)) {
			StatusSummaryChartWidget statusSummaryChartWidget = null;
			statusSummaryChartWidget = new StatusSummaryChartWidget();
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					statusSummaryChartWidget);
			bodyContent.addComponent(lazyComp);

			final BugSearchCriteria statusSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			statusSummaryChartWidget.setSearchCriteria(statusSearchCriteria);
		} else if ("BugByResolution".equals(reportName)) {
			BugResolutionSummaryChartWidget resolutionSummaryWdiget = null;
			resolutionSummaryWdiget = new BugResolutionSummaryChartWidget();
			final LazyLoadWrapper lazyComp = new LazyLoadWrapper(
					resolutionSummaryWdiget);
			bodyContent.addComponent(lazyComp);

			final BugSearchCriteria statusSearchCriteria = new Cloner()
					.deepClone(baseSearchCriteria);
			resolutionSummaryWdiget.setSearchCriteria(statusSearchCriteria);
		}
	}

	private void initUI() {

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
