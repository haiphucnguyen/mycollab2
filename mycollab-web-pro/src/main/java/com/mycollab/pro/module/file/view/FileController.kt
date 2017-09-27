package com.mycollab.pro.module.file.view

import com.google.common.eventbus.Subscribe
import com.mycollab.eventmanager.ApplicationEventListener
import com.mycollab.module.file.event.FileEvent
import com.mycollab.vaadin.mvp.AbstractController
import com.mycollab.vaadin.mvp.PresenterResolver

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class FileController(container: FileModule) : AbstractController() {
    init {
        this.register(object : ApplicationEventListener<FileEvent.GotoList> {
            @Subscribe
            override fun handle(event: FileEvent.GotoList) {
                val presenter = PresenterResolver.getPresenter(FileMainPresenter::class.java)
                presenter.go(container, null)
            }
        })
    }
}