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
package com.esofthead.mycollab.module.project.view.page;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.page.domain.Folder;
import com.esofthead.mycollab.module.page.service.PageService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.PageEvent;
import com.esofthead.mycollab.module.project.i18n.PageI18nEnum;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd.
 * @since 5.0.2
 */
class GroupPageAddWindow extends Window {
    private static final long serialVersionUID = 1L;

    private Folder folder;

    GroupPageAddWindow(Folder editFolder) {
        super();
        this.setModal(true);
        this.setWidth("700px");
        this.setResizable(false);
        this.center();
        MVerticalLayout content = new MVerticalLayout().withMargin(new MarginInfo(false, false, true, false));

        EditForm editForm = new EditForm();

        if (editFolder == null) {
            folder = new Folder();
            this.setCaption(AppContext.getMessage(PageI18nEnum.NEW_GROUP));
            String pagePath = CurrentProjectVariables.getCurrentPagePath();
            folder.setPath(pagePath + "/" + StringUtils.generateSoftUniqueId());
        } else {
            folder = editFolder;
            this.setCaption(AppContext.getMessage(PageI18nEnum.DETAIL_GROUP));
        }

        editForm.setBean(folder);
        content.addComponent(editForm);

        this.setContent(content);
    }

    GroupPageAddWindow() {
        this(null);
    }

    private class EditForm extends AdvancedEditBeanForm<Folder> {
        private static final long serialVersionUID = -1898444508905690238L;

        @Override
        public void setBean(final Folder item) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new EditFormFieldFactory(EditForm.this));
            super.setBean(item);
        }

        class FormLayoutFactory extends AbstractFormLayoutFactory {
            private static final long serialVersionUID = 1L;

            private GridFormLayoutHelper informationLayout;

            @Override
            public ComponentContainer getLayout() {
                final VerticalLayout layout = new VerticalLayout();
                informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 2);
                layout.addComponent(informationLayout.getLayout());

                final MHorizontalLayout controlsBtn = new MHorizontalLayout().withMargin(new MarginInfo(true, true, true, false));
                layout.addComponent(controlsBtn);

                final MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                        .withStyleName(UIConstants.BUTTON_OPTION);

                MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                    if (EditForm.this.validateForm()) {
                        PageService pageService = AppContextUtil.getSpringBean(PageService.class);
                        pageService.createFolder(folder, AppContext.getUsername());
                        folder.setCreatedTime(new GregorianCalendar());
                        folder.setCreatedUser(AppContext.getUsername());
                        GroupPageAddWindow.this.close();
                        EventBusFactory.getInstance().post(new PageEvent.GotoList(GroupPageAddWindow.this, folder.getPath()));
                    }
                }).withIcon(FontAwesome.SAVE).withStyleName(UIConstants.BUTTON_ACTION);
                saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
                controlsBtn.with(cancelBtn, saveBtn);
                layout.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            protected Component onAttachField(Object propertyId, Field<?> field) {
                if (propertyId.equals("name")) {
                    return informationLayout.addComponent(field, AppContext.getMessage(PageI18nEnum.FORM_GROUP), 0, 0);
                } else if (propertyId.equals("description")) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 1, 2, "100%");
                }
                return null;
            }
        }
    }

    private static class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<Folder> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<Folder> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("description")) {
                return new RichTextArea();
            }

            return null;
        }
    }
}
