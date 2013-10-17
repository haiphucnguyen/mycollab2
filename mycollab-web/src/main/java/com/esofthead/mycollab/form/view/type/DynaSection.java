package com.esofthead.mycollab.form.view.type;

import java.util.ArrayList;
import java.util.List;

public class DynaSection {

	private int orderIndex;
	private LayoutType layoutType;
	private List<DynaField> fields = new ArrayList<DynaField>();

	public LayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public int getFieldCount() {
		return fields.size();
	}

	public void addField(DynaField field) {
		fields.add(field);
	}

	public DynaField getField(int index) {
		return fields.get(index);
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public static enum LayoutType {
		ONE_COLUMN, TWO_COLUMN
	}
}
