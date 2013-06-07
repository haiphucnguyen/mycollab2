package com.esofthead.mycollab.module.ecm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ContentJcrDao contentJcrDao;

	@Override
	public List<Resource> getResources(String path) {
		return null;

	}

	@Override
	public List<Folder> getSubFolders(String path) {
		// TODO Auto-generated method stub
		return null;
	}
}
