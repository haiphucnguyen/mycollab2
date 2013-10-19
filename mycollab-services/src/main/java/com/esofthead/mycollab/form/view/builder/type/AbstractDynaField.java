package com.esofthead.mycollab.form.view.builder.type;

import com.esofthead.mycollab.core.utils.JsonDeSerializer.Exclude;

public class AbstractDynaField implements Comparable<AbstractDynaField> {
	private int fieldIndex;

	private String fieldName;

	private String displayName;

	private boolean isRequired;

	@Exclude
	private DynaSection ownSection;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldIndex() {
		return fieldIndex;
	}

	public void setFieldIndex(int fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public DynaSection getOwnSection() {
		return ownSection;
	}

	public void setOwnSection(DynaSection ownSection) {
		this.ownSection = ownSection;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public int compareTo(AbstractDynaField paramT) {
		return (this.fieldIndex - paramT.fieldIndex);
	}
}
