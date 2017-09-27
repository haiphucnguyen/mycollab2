package com.mycollab.vaadin.mvp

import com.mycollab.db.arguments.SearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
open class ScreenData<P>(var params: P?) {

    class Add<P>(params: P?) : ScreenData<P>(params)

    class Edit<P>(params: P?) : ScreenData<P>(params)

    class Preview<P>(params: P?) : ScreenData<P>(params)

    class Search<P : SearchCriteria>(params: P?) : ScreenData<P>(params)
} 