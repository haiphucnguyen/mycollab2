package com.mycollab.module.project;

import com.mycollab.core.MyCollabException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class ProjectLinkParams {
    private static Pattern PATTERN = Pattern.compile("^\\w{1,3}-\\d*$");

    public static boolean isValidParam(String param) {
        Matcher matcher = PATTERN.matcher(param);
        return matcher.find();
    }

    public static String getProjectShortName(String param) {
        int index = param.indexOf("-");
        if (index > 0) {
            return param.substring(0, index);
        } else {
            throw new MyCollabException("Invalid param " + param);
        }
    }

    public static Integer getItemKey(String param) {
        int index = param.indexOf("-");
        if (index > 0) {
            return Integer.parseInt(param.substring(index + 1));
        } else {
            throw new MyCollabException("Invalid param " + param);
        }
    }
}
