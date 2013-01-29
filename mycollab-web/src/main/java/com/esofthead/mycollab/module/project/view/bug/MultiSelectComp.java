package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addon.customfield.CustomField;

@SuppressWarnings("serial")
public abstract class MultiSelectComp extends CustomField {

    private TextField componentsDisplay;
    private MultipleItemsPopupSelection selectBtn;

    public MultiSelectComp() {
        this.setWidth("100%");
        GridLayout content = new GridLayout(2, 1);
        content.setSpacing(false);

        componentsDisplay = new TextField();
        componentsDisplay.setNullRepresentation("");
        componentsDisplay.setReadOnly(true);
        componentsDisplay.setWidth("210px");
        componentsDisplay.addStyleName("noBorderRight");

        selectBtn = new MultipleItemsPopupSelection(this);
        selectBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                initData();

                if (lstStringValue.size() > 0) {
                    for (int i = 0; i < lstStringValue.size(); i++) {
                        selectBtn.checkItem(lstStringValue.get(i));
                    }
                }
            }
        });

        content.addComponent(componentsDisplay, 0, 0);
        content.setComponentAlignment(componentsDisplay, Alignment.MIDDLE_CENTER);

        selectBtn.addStyleName("nonPopupIndicator");
        selectBtn.addStyleName(UIConstants.SELECT_BG);
        content.addComponent(selectBtn, 1, 0);
        content.setComponentAlignment(selectBtn, Alignment.MIDDLE_CENTER);

        this.setCompositionRoot(content);
    }

    abstract void initData();

    protected void loadData(List<String> values) {
        for (int i = 0; i < values.size(); i++) {
            selectBtn.addItemComponent(values.get(i));
        }
    }

    protected List<String> getSelectedItem() {
        return lstStringValue;
    }

    public void setSelectedItem(String item) {
        componentsDisplay.setReadOnly(false);
        if (!lstStringValue.contains(item)) {
            lstStringValue.add(item);
        }
        componentsDisplay.setValue(buildStringDisplay());
        componentsDisplay.setReadOnly(true);
    }
    private List<String> lstStringValue = new ArrayList<String>();

    private String buildStringDisplay() {
        String str = "";
        for (int i = 0; i < lstStringValue.size(); i++) {
            if (i == lstStringValue.size() - 1) {
                str += lstStringValue.get(i);
            } else {
                str += lstStringValue.get(i) + ", ";
            }
        }
        return str;
    }

    public void removeSelectedItem(String item) {
        componentsDisplay.setReadOnly(false);
        lstStringValue.remove(item);
        componentsDisplay.setValue(buildStringDisplay());
        componentsDisplay.setReadOnly(true);
    }

    @Override
    public Class<?> getType() {
        // TODO Auto-generated method stub
        return null;
    }
}
