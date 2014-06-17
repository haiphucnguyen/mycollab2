package com.esofthead.mycollab.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

/**
 * Wrapper class to get localization string.
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class LocalizationHelper {
	private static Logger log = LoggerFactory
			.getLogger(LocalizationHelper.class);

	private static final Map<Locale, IMessageConveyor> languageMap;

	public static final Locale defaultLocale = Locale.US;

	static {
		languageMap = new HashMap<Locale, IMessageConveyor>();
		languageMap.put(Locale.US, new MessageConveyor(Locale.US));
		languageMap.put(Locale.JAPANESE, new MessageConveyor(Locale.JAPAN));
	}

	public static IMessageConveyor getMessageConveyor(Locale language) {
		if (language == null) {
			return languageMap.get(Locale.US);
		} else {
			IMessageConveyor messageConveyor = languageMap.get(language);
			if (messageConveyor == null) {
				return languageMap.get(Locale.US);
			}

			return messageConveyor;
		}
	}

	// LOCALIZATION
	private static IMessageConveyor english = new MessageConveyor(Locale.US);

	public static String getMessage(Locale locale, Enum key, Object... objects) {
		try {
			IMessageConveyor messageConveyor = getMessageConveyor(locale);
			return messageConveyor.getMessage(key, objects);
		} catch (Exception e) {
			log.error("Can not find resource key " + key, e);
			return "Undefined";
		}
	}
}
