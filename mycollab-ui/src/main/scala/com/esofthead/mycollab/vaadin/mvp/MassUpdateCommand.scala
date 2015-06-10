package com.esofthead.mycollab.vaadin.mvp

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
trait MassUpdateCommand[V] {
    def massUpdate(value: V)
}
