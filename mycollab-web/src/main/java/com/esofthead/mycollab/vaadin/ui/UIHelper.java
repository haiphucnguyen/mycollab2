package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public class UIHelper {
	public static void addWindowToRoot(Component baseComp, Window window) {
		Window baseWindow = baseComp.getWindow();
		while (true) {
			Window parentWindow = baseWindow.getParent();
			if (parentWindow == null) {
				break;
			} else {
				baseWindow = parentWindow;
			}
		}
		baseWindow.addWindow(window);
	}
}
