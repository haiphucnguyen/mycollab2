package com.mycollab.common

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
object GenericLinkUtils {
    val URL_PREFIX_PARAM = "#"

    /**
     * @param params
     * @return
     */
    fun encodeParam(vararg params: Any): String {
        val paramStr = StringBuilder("")
        for (i in params.indices) {
            paramStr.append(params[i].toString())
            if (i != params.size - 1) {
                paramStr.append("/")
            }
        }
        return UrlEncodeDecoder.encode(paramStr.toString())
    }
}
