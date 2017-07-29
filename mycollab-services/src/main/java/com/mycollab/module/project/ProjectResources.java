package com.mycollab.module.project;

import com.mycollab.core.MyCollabException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectResources {
    private static Logger LOG = LoggerFactory.getLogger(ProjectResources.class);
    private static Method getResMethod;

    public static final String T_PRIORITY_HIGHT_IMG = "icons/12/priority_high.png";
    public static final String T_PRIORITY_LOW_IMG = "icons/12/priority_low.png";
    public static final String T_PRIORITY_MEDIUM_IMG = "icons/12/priority_medium.png";
    public static final String T_PRIORITY_NONE_IMG = "icons/12/priority_none.png";
    public static final String T_PRIORITY_URGENT_IMG = "icons/12/priority_urgent.png";

    public static final String B_PRIORITY_BLOCKER_IMG_12 = "icons/12/priority_urgent.png";
    public static final String B_PRIORITY_CRITICAL_IMG_12 = "icons/12/priority_high.png";
    public static final String B_PRIORITY_MAJOR_IMG_12 = "icons/12/priority_medium.png";
    public static final String B_PRIORITY_MINOR_IMG_12 = "icons/12/priority_low.png";
    public static final String B_PRIORITY_TRIVIAL_IMG_12 = "icons/12/priority_none.png";

    public static final String B_SEVERITY_CRITICAL_IMG_12 = "icons/12/severity_critical.png";
    public static final String B_SEVERITY_MAJOR_IMG_12 = "icons/12/severity_major.png";
    public static final String B_SEVERITY_MINOR_IMG_12 = "icons/12/severity_minor.png";
    public static final String B_SEVERITY_TRIVIAL_IMG_12 = "icons/12/severity_trivial.png";

    static {
        try {
            Class<?> resourceCls = Class.forName("com.mycollab.module.project.ui.ProjectAssetsManager");
            getResMethod = resourceCls.getMethod("toHexString", String.class);
        } catch (Exception e) {
            throw new MyCollabException("Can not reload resource", e);
        }
    }

    public static String getFontIconHtml(String type) {
        try {
            String codePoint = (String) getResMethod.invoke(null, type);
            return String.format("<span class=\"v-icon\" style=\"font-family: FontAwesome;\">%s;</span>", codePoint);
        } catch (Exception e) {
            LOG.error("Can not get resource type {}", type);
            return "";
        }
    }
}
