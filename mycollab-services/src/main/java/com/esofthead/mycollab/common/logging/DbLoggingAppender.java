/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author haiphucnguyen
 */
public class DbLoggingAppender extends AppenderSkeleton {

	@Override
	protected void append(LoggingEvent event) {
		if (this.layout == null) {
			errorHandler.error("No layout for appender " + name, null,
					ErrorCode.MISSING_LAYOUT);
			return;
		}

		StringBuilder message = new StringBuilder(this.layout.format(event));

		if (layout.ignoresThrowable()) {
			String[] messages = event.getThrowableStrRep();
			if (messages != null) {
				for (int j = 0; j < messages.length; ++j) {
					message.append(messages[j], 0, messages[j].length());
					message.append("\r\n");
				}
			}
		}

		ReportBugIssueWithBLOBs record = new ReportBugIssueWithBLOBs();
		record.setErrortrace(message.toString());

		commitLog(record);
	}

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	private static final void commitLog(ReportBugIssueWithBLOBs record) {
		try {
			ReportBugIssueMapper mapper = ApplicationContextUtil
					.getSpringBean(ReportBugIssueMapper.class);
			if (mapper != null) {
				mapper.insertSelective(record);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(record.getErrortrace());
		}
	}
}
