package com.esofthead.mycollab.module.ecm.esb.impl

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock

import com.esofthead.mycollab.core.utils.{BeanUtility, StringUtils}
import com.esofthead.mycollab.lock.DistributionLockUtil
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.domain.DriveInfo
import com.esofthead.mycollab.module.ecm.esb.SaveContentEvent
import com.esofthead.mycollab.module.ecm.service.DriveInfoService
import com.esofthead.mycollab.module.file.service.RawContentService
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 1.0
  */
object SaveContentCommandImpl {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[SaveContentCommandImpl])
}

@Component("saveContentCommand") class SaveContentCommandImpl extends GenericCommand {
  @Autowired private val driveInfoService: DriveInfoService = null
  @Autowired private val rawContentService: RawContentService = null

  @AllowConcurrentEvents
  @Subscribe
  def saveContent(event: SaveContentEvent): Unit = {
    SaveContentCommandImpl.LOG.debug("Save content {} by {}", Array(BeanUtility.printBeanObj(event.content),
      event.createdUser))
    if (event.sAccountId == null) {
      return
    }
    val lock: Lock = DistributionLockUtil.getLock("ecm-" + event.sAccountId)
    var totalSize: Long = event.content.getSize
    if (StringUtils.isNotBlank(event.content.getThumbnail)) {
      totalSize += rawContentService.getSize(event.content.getThumbnail)
    }
    try {
      if (lock.tryLock(1, TimeUnit.HOURS)) {
        val driveInfo: DriveInfo = driveInfoService.getDriveInfo(event.sAccountId)
        if (driveInfo.getUsedvolume == null) {
          driveInfo.setUsedvolume(totalSize)
        }
        else {
          driveInfo.setUsedvolume(totalSize + driveInfo.getUsedvolume)
        }
        driveInfoService.saveOrUpdateDriveInfo(driveInfo)
      }
    }
    catch {
      case e: Exception => SaveContentCommandImpl.LOG.error(String.format("Error while save content %s",
        BeanUtility.printBeanObj(event.content)), e)
    } finally {
      DistributionLockUtil.removeLock("ecm-" + event.sAccountId)
      lock.unlock
    }
  }
}