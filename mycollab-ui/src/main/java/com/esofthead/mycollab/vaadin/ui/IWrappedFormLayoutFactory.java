package com.esofthead.mycollab.vaadin.ui;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public interface IWrappedFormLayoutFactory extends IFormLayoutFactory {
    IFormLayoutFactory getWrappedFactory();
}
