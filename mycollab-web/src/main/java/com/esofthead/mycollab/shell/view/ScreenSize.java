/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.shell.view;

public class ScreenSize {

	private static int width = 1024;

	private static int DEVIATION = 100;

	public static int PIXELS_1024_WIDTH = 1024;

	public static int PIXELS_1280_WIDTH = 1280;

	public static void setWidth(int widthVal) {
		width = widthVal;
	}

	public static boolean hasSupport1024Pixels() {
		return false;
		// return width >= (PIXELS_1024_WIDTH - DEVIATION)
		// && width < (PIXELS_1280_WIDTH - DEVIATION);
	}

	public static boolean hasSupport1280Pixels() {
		// return width >= (PIXELS_1280_WIDTH - DEVIATION);
		return true;
	}
}
