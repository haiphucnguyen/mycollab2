/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.events.*;
import com.esofthead.mycollab.module.crm.i18n.*;
import com.esofthead.mycollab.module.crm.ui.CrmAssetsManager;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.ui.ServiceMenu;
import com.esofthead.mycollab.web.IDesktopModule;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class CrmModule extends AbstractPageView implements IDesktopModule {
    private static final long serialVersionUID = 1L;

    private MHorizontalLayout serviceMenuContainer;
    private final MVerticalLayout container;

    public CrmModule() {
        this.setStyleName("crm-module");
        ControllerRegistry.addController(new CrmController(this));

        container = new MVerticalLayout().withWidth("100%").withSpacing(false)
                .withMargin(false).withStyleName("crmContainer");

        CrmToolbar toolbar = new CrmToolbar();
        container.addComponent(toolbar);

        this.addComponent(container);
        this.setComponentAlignment(container, Alignment.MIDDLE_CENTER);
    }

    public void gotoCrmDashboard() {
        EventBusFactory.getInstance().post(new CrmEvent.GotoHome(this, null));
    }

    public void addView(PageView view) {
        if (container.getComponentCount() > 1) {
            container.replaceComponent(container.getComponent(1), view.getWidget());
        } else {
            container.addComponent(view.getWidget());
        }
    }

    @Override
    public MHorizontalLayout buildMenu() {
        if (serviceMenuContainer == null) {
            serviceMenuContainer = new MHorizontalLayout();
            final ServiceMenu serviceMenu = new ServiceMenu();
            serviceMenu.addService(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_DASHBOARD_HEADER), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new CrmEvent.GotoHome(this, null));
                }
            });

            serviceMenu.addService(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_ACCOUNTS_HEADER), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new AccountEvent.GotoList(this, null));
                }
            });

            serviceMenu.addService(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new ContactEvent.GotoList(this, null));
                }
            });

            serviceMenu.addService(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_LEADS_HEADER), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new LeadEvent.GotoList(this, null));
                }
            });

            serviceMenu.addService(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new OpportunityEvent.GotoList(this, null));
                }
            });

            serviceMenu.addService(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_CASES_HEADER), new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    EventBusFactory.getInstance().post(new CaseEvent.GotoAdd(this, null));
                }
            });

            serviceMenuContainer.with(serviceMenu);

            Button.ClickListener listener = new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            };

            PopupButton addPopupMenu = new PopupButton("Quick Add");
            addPopupMenu.addStyleName("quickadd-btn");
            addPopupMenu.setDirection(Alignment.BOTTOM_LEFT);
            OptionPopupContent popupButtonsControl = new OptionPopupContent().withWidth("150px");

            Button newAccountBtn = new Button(AppContext.getMessage(AccountI18nEnum.BUTTON_NEW_ACCOUNT), listener);
            newAccountBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));
            newAccountBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.ACCOUNT));
            popupButtonsControl.addOption(newAccountBtn);

            Button newContactBtn = new Button(AppContext.getMessage(ContactI18nEnum.BUTTON_NEW_CONTACT), listener);
            newContactBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CONTACT));
            newContactBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.CONTACT));
            popupButtonsControl.addOption(newContactBtn);

            Button newCampaignBtn = new Button(AppContext.getMessage(CampaignI18nEnum.BUTTON_NEW_CAMPAIGN), listener);
            newCampaignBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
            newCampaignBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.CAMPAIGN));
            popupButtonsControl.addOption(newCampaignBtn);

            Button newOpportunityBtn = new Button(AppContext.getMessage(OpportunityI18nEnum.BUTTON_NEW_OPPORTUNITY), listener);
            newOpportunityBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
            newOpportunityBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.OPPORTUNITY));
            popupButtonsControl.addOption(newOpportunityBtn);

            Button newLeadBtn = new Button(AppContext.getMessage(LeadI18nEnum.BUTTON_NEW_LEAD), listener);
            newLeadBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_LEAD));
            newLeadBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.LEAD));
            popupButtonsControl.addOption(newLeadBtn);

            Button newCaseBtn = new Button(AppContext.getMessage(CaseI18nEnum.BUTTON_NEW_CASE), listener);
            newCaseBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CASE));
            newCaseBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.CASE));
            popupButtonsControl.addOption(newCaseBtn);

            Button newTaskBtn = new Button(AppContext.getMessage(TaskI18nEnum.BUTTON_NEW_TASK), listener);
            newTaskBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_TASK));
            newTaskBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.TASK));
            popupButtonsControl.addOption(newTaskBtn);

            Button newCallBtn = new Button(AppContext.getMessage(CallI18nEnum.BUTTON_NEW_CALL), listener);
            newCallBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CALL));
            newCallBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.CALL));
            popupButtonsControl.addOption(newCallBtn);

            Button newMeetingBtn = new Button(AppContext.getMessage(MeetingI18nEnum.BUTTON_NEW_MEETING), listener);
            newMeetingBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_MEETING));
            newMeetingBtn.setIcon(CrmAssetsManager.getAsset(CrmTypeConstants.MEETING));
            popupButtonsControl.addOption(newMeetingBtn);

            addPopupMenu.setContent(popupButtonsControl);
            addPopupMenu.addStyleName("quickadd-btn");
            serviceMenuContainer.with(addPopupMenu).withAlign(addPopupMenu, Alignment.MIDDLE_LEFT);

        }
        return serviceMenuContainer;
    }
}
