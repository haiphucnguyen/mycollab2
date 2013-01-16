/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import org.vaadin.hene.splitbutton.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserListViewImpl extends AbstractView implements UserListView {

    private static final long serialVersionUID = 1L;
    private final UserSearchPanel searchPanel;
    private SelectionOptionButton selectOptionButton;
    private UserTableDisplay tableItem;
    private final VerticalLayout listLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public UserListViewImpl() {
        this.setSpacing(true);

        searchPanel = new UserSearchPanel();
        this.addComponent(searchPanel);

        listLayout = new VerticalLayout();
        listLayout.setSpacing(true);
        this.addComponent(listLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new UserTableDisplay(new String[]{"selected", "displayName", "username", "email", "lastAccessedTime"},
                new String[]{"", "Name", "User Name", "Email", "Last Accessed Time"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleUser user = (SimpleUser) event.getData();
                if (("username".equals(event.getFieldName()) || ("displayName".equals(event.getFieldName())))) {
                    EventBus.getInstance().fireEvent(new UserEvent.GotoRead(UserListViewImpl.this, user));
                }
            }
        });

        listLayout.addComponent(constructTableActionControls());
        listLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<UserSearchCriteria> getSearchHandlers() {
        return searchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        selectOptionButton = new SelectionOptionButton(tableItem);
        layout.addComponent(selectOptionButton);

        tableActionControls = new PopupButtonControl("delete", "Delete");
        tableActionControls.addOptionItem("mail", "Mail");
        tableActionControls.addOptionItem("export", "Export");

        layout.addComponent(tableActionControls);
        layout.addComponent(selectedItemsNumberLabel);
        layout.setComponentAlignment(selectedItemsNumberLabel,
                Alignment.MIDDLE_CENTER);
        return layout;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setEnabled(true);
        selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setEnabled(false);
        selectedItemsNumberLabel.setValue("");
    }

    @Override
    public HasSelectionOptionHandlers getOptionSelectionHandlers() {
        return selectOptionButton;
    }

    @Override
    public HasPopupActionHandlers getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public HasSelectableItemHandlers<SimpleUser> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<UserSearchCriteria, SimpleUser> getPagedBeanTable() {
        return tableItem;
    }
}
