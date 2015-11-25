/*
 * Copyright 2014 eSoftHead Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.esofthead.vaadin.navigationbarquickmenu;

import com.esofthead.vaadin.navigationbarquickmenu.client.NavigationBarQuickMenuState;
import com.vaadin.ui.AbstractSingleComponentContainer;

public class NavigationBarQuickMenu extends AbstractSingleComponentContainer {

	private static final long serialVersionUID = 4214096999617161353L;

	public NavigationBarQuickMenu() {

	}

	@Override
	public NavigationBarQuickMenuState getState() {
		return (NavigationBarQuickMenuState) super.getState();
	}

	public void setButtonCaption(String captionString) {
		getState().buttonCaption = captionString;
	}

}
