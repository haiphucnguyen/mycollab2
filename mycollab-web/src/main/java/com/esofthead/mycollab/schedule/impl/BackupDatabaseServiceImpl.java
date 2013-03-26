package com.esofthead.mycollab.schedule.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.db.sqldump.DbConfiguration;
import com.esofthead.db.sqldump.DbExport;
import com.esofthead.mycollab.common.ApplicationProperties;

@Service
public class BackupDatabaseServiceImpl {
	
	private static String today() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return formater.format(Calendar.getInstance().getTime());
	}
	
	@Scheduled(fixedDelay = 60000)
	public void backupDatabase() {
		System.out.println("\r\nStarting backup database ----------------\r\n");
		try {
			DbConfiguration config = new DbConfiguration();
			config.setPassword(ApplicationProperties.getProperty(ApplicationProperties.DB_PASSWORD));
			config.setUserName(ApplicationProperties.getProperty(ApplicationProperties.DB_USERNAME));
			config.setUrl(ApplicationProperties.getProperty(ApplicationProperties.DB_URL));
			
			String dbModel = ApplicationProperties.getProperty(ApplicationProperties.DB_DBMODEL);
			config.setMySqlModel(null != dbModel && dbModel.toUpperCase().equals("MYSQL"));
			
			String bkFolder = ApplicationProperties.getProperty(ApplicationProperties.BK_FOLDER);
			
			OutputStream outStream = new FileOutputStream(new File(String.format("%s/%s.zip", bkFolder, today())));
			DbExport.backupDB(config, outStream, true);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("\r\nEnd backup database ----------------\r\n");
	}
}
