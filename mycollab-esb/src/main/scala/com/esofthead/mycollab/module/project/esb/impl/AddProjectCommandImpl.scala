package com.esofthead.mycollab.module.project.esb.impl

import java.util.GregorianCalendar

import com.esofthead.mycollab.common.dao.OptionValMapper
import com.esofthead.mycollab.common.domain.{OptionVal, OptionValExample}
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.project.esb.AddProjectEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component class AddProjectCommandImpl extends GenericCommand {
    @Autowired val optionValMapper: OptionValMapper = null

    @AllowConcurrentEvents
    @Subscribe
    def addProject(event: AddProjectEvent): Unit = {
        val ex: OptionValExample = new OptionValExample
        ex.createCriteria().andIsdefaultEqualTo(true).andSaccountidEqualTo(event.accountId)
        import scala.collection.JavaConversions._
        val defaultOptions = optionValMapper.selectByExample(ex)
        for (option <- defaultOptions) {
            val prjOption = new OptionVal;
            prjOption.setCreatedtime(new GregorianCalendar().getTime)
            prjOption.setDescription(option.getDescription)
            prjOption.setExtraid(event.projectId)
            prjOption.setIsdefault(false)
            prjOption.setSaccountid(event.accountId)
            prjOption.setType(option.getType)
            prjOption.setTypeval(option.getTypeval)
            prjOption.setRefoption(option.getId)
            optionValMapper.insert(prjOption)
        }
    }
}
