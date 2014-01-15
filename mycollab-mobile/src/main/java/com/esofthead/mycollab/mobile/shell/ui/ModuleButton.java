package com.esofthead.mycollab.mobile.shell.ui;

import com.vaadin.addon.touchkit.ui.NavigationButton;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class ModuleButton extends NavigationButton {
    public static String CLASSNAME = "module-button";

    public ModuleButton(String moduleName) {
        super(moduleName);

        this.setStyleName(CLASSNAME);
    }
}
