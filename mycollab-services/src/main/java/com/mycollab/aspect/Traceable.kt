package com.mycollab.aspect

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Traceable(val idField: String = "id", val nameField: String, val extraFieldName: String = "")
