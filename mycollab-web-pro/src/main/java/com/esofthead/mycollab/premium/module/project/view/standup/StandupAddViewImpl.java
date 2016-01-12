package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class StandupAddViewImpl extends AbstractPageView implements StandupAddView {
    private static final long serialVersionUID = 1L;

    private AdvancedEditBeanForm<StandupReportWithBLOBs> editForm;

    public StandupAddViewImpl() {
        super();
        editForm = new AdvancedEditBeanForm<>();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(final StandupReportWithBLOBs standupReport) {
        editForm.setFormLayoutFactory(new FormLayoutFactory());
        editForm.setBeanFormFieldFactory(new EditFormFieldFactory(editForm));
        editForm.setBean(standupReport);
    }

    private static class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<StandupReportWithBLOBs> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<StandupReportWithBLOBs> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("whatlastday") || propertyId.equals("whattoday") || propertyId.equals("whatproblem")) {
                final RichTextArea richText = new RichTextArea();
                richText.setWidth("100%");
                return richText;
            }
            return null;
        }
    }

    class FormLayoutFactory extends StandupReportFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        public FormLayoutFactory() {
            super(AppContext.getMessage(StandupI18nEnum.FORM_EDIT_TITLE, AppContext.formatDate(new GregorianCalendar().getTime())));
        }

        private Layout createButtonControls() {
            return (new EditFormControlsGenerator<>(editForm)).createButtonControls(true, false, true);
        }

        @Override
        protected Layout createTopPanel() {
            return this.createButtonControls();
        }
    }

    @Override
    public HasEditFormHandlers<StandupReportWithBLOBs> getEditFormHandlers() {
        return editForm;
    }

}
