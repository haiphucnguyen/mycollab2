package com.mycollab.pro.module.project.service.impl

import com.google.common.eventbus.AsyncEventBus
import com.mycollab.cache.CleanCacheEvent
import com.mycollab.core.cache.CacheKey
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import com.mycollab.module.project.dao.MilestoneMapperExt
import com.mycollab.module.project.domain.ItemTimeLogging
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.mycollab.module.project.service.*
import com.mycollab.module.tracker.dao.ComponentMapperExt
import com.mycollab.module.tracker.dao.VersionMapperExt
import com.mycollab.module.tracker.service.BugService
import com.mycollab.module.tracker.service.ComponentService
import com.mycollab.module.tracker.service.VersionService
import com.mycollab.pro.module.project.dao.ItemTimeLoggingMapper
import com.mycollab.pro.module.project.dao.ItemTimeLoggingMapperExt
import com.mycollab.spring.AppContextUtil
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Timestamp
import java.sql.Types
import java.util.*
import javax.sql.DataSource

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
class ItemTimeLoggingServiceImpl(private val itemTimeLoggingMapper: ItemTimeLoggingMapper,
                                 private val itemTimeLoggingMapperExt: ItemTimeLoggingMapperExt,
                                 private val milestoneMapperExt: MilestoneMapperExt,
                                 private val componentMapperExt: ComponentMapperExt,
                                 private val versionMapperExt: VersionMapperExt,
                                 private val asyncEventBus: AsyncEventBus) : DefaultService<Int, ItemTimeLogging, ItemTimeLoggingSearchCriteria>(), ItemTimeLoggingService {

    override val crudMapper: ICrudGenericDAO<Int, ItemTimeLogging>
        get() = itemTimeLoggingMapper as ICrudGenericDAO<Int, ItemTimeLogging>

    override val searchMapper: ISearchableDAO<ItemTimeLoggingSearchCriteria>
        get() = itemTimeLoggingMapperExt

    override fun saveWithSession(record: ItemTimeLogging, username: String?): Int {
        val result = super.saveWithSession(record, username)
        cleanCache(record.saccountid)
        return result
    }

    override fun updateWithSession(record: ItemTimeLogging, username: String?): Int {
        val result = super.updateWithSession(record, username)
        cleanCache(record.saccountid)
        return result
    }

    override fun massRemoveWithSession(items: List<ItemTimeLogging>, username: String?, sAccountId: Int) {
        super.massRemoveWithSession(items, username, sAccountId)
        cleanCache(sAccountId)
    }

    override fun getTotalHoursByCriteria(criteria: ItemTimeLoggingSearchCriteria): Double {
        val value = itemTimeLoggingMapperExt.getTotalHoursByCriteria(criteria)
        return value
    }

    override fun batchSaveTimeLogging(timeLoggings: List<ItemTimeLogging>, @CacheKey sAccountId: Int) {
        val dataSource = AppContextUtil.getSpringBean(DataSource::class.java)
        val jdbcTemplate = JdbcTemplate(dataSource)
        jdbcTemplate.batchUpdate(
                "insert into m_prj_time_logging (projectId, type, typeid, logValue, loguser, createdTime, lastUpdatedTime, sAccountId, logForDay, isBillable, createdUser, " + "note) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                object : BatchPreparedStatementSetter {

                    @Throws(SQLException::class)
                    override fun setValues(ps: PreparedStatement, i: Int) {
                        val itemLogging = timeLoggings[i]
                        ps.setInt(1, itemLogging.projectid!!)
                        ps.setString(2, itemLogging.type)

                        if (itemLogging.typeid == null) {
                            ps.setNull(3, Types.INTEGER)
                        } else {
                            ps.setInt(3, itemLogging.typeid!!)
                        }

                        ps.setDouble(4, itemLogging.logvalue!!)
                        ps.setString(5, itemLogging.loguser)
                        ps.setTimestamp(6, Timestamp(GregorianCalendar().time.time))
                        ps.setTimestamp(7, Timestamp(GregorianCalendar().time.time))
                        ps.setInt(8, itemLogging.saccountid!!)
                        // TODO: fix issue
//                        ps.setTimestamp(9, Timestamp(itemLogging.logforday.time))
                        ps.setBoolean(10, itemLogging.isbillable!!)
                        ps.setString(11, itemLogging.createduser)
                        ps.setString(12, itemLogging.note)
                    }

                    override fun getBatchSize(): Int {
                        return timeLoggings.size
                    }
                })
        cleanCache(sAccountId)
    }

    private fun cleanCache(sAccountId: Int) {
        asyncEventBus.post(CleanCacheEvent(sAccountId, arrayOf(ProjectService::class.java,
                MilestoneService::class.java, ProjectTaskService::class.java, BugService::class.java,
                ComponentService::class.java, VersionService::class.java, RiskService::class.java,
                ItemTimeLoggingService::class.java, ProjectMemberService::class.java)))
    }

    override fun getTotalBillableHoursByMilestone(milestoneId: Int, sAccountId: Int): Double =
            milestoneMapperExt.getTotalBillableHours(milestoneId)

    override fun getTotalNonBillableHoursByMilestone(milestoneId: Int, sAccountId: Int): Double =
            milestoneMapperExt.getTotalNonBillableHours(milestoneId)

    override fun getRemainHoursByMilestone(milestoneId: Int, sAccountId: Int): Double =
            milestoneMapperExt.getRemainHours(milestoneId)

    override fun getTotalBillableHoursByComponent(componentId: Int, @CacheKey sAccountId: Int): Double =
            componentMapperExt.getTotalBillableHours(componentId)

    override fun getTotalNonBillableHoursByComponent(componentId: Int, @CacheKey sAccountId: Int): Double =
            componentMapperExt.getTotalNonBillableHours(componentId)

    override fun getRemainHoursByComponent(componentId: Int, @CacheKey sAccountId: Int): Double =
            componentMapperExt.getRemainHours(componentId)

    override fun getTotalBillableHoursByVersion(versionId: Int, @CacheKey sAccountId: Int): Double =
            versionMapperExt.getTotalBillableHours(versionId)

    override fun getTotalNonBillableHoursByVersion(versionId: Int, @CacheKey sAccountId: Int): Double =
            versionMapperExt.getTotalNonBillableHours(versionId)

    override fun getRemainHoursByVersion(versionId: Int, @CacheKey sAccountId: Int): Double =
            versionMapperExt.getRemainHours(versionId)
}
