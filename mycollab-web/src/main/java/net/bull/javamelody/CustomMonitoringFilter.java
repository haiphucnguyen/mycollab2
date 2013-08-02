package net.bull.javamelody;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.monitoring.MyCollabMonitoringReporter;

public class CustomMonitoringFilter extends MonitoringFilter {
	private static Logger log = LoggerFactory
			.getLogger(CustomMonitoringFilter.class);

	private static Collector theCollector;
	private static final MyCollabMonitoringReporter reporter = new MyCollabMonitoringReporter();

	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);

		/*
		 * Get the reference of collector
		 */
		// startDate = Calendar.getInstance().getTime();
		theCollector = getFilterContext().getCollector();
		final Timer timer = new Timer("mycollab-monitoring-timer");
		scheduleReportMailForLocalServer(timer, Period.JOUR);
	}

	private static int counter = 0;
	
	static void scheduleReportMailForLocalServer(final Timer timer,
			final Period period) {
		System.out.println(counter++);
		final TimerTask task = new TimerTask() {
			/** {@inheritDoc} */
			@Override
			public void run() {
				doStatistic(period);
				System.out.println("shcedule next round");
				scheduleReportMailForLocalServer(timer, period);
			}
		};

		final Date nextExecutionDate = getNextExecutionDate(period);
		timer.schedule(task, nextExecutionDate);
	}

	static void doStatistic(Period period) {
		final JavaInformations javaInformations = new JavaInformations(
				Parameters.getServletContext(), true);
		final File tmpFile = new File(Parameters.TEMPORARY_DIRECTORY,
				PdfReport.getFileName(theCollector.getApplication()));

		try {
			final OutputStream output = new BufferedOutputStream(
					new FileOutputStream(tmpFile));
			try {
				final PdfReport pdfReport = new PdfReport(theCollector, false,
						Collections.singletonList(javaInformations), period,
						output);
				pdfReport.toPdf();
			} catch (Exception ex) {
				log.error("Error while do statistic", ex);
			} finally {
				output.close();
			}

			reporter.sendDailyReport(tmpFile.getAbsolutePath());
			log.debug("Run statistic report successfully");
		} catch (Exception ex) {
		}
	}

	static Date getNextExecutionDate(Period period) {
		final Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.HOUR_OF_DAY, 0);
		 calendar.set(Calendar.MINUTE, 0);
		 calendar.set(Calendar.SECOND, 0);
		 calendar.set(Calendar.MILLISECOND, 0);
		switch (period) {
		case JOUR:
			 calendar.add(Calendar.DAY_OF_YEAR, 1);
//			calendar.add(Calendar.MINUTE, 1);
			break;
		case SEMAINE:
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
				calendar.add(Calendar.DAY_OF_YEAR, 7);
			}
			break;
		case MOIS:
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
				calendar.add(Calendar.MONTH, 1);
			}
			break;
		case ANNEE:
			throw new IllegalArgumentException(String.valueOf(period));
		case TOUT:
			throw new IllegalArgumentException(String.valueOf(period));
		default:
			throw new IllegalArgumentException(String.valueOf(period));
		}
		return calendar.getTime();
	}
}
