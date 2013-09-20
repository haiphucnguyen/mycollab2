package com.esofthead.mycollab.module.ecm;

import javax.jcr.Node;

public class NodesUtil {
	public static String getString(Node node, String property) {
		return getString(node, property, "");
	}

	public static String getString(Node node, String property,
			String defaultValue) {
		try {
			return node.getProperty(property).getString();
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
