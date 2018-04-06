/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.ui

import com.mycollab.vaadin.event.IEditFormHandler
import com.mycollab.vaadin.event.HasEditFormHandlers

import java.util.ArrayList

/**
 * Generic attachForm with java bean as datasource. It includes validation
 * against bean input
 *
 * @param <B> java bean as datasource map with attachForm fields
 * @author MyCollab Ltd.
 * @since 2.0
</B> */
open class AdvancedEditBeanForm<B> : GenericBeanForm<B>(), HasEditFormHandlers<B> {

    private var editFormHandlers: MutableList<IEditFormHandler<B>>? = null

    /**
     * Validate attachForm against data
     *
     * @return true if data is valid, otherwise return false and show result to
     * attachForm
     */
    fun validateForm(): Boolean {
        fieldFactory.commit()
        return isValid
    }

    override fun addFormHandler(editFormHandler: IEditFormHandler<B>) {
        if (editFormHandlers == null) {
            editFormHandlers = ArrayList()
        }

        editFormHandlers!!.add(editFormHandler)
    }

    fun fireSaveForm() {
        if (editFormHandlers != null) {
            for (editFormHandler in editFormHandlers!!) {
                editFormHandler.onSave(this.getBean())
            }
        }
    }

    fun fireSaveAndNewForm() {
        if (editFormHandlers != null) {
            for (editFormHandler in editFormHandlers!!) {
                editFormHandler.onSaveAndNew(this.getBean())
            }
        }
    }

    fun fireCancelForm() {
        if (editFormHandlers != null) {
            for (editFormHandler in editFormHandlers!!) {
                editFormHandler.onCancel()
            }
        }
    }
}
