package com.esofthead.mycollab.module.ecm;

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
	
	public static void main(String[] args) {
		System.out.println(getVolumeDisplay(10737418240L));
	}
}
