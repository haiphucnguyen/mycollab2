/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.schedule.impl;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.common.logging.MybatisConfig;
import com.esofthead.mycollab.module.mail.service.gmail.GmailManager;
import com.esofthead.mycollab.schedule.MyCollabScheduleService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class MyCollabScheduleServiceImpl implements MyCollabScheduleService {

	private static boolean isEnterConflictZone = false;
	private static final Object syncKey = new Object();

	@Override
	@Scheduled(fixedDelay = 10000)
	public void doSomething() {
		synchronized (syncKey) {
			if (isEnterConflictZone) /* the processing is in progress */
				return;
			isEnterConflictZone = true;
		}

		List<ReportBugIssueWithBLOBs> lsIssue = loadBugIssue();
		if (0 < lsIssue.size()) {
			for (ReportBugIssueWithBLOBs issue : lsIssue) {
				/*
				 * resolve each issue
				 */
				resolveIssue(issue);
			}
		}

		/*
		 * release key
		 */
		synchronized (syncKey) {
			isEnterConflictZone = false;
		}
	}

	private List<ReportBugIssueWithBLOBs> loadBugIssue() {
		List<ReportBugIssueWithBLOBs> lsResult = null;
		SqlSessionFactory sessionFactory = loadSqlSessionFactory();
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			ReportBugIssueMapper mapper = session
					.getMapper(ReportBugIssueMapper.class);
			lsResult = mapper.selectByExampleWithBLOBs(null);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		if (null != lsResult)
			return lsResult;

		return new LinkedList<ReportBugIssueWithBLOBs>();
	}

	private SqlSessionFactory loadSqlSessionFactory() {
		String mybatisConfig = MybatisConfig.loadConfig();
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		return builder.build(new StringReader(mybatisConfig));
	}

	private String toHtmlContent(ReportBugIssueWithBLOBs issue) {
		final String bodyTemplate = "User Name: %s\r\n"
				+ "Account type: %s\r\n"
				+ "Ip address: %s\r\n"
				+ "Country code: %s\r\n"
				+ "User agent: %s"
				+ "\r\n\r\n/------------------------------- Error Trace ---------------------------/\r\n\r\n"
				+ "%s\r\n";

		return String.format(bodyTemplate, issue.getUsername(),
				issue.getSaccountid(), issue.getIpaddress(),
				issue.getCountryCode(), issue.getUseragent(),
				issue.getErrortrace());
	}

	private void resolveIssue(ReportBugIssueWithBLOBs issue) {
		final String subject = "[MyCollab] - Report bug issues - [%s]";

		boolean isSentSuccess = false;
		int __try = 1;

		while (!isSentSuccess && __try <= 3) {
			try {
				GmailManager.sendErrorLogMail(
						String.format(subject, issue.getCountryCode()),
						toHtmlContent(issue), null);
				isSentSuccess = true;
			} catch (Exception e) {
				__try++;
			}
		}

		if (isSentSuccess) {
			/*
			 * release issue from database
			 */
			SqlSessionFactory sessionFactory = loadSqlSessionFactory();
			SqlSession session = null;
			try {
				session = sessionFactory.openSession();
				ReportBugIssueMapper mapper = session
						.getMapper(ReportBugIssueMapper.class);
				mapper.deleteByPrimaryKey(issue.getId());
				session.commit();
			} finally {
				if (null != session) {
					session.close();
				}
			}
		}
	}
}
