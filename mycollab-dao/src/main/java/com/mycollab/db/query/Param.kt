package com.mycollab.db.query

import org.apache.commons.lang3.builder.EqualsBuilder

import java.io.Serializable

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
abstract class Param @JvmOverloads constructor(var id: String = "") : Serializable {

    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        }
        if (obj === this) {
            return true
        }
        if (obj.javaClass != javaClass) {
            return false
        }
        val item = obj as Param?
        return EqualsBuilder().append(id, item!!.id).build()!!
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}