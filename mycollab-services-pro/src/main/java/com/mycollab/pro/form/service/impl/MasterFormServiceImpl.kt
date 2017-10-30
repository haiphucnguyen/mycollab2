/**
 * Copyright © MyCollab
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */
package com.mycollab.pro.form.service.impl

import com.mycollab.core.MyCollabException
import com.mycollab.core.cache.CacheKey
import com.mycollab.core.utils.JsonDeSerializer
import com.mycollab.form.dao.FormSectionFieldMapper
import com.mycollab.form.dao.FormSectionMapper
import com.mycollab.form.dao.FormSectionMapperExt
import com.mycollab.form.domain.FormSection
import com.mycollab.form.domain.FormSectionExample
import com.mycollab.form.domain.FormSectionField
import com.mycollab.form.service.MasterFormService
import com.mycollab.form.view.builder.type.AbstractDynaField
import com.mycollab.form.view.builder.type.DynaForm
import com.mycollab.form.view.builder.type.DynaSection
import com.mycollab.form.view.builder.type.DynaSection.LayoutType
import org.apache.commons.collections.CollectionUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MasterFormServiceImpl(private val formSectionMapper: FormSectionMapper,
                            private val formSectionMapperExt: FormSectionMapperExt,
                            private val formSectionFieldMapper: FormSectionFieldMapper) : MasterFormService {

    override fun findCustomForm(sAccountId: Int, moduleName: String): DynaForm? {
        val sections = formSectionMapperExt.findSections(sAccountId, moduleName)

        if (CollectionUtils.isEmpty(sections)) {
            return null
        } else {
            val form = DynaForm()

            for (section in sections) {
                val dySection = DynaSection()
                dySection.layoutType = LayoutType.fromVal(section.layouttype)

                //                dySection.setHeader(section.getName());
                dySection.orderIndex = section.layoutindex!!
                dySection.isDeletedSection = section.isdeletesection!!

                val fields = section.fields
                if (CollectionUtils.isNotEmpty(fields)) {
                    for (field in fields!!) {
                        val fieldType = "$TYPE_PACKAGE${field.fieldtype}"
                        val clsType: Class<*>
                        try {
                            clsType = Class.forName(fieldType)
                        } catch (e: ClassNotFoundException) {
                            throw MyCollabException(e)
                        }

                        val dynaField = JsonDeSerializer.fromJson(field.fieldformat, clsType) as AbstractDynaField
                        //                        dynaField.setDisplayName(field.getDisplayname());
                        dynaField.fieldIndex = field.fieldindex!!
                        dynaField.fieldName = field.fieldname
                        dynaField.isMandatory = field.ismandatory
                        dynaField.isRequired = field.isrequired
                        dynaField.isCustom = field.iscustom

                        dySection.fields(dynaField)
                    }
                }

                form.sections(dySection)
            }

            return form
        }
    }

    override fun saveCustomForm(@CacheKey sAccountId: Int, moduleName: String, form: DynaForm) {
        LOG.debug("Save form section")

        val sectionCount = form.sectionCount

        if (sectionCount > 0) {
            LOG.debug("Remove existing form section of module $moduleName")
            val ex = FormSectionExample()
            ex.createCriteria().andSaccountidEqualTo(sAccountId).andModuleEqualTo(moduleName)
            formSectionMapper.deleteByExample(ex)
        }

        for (i in 0 until sectionCount) {
            val section = form.getSection(i)

            val formSection = FormSection()
            formSection.module = moduleName
            formSection.layoutindex = section.orderIndex
            formSection.layouttype = LayoutType.toVal(section.layoutType)
            //            formSection.setName(section.getHeader());
            formSection.isdeletesection = section.isDeletedSection
            formSection.saccountid = sAccountId

            formSectionMapper.insertAndReturnKey(formSection)
            val sectionId = formSection.id

            LOG.debug("Save section name ${section.header} of module $moduleName of account $sAccountId successfully, Return id is $sectionId")

            val fieldCount = section.fieldCount
            for (j in 0 until fieldCount) {
                val field = section.getField(j)

                val dbField = FormSectionField()
                dbField.sectionid = sectionId
                //                dbField.setDisplayname(field.getDisplayName());
                dbField.fieldformat = JsonDeSerializer.toJson(field)
                dbField.fieldindex = field.fieldIndex
                dbField.fieldname = field.fieldName
                dbField.fieldtype = field.javaClass.simpleName
                dbField.ismandatory = field.isMandatory
                dbField.isrequired = field.isRequired
                dbField.iscustom = field.isCustom

                LOG.debug("Save field ${field.displayName} with name ${field.fieldName}")
                formSectionFieldMapper.insertAndReturnKey(dbField)
            }
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(MasterFormServiceImpl::class.java)
        private val TYPE_PACKAGE = "com.mycollab.form.view.builder.type."
    }
}