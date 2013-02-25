package com.esofthead.mycollab.shell.view;

public class ScreenSize {

	private static int width = 1024;

	public static int PIXELS_1024_WIDTH = 1024;

	public static int PIXELS_1280_WIDTH = 1280;

	public static void setWidth(int widthVal) {
		width = widthVal;
	}

	public static boolean hasSupport1024Pixels() {
		return width >= PIXELS_1024_WIDTH && width < PIXELS_1280_WIDTH;
	}

	public static boolean hasSupport1280Pixels() {
		return width >= PIXELS_1280_WIDTH;
	}
}
