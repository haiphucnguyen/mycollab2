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
		return width >= (PIXELS_1024_WIDTH - DEVIATION) && width < (PIXELS_1280_WIDTH - DEVIATION);
	}

	public static boolean hasSupport1280Pixels() {
		return width >= (PIXELS_1280_WIDTH - DEVIATION);
	}
}
