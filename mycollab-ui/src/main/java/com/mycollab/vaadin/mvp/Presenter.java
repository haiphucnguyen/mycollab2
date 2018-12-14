package com.mycollab.vaadin.mvp;

import com.vaadin.navigator.View;

import java.io.Serializable;

public class Presenter<V extends View> implements Serializable {
    protected V view;

    public void initView(V view) {
        this.view = view;
    }
}
