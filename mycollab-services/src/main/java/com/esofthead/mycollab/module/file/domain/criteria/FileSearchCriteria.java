package com.esofthead.mycollab.module.file.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class FileSearchCriteria extends SearchCriteria {
	private String rootFolder;
	private String fileName;
	private String baseFolder;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBaseFolder() {
		return baseFolder;
	}

	public void setBaseFolder(String baseFolder) {
		this.baseFolder = baseFolder;
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}
}
