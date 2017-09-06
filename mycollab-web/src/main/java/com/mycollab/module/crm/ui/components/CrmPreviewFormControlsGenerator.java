package com.mycollab.module.crm.ui.components;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.reporting.PrintButton;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CrmPreviewFormControlsGenerator<T> {
    public static final int BACK_BTN_PRESENTED = 2;
    public static final int EDIT_BTN_PRESENTED = 4;
    public static final int DELETE_BTN_PRESENTED = 8;
    public static final int CLONE_BTN_PRESENTED = 16;
    public static final int NAVIGATOR_BTN_PRESENTED = 32;
    public static final int ADD_BTN_PRESENTED = 64;
    public static final int PRINT_BTN_PRESENTED = 128;

    private AdvancedPreviewBeanForm<T> previewForm;
    private PopupButton optionBtn;
    private MHorizontalLayout layout;

    public CrmPreviewFormControlsGenerator(AdvancedPreviewBeanForm<T> editForm) {
        this.previewForm = editForm;
        layout = new MHorizontalLayout();

        optionBtn = new PopupButton();
        optionBtn.addStyleName(WebThemes.BUTTON_OPTION);
        optionBtn.setIcon(FontAwesome.ELLIPSIS_H);
    }

    public void insertToControlBlock(Button button) {
        layout.addComponent(button, 0);
    }

    public HorizontalLayout createButtonControls(final String permissionItem) {
        return createButtonControls(EDIT_BTN_PRESENTED | DELETE_BTN_PRESENTED
                | CLONE_BTN_PRESENTED | PRINT_BTN_PRESENTED | NAVIGATOR_BTN_PRESENTED
                | ADD_BTN_PRESENTED, permissionItem);
    }

    public HorizontalLayout createButtonControls(int buttonEnableFlags, String permissionItem) {
        boolean canRead = false;
        boolean canWrite = false;
        boolean canAccess = false;
        if (permissionItem != null) {
            canRead = UserUIContext.canRead(permissionItem);
            canWrite = UserUIContext.canWrite(permissionItem);
            canAccess = UserUIContext.canAccess(permissionItem);
        }

        MHorizontalLayout editBtns = new MHorizontalLayout();
        layout.addComponent(editBtns);

        OptionPopupContent popupButtonsControl = new OptionPopupContent();

        if ((buttonEnableFlags & ADD_BTN_PRESENTED) == ADD_BTN_PRESENTED) {
            MButton addBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADD), clickEvent -> {
                optionBtn.setPopupVisible(false);
                T item = previewForm.getBean();
                previewForm.fireAddForm(item);
            }).withIcon(FontAwesome.PLUS).withStyleName(WebThemes.BUTTON_ACTION).withVisible(canWrite);
            editBtns.addComponent(addBtn);
        }

        if ((buttonEnableFlags & EDIT_BTN_PRESENTED) == EDIT_BTN_PRESENTED) {
            MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT), clickEvent -> {
                optionBtn.setPopupVisible(false);
                T item = previewForm.getBean();
                previewForm.fireEditForm(item);
            }).withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION).withVisible(canWrite);
            editBtns.addComponent(editBtn);
        }

        if ((buttonEnableFlags & DELETE_BTN_PRESENTED) == DELETE_BTN_PRESENTED) {
            MButton deleteBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_DELETE), clickEvent -> {
                T item = previewForm.getBean();
                previewForm.fireDeleteForm(item);
            }).withIcon(FontAwesome.TRASH_O).withStyleName(WebThemes.BUTTON_DANGER).withVisible(canAccess);
            editBtns.addComponent(deleteBtn);
        }

        if ((buttonEnableFlags & PRINT_BTN_PRESENTED) == PRINT_BTN_PRESENTED) {
            final PrintButton printBtn = new PrintButton();
            printBtn.withListener(clickEvent -> {
                T item = previewForm.getBean();
                previewForm.firePrintForm(printBtn, item);
            }).withStyleName(WebThemes.BUTTON_OPTION).withVisible(canRead);
            editBtns.addComponent(printBtn);
        }

        if ((buttonEnableFlags & CLONE_BTN_PRESENTED) == CLONE_BTN_PRESENTED) {
            MButton cloneBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLONE), clickEvent -> {
                optionBtn.setPopupVisible(false);
                T item = previewForm.getBean();
                previewForm.fireCloneForm(item);
            }).withIcon(FontAwesome.ROAD).withVisible(canWrite);
            popupButtonsControl.addOption(cloneBtn);
        }

        optionBtn.setContent(popupButtonsControl);

        ButtonGroup navigationBtns = new ButtonGroup();
        navigationBtns.setStyleName("navigation-btns");

        if ((buttonEnableFlags & NAVIGATOR_BTN_PRESENTED) == NAVIGATOR_BTN_PRESENTED) {
            MButton previousItem = new MButton("", clickEvent -> {
                T item = previewForm.getBean();
                previewForm.fireGotoPrevious(item);
            }).withIcon(FontAwesome.CHEVRON_LEFT).withStyleName(WebThemes.BUTTON_OPTION)
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.TOOLTIP_SHOW_PREVIOUS_ITEM))
                    .withVisible(canRead);
            navigationBtns.addButton(previousItem);

            MButton nextItemBtn = new MButton("", clickEvent -> {
                T item = previewForm.getBean();
                previewForm.fireGotoNextItem(item);
            }).withIcon(FontAwesome.CHEVRON_RIGHT).withStyleName(WebThemes.BUTTON_OPTION)
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.TOOLTIP_SHOW_NEXT_ITEM))
                    .withVisible(canRead);
            navigationBtns.addButton(nextItemBtn);
        }

        layout.addComponent(navigationBtns);
        if (popupButtonsControl.getComponentCount() > 0) {
            optionBtn.setContent(popupButtonsControl);
            layout.addComponent(optionBtn);
        }
        return layout;
    }
}
