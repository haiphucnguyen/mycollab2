package com.mycollab.core.utils

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
object ScalaUtils {
    /**
     *
     * @param value
     * @return null if value is can not be converted to the string array
     */
    def stringConvertSeqToArray(value: Any): Array[String] = {
        if (value.isInstanceOf[Seq[String]]) return value.asInstanceOf[Seq[String]].toArray[String]
        else if (value.isInstanceOf[Array[String]]) return value.asInstanceOf[Array[String]]
        else return null
    }
}
