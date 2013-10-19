package com.esofthead.mycollab.form.view.builder.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DynaForm {
	private List<DynaSection> sections = new ArrayList<DynaSection>();

	public int getSectionCount() {
		return sections.size();
	}

	public void addSection(DynaSection section) {
		sections.add(section);
		Collections.sort(sections);
	}

	public DynaSection getSection(int index) {
		return sections.get(index);
	}
}
