package com.esofthead.mycollab.module.ecm.esb.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.jgroups.DistributionLockUtil;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.esb.SaveContentCommand;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;

@Component
public class SaveContentCommandImpl implements SaveContentCommand {
	private static Logger log = LoggerFactory
			.getLogger(SaveContentCommandImpl.class);

	@Autowired
	private DriveInfoService driveInfoService;

	@Override
	public void saveContent(Content content, String createdUser,
			Integer sAccountId) {
		log.debug("Save content {} by {}", BeanUtility.printBeanObj(content),
				createdUser);

		Lock lock = DistributionLockUtil.getLock("ecm-" + sAccountId);
		try {
			if (lock.tryLock(1, TimeUnit.HOURS)) {
				DriveInfo driveInfo = driveInfoService.getDriveInfo(sAccountId);
				if (driveInfo.getUsedvolume() == null) {
					driveInfo.setUsedvolume(content.getSize());
				} else {
					driveInfo.setUsedvolume(content.getSize()
							+ driveInfo.getUsedvolume());
				}

				driveInfoService.saveOrUpdateDriveInfo(driveInfo);
			}
		} catch (Exception e) {
			log.error(
					"Error while save content "
							+ BeanUtility.printBeanObj(content), e);
		} finally {
			lock.unlock();
		}
	}
}
