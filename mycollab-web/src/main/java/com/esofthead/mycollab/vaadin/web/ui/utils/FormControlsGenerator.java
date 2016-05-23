package com.esofthead.mycollab.vaadin.web.ui.utils;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public class FormControlsGenerator {
    public static final <T> ComponentContainer generateEditFormControls(final AdvancedEditBeanForm<T> editForm) {
        return generateEditFormControls(editForm, true, true, true);
    }

    public static final <T> ComponentContainer generateEditFormControls(final AdvancedEditBeanForm<T> editForm, boolean
            isSaveBtnVisible, boolean isSaveAndNewBtnVisible, boolean isCancelBtnVisible) {
        MHorizontalLayout layout = new MHorizontalLayout();

        if (isCancelBtnVisible) {
            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    editForm.fireCancelForm();
                }
            });
            cancelBtn.setIcon(FontAwesome.MINUS);
            cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
            layout.addComponent(cancelBtn);
        }

        if (isSaveAndNewBtnVisible) {
            Button saveAndNewBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE_NEW), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    if (editForm.validateForm()) {
                        editForm.fireSaveAndNewForm();
                    }
                }
            });
            saveAndNewBtn.setIcon(FontAwesome.SHARE_ALT);
            saveAndNewBtn.setStyleName(UIConstants.BUTTON_ACTION);
            layout.addComponent(saveAndNewBtn);
        }

        if (isSaveBtnVisible) {
            final Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    if (editForm.validateForm()) {
                        editForm.fireSaveForm();
                    }
                }
            });
            saveBtn.setIcon(FontAwesome.SAVE);
            saveBtn.setStyleName(UIConstants.BUTTON_ACTION);
            layout.addComponent(saveBtn);
        }

        return layout;
    }
}
