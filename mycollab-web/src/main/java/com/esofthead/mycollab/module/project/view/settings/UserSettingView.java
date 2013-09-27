/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.Component;

/**
 * 
 * @author haiphucnguyen
 */
public interface UserSettingView extends View {
	Component gotoSubView(String name);
}
