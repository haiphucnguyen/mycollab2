package com.esofthead.mycollab.module.tracker.domain;

import java.util.HashMap;
import java.util.Map;

public class MetaOptionField extends MetaField {
	private Map<String, String> options = new HashMap<String, String>();

	public void putOption(String label, String value) {
		options.put(label, value);
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
}
