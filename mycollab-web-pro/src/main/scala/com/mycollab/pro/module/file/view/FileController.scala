package com.mycollab.pro.module.file.view

import com.mycollab.eventmanager.ApplicationEventListener
import com.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}
import com.google.common.eventbus.Subscribe
import com.mycollab.module.file.event.FileEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class FileController(container: FileModule) extends AbstractController {
  bindFileEvents()

  private def bindFileEvents() {
    this.register(new ApplicationEventListener[FileEvent.GotoList]() {
      @Subscribe def handle(event: FileEvent.GotoList) {
        val presenter = PresenterResolver.getPresenter(classOf[FileMainPresenter])
        presenter.go(container, null)
      }
    })
  }
}
