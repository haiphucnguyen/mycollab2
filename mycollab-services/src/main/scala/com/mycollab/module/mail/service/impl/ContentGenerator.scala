package com.mycollab.module.mail.service.impl

import java.io._
import java.util.Locale

import com.mycollab.configuration.{ApplicationConfiguration, ServerConfiguration, SiteConfiguration}
import com.mycollab.module.file.service.AbstractStorageService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.schedule.email.MailStyles
import com.mycollab.spring.AppContextUtil
import freemarker.template.Configuration
import org.joda.time.LocalDate
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd.
  * @since 4.6.0
  */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ContentGenerator extends IContentGenerator with InitializingBean {
  private var templateContext: java.util.HashMap[String, Any] = _
  @Autowired private val applicationConfiguration: ApplicationConfiguration = null
  @Autowired private val templateEngine: Configuration = null

  @throws(classOf[Exception])
  def afterPropertiesSet() {
    templateContext = new java.util.HashMap[String, Any]()
    val defaultUrls = Map[String, String](
      "cdn_url" -> SiteConfiguration.getCdnUrl,
      "facebook_url" -> applicationConfiguration.getFacebookUrl,
      "google_url" -> applicationConfiguration.getGoogleUrl,
      "linkedin_url" -> applicationConfiguration.getLinkedinUrl,
      "twitter_url" -> applicationConfiguration.getTwitterUrl)
    putVariable("defaultUrls", defaultUrls)
    putVariable("current_year", new LocalDate().getYear)
    putVariable("siteName", SiteConfiguration.getDefaultSiteName)
    putVariable("styles", MailStyles.instance())

    val storageFactory = AppContextUtil.getSpringBean(classOf[AbstractStorageService])
    putVariable("storageFactory", storageFactory)
  }

  override def putVariable(key: String, value: Any): Unit = {
    import scala.collection.JavaConverters._
    value match {
      case map: Map[_, _] => templateContext.put(key, mapAsJavaMap(map))
      case list: List[_] => templateContext.put(key, seqAsJavaList(list))
      case _ => templateContext.put(key, value)
    }
  }

  override def parseFile(templateFilePath: String): String = parseFile(templateFilePath, null)

  override def parseFile(templateFilePath: String, locale: Locale): String = {
    val writer = new StringWriter
    val template = templateEngine.getTemplate(templateFilePath, locale)
    template.process(templateContext, writer)
    writer.toString
  }
}