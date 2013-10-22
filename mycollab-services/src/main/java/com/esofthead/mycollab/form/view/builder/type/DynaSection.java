package com.esofthead.mycollab.form.view.builder.type;

import java.util.ArrayList;
import java.util.List;

public class DynaSection implements Comparable<DynaSection> {

	private String header;
	private int orderIndex;
	private boolean isDeletedSection = false;
	private LayoutType layoutType;
	private List<AbstractDynaField> fields = new ArrayList<AbstractDynaField>();

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public LayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public int getFieldCount() {
		return fields.size();
	}

	public void addField(AbstractDynaField field) {
		fields.add(field);
		field.setOwnSection(this);
	}

	public AbstractDynaField getField(int index) {
		return fields.get(index);
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public boolean isDeletedSection() {
		return isDeletedSection;
	}

	public void setDeletedSection(boolean isDeletedSection) {
		this.isDeletedSection = isDeletedSection;
	}

	public static enum LayoutType {
		ONE_COLUMN, TWO_COLUMN
	}

	@Override
	public int compareTo(DynaSection o) {
		return (orderIndex - o.orderIndex);
	}
}
