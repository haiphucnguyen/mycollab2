package com.mycollab.premium.module.user.accountsettings.view

import com.google.common.eventbus.Subscribe
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.db.arguments.{NumberSearchField, SetSearchField}
import com.mycollab.eventmanager.ApplicationEventListener
import com.mycollab.module.billing.RegisterStatusConstants
import com.mycollab.module.user.accountsettings.billing.view.IBillingPresenter
import com.mycollab.module.user.accountsettings.customize.view.AccountSettingPresenter
import com.mycollab.module.user.accountsettings.profile.view.ProfilePresenter
import com.mycollab.module.user.accountsettings.setup.view.SetupPresenter
import com.mycollab.module.user.accountsettings.team.view.UserPermissionManagementPresenter
import com.mycollab.module.user.accountsettings.view.AccountModule
import com.mycollab.module.user.accountsettings.view.events.{AccountBillingEvent, ProfileEvent, SettingEvent, SetupEvent}
import com.mycollab.module.user.accountsettings.view.parameters.SettingExtScreenData.{GeneralSetting, ThemeCustomize}
import com.mycollab.module.user.accountsettings.view.parameters.{BillingScreenData, RoleScreenData, UserScreenData}
import com.mycollab.module.user.domain.criteria.{RoleSearchCriteria, UserSearchCriteria}
import com.mycollab.module.user.domain.{Role, SimpleUser}
import com.mycollab.module.user.events.{RoleEvent, UserEvent}
import com.mycollab.vaadin.AppUI.getAccountId
import com.mycollab.vaadin.{AppUI, UserUIContext}
import com.mycollab.vaadin.mvp.{AbstractController, PresenterResolver}

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class UserAccountController(container: AccountModule) extends AbstractController {
  bindProfileEvents()
  bindBillingEvents()
  bindRoleEvents()
  bindUserEvents()
  bingSettingEvents()

  if (!SiteConfiguration.isDemandEdition) {
    bindSetupEvents()
  }

  private def bindBillingEvents(): Unit = {
    this.register(new ApplicationEventListener[AccountBillingEvent.CancelAccount]() {
      @Subscribe def handle(event: AccountBillingEvent.CancelAccount) {
        val presenter = PresenterResolver.getPresenter(classOf[IBillingPresenter])
        presenter.go(container, new BillingScreenData.CancelAccount)
      }
    })
    this.register(new ApplicationEventListener[AccountBillingEvent.GotoSummary]() {
      @Subscribe def handle(event: AccountBillingEvent.GotoSummary) {
        val presenter = PresenterResolver.getPresenter(classOf[IBillingPresenter])
        presenter.go(container, new BillingScreenData.BillingSummary)
      }
    })
    this.register(new ApplicationEventListener[AccountBillingEvent.GotoHistory]() {
      @Subscribe def handle(event: AccountBillingEvent.GotoHistory) {
        val presenter = PresenterResolver.getPresenter(classOf[IBillingPresenter])
        presenter.go(container, new BillingScreenData.BillingHistory)
      }
    })
  }

  private def bindProfileEvents(): Unit = {
    this.register(new ApplicationEventListener[ProfileEvent.GotoProfileView]() {
      @Subscribe def handle(event: ProfileEvent.GotoProfileView) {
        val presenter = PresenterResolver.getPresenter(classOf[ProfilePresenter])
        presenter.go(container, null)
      }
    })
  }

  private def bindUserEvents(): Unit = {
    this.register(new ApplicationEventListener[UserEvent.GotoAdd]() {
      @Subscribe def handle(event: UserEvent.GotoAdd) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        presenter.go(container, new UserScreenData.Add(new SimpleUser))
      }
    })
    this.register(new ApplicationEventListener[UserEvent.GotoEdit]() {
      @Subscribe def handle(event: UserEvent.GotoEdit) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        val user = event.getData.asInstanceOf[SimpleUser]
        presenter.go(container, new UserScreenData.Edit(user))
      }
    })
    this.register(new ApplicationEventListener[UserEvent.GotoRead]() {
      @Subscribe def handle(event: UserEvent.GotoRead) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        presenter.go(container, new UserScreenData.Read(event.getData.asInstanceOf[String]))
      }
    })
    this.register(new ApplicationEventListener[UserEvent.GotoList]() {
      @Subscribe def handle(event: UserEvent.GotoList) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        val criteria = new UserSearchCriteria
        criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId))
        criteria.setRegisterStatuses(new SetSearchField[String](RegisterStatusConstants.ACTIVE, RegisterStatusConstants.NOT_LOG_IN_YET))
        presenter.go(container, new UserScreenData.Search(criteria))
      }
    })
  }

  private def bindRoleEvents(): Unit = {
    this.register(new ApplicationEventListener[RoleEvent.GotoAdd]() {
      @Subscribe def handle(event: RoleEvent.GotoAdd) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        presenter.go(container, new RoleScreenData.Add(new Role))
      }
    })
    this.register(new ApplicationEventListener[RoleEvent.GotoEdit]() {
      @Subscribe def handle(event: RoleEvent.GotoEdit) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        val role = event.getData.asInstanceOf[Role]
        presenter.go(container, new RoleScreenData.Edit(role))
      }
    })
    this.register(new ApplicationEventListener[RoleEvent.GotoRead]() {
      @Subscribe def handle(event: RoleEvent.GotoRead) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        presenter.go(container, new RoleScreenData.Read(event.getData.asInstanceOf[Integer]))
      }
    })
    this.register(new ApplicationEventListener[RoleEvent.GotoList]() {
      @Subscribe def handle(event: RoleEvent.GotoList) {
        val presenter = PresenterResolver.getPresenter(classOf[UserPermissionManagementPresenter])
        val criteria = new RoleSearchCriteria
        criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId))
        presenter.go(container, new RoleScreenData.Search(criteria))
      }
    })
  }

  private def bingSettingEvents(): Unit = {
    this.register(new ApplicationEventListener[SettingEvent.GotoGeneralSetting]() {
      @Subscribe def handle(event: SettingEvent.GotoGeneralSetting) {
        val presenter = PresenterResolver.getPresenter(classOf[AccountSettingPresenter])
        presenter.go(container, new GeneralSetting())
      }
    })

    this.register(new ApplicationEventListener[SettingEvent.GotoTheme]() {
      @Subscribe def handle(event: SettingEvent.GotoTheme) {
        val presenter = PresenterResolver.getPresenter(classOf[AccountSettingPresenter])
        presenter.go(container, new ThemeCustomize())
      }
    })
  }

  private def bindSetupEvents(): Unit = {
    this.register(new ApplicationEventListener[SetupEvent.GotoSetupPage]() {
      @Subscribe def handle(event: SetupEvent.GotoSetupPage) {
        val presenter = PresenterResolver.getPresenter(classOf[SetupPresenter])
        presenter.go(container, null)
      }
    })
  }
}