/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.schedule.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.ReportBugIssueMapper;
import com.esofthead.mycollab.common.domain.ReportBugIssueExample;
import com.esofthead.mycollab.common.domain.ReportBugIssueWithBLOBs;
import com.esofthead.mycollab.common.logging.MyBatisFactory;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.schedule.MyCollabScheduleService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class MyCollabScheduleServiceImpl implements MyCollabScheduleService {

	@Override
	@Scheduled(fixedDelay = 10000)
	public void sendErrorReports() {
		SqlSession session = MyBatisFactory.build().openSession();
		ReportBugIssueMapper mapper = session
				.getMapper(ReportBugIssueMapper.class);
		List<ReportBugIssueWithBLOBs> listIssues = mapper
				.selectByExampleWithBLOBs(new ReportBugIssueExample());
		for (ReportBugIssueWithBLOBs issue : listIssues) {
			System.out.println("Issue: " + BeanUtility.printBeanObj(issue));
		}
	}
}
