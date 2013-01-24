package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;
import java.util.HashMap;
import org.vaadin.hene.popupbutton.PopupButton;

@SuppressWarnings("serial")
public class MultipleItemsPopupSelection extends PopupButton {

    private Panel panel;
    private MultiSelectComp versionItem;
    private HashMap<String, CheckBox> hashMapCheckbox = new HashMap<String, CheckBox>();

    public MultipleItemsPopupSelection(MultiSelectComp owner) {
        this.versionItem = owner;
        panel = new Panel();
        panel.setStyleName(Reindeer.PANEL_LIGHT);
        panel.setHeight("200px");
        panel.setWidth("300px");
        this.addComponent(panel);
    }

    public void unCheckItem(String itemName) {
        CheckBox chk = hashMapCheckbox.get(itemName);
        chk.setValue(false);
    }

    public void addItemComponent(String itemName) {
        final CheckBox chkItem = new CheckBox(itemName);
        chkItem.setImmediate(true);
        chkItem.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                Boolean value = (Boolean) chkItem.getValue();
                if (value) {
                    versionItem.setSelectedItem(chkItem.getCaption());
                } else {
                    versionItem.removeSelectedItem(chkItem.getCaption());
                }
            }
        });
        if (!hashMapCheckbox.containsKey(itemName)) {
        	 hashMapCheckbox.put(itemName, chkItem);
             panel.addComponent(chkItem);
        }
    }
}
