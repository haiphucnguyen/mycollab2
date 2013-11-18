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
package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

/**
 * <p>
 * <b>DESCRIPTION AND FEATURES</b>
 * </p>
 * <p>
 * MessageBox is a flexible utility class for generating different styles of
 * message boxes. The message box is typically a modal dialog, with an icon on
 * the left side, a message on the right of the icon and some buttons on the
 * bottom of the dialog.
 * </p>
 * <p>
 * The class is designed to be very flexible. E.g. you can define
 * <ul>
 * <li>the window caption</li>
 * <li>different types of icons (see {@link Icon})</li>
 * <li>a simple message as string, optionally formatted with HTML elements</li>
 * <li>a complex message designed with multiple Vaadin components</li>
 * <li>how many buttons are placed on the dialog</li>
 * <li>the caption for the buttons for your specific language</li>
 * <li>the icon of the buttons (see {@link ButtonType})</li>
 * <li>one simple event listener (see {@link EventListener}) for all buttons</li>
 * <li>the buttons alignment (left, centered, right)</li>
 * <li>optionally the window width and height, if required (full automated
 * layout seems not to be possible yet)</li>
 * </ul>
 * There are different simple/complex constructors are available to influence
 * the appearance of the message box.
 * </p>
 * <p>
 * Additionally, you can override the default behavior of the dialog and its
 * components by using the published static member fields:
 * <ul>
 * <li>the size of the dialog using {@code DIALOG_DEFAULT_WIDTH} and
 * {@code DIALOG_DEFAULT_HEIGHT}</li>
 * <li>the button default alignment using {@code BUTTON_DEFAULT_ALIGNMENT}</li>
 * <li>the button default width using {@code BUTTON_DEFAULT_WIDTH}</li>
 * <li>customized icons using {@code RESOURCE_FACTORY}</li>
 * </ul>
 * The static member fields should be overridden at the start of the
 * application. (Okay, that information should not really surprise). <br>
 * <p>
 * <b>USAGE</b>
 * </p>
 * <p>
 * <code>
 * <pre> MessageBox mb = new MessageBox(getWindow(), 
 * 				"My message ...", 
 * 				MessageBox.Icon.INFO, 
 * 				"Hello World!",  
 * 				new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
 * mb.show();</pre></code> This example shows a simple message dialog, with
 * "My message ..." as dialog caption, an info icon, "Hello World!" as message
 * and an "Ok" labeled button. The dialog is displayed modally. You can simply
 * add further ButtonConfigs after the "Ok"-button. To receive an event of the
 * pressed button, add an event listener (see {@link EventListener}) as
 * parameter to the show method (see {@link #show(EventListener)} or
 * {@link #show(boolean, EventListener)}). Dialog width and height can be
 * optionally set, if required.
 * </p>
 * <br>
 * <p>
 * <b>LICENSE</b>
 * </p>
 * <p>
 * The licenses are suitable for commercial usage.
 * </p>
 * 
 * <p>
 * Code license: Apache License 2.0
 * </p>
 * 
 * <p>
 * Dialog icons:
 * <ul>
 * <li>Author: Dieter Steinwedel</li>
 * <li>License: Creative Commons Attribution 2.5 License</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Button icons:
 * <ul>
 * <li>Author: Mark James</li>
 * <li>URL: <a
 * href="http://www.famfamfam.com/lab/icons/silk/">http://www.famfamfam
 * .com/lab/icons/silk/</a></li>
 * <li>License: Creative Commons Attribution 2.5 License</li>
 * </ul>
 * </p>
 * 
 * @author Dieter Steinwedel
 */
/**
 * @author Dell
 *
 */
/**
 * @author Dell
 *
 */
/**
 * @author Dell
 * 
 */
public class MessageBox extends Window {

    private static final long serialVersionUID = 1L;

    public static String DIALOG_DEFAULT_WIDTH = "365px";
    public static String DIALOG_DEFAULT_HEIGHT = null;
    public static String BUTTON_DEFAULT_WIDTH = "100px";
    public static Alignment BUTTON_DEFAULT_ALIGNMENT = Alignment.MIDDLE_RIGHT;
    public static String ICON_DEFAULT_SIZE = "48px";
    public static String DEFAULT_STYLE_NAME = UIConstants.THEME_BLUE_LINK;

    public static VisibilityInterceptor VISIBILITY_INTERCEPTOR;

    private final Window parentWindow;
    private EventListener listener;

    private Button defaultButton;

    /**
     * Defines the possible icon types for the message box.
     * 
     * @author Dieter Steinwedel
     */
    public static enum Icon {
        NONE, QUESTION, INFO, WARN, ERROR
    }

    /**
     * Defines the possible buttons type for the message box. The button type
     * defines which icon is displayed on the button. The button type is also
     * used for the event listener (see {@link EventListener}) to determine
     * which button is pressed.
     * 
     * @author ds
     */
    public static enum ButtonType {
        OK, ABORT, CANCEL, YES, NO, CLOSE, SAVE, RETRY, IGNORE,
        /**
         * The message box is not closed on using this value. You may have to
         * explicitly close the message box.
         */
        HELP, NONE, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5
    }

    /**
     * This class defines the appearance and caption of a button displayed
     * inside the message box.
     * 
     * @author Dieter Steinwedel
     */
    public static class ButtonConfig implements Serializable {

        private static final long serialVersionUID = 1L;

        private final Resource optionalResource;
        private final ButtonType buttonType;
        private final String caption;
        private final String width;
        private final String styleName;

        /**
         * Equals to {@link ButtonConfig(ButtonType, String, String, Resource)},
         * but the button style name is set explicitly instead of using
         * {@code DEFAULT_STYLE_NAME}.
         * 
         * @param styleName
         *            an none default resource, that should be displayed as icon
         */
        public ButtonConfig(ButtonType buttonType, String styleName,
                String caption, String width, Resource optionalResource) {
            super();
            if (buttonType == null) {
                throw new IllegalArgumentException(
                        "The field buttonType cannot be null");
            }
            this.optionalResource = optionalResource;
            this.buttonType = buttonType;
            this.caption = caption;
            this.width = width;
            this.styleName = styleName;
        }

        /**
         * Equals to {@link ButtonConfig(ButtonType, String, String)}, but the
         * button icon resource is set explicitly.
         * 
         * @param optionalResource
         *            an none default resource, that should be displayed as icon
         */
        public ButtonConfig(ButtonType buttonType, String caption,
                String width, Resource optionalResource) {
            this(buttonType, DEFAULT_STYLE_NAME, caption, width,
                    optionalResource);
        }

        /**
         * Equals to {@link ButtonConfig(ButtonType, String)}, but the button
         * width is set explicitly instead of using {@code BUTTON_DEFAULT_WIDTH}
         * .
         * 
         * @param width
         *            button width
         */
        public ButtonConfig(ButtonType buttonType, String caption, String width) {
            this(buttonType, caption, width, null);
        }

        /**
         * Creates an instance of this class for defining the appearance and
         * caption of a button displayed inside the message box.
         * 
         * @param buttonType
         *            which button type (see {@link ButtonType})
         * @param caption
         *            caption of the button
         */
        public ButtonConfig(ButtonType buttonType, String caption) {
            this(buttonType, caption, BUTTON_DEFAULT_WIDTH);
        }

        /**
         * Returns the optional resource, if set.
         * 
         * @return optional resource.
         */
        public Resource getOptionalResource() {
            return optionalResource;
        }

        /**
         * Returns the button type.
         * 
         * @return button type
         */
        public ButtonType getButtonType() {
            return buttonType;
        }

        /**
         * Returns the button caption.
         * 
         * @return button caption
         */
        public String getCaption() {
            return caption;
        }

        /**
         * Returns the button width.
         * 
         * @return button width
         */
        public String getWidth() {
            return width;
        }

        /**
         * Returns the style name, if set.
         * 
         * @return optional resource.
         */
        public String getStyleName() {
            return styleName;
        }

    }

    /**
     * Intercepts the displaying and closing of the dialog.
     * 
     * @author ds
     */
    public interface VisibilityInterceptor extends Serializable {

        /**
         * Intercepts the displaying of the dialog.
         * 
         * @param parentWindow
         *            The parent Window for the <code>MessageBox</code>
         * @param displayedDialog
         *            The <code>MessageBox</code> instance to be displayed
         * @return Returns <code>false</code>, if the method implementation
         *         opens the <code>MessageBox</code> window itself. Otherwise
         *         returns <code>true</code>.
         */
        public boolean show(Window parentWindow, MessageBox displayedDialog);

        /**
         * Intercepts the closing of the dialog.
         * 
         * @param parentWindow
         *            The parent Window for the <code>MessageBox</code>
         * @param displayedDialog
         *            The <code>MessageBox</code> instance to be displayed
         * @return Returns <code>false</code>, if the method implementation
         *         closes the <code>MessageBox</code> window itself. Otherwise
         *         returns <code>true</code>.
         */
        public boolean close(Window parentWindow, MessageBox displayedDialog);

    }

    /**
     * This event listener is triggered when a button of the message box is
     * pressed.
     * 
     * @author ds
     */
    public interface EventListener extends Serializable {

        /**
         * The method is triggered when a button of the message box is pressed.
         * The parameter describes, which configured button was pressed.
         * 
         * @param buttonType
         *            pressed button
         */
        public void buttonClicked(ButtonType buttonType);

    }

    /**
     * Private implementation for handling the button events.
     * 
     * @author ds
     */
    private class ButtonClickListener implements ClickListener {

        private static final long serialVersionUID = 1L;

        private final ButtonType buttonType;

        /**
         * The constructor.
         */
        public ButtonClickListener(ButtonType buttonType) {
            this.buttonType = buttonType;
        }

        /**
         * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
         */
        @Override
        public void buttonClick(ClickEvent event) {
            if (!buttonType.equals(ButtonType.HELP)) {
                close();
            }
            if (listener != null) {
                listener.buttonClicked(buttonType);
            }
        }
    }

    /**
     * Creates an MessageBox instance.
     * 
     * @param parentWindow
     *            the parent window
     * @param dialogCaption
     *            the caption of the dialog
     * @param dialogIcon
     *            the displayed icon
     * @param message
     *            the displayed message
     * @param buttonConfigs
     *            the displayed buttons (see {@link ButtonConfig})
     */
    public MessageBox(Window parentWindow, String dialogCaption,
            Icon dialogIcon, String message, ButtonConfig... buttonConfigs) {
        this(parentWindow, DIALOG_DEFAULT_WIDTH, DIALOG_DEFAULT_HEIGHT,
                dialogCaption, dialogIcon, message, BUTTON_DEFAULT_ALIGNMENT,
                buttonConfigs);
    }

    /**
     * Similar to
     * {@link #MessageBox(Window, String, Icon, String, ButtonConfig...)}, but
     * the alignment of the buttons is an additional button.
     * 
     * @param buttonsAlignment
     *            alignment of the button.
     */
    public MessageBox(Window parentWindow, String dialogCaption,
            Icon dialogIcon, String message, Alignment buttonsAlignment,
            ButtonConfig... buttonConfigs) {
        this(parentWindow, DIALOG_DEFAULT_WIDTH, DIALOG_DEFAULT_HEIGHT,
                dialogCaption, dialogIcon, message, buttonsAlignment,
                buttonConfigs);
    }

    /**
     * Similar to
     * {@link #MessageBox(Window, String, Icon, String, Alignment, ButtonConfig...)}
     * , but the window size is defined explicitly.
     * 
     * @param dialogWidth
     *            width of the dialog (e.g. absolute "100px" or relative "50%")
     * @param dialogHeight
     *            height of the dialog (e.g. absolute "100px" or relative "50%")
     */
    public MessageBox(Window parentWindow, String dialogWidth,
            String dialogHeight, String dialogCaption, Icon dialogIcon,
            String message, Alignment buttonsAlignment,
            ButtonConfig... buttonConfigs) {
        this(parentWindow, dialogWidth, dialogHeight, dialogCaption,
                dialogIcon, new Label(message, Label.CONTENT_XHTML),
                buttonsAlignment, buttonConfigs);
    }

    /**
     * Similar to
     * {@link #MessageBox(Window, String, Icon, String, Alignment, ButtonConfig...)}
     * , but the message component is defined explicitly. The component can be
     * even a composite of a layout manager and manager further Vaadin
     * components.
     * 
     * @param messageComponent
     *            a Vaadin component
     */
    public MessageBox(Window parentWindow, String dialogWidth,
            String dialogHeight, String dialogCaption, Icon dialogIcon,
            Component messageComponent, Alignment buttonsAlignment,
            ButtonConfig... buttonConfigs) {
        super();
        this.parentWindow = parentWindow;
        setResizable(false);
        setClosable(false);
        setWidth(dialogWidth);
        setHeight(dialogHeight);
        setCaption(dialogCaption);

        GridLayout mainLayout = new GridLayout(2, 2);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setSizeFull();

        // Add Content
        messageComponent.setSizeFull();
        if (dialogIcon == null || Icon.NONE.equals(dialogIcon)) {
            mainLayout.addComponent(messageComponent, 0, 0, 1, 0);
            mainLayout.setRowExpandRatio(0, 1.0f);
            mainLayout.setColumnExpandRatio(0, 1.0f);
        } else {
            mainLayout.addComponent(messageComponent, 1, 0);
            mainLayout.setComponentAlignment(messageComponent,
                    Alignment.MIDDLE_LEFT);
            messageComponent.setSizeFull();
            mainLayout.setRowExpandRatio(0, 1.0f);
            mainLayout.setColumnExpandRatio(1, 1.0f);

            Embedded icon = null;
            switch (dialogIcon) {
            case QUESTION:
                icon = new Embedded(null,
                        MyCollabResource.newResource("icons/48/question.png"));
                break;
            case INFO:
                icon = new Embedded(null,
                        MyCollabResource.newResource("icons/48/info.png"));
                break;
            case WARN:
                icon = new Embedded(null,
                        MyCollabResource.newResource("icons/48/warn.png"));
                break;
            case ERROR:
                icon = new Embedded(null,
                        MyCollabResource.newResource("icons/48/error.png"));
                break;
            }
            mainLayout.addComponent(icon, 0, 0);
            icon.setWidth(ICON_DEFAULT_SIZE);
            icon.setHeight(ICON_DEFAULT_SIZE);
        }

        // Add Buttons
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);
        mainLayout.addComponent(buttonLayout, 0, 1, 1, 1);
        mainLayout.setComponentAlignment(buttonLayout, buttonsAlignment);
        for (ButtonConfig buttonConfig : buttonConfigs) {
            defaultButton = new Button(buttonConfig.getCaption(),
                    new ButtonClickListener(buttonConfig.getButtonType()));
            defaultButton.setStyleName(buttonConfig.getStyleName());
            if (buttonConfig.getWidth() != null) {
                defaultButton.setWidth(buttonConfig.getWidth());
            }
            if (buttonConfig.getOptionalResource() != null) {
                defaultButton.setIcon(buttonConfig.getOptionalResource());
            } else {
                // Resource icon = null;
                // switch (buttonConfig.getButtonType()) {
                // case ABORT:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.ABORT,
                // parentWindow.getApplication());
                // break;
                // case CANCEL:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.CANCEL,
                // parentWindow.getApplication());
                // break;
                // case CLOSE:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.CLOSE,
                // parentWindow.getApplication());
                // break;
                // case HELP:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.HELP,
                // parentWindow.getApplication());
                // break;
                // case OK:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.OK,
                // parentWindow.getApplication());
                // break;
                // case YES:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.YES,
                // parentWindow.getApplication());
                // break;
                // case NO:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.NO,
                // parentWindow.getApplication());
                // break;
                // case SAVE:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.SAVE,
                // parentWindow.getApplication());
                // break;
                // case RETRY:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.RETRY,
                // parentWindow.getApplication());
                // break;
                // case IGNORE:
                // icon = RESOURCE_FACTORY.loadResource(IconResource.IGNORE,
                // parentWindow.getApplication());
                // break;
                // }
                // button.setIcon(icon);
            }
            buttonLayout.addComponent(defaultButton);
        }

        setContent(mainLayout);
    }

    /**
     * Displays the message box in modal style. No listener is used.
     */
    public void show() {
        show(true, null);
    }

    /**
     * Displays the message box. No listener is used.
     * 
     * @param modal
     *            switches the message box modal or none-modal
     */
    public void show(boolean modal) {
        show(modal, null);
    }

    /**
     * Displays the message box in modal style with triggering the event
     * listener on pressing a button.
     * 
     * @param listener
     *            trigger the parameterized listener on pressing a button
     */
    public void show(EventListener listener) {
        show(true, listener);
    }

    /**
     * Displays the message box with triggering the event listener on pressing a
     * button.
     * 
     * @param modal
     *            switches the message box modal or none-modal
     * @param listener
     *            trigger the parameterized listener on pressing a button
     */
    public void show(boolean modal, EventListener listener) {
        this.listener = listener;
        setModal(modal);
        if (VISIBILITY_INTERCEPTOR == null
                || (VISIBILITY_INTERCEPTOR != null && VISIBILITY_INTERCEPTOR
                        .show(parentWindow, this))) {
            parentWindow.addWindow(this);
        }
    }

    /**
     * Closes the message box. Call this method, if the dialog should be closed
     * without a button event, e.g. by a timeout. The buttons inside the dialog
     * close automatically the message box.
     */
    @Override
    public void close() {
        if (VISIBILITY_INTERCEPTOR == null
                || (VISIBILITY_INTERCEPTOR != null && VISIBILITY_INTERCEPTOR
                        .close(parentWindow, this))) {
            parentWindow.removeWindow(this);
        }
    }

    /**
     * Get the button for custom modification.
     */

    public Button getButton() {
        return this.defaultButton;
    }

	public static void showMessagePermissionAlert() {
		MessageBox mb = new MessageBox(AppContext.getApplication()
				.getMainWindow(),
				LocalizationHelper
						.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
				Icon.WARN,
				"Sorry! You do not have permission to do this task.",
				new ButtonConfig(ButtonType.OK, "OK"));
		mb.show();
	}
}
