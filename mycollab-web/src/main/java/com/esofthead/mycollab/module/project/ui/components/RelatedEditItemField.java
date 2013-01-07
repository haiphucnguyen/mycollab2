package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

public class RelatedEditItemField extends CustomField implements FieldSelection {

    private static final long serialVersionUID = 1L;
    private Object bean;
    private RelatedItemComboBox relatedItemComboBox;
    private TextField itemField;
    private Embedded browseBtn;
    private Embedded clearBtn;

    public RelatedEditItemField(String[] types, Object bean) {
        this.bean = bean;
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        relatedItemComboBox = new RelatedItemComboBox(types);
        layout.addComponent(relatedItemComboBox);

        itemField = new TextField();
        itemField.setEnabled(true);
        layout.addComponent(itemField);
        layout.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

        browseBtn = new Embedded(null, new ThemeResource(
                "icons/16/browseItem.png"));
        browseBtn.addListener(new MouseEvents.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                String type = (String) relatedItemComboBox.getValue();
                if ("Task".equals(type)) {
                } else if ("MileStone".equals(type)) {
                } else if ("Bug".equals(type)) {
                } else {
                    relatedItemComboBox.focus();
                }
            }
        });

        layout.addComponent(browseBtn);
        layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

        clearBtn = new Embedded(null, new ThemeResource(
                "icons/16/clearItem.png"));
        clearBtn.addListener(new MouseEvents.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void click(ClickEvent event) {
                try {
                    PropertyUtils.setProperty(RelatedEditItemField.this.bean,
                            "type", "");
                    PropertyUtils.setProperty(RelatedEditItemField.this.bean,
                            "typeid", null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        layout.addComponent(clearBtn);
        layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

        this.setCompositionRoot(layout);
    }

    @Override
    public Class<?> getType() {
        return (new String[2]).getClass();
    }

    private class RelatedItemComboBox extends ValueComboBox {

        private static final long serialVersionUID = 1L;

        public RelatedItemComboBox(String[] types) {
            super();
            setCaption(null);
            this.setWidth("100px");
            this.loadData(types);
        }
    }

    public void setType(String type) {
        relatedItemComboBox.select(type);
        try {
            Integer typeid = (Integer) PropertyUtils
                    .getProperty(bean, "typeid");
            if (typeid != null) {
                if ("Task".equals(type)) {
                } else if ("MileStone".equals(type)) {
                } else if ("Bug".equals(type)) {
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fireValueChange(Object data) {
        try {
            if (data instanceof SimpleTask) {
                PropertyUtils.setProperty(bean, "type", "Task");
                PropertyUtils.setProperty(bean, "typeid",
                        ((SimpleTask) data).getId());
                itemField.setValue(((SimpleTask) data).getTaskname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
