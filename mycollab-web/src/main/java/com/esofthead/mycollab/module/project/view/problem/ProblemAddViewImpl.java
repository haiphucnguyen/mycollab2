package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.RichTextEditor;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import java.util.HashMap;
import java.util.Map;

@ViewComponent
public class ProblemAddViewImpl extends AbstractView implements ProblemAddView,
        IFormAddView<Problem> {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Problem problem;
    private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
            5);

    static {
        valueCaptions.put(1, "Epic Fail");
        valueCaptions.put(2, "Poor");
        valueCaptions.put(3, "OK");
        valueCaptions.put(4, "Good");
        valueCaptions.put(5, "Excellent");
    }

    public ProblemAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Problem problem) {
        this.problem = problem;
        editForm.setItemDataSource(new BeanItem<Problem>(problem));
    }

    private class EditForm extends AdvancedEditBeanForm<Problem> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends ProblemFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Create Problem");
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Problem>(EditForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createTopPanel() {
                return createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return createButtonControls();
            }
        }

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {

                if (propertyId.equals("description")) {
                    return new RichTextEditor();
                } else if (propertyId.equals("raisedbyuser")) {
                    return new UserComboBox();
                } else if (propertyId.equals("type")) {
                } else if (propertyId.equals("assigntouser")) {
                    return new UserComboBox();
                } else if (propertyId.equals("priority")) {
                } else if (propertyId.equals("status")) {
                } else if (propertyId.equals("level")) {
                } else if (propertyId.equals("resolution")) {
                }

                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<Problem> getEditFormHandlers() {
        return editForm;
    }
}
