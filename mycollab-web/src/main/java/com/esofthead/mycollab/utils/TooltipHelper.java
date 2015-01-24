package com.esofthead.mycollab.utils;

import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Div;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
public class TooltipHelper {

    public static Div buildDivTooltipEnable(String uid) {
        Div div1 = new Div().setId("div1" + uid);
        div1.setAttribute("class", "stickytooltip");

        Div div12 = new Div();
        div12.setAttribute("style", "padding:5px");
        div1.appendChild(div12);

        Div div13 = new Div().setId("div13" + uid);
        div13.setAttribute("class", "atip");
        div13.setAttribute("style", "width:500px");
        div12.appendChild(div13);

        Div div14 = new Div().setId("div14" + uid);
        div13.appendChild(div14);
        return div1;
    }

    public static String buildUserHtmlTooltip(String uid, String user) {
        String arg3 = "'" + uid + "'";
        String arg4 = "'" + user + "'";
        String arg5 = "'" + AppContext.getSiteUrl() + "tooltip/'";
        String arg6 = "'" + AppContext.getSiteUrl() + "'";
        String arg7 = AppContext.getSession().getTimezone();
        String arg8 = "'" + AppContext.getAccountId() + "'";
        String arg9 = "'" + AppContext.getUserLocale().toString() + "'";

        return String.format(
                "return showUserTooltip(%s,%s,%s,%s,%s,%s,%s);", arg3, arg4, arg5,
                arg6, arg7, arg8, arg9);
    }
}
