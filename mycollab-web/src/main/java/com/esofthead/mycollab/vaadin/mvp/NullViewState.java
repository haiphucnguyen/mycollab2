package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;

public class NullViewState extends ViewState {

    public NullViewState() {
        super(null, new EmptyPresenter(), null);
    }

    private static class EmptyPresenter implements Presenter {

        private static final long serialVersionUID = 1L;

        @Override
        public void go(ComponentContainer container, ScreenData<?> data) {
        }

        @Override
        public void go(ComponentContainer container, ScreenData<?> data,
                boolean isHistoryTrack) {
        }
    }
}
