package com.mycollab.schedule.jobs

import org.quartz.{JobExecutionContext, JobExecutionException}
import org.slf4j.LoggerFactory
import org.springframework.dao.{DataAccessException, TransientDataAccessException}
import org.springframework.scheduling.quartz.QuartzJobBean

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
abstract class GenericQuartzJobBean extends QuartzJobBean {
  private val LOG = LoggerFactory.getLogger(classOf[GenericQuartzJobBean])

  @throws(classOf[JobExecutionException])
  protected def executeInternal(context: JobExecutionContext) {
    try {
      executeJob(context)
      GenericQuartzJobBean.isSendingDbError = false
    }
    catch {
      case e@(_: DataAccessException | _: TransientDataAccessException) => {
        if (!GenericQuartzJobBean.isSendingDbError) {
          LOG.error("Exception in running schedule", e)
          GenericQuartzJobBean.isSendingDbError = true
        }
      }
      case e: Exception => LOG.error("Exception in running schedule", e)
    }
  }

  @throws(classOf[JobExecutionException])
  protected def executeJob(context: JobExecutionContext): Unit
}

object GenericQuartzJobBean {
  private var isSendingDbError = false
}
