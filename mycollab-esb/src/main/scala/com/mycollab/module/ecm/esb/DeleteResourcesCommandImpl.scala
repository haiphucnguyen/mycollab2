package com.mycollab.module.ecm.esb

import java.util.concurrent.TimeUnit

import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.core.utils.StringUtils
import com.mycollab.concurrent.DistributionLockUtil
import com.mycollab.module.ecm.service.DriveInfoService
import com.mycollab.module.esb.GenericCommand
import com.mycollab.module.file.service.RawContentService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
object DeleteResourcesCommandImpl {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[DeleteResourcesCommandImpl])
}

@Component class DeleteResourcesCommandImpl extends GenericCommand {
  @Autowired private val rawContentService: RawContentService = null
  @Autowired private val driveInfoService: DriveInfoService = null
  
  @AllowConcurrentEvents
  @Subscribe
  def removeResource(event: DeleteResourcesEvent): Unit = {
    if (event.sAccountId == null) {
      for (path <- event.paths) {
        if (StringUtils.isNotBlank(path)) {
          rawContentService.removePath(path)
        }
      }
      return
    }
    if (event.isUpdateDriveInfo) {
      val lock = DistributionLockUtil.getLock("ecm-" + event.sAccountId)
      try {
        if (lock.tryLock(1, TimeUnit.HOURS)) {
          var totalSize = 0L
          val driveInfo = driveInfoService.getDriveInfo(event.sAccountId)
          for (path <- event.paths) {
            if (StringUtils.isNotBlank(path)) {
              totalSize += rawContentService.getSize(path)
              rawContentService.removePath(path)
            }
          }
          if (driveInfo.getUsedvolume == null || (driveInfo.getUsedvolume < totalSize)) {
            driveInfo.setUsedvolume(0L)
          } else {
            driveInfo.setUsedvolume(driveInfo.getUsedvolume - totalSize)
          }
          driveInfoService.saveOrUpdateDriveInfo(driveInfo)
        }
      }
      catch {
        case e: Exception => DeleteResourcesCommandImpl.LOG.error("Error while delete content " + event.paths.mkString, e)
      } finally {
        DistributionLockUtil.removeLock("ecm-" + event.sAccountId)
        lock.unlock()
      }
    }
  }
}