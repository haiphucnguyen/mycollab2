package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.service.ClientService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ui.FieldSelection;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 7.0.0
 */
public class ClientSelectionField extends CustomField<Integer> implements FieldSelection<Client> {
    private static final long serialVersionUID = 1L;

    private TextField clientNameField = new TextField();
    private Client client = null;

    private void clearValue() {
        clientNameField.setValue("");
        this.client = null;
    }

    @Override
    protected Component initContent() {
        MHorizontalLayout layout = new MHorizontalLayout().withFullWidth()
                .withDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        clientNameField.setEnabled(true);
        clientNameField.setWidth("100%");

        MButton browseBtn = new MButton("", clickEvent -> {
            ClientSelectionWindow clientWindow = new ClientSelectionWindow(ClientSelectionField.this);
            UI.getCurrent().addWindow(clientWindow);
            clientWindow.show();
        }).withIcon(VaadinIcons.ELLIPSIS_H).withStyleName(WebThemes.BUTTON_OPTION, WebThemes.BUTTON_SMALL_PADDING);

        MButton clearBtn = new MButton("", clickEvent -> clearValue()).withIcon(VaadinIcons.TRASH)
                .withStyleName(WebThemes.BUTTON_OPTION, WebThemes.BUTTON_SMALL_PADDING);

        layout.with(clientNameField, browseBtn, clearBtn).expand(clientNameField);
        return layout;
    }

    @Override
    public void fireValueChange(Client data) {
        this.client = data;
        clientNameField.setValue(client.getName());
    }

    @Override
    protected void doSetValue(Integer value) {
        ClientService clientService = AppContextUtil.getSpringBean(ClientService.class);
        client = clientService.findById(value, AppUI.getAccountId());
        if (client != null) {
            clientNameField.setValue(client.getName());
        }
    }

    @Override
    public Integer getValue() {
        return (client != null) ? client.getId() : null;
    }
}
