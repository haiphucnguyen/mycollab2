/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.ecm;

/**
 * 
 * @author haiphucnguyen
 *
 */
public class VolumeUtils {
	public static long KB_SIZE = 1024;

	public static long MB_SIZE = 1024 * 1024;

	public static long GB_SIZE = 1024 * 1024 * 1024;

	public static String getVolumeDisplay(Long volume) {
		if (volume == null) {
			return "0 Kb";
		} else if (volume < KB_SIZE) {
			return volume + " Bytes";
		} else if (volume < MB_SIZE) {
			return Math.floor(volume / KB_SIZE) + " Kb";
		} else if (volume < GB_SIZE) {
			return Math.floor(volume / MB_SIZE) + " Mb";
		} else {
			return Math.floor(volume / GB_SIZE) + " Gb";
		}
	}
}
