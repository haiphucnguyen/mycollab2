package com.esofthead.mycollab.vaadin.mvp;

public abstract class ViewManager {

    private static ViewManager impl = new ViewManagerImpl();

    protected abstract <T extends View> T getViewInstance(final Class<T> viewClass);

    protected abstract void resetResources();

    public static <T extends View> T getView(final Class<T> viewClass) {
        return impl.getViewInstance(viewClass);
    }

    public static void clearResources() {
        impl.resetResources();
    }
}
