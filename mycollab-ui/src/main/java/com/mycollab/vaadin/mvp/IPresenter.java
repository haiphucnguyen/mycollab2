package com.mycollab.vaadin.mvp;

import com.vaadin.ui.HasComponents;

import java.io.Serializable;

/**
 * @param <V>
 * @author MyCollab Ltd
 * @since 2.0
 */
public interface IPresenter<V extends PageView> extends Serializable {

    /**
     * @param container
     * @param pageActionChain
     */
    void handleChain(HasComponents container, PageActionChain pageActionChain);

    /**
     * @param container
     * @param data
     */
    boolean go(HasComponents container, ScreenData<?> data);

    /**
     * @return
     */
    V getView();
}
