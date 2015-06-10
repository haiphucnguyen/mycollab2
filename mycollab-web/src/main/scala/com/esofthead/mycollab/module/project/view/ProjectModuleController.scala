package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.eventmanager.ApplicationEventListener
import com.esofthead.mycollab.module.project.events.ProjectEvent.GotoMyProject
import com.esofthead.mycollab.module.project.events.{FollowingTicketEvent, ProjectEvent, TimeTrackingEvent}
import com.esofthead.mycollab.module.project.view.parameters.FollowingTicketsScreenData
import com.esofthead.mycollab.vaadin.mvp.{AbstractController, PageActionChain, PresenterResolver, ScreenData}
import com.google.common.eventbus.Subscribe

/**
 * @author MyCollab Ltd.
 * @since 5.0.9
 */
class ProjectModuleController(val container: ProjectModule) extends AbstractController {
    bindProjectEvents()
    bindFollowingTicketEvents()
    bindTimeTrackingEvents()

    private def bindProjectEvents(): Unit = {
        this.register(new ApplicationEventListener[ProjectEvent.GotoMyProject]() {
            @Subscribe override def handle(event: GotoMyProject): Unit = {
                val presenter: ProjectViewPresenter = PresenterResolver.getPresenter(classOf[ProjectViewPresenter])
                presenter.handleChain(container, event.getData.asInstanceOf[PageActionChain])
            }
        })
    }

    private def bindFollowingTicketEvents(): Unit = {
        this.register(new ApplicationEventListener[FollowingTicketEvent.GotoMyFollowingItems]() {
            @Subscribe def handle(event: FollowingTicketEvent.GotoMyFollowingItems) {
                val presenter: FollowingTicketPresenter = PresenterResolver.getPresenter(classOf[FollowingTicketPresenter])
                presenter.go(container, new FollowingTicketsScreenData.GotoMyFollowingItems(event.getData
                    .asInstanceOf[java.util.List[Integer]]))
            }
        })
    }

    private def bindTimeTrackingEvents(): Unit = {
        this.register(new ApplicationEventListener[TimeTrackingEvent.GotoTimeTrackingView]() {
            @Subscribe def handle(event: TimeTrackingEvent.GotoTimeTrackingView) {
                val presenter: TimeTrackingSummaryPresenter = PresenterResolver.getPresenter(classOf[TimeTrackingSummaryPresenter])
                presenter.go(container, new ScreenData[AnyRef](event.getData))
            }
        })
    }
}
