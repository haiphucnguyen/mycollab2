package com.esofthead.mycollab.vaadin.ui;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class PrefixNameComboBox extends ValueComboBox {
    private static final long serialVersionUID = 1L;

    public PrefixNameComboBox() {
        super();
        this.setWidth("80px");
        setCaption(null);
        this.loadData("Mr.", "Ms.", "Mrs.", "Dr.", "Prof.");
    }
}
