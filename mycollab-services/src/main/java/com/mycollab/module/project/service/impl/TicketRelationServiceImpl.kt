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
package com.mycollab.module.project.service.impl

import com.mycollab.module.project.dao.TicketRelationMapper
import com.mycollab.module.project.domain.Component
import com.mycollab.module.project.domain.TicketRelation
import com.mycollab.module.project.domain.TicketRelationExample
import com.mycollab.module.project.domain.Version
import com.mycollab.module.tracker.domain.SimpleRelatedBug
import com.mycollab.module.project.service.TicketRelationService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
class TicketRelationServiceImpl(private val bugRelatedItemMapper: TicketRelationMapper) : TicketRelationService {

    override fun saveAffectedVersionsOfTicket(ticketId: Int, ticketType: String, versions: List<Version>?) {
        insertAffectedVersionsOfBug(ticketId, versions)
    }

    private fun insertAffectedVersionsOfBug(bugId: Int, versions: List<Version>?) {
        versions?.forEach {
            val relatedItem = TicketRelation()
            relatedItem.ticketid = bugId
            relatedItem.typeid = it.id
            relatedItem.type = SimpleRelatedBug.AFFVERSION
            bugRelatedItemMapper.insert(relatedItem)
        }
    }

    override fun saveFixedVersionsOfTicket(ticketId: Int, ticketType: String, versions: List<Version>?) {
        insertFixedVersionsOfBug(ticketId, versions)
    }

    private fun insertFixedVersionsOfBug(bugId: Int, versions: List<Version>?) {
        versions?.forEach {
            val relatedItem = TicketRelation()
            relatedItem.ticketid = bugId
            relatedItem.typeid = it.id
            relatedItem.type = SimpleRelatedBug.FIXVERSION
            bugRelatedItemMapper.insert(relatedItem)
        }
    }

    override fun saveComponentsOfTicket(ticketId: Int, ticketType: String, components: List<Component>?) {
        insertComponentsOfBug(ticketId, components)
    }

    private fun insertComponentsOfBug(bugId: Int, components: List<Component>?) {
        components?.forEach {
            val relatedItem = TicketRelation()
            relatedItem.ticketid = bugId
            relatedItem.typeid = it.id
            relatedItem.type = SimpleRelatedBug.COMPONENT
            bugRelatedItemMapper.insert(relatedItem)
        }
    }

    private fun deleteTrackerBugRelatedItem(bugId: Int, type: String) {
        val ex = TicketRelationExample()
        ex.createCriteria().andTicketidEqualTo(bugId).andTypeEqualTo(type)
        bugRelatedItemMapper.deleteByExample(ex)
    }


    override fun updateAffectedVersionsOfTicket(ticketId: Int, ticketType: String, versions: List<Version>?) {
        deleteTrackerBugRelatedItem(ticketId, SimpleRelatedBug.AFFVERSION)
        if (versions != null) {
            insertAffectedVersionsOfBug(ticketId, versions)
        }
    }

    override fun updateFixedVersionsOfTicket(ticketId: Int, ticketType: String, versions: List<Version>?) {
        deleteTrackerBugRelatedItem(ticketId, SimpleRelatedBug.FIXVERSION)
        if (versions != null) {
            insertFixedVersionsOfBug(ticketId, versions)
        }
    }

    override fun updateComponentsOfTicket(ticketId: Int, ticketType: String, components: List<Component>?) {
        deleteTrackerBugRelatedItem(ticketId, SimpleRelatedBug.COMPONENT)
        if (components != null) {
            insertComponentsOfBug(ticketId, components)
        }
    }
}
