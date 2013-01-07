package com.esofthead.mycollab.module.project.events;

import com.esofthead.mycollab.vaadin.events.ApplicationEvent;

@SuppressWarnings("serial")
public class ProjectEvent {

    public static class GotoAdd extends ApplicationEvent {

        public GotoAdd(Object source, Object data) {
            super(source, data);
        }
    }

    public static class Save extends ApplicationEvent {

        public Save(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GetMyProjects extends ApplicationEvent {

        public GetMyProjects(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoMyProject extends ApplicationEvent {

        public GotoMyProject(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoMyProjectList extends ApplicationEvent {

        public GotoMyProjectList(Object source, Object data) {
            super(source, data);
        }
    }
}
