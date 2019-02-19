package com.mycollab.ondemand.module.file.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "s3")
data class S3Configuration(var key:String, var secretKey:String, var bucket:String)