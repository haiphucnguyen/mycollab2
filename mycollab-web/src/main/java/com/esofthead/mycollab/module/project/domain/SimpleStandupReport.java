package com.esofthead.mycollab.module.project.domain;

public class SimpleStandupReport extends StandupReportWithBLOBs {
	private static final long serialVersionUID = 1L;

	private String logByFullName;

	public String getLogByFullName() {
		return logByFullName;
	}

	public void setLogByFullName(String logByFullName) {
		this.logByFullName = logByFullName;
	}
}
