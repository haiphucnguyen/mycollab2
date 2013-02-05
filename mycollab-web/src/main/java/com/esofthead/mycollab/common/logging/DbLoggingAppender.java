/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.logging;

import java.io.StringReader;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;

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
		try {
			/* continue on error */
			record.setUsername(AppContext.getUsername());
			record.setSaccountid(AppContext.getAccountId());
			AbstractWebApplicationContext context = (AbstractWebApplicationContext) AppContext
					.getApplication().getContext();
			record.setUseragent(context.getBrowser().getBrowserApplication());
			
			
		} catch (Exception e) {
				
		}
		
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
		String mybatisConfig = MybatisConfig.loadConfig();
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = builder.build(new StringReader(mybatisConfig));
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			ReportBugIssueMapper mapper = session.getMapper(ReportBugIssueMapper.class);
			mapper.insertSelective(record);
			session.commit();
		}
		finally {
			if (null != session) {
				session.close();
			}
		}
	}
}
