package com.mycollab.pro.module.file.view

import com.mycollab.vaadin.mvp.CacheableComponent
import com.vaadin.ui.Window

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
interface OauthWindowFactory : CacheableComponent {
    fun newDropBoxAuthWindow(): Window
}
