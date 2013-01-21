/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Layout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskReadViewImpl extends AbstractView implements TaskReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleTask task;

    public TaskReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleTask task) {
        this.task = task;
        previewForm.setItemDataSource(new BeanItem<Task>(task));
    }

    @Override
    public HasPreviewFormHandlers<Task> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends TaskFormComponent {

        @Override
        TaskFormLayoutFactory getFormLayoutFactory() {
            return new FormLayoutFactory();
        }

        class FormLayoutFactory extends TaskFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(task.getTaskname());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<Task>(PreviewForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return null;
            }
        }
    }

    @Override
    public SimpleTask getItem() {
        return task;
    }
}
