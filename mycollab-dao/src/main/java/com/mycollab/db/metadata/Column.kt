package com.mycollab.db.metadata

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Column(val value: String)
