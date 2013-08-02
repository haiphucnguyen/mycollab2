package com.esofthead.mycollab.module.file.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.module.file.service.ContentService;
import com.esofthead.mycollab.module.file.service.RawContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	protected RawContentService rawContentService;

	@Override
	public void saveContent(Integer accountId, String objectPath,
			InputStream stream) {
		String newPath = ((accountId == null) ? "" : accountId + "/")
				+ objectPath;
		rawContentService.saveContent(newPath, stream);
	}

	@Override
	public InputStream getContent(Integer accountId, String objectPath) {
		String newPath = ((accountId == null) ? "" : accountId + "/")
				+ objectPath;
		return rawContentService.getContent(newPath);
	}

	@Override
	public void removeContent(Integer accountId, String objectPath) {
		String newPath = ((accountId == null) ? "" : accountId + "/")
				+ objectPath;
		rawContentService.removeContent(newPath);
	}
}
