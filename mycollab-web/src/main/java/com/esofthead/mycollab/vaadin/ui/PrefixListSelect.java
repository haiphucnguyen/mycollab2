package com.esofthead.mycollab.vaadin.ui;

public class PrefixListSelect extends ValueComboBox {

    private static final long serialVersionUID = 1L;

    public PrefixListSelect() {
        super();
        setCaption(null);
        this.loadData(new String[]{"Mr.", "Ms.", "Mrs.", "Dr.", "Prof."});
    }
}
