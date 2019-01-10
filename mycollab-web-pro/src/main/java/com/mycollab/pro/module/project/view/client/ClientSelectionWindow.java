package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.domain.SimpleClient;
import com.mycollab.common.domain.criteria.ClientSearchCriteria;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.fielddef.ClientTableFieldDef;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.FieldSelection;
import com.mycollab.vaadin.web.ui.WebThemes;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 7.0.0
 */
public class ClientSelectionWindow extends MWindow {
    private static final long serialVersionUID = 1L;

    private ClientTableDisplay tableItem;
    private FieldSelection<Client> fieldSelection;

    public ClientSelectionWindow(FieldSelection<Client> fieldSelection) {
        super(UserUIContext.getMessage(GenericI18Enum.ACTION_SELECT_VALUE, UserUIContext.getMessage(ClientI18nEnum.SINGLE)));
        this.fieldSelection = fieldSelection;
        this.withModal(true).withResizable(false).withWidth("900px").withCenter();
    }

    public void show() {
        createClientList();

        ClientSearchPanel searchPanel = new ClientSearchPanel();
        searchPanel.addSearchHandler(criteria -> tableItem.setSearchCriteria(criteria));
        this.setContent(new MVerticalLayout(searchPanel, tableItem));
        tableItem.setSearchCriteria(new ClientSearchCriteria());
    }

    private void createClientList() {
        tableItem = new ClientTableDisplay(Arrays.asList(ClientTableFieldDef.accountname, ClientTableFieldDef.city,
                ClientTableFieldDef.assignUser));

        tableItem.setWidth("100%");
        tableItem.setDisplayNumItems(10);

        tableItem.addGeneratedColumn("accountname", (source, itemId, columnId) -> {
            final SimpleClient client = tableItem.getBeanByIndex(itemId);

            return new MButton(client.getName(), clickEvent -> {
                fieldSelection.fireValueChange(client);
                close();
            }).withStyleName(WebThemes.BUTTON_LINK);
//                    .withDescription(CrmTooltipGenerator.generateToolTipClient(
//                    UserUIContext.getUserLocale(), client, AppUI.getSiteUrl()));
        });
    }
}
