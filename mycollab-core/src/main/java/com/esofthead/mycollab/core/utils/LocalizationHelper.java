/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.utils;

import java.util.Locale;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

/**
 * Wrapper class to get localization string.
 * 
 * @author haiphucnguyen
 * 
 */
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
