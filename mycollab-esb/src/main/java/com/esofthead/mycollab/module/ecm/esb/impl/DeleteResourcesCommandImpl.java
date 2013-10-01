package com.esofthead.mycollab.module.ecm.esb.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.jgroups.DistributionLockUtil;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.esb.DeleteResourcesCommand;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.file.service.RawContentService;

@Component
public class DeleteResourcesCommandImpl implements DeleteResourcesCommand {

	private static Logger log = LoggerFactory
			.getLogger(DeleteResourcesCommandImpl.class);

	@Autowired
	private RawContentService rawContentService;

	@Autowired
	private DriveInfoService driveInfoService;

	@Override
	public void removeResource(String path, String userDelete,
			Integer sAccountId) {
		log.debug("Delete resource path {} by user {}", path, userDelete);

		long size = rawContentService.getSize(path);
		if (size > 0) {

			Lock lock = DistributionLockUtil.getLock("" + sAccountId);
			try {
				if (lock.tryLock(1, TimeUnit.HOURS)) {
					DriveInfo driveInfo = driveInfoService
							.getDriveInfo(sAccountId);
					if (driveInfo.getUsedvolume() == null
							|| (driveInfo.getUsedvolume() < size)) {
						log.error(
								"Inconsistent storage volumne site of account {}, used storage is less than removed storage ",
								sAccountId);
						driveInfo.setUsedvolume(0L);
					} else {
						driveInfo.setUsedvolume(driveInfo.getUsedvolume()
								- size);
					}

					driveInfoService.saveOrUpdateDriveInfo(driveInfo);
					rawContentService.removePath(path);
				}
			} catch (Exception e) {
				log.error("Error while delete content " + path, e);
			} finally {
				lock.unlock();
			}

		}
	}

}
