package com.esofthead.mycollab.module.file.service;

import java.io.InputStream;

public interface RawContentService {
	void saveContent(String objectPath, InputStream stream);
	
	InputStream getContent(String objectPath);
	
	void removeContent(String objectPath);
}