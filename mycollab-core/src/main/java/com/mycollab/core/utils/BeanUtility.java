package com.mycollab.core.utils;

import com.rits.cloning.Cloner;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Utility class to print bean properties. This class is used for debug purpose
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class BeanUtility {

    public static String printBeanObj(Object bean) {
        return ToStringBuilder.reflectionToString(bean, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static <B> B deepClone(B b) {
        return new Cloner().deepClone(b);
    }
}
