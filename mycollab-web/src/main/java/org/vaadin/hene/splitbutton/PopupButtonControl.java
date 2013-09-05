package org.vaadin.hene.splitbutton;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class PopupButtonControl extends SplitButton implements
        HasPopupActionHandlers {

    private static final long serialVersionUID = 1L;
    private VerticalLayout selectContent;
    private Set<PopupActionHandler> handlers;

    public PopupButtonControl(final String id, final Button button) {
        super(button, new SplitButton.PopupButton());

        addStyleName(UIConstants.THEME_GRAY_LINK);

        initPopupButton(id, button.getCaption());
    }

    public PopupButtonControl(final String id, final String defaultName) {
        super();
        setCaption(defaultName);
        addStyleName(UIConstants.THEME_GRAY_LINK);
        initPopupButton(id, defaultName);
    }

    public void addOptionItem(final String id, final String name) {
        this.addOptionItem(id, name, true);
    }

    public void addOptionItem(final String id, final String name,
            final boolean isEnable) {
        final Button optionBtn = new Button(name, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                changeOption(id, name);
                PopupButtonControl.this.setPopupVisible(false);
            }
        });
        optionBtn.addStyleName("link");
        optionBtn.setEnabled(isEnable);
        selectContent.addComponent(optionBtn);
    }

    @Override
    public void addPopupActionHandler(final PopupActionHandler handler) {
        if (handlers == null) {
            handlers = new HashSet<PopupActionHandler>();
        }
        handlers.add(handler);

    }

    private void changeOption(final String id, final String caption) {
        if (handlers != null) {
            for (final PopupActionHandler handler : handlers) {
                handler.onSelect(id, caption);
            }
        }
    }

    private void initPopupButton(final String id, final String defaultName) {
        setData(id);
        addStyleName(UIConstants.THEME_BLUE_LINK);

        addClickListener(new SplitButton.SplitButtonClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void splitButtonClick(
                    final SplitButton.SplitButtonClickEvent event) {
                changeOption(id, defaultName);
                PopupButtonControl.this.setPopupVisible(false);
            }
        });

        selectContent = new VerticalLayout();
        selectContent.setWidth("100px");
        setComponent(selectContent);
    }
}
