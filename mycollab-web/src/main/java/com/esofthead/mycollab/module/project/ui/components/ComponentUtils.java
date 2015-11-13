package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.ui.HeaderWithFontAwesome;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class ComponentUtils {
    public static final HeaderWithFontAwesome headerH2(String resId, String title) {
        return HeaderWithFontAwesome.h2(ProjectAssetsManager.getAsset(resId), title);
    }

    public static final HeaderWithFontAwesome headerH3(String resId, String title) {
        return HeaderWithFontAwesome.h3(ProjectAssetsManager.getAsset(resId), title);
    }
}
