package com.esofthead.mycollab.module.ecm.domain;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Resource {
	private List<Folder> childs = new ArrayList<Folder>();

	public List<Folder> getChilds() {
		return childs;
	}

	public void setChilds(List<Folder> childs) {
		this.childs = childs;
	}

	public void addChild(Folder child) {
		childs.add(child);
	}
}
