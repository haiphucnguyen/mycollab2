package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
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
    
    public static class EditComponentData extends ScreenData<Component> {

        public EditComponentData(Component component) {
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
    
    public static class AddVersionData extends ScreenData<Version> {

        public AddVersionData(Version version) {
            super(version);
        }
    }
    
    public static class EditVersionData extends ScreenData<Version> {

        public EditVersionData(Version version) {
            super(version);
        }
    }
    
    public static class ReadVersionData extends ScreenData<Integer> {

        public ReadVersionData(Integer componentId) {
            super(componentId);
        }
    }
    
    public static class SearchVersionData extends ScreenData<VersionSearchCriteria> {

        public SearchVersionData(VersionSearchCriteria criteria) {
            super(criteria);
        }
    }
}
