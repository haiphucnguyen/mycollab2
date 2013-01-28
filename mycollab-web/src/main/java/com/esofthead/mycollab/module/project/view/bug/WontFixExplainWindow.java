/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author haiphucnguyen
 */
public class WontFixExplainWindow extends Window {

    private SimpleBug bug;
    private EditForm editForm;
    
    private VersionMultiSelectField fixedVersionSelect;

    public WontFixExplainWindow(SimpleBug bug) {
        this.bug = bug;
        this.setWidth("720px");
        editForm = new EditForm();
        this.addComponent(editForm);
        editForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
    }

    private class EditForm extends AdvancedEditBeanForm<Bug> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory implements IFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public Layout getLayout() {
                VerticalLayout layout = new VerticalLayout();
                informationLayout = new GridFormLayoutHelper(2, 6);
                informationLayout.getLayout().setWidth("700px");

                layout.addComponent(informationLayout.getLayout());

                HorizontalLayout controlsBtn = new HorizontalLayout();
                controlsBtn.setSpacing(true);
                layout.addComponent(controlsBtn);

                Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        WontFixExplainWindow.this.close();
                    }
                });
                cancelBtn.setStyleName("link");
                controlsBtn.addComponent(cancelBtn);

                Button wonFixBtn = new Button("Won't Fix", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                wonFixBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                controlsBtn.addComponent(wonFixBtn);

                layout.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);

                return layout;
            }

            @Override
            public void attachField(Object propertyId, Field field) {
                if (propertyId.equals("resolution")) {
                    informationLayout.addComponent(field, "Resolution", 0, 0);
                } else if (propertyId.equals("assignuser")) {
                    informationLayout.addComponent(field, "Assign User", 0, 1, 2, "100%");
                } else if (propertyId.equals("fixedVersions")) {
                    informationLayout.addComponent(field, "Fixed Versions", 0, 2, 2, "100%");
                } else if (propertyId.equals("id")) {
                    informationLayout.addComponent(field, "Comments", 0, 3, 2, UIConstants.DEFAULT_2XCONTROL_WIDTH);
                }
            }
        }

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("resolution")) {
                    return new BugResolutionComboBox();
                } else if (propertyId.equals("assignuser")) {
                    return new UserComboBox();
                } else if (propertyId.equals("fixedVersions")) {
                    fixedVersionSelect = new VersionMultiSelectField();
                    return fixedVersionSelect;
                } else if (propertyId.equals("id")) {
                    RichTextArea richText = new RichTextArea();
                    richText.setNullRepresentation("");
                    return richText;
                }


                return null;
            }
        }
    }
}
