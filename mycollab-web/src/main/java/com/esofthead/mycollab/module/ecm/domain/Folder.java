package com.esofthead.mycollab.module.ecm.domain;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Resource {
	private List<Resource> childs = new ArrayList<Resource>();

	public List<Resource> getChilds() {
		return childs;
	}

	public void setChilds(List<Resource> childs) {
		this.childs = childs;
	}
}
