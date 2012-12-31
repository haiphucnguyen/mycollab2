package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import org.vaadin.teemu.ratingstars.RatingStars;

@ViewComponent
public class ProblemReadViewImpl extends AbstractView implements ProblemReadView {

    private static final long serialVersionUID = 1L;
    private SimpleProblem problem;
    private PreviewForm previewForm;

    public ProblemReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleProblem item) {
        problem = item;
        previewForm.setItemDataSource(new BeanItem<Problem>(item));
    }

    @Override
    public SimpleProblem getItem() {
        return problem;
    }

    @Override
    public HasPreviewFormHandlers<Problem> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<Problem> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {

                    if (propertyId.equals("raisedbyuser")) {
                        return new FormViewField(problem.getRaisedByUserFullName());
                    } else if (propertyId.equals("assigntouser")) {
                        return new FormViewField(problem.getAssignedUserFullName());
                    } else if (propertyId.equals("level")) {
                        RatingStars tinyRs = new RatingStars();
                        tinyRs.setValue(problem.getLevel());
                        tinyRs.setReadOnly(true);
                        return tinyRs;
                    } else if (propertyId.equals("datedue")) {
                        return new FormViewField(AppContext.formatDate(problem
                                .getDatedue()));
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends ProblemFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(problem.getIssuename());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<Problem>(
                        PreviewForm.this)).createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return new CommentListDepot(CommentTypeConstants.PRJ_PROBLEM, problem.getId());
            }
        }
    }
}
