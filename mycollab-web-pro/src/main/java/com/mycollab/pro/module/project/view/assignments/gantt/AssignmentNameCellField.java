package com.mycollab.pro.module.project.view.assignments.gantt;

import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class AssignmentNameCellField extends CustomField<String> implements FieldEvents.BlurNotifier {
    private TextField field = new TextField();
    private String type;

    public AssignmentNameCellField(String type) {
        this.type = type;
    }

    @Override
    public void addBlurListener(FieldEvents.BlurListener blurListener) {
        field.addBlurListener(blurListener);
    }

    @Override
    public void addListener(FieldEvents.BlurListener blurListener) {

    }

    @Override
    public void removeBlurListener(FieldEvents.BlurListener blurListener) {
        field.removeBlurListener(blurListener);
    }

    @Override
    public void removeListener(FieldEvents.BlurListener blurListener) {

    }

    @Override
    public void commit() throws SourceException, Validator.InvalidValueException {
        String value = field.getValue();
        this.setInternalValue(value);
        super.commit();
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        field.setPropertyDataSource(newDataSource);
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        field.setReadOnly(readOnly);
    }

    public void setDescription(String description) {
        field.setDescription(description);
    }

    @Override
    public String getValue() {
        return field.getValue();
    }

    @Override
    public boolean isModified() {
        return field.isModified();
    }

    @Override
    protected Component initContent() {
        MHorizontalLayout layout = new MHorizontalLayout().withFullWidth();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        field.setBuffered(true);
        field.setWidth("100%");
        Label icon = ELabel.fontIcon(ProjectAssetsManager.getAsset(type)).withWidthUndefined();
        layout.with(icon, field).expand(field);
        return layout;
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }
}
