package org.vaadin.addon.customfield.demo;

import org.vaadin.addon.customfield.demo.field.BooleanField;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BooleanFieldExample extends CustomComponent {

    /**
     * Data model class with two boolean fields.
     */
    public static class TwoBooleans {
        private boolean normal;
        private boolean custom;

        public void setNormal(boolean normal) {
            this.normal = normal;
        }

        public boolean isNormal() {
            return normal;
        }

        public void setCustom(boolean custom) {
            this.custom = custom;
        }

        public boolean isCustom() {
            return custom;
        }
    }

    public BooleanFieldExample() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        layout.addComponent(new Label(
                "A customized field (a two-state button) for editing a boolean value."));

        final Form form = new Form();
        form.setFormFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId,
                    Component uiContext) {
                if ("custom".equals(propertyId)) {
                    return new BooleanField();
                }
                return super.createField(item, propertyId, uiContext);
            }
        });
        final TwoBooleans data = new TwoBooleans();
        form.setItemDataSource(new BeanItem<TwoBooleans>(data));

        layout.addComponent(form);

        Button submit = new Button("Submit", new ClickListener() {
            public void buttonClick(ClickEvent event) {
                form.commit();
                layout.getWindow().showNotification(
                                "The custom boolean field value is "
                                        + data.isCustom()
                                        + ".<br>"
                                        + "The checkbox (default boolean field) value is "
                                        + data.isNormal() + ".");
            }
        });
        layout.addComponent(submit);

        setCompositionRoot(layout);
    }

}
