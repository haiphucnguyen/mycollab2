package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class BugContainer extends AbstractView {

    private static final long serialVersionUID = 1L;

    public static class AddComponentData extends ScreenData<Component> {

        public AddComponentData(Component component) {
            super(component);
        }
    }
    
    public static class ReadComponentData extends ScreenData<Integer> {

        public ReadComponentData(Integer componentId) {
            super(componentId);
        }
    }
    
    public static class SearchComponentData extends ScreenData<ComponentSearchCriteria> {

        public SearchComponentData(ComponentSearchCriteria criteria) {
            super(criteria);
        }
    }
}
