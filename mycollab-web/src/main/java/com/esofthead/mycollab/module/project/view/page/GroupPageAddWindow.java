package com.esofthead.mycollab.module.project.view.page;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.page.domain.Folder;
import com.esofthead.mycollab.module.page.service.PageService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.PageEvent;
import com.esofthead.mycollab.module.project.i18n.Page18InEnum;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.ui.form.field.RichTextEditField;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

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
            this.setCaption(AppContext
                    .getMessage(Page18InEnum.DIALOG_NEW_GROUP_TITLE));
            String pagePath = CurrentProjectVariables.getCurrentPagePath();
            folder.setPath(pagePath + "/"
                    + StringUtils.generateSoftUniqueId());
        } else {
            folder = editFolder;
            this.setCaption(AppContext
                    .getMessage(Page18InEnum.DIALOG_EDIT_GROUP_TITLE));
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
            this.setBeanFormFieldFactory(new EditFormFieldFactory(
                    EditForm.this));
            super.setBean(item);
        }

        class FormLayoutFactory implements IFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public ComponentContainer getLayout() {
                final VerticalLayout layout = new VerticalLayout();
                this.informationLayout = new GridFormLayoutHelper(2, 2,
                        "100%", "167px", Alignment.TOP_LEFT);
                this.informationLayout.getLayout().setWidth("100%");
                this.informationLayout.getLayout().setMargin(false);
                this.informationLayout.getLayout().addStyleName(
                        "colored-gridlayout");

                layout.addComponent(this.informationLayout.getLayout());

                final MHorizontalLayout controlsBtn = new MHorizontalLayout().withMargin(new MarginInfo(true, true, true,
                        false));
                layout.addComponent(controlsBtn);

                final Button cancelBtn = new Button(
                        AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(
                                    final Button.ClickEvent event) {
                                GroupPageAddWindow.this.close();
                            }
                        });
                cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
                controlsBtn.addComponent(cancelBtn);
                controlsBtn.setComponentAlignment(cancelBtn,
                        Alignment.MIDDLE_LEFT);

                final Button saveBtn = new Button(
                        AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(
                                    final Button.ClickEvent event) {
                                if (EditForm.this.validateForm()) {
                                    PageService pageService = ApplicationContextUtil
                                            .getSpringBean(PageService.class);
                                    pageService.createFolder(folder,
                                            AppContext.getUsername());
                                    folder.setCreatedTime(new GregorianCalendar());
                                    folder.setCreatedUser(AppContext
                                            .getUsername());
                                    GroupPageAddWindow.this.close();
                                    EventBusFactory.getInstance().post(new PageEvent.GotoList(GroupPageAddWindow.this,
                                            folder.getPath()));
                                }
                            }
                        });
                saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
                saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
                controlsBtn.addComponent(saveBtn);
                controlsBtn.setComponentAlignment(saveBtn,
                        Alignment.MIDDLE_RIGHT);

                layout.setComponentAlignment(controlsBtn,
                        Alignment.MIDDLE_RIGHT);

                return layout;
            }

            @Override
            public void attachField(Object propertyId, Field<?> field) {
                if (propertyId.equals("name")) {
                    this.informationLayout.addComponent(field,
                            AppContext.getMessage(Page18InEnum.FORM_GROUP),
                            0, 0);
                } else if (propertyId.equals("description")) {
                    this.informationLayout.addComponent(field, AppContext
                                    .getMessage(GenericI18Enum.FORM_DESCRIPTION),
                            0, 1, 2, "100%", Alignment.MIDDLE_LEFT);
                }

            }
        }
    }

    private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<Folder> {
        private static final long serialVersionUID = 1L;

        public EditFormFieldFactory(GenericBeanForm<Folder> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("description")) {
                return new RichTextEditField();
            }

            return null;
        }
    }
}
