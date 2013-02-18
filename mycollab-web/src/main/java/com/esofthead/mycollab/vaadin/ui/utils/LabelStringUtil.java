package com.esofthead.mycollab.vaadin.ui.utils;

public class LabelStringUtil {

	private static int NUM_CUT = 35; 
	
	public static String subLabelString(String label) {
		if (label.length() > NUM_CUT) {
			return label.substring(0, NUM_CUT) + "...";
		}
		return label;
	}
	
}
