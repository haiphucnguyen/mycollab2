package com.esofthead.mycollab.module.ecm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.ecm.dao.DriveInfoMapper;
import com.esofthead.mycollab.module.ecm.domain.DriveInfo;
import com.esofthead.mycollab.module.ecm.domain.DriveInfoExample;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;

@Service
public class DriveInfoServiceImpl extends
		DefaultCrudService<Integer, DriveInfo> implements DriveInfoService {

	@Autowired
	private DriveInfoMapper driveInfoMapper;

	@Override
	public ICrudGenericDAO<Integer, DriveInfo> getCrudMapper() {
		return driveInfoMapper;
	}

	@Override
	public void saveOrUpdateDriveInfo(@CacheKey DriveInfo driveInfo) {
		Integer sAccountId = driveInfo.getSaccountid();
		DriveInfoExample ex = new DriveInfoExample();
		ex.createCriteria().andSaccountidEqualTo(sAccountId);
		if (driveInfoMapper.countByExample(ex) > 0) {
			driveInfoMapper.updateByExampleSelective(driveInfo, ex);
		} else {
			driveInfoMapper.insert(driveInfo);
		}
	}

	@Override
	public DriveInfo getDriveInfo(@CacheKey Integer sAccountId) {
		DriveInfoExample ex = new DriveInfoExample();
		ex.createCriteria().andSaccountidEqualTo(sAccountId);
		List<DriveInfo> driveInfos = driveInfoMapper.selectByExample(ex);
		if (driveInfos != null && driveInfos.size() > 0) {
			return driveInfos.get(0);
		} else {
			DriveInfo driveInfo = new DriveInfo();
			driveInfo.setSaccountid(sAccountId);
			return driveInfo;
		}
	}

	@Override
	public Long getUsedStorageVolume(@CacheKey Integer sAccountId) {
		DriveInfo driveInfo = getDriveInfo(sAccountId);
		return driveInfo.getUsedvolume();
	}

}
