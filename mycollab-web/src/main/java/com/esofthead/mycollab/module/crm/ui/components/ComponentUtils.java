package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import org.vaadin.maddon.button.MButton;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class ComponentUtils {
    public static MButton createCustomizeViewButton() {
        MButton customizeViewBtn = new MButton("");
        customizeViewBtn.setIcon(FontAwesome.ADJUST);
        customizeViewBtn.setDescription("Layout Options");
        customizeViewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        customizeViewBtn.addStyleName("small-padding");
        return customizeViewBtn;
    }

    public static MButton createImportEntitiesButton() {
        MButton importBtn = new MButton("");
        importBtn.setDescription("Import");
        importBtn.setIcon(FontAwesome.CLOUD_UPLOAD);
        importBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        importBtn.addStyleName("small-padding");
        return importBtn;
    }
}
