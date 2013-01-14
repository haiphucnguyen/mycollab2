package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProblemReadPresenter extends AbstractPresenter<ProblemReadView> {

    private static final long serialVersionUID = 1L;

    public ProblemReadPresenter() {
        super(ProblemReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Problem>() {
                    @Override
                    public void onEdit(Problem data) {
                        EventBus.getInstance().fireEvent(
                                new ProblemEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(Problem data) {
                        ProblemService riskService = AppContext
                                .getSpringBean(ProblemService.class);
                        riskService.removeWithSession(data.getId(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new ProblemEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(Problem data) {
                        Problem cloneData = (Problem) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new ProblemEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new ProblemEvent.GotoList(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ProblemContainer problemContainer = (ProblemContainer) container;
        problemContainer.removeAllComponents();
        problemContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof Integer) {
            ProblemService problemService = AppContext
                    .getSpringBean(ProblemService.class);
            SimpleProblem problem = problemService.findProblemById((Integer) data
                    .getParams());
            view.previewItem(problem);

        }
    }
}
