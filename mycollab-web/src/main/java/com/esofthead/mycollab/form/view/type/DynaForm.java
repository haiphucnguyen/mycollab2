package com.esofthead.mycollab.form.view.type;

import java.util.ArrayList;
import java.util.List;

public class DynaForm {
	private List<DynaSection> sections = new ArrayList<DynaSection>();

	public int getSectionCount() {
		return sections.size();
	}

	public void addSection(DynaSection section) {
		sections.add(section);
	}

}
