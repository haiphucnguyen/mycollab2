package com.mycollab.module.project.httpmapping

import com.mycollab.common.{InvalidTokenException, UrlTokenizer}
import com.mycollab.core.MyCollabException
import com.mycollab.core.utils.StringUtils
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.page.domain.Page
import com.mycollab.module.page.service.PageService
import com.mycollab.module.project.event.ProjectEvent
import com.mycollab.module.project.view.parameters.{PageScreenData, ProjectScreenData}
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.UserUIContext
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class PageUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  
  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      try {
        val tokenizer = UrlTokenizer(params(0))
        val projectId = tokenizer.getInt
        val pagePath = tokenizer.getRemainValue
        val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
          new PageScreenData.Search(pagePath))
        EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
      }
      catch {
        case e: Exception => throw new MyCollabException(e)
      }
    }
  }
  
  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      try {
        val tokenizer = UrlTokenizer(params(0))
        val projectId = tokenizer.getInt
        val pagePath = tokenizer.getRemainValue
        val pageService = AppContextUtil.getSpringBean(classOf[PageService])
        val page = pageService.getPage(pagePath, UserUIContext.getUsername)
        if (page != null) {
          val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new PageScreenData.Read(page))
          EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
        }
        else {
          val chain = new PageActionChain(new ProjectScreenData.Goto(projectId))
          EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
        }
      }
      catch {
        case e: Exception => throw new MyCollabException(e)
      }
    }
  }
  
  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      try {
        val tokenizer = UrlTokenizer(params(0))
        val projectId = tokenizer.getInt
        val pagePath = tokenizer.getRemainValue
        val pageService = AppContextUtil.getSpringBean(classOf[PageService])
        val page = pageService.getPage(pagePath, UserUIContext.getUsername)
        if (page != null) {
          val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new PageScreenData.Edit(page))
          EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
        }
        else {
          val chain = new PageActionChain(new ProjectScreenData.Goto(projectId))
          EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
        }
      }
      catch {
        case e: InvalidTokenException => throw new MyCollabException(e)
      }
    }
  }
  
  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      try {
        val tokenizer = UrlTokenizer(params(0))
        val projectId = tokenizer.getInt
        val pagePath = tokenizer.getRemainValue
        val page = new Page
        page.setPath(pagePath + "/" + StringUtils.generateSoftUniqueId)
        val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new PageScreenData.Add(page))
        EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
      }
      catch {
        case e: Exception => throw new MyCollabException(e)
      }
    }
  }
  
}
