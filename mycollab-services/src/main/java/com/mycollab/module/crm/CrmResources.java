package com.mycollab.module.crm;

import com.mycollab.core.MyCollabException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CrmResources {
    private static Logger LOG = LoggerFactory.getLogger(CrmResources.class);
    private static Method getResMethod;

    static {
        try {
            Class<?> resourceCls = Class.forName("com.mycollab.module.crm.ui.CrmAssetsManager");
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
