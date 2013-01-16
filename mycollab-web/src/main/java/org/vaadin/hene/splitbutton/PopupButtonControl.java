package org.vaadin.hene.splitbutton;

import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import java.util.HashSet;
import java.util.Set;

public class PopupButtonControl extends SplitButton implements
        HasPopupActionHandlers {

    private static final long serialVersionUID = 1L;
    private VerticalLayout selectContent;
    private Set<PopupActionHandler> handlers;

    public PopupButtonControl(final String id, final Button button) {
        super(button, new SplitButton.PopupButton());

        this.addStyleName(UIConstants.SPLIT_BUTTON);

        initPopupButton(id, button.getCaption());
    }

    public PopupButtonControl(final String id, final String defaultName) {
        super();
        this.setCaption(defaultName);

        initPopupButton(id, defaultName);
    }

    private void initPopupButton(final String id, final String defaultName) {
        this.setData(id);
        this.addStyleName(UIConstants.SPLIT_BUTTON);

        this.addClickListener(new SplitButton.SplitButtonClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                changeOption(id, defaultName);
                PopupButtonControl.this.setPopupVisible(false);
            }
        });

        selectContent = new VerticalLayout();
        selectContent.setWidth("100px");
        this.setComponent(selectContent);
    }

    public void addOptionItem(final String id, final String name) {
        this.addOptionItem(id, name, true);
    }

    public void addOptionItem(final String id, final String name,
            boolean isEnable) {
        ButtonLink optionBtn = new ButtonLink(name, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                changeOption(id, name);
                PopupButtonControl.this.setPopupVisible(false);
            }
        });
        optionBtn.setEnabled(isEnable);
        selectContent.addComponent(optionBtn);
    }

    private void changeOption(String id, String caption) {
        if (handlers != null) {
            for (PopupActionHandler handler : handlers) {
                handler.onSelect(id, caption);
            }
        }
    }

    @Override
    public void addPopupActionHandler(PopupActionHandler handler) {
        if (handlers == null) {
            handlers = new HashSet<PopupActionHandler>();
        }
        handlers.add(handler);

    }
}
