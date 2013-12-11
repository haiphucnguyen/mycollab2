/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.events;

/**
 *
 * @author MyCollab Ltd.
 */
public class DefaultPreviewFormHandler<T> implements PreviewFormHandler<T> {

    @Override
    public void gotoNext(T data) {
    }

    @Override
    public void gotoPrevious(T data) {
    }

    @Override
    public void onEdit(T data) {
    }

    @Override
    public void onDelete(T data) {
    }

    @Override
    public void onClone(T data) {
    }

    @Override
    public void onCancel() {
    }

	@Override
	public void onAssign(T data) {
	}
}
