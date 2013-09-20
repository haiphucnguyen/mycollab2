package com.esofthead.mycollab.module.ecm.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.ecm.MimeTypesUtil;
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

		String mimeType = content.getMimeType();
		String mycollabType = MimeTypesUtil.detectMyCollabContentType(mimeType);

		DriveInfo driveInfo = driveInfoService.getDriveInfo(sAccountId);

		if (MimeTypesUtil.AUDIO_TYPE.equals(mycollabType)) {
			if (driveInfo.getAudiovolume() == null) {
				driveInfo.setAudiovolume(content.getSize());
			} else {
				driveInfo.setAudiovolume(content.getSize()
						+ driveInfo.getAudiovolume());
			}
		} else if (MimeTypesUtil.IMAGE_TYPE.equals(mycollabType)) {
			if (driveInfo.getImagevolume() == null) {
				driveInfo.setImagevolume(content.getSize());
			} else {
				driveInfo.setImagevolume(content.getSize()
						+ driveInfo.getImagevolume());
			}
		} else if (MimeTypesUtil.TEXT_TYPE.equals(mycollabType)) {
			if (driveInfo.getTextvolume() == null) {
				driveInfo.setTextvolume(content.getSize());
			} else {
				driveInfo.setTextvolume(content.getSize()
						+ driveInfo.getTextvolume());
			}
		} else if (MimeTypesUtil.VIDEO_TYPE.equals(mycollabType)) {
			if (driveInfo.getVideovolume() == null) {
				driveInfo.setVideovolume(content.getSize());
			} else {
				driveInfo.setVideovolume(content.getSize()
						+ driveInfo.getVideovolume());
			}
		} else {
			if (driveInfo.getBinaryvolume() == null) {
				driveInfo.setBinaryvolume(content.getSize());
			} else {
				driveInfo.setBinaryvolume(content.getSize()
						+ driveInfo.getBinaryvolume());
			}
		}

		driveInfoService.saveOrUpdateDriveInfo(driveInfo);
	}
}
