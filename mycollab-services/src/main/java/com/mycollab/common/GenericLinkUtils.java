package com.mycollab.common;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public final class GenericLinkUtils {
    public static final String URL_PREFIX_PARAM = "#";

    private GenericLinkUtils() {
    }

    /**
     * @param params
     * @return
     */
    public static String encodeParam(Object... params) {
        StringBuilder paramStr = new StringBuilder("");
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                return "";
            }
            paramStr.append(params[i].toString());
            if (i != params.length - 1) {
                paramStr.append("/");
            }
        }
        return UrlEncodeDecoder.encode(paramStr.toString());
    }
}
