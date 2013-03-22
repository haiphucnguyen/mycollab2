package com.esofthead.mycollab.schedule.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BackupDatabaseServiceImpl {
	@Scheduled(fixedDelay = 60000)
	public void backupDatabase() {

	}
}
