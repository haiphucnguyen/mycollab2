package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class BugReadFormLayoutFactory implements IFormLayoutFactory {

    private static final long serialVersionUID = 1L;

    @Override
    public Layout getLayout() {
        return null;
    }

    @Override
    public void attachField(Object propertyId, Field field) {
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();
}
