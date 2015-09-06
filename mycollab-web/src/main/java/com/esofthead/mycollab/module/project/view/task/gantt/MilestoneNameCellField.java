package com.esofthead.mycollab.module.project.view.task.gantt;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
class MilestoneNameCellField extends CustomField<String> implements FieldEvents.BlurNotifier {
    private TextField field = new TextField();

    @Override
    protected Component initContent() {
        MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%");
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        field.setImmediate(true);
        field.setBuffered(true);
        field.setWidth("100%");
        Label icon = new Label(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml(), ContentMode.HTML);
        layout.with(new CssLayout(icon), field).expand(field);
        return layout;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        if (newDataSource != null) {
            String value = (String) newDataSource.getValue();
            if (value != null) {
                if (field.isReadOnly()) {
                    field.setReadOnly(false);
                    field.setValue(value);
                    field.setReadOnly(true);
                } else {
                    field.setValue(value);
                }
            }
        }

        super.setPropertyDataSource(newDataSource);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        field.setReadOnly(readOnly);
    }

    @Override
    public String getValue() {
        return field.getValue();
    }

    @Override
    public void addBlurListener(FieldEvents.BlurListener listener) {
        field.addBlurListener(listener);
    }

    @Override
    public void addListener(FieldEvents.BlurListener listener) {

    }

    @Override
    public void removeBlurListener(FieldEvents.BlurListener listener) {
        field.removeBlurListener(listener);
    }

    @Override
    public void removeListener(FieldEvents.BlurListener listener) {

    }

    @Override
    public void commit() throws SourceException, Validator.InvalidValueException {
        String value = field.getValue();
        this.setInternalValue(value);
        super.commit();
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }
}
