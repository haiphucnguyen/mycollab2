package com.esofthead.mycollab.premium.module.file.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.file.events.FileEvent
import com.esofthead.mycollab.vaadin.mvp.{PresenterResolver, AbstractController}
import com.google.common.eventbus.Subscribe

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class FileController(container: FileModule) extends AbstractController {
    bindFileEvents()

    private def bindFileEvents() {
        this.register(new ApplicationEventListener[FileEvent.GotoList]() {
            @Subscribe def handle(event: FileEvent.GotoList) {
                val presenter: FileMainPresenter = PresenterResolver.getPresenter(classOf[FileMainPresenter])
                presenter.go(container, null)
            }
        })
    }
}
