package com.esofthead.mycollab.form.view.builder.type;

import com.esofthead.mycollab.core.utils.JsonDeSerializer.Exclude;

public class AbstractDynaField implements Comparable<AbstractDynaField> {
	@Exclude
	protected int fieldIndex;

	@Exclude
	protected String fieldName;

	@Exclude
	protected String displayName;

	@Exclude
	protected boolean isMandatory = false;

	@Exclude
	protected boolean isRequired = false;

	@Exclude
	protected boolean isCustom = false;

	@Exclude
	protected DynaSection ownSection;

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

	public boolean isCustom() {
		return isCustom;
	}

	public void setCustom(boolean isCustom) {
		this.isCustom = isCustom;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	@Override
	public int compareTo(AbstractDynaField paramT) {
		return (this.fieldIndex - paramT.fieldIndex);
	}
}
