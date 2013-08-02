package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;

public class ProjectUiUtils {
	
	public static GridFormLayoutHelper getGridFormLayoutHelper(int col, int row, String fieldControlWidth, String captionWidth) {
		return new GridFormLayoutHelper(col, row, fieldControlWidth, captionWidth);
	}
	
	public static GridFormLayoutHelper getGridFormLayoutHelper(int col, int row) {
		return new GridFormLayoutHelper(col, row, "220px", "120px");
	}
}
