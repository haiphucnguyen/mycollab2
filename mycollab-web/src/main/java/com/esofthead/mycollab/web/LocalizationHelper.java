package com.esofthead.mycollab.web;

import java.util.Locale;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

public class LocalizationHelper {
	// LOCALIZATION
	private static IMessageConveyor mc = new MessageConveyor(Locale.US);

	public static String getMessage(Enum key) {
		return mc.getMessage(key);
	}

	public static String getMessage(Enum key, Object... objects) {
		return mc.getMessage(key, objects);
	}
}
