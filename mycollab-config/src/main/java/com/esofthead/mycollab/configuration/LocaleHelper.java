/**
 * This file is part of mycollab-config.
 *
 * mycollab-config is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-config is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-config.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.configuration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.esofthead.mycollab.core.format.DefaultDateFormat;
import com.esofthead.mycollab.core.format.IDateFormat;
import com.esofthead.mycollab.core.format.JpDateFormat;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.0
 * 
 */
public class LocaleHelper {
	private static final String JAPANESE = "Japanese";
	private static final String ENGLISH = "English";

	private static Map<String, Locale> languages = new HashMap<String, Locale>();

	private static Map<String, Locale> languageBase = new HashMap<String, Locale>();

	private static Map<Locale, IDateFormat> dateFormats = new HashMap<Locale, IDateFormat>();

	static {
		languages.put(JAPANESE, Locale.JAPAN);
		languages.put(ENGLISH, Locale.US);

		languageBase.put(Locale.JAPAN.toString(), Locale.JAPAN);
		languageBase.put(Locale.US.toString(), Locale.US);

		dateFormats.put(Locale.JAPAN, new JpDateFormat());
		dateFormats.put(Locale.US, new DefaultDateFormat());
	}

	static Map<String, Locale> getNativeLanguages() {
		return languages;
	}

	public static Locale toLocale(String language) {
		if (language == null) {
			return Locale.US;
		}

		Locale locale = languages.get(language);
		if (locale == null) {
			locale = languageBase.get(language);
		}
		return (locale != null) ? locale : SiteConfiguration.getDefaultLocale();
	}

	public static String getShortDateFormatAssociateToLocale(Locale locale) {
		return dateFormats.get(locale).getShortDateFormat();
	}

	public static String getDateFormatAssociateToLocale(Locale locale) {
		return dateFormats.get(locale).getDateFormat();
	}

	public static String getDateTimeFormatAssociateToLocale(Locale locale) {
		return dateFormats.get(locale).getDateTimeFormat();
	}

	public static String getDayMonthFormatAssociateToLocale(Locale locale) {
		return dateFormats.get(locale).getDayMonthFormat();
	}
}
