package com.esofthead.mycollab.module.ecm.domain;

public class ExternalFolder extends Folder {
	private String storageName;

	private ExternalDrive externalDrive;

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public ExternalDrive getExternalDrive() {
		return externalDrive;
	}

	public void setExternalDrive(ExternalDrive externalDrive) {
		this.externalDrive = externalDrive;
	}
}
