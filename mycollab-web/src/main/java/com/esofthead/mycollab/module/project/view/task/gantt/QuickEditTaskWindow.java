package com.esofthead.mycollab.module.project.view.task.gantt;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.crm.i18n.TaskI18nEnum;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class QuickEditTaskWindow extends Window {
    public QuickEditTaskWindow(GanttExt gantt, GanttItemWrapper task) {
        super("Quick Edit Task");
        this.setWidth("600px");
        this.setModal(true);
        this.setResizable(false);
        this.setClosable(true);
        this.center();

        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);

        EditForm editForm = new EditForm();
        content.addComponent(editForm);
        editForm.setBean(task.getTask());
    }

    private class EditForm extends AdvancedEditBeanForm<SimpleTask> {
        @Override
        public void setBean(final SimpleTask item) {
            this.setFormLayoutFactory(new FormLayoutFactory());
//            this.setBeanFormFieldFactory(new EditFormFieldFactory(EditForm.this));
            super.setBean(item);
        }

        class FormLayoutFactory implements IFormLayoutFactory {
            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public ComponentContainer getLayout() {
                VerticalLayout layout = new VerticalLayout();
                this.informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 2);
                layout.addComponent(this.informationLayout.getLayout());

                MHorizontalLayout buttonControls = new MHorizontalLayout();
                buttonControls.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
                Button updateBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {

                    }
                });
                updateBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

                Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        QuickEditTaskWindow.this.close();
                    }
                });
                cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
                buttonControls.with(updateBtn, cancelBtn);

                layout.addComponent(buttonControls);
                layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            public void attachField(Object propertyId, Field<?> field) {
                if (propertyId.equals("name")) {
//                    informationLayout.addComponent(field, AppContext.getMessage(TaskI18nEnum.FORM_SUBJECT), );
                } else if (propertyId.equals("startDate")) {
                    this.informationLayout.addComponent(field, AppContext.getMessage(TaskI18nEnum.FORM_START_DATE), 0, 0);
                } else if (propertyId.equals("endDate")) {
                    this.informationLayout.addComponent(field, "Comment", 0, 1, 2, "100%");
                }
            }
        }
    }
}
