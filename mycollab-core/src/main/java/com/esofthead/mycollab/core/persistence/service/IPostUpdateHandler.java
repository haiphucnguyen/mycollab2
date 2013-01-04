/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.core.persistence.service;

/**
 *
 * @author haiphucnguyen
 */
public interface IPostUpdateHandler<T> {
    void postUpdate(T oldValue, T newValue);
}
