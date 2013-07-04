/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view.component;

import com.esofthead.mycollab.module.user.PermissionFlag;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

/**
 *
 * @author haiphucnguyen
 */
public class PermissionComboBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	public PermissionComboBox() {
        super();
        this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
        this.setNullSelectionAllowed(false);
        
        BeanContainer<String, PermissionIdName> beanItem = new BeanContainer<String, PermissionIdName>(
                PermissionIdName.class);
        beanItem.setBeanIdProperty("id");
        this.setItemCaptionPropertyId("displayName");
        
        beanItem.addBean(new PermissionIdName(PermissionFlag.NO_ACCESS, "No Access"));
        beanItem.addBean(new PermissionIdName(PermissionFlag.READ_ONLY, "Read Only"));
        beanItem.addBean(new PermissionIdName(PermissionFlag.READ_WRITE, "Read & Write"));
        beanItem.addBean(new PermissionIdName(PermissionFlag.ACCESS, "Admin"));
        
        this.setContainerDataSource(beanItem);
        this.setValue(PermissionFlag.READ_ONLY);
    }
    
    public static class PermissionIdName {
        private int id;
        
        private String displayName;

        public PermissionIdName(int id, String displayName) {
            this.id = id;
            this.displayName = displayName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }
}
