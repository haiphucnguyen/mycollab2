package com.esofthead.mycollab.module.mail

import java.io._
import java.util.Locale

import com.esofthead.mycollab.configuration.{SharingOptions, SiteConfiguration}
import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.i18n.LocalizationHelper
import com.esofthead.mycollab.template.velocity.TemplateContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import scala.collection.immutable.HashMap

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ContentGenerator extends IContentGenerator with InitializingBean {
  private var templateContext: TemplateContext = null
  @Autowired private val templateEngine: VelocityEngine = null

  @throws(classOf[Exception])
  def afterPropertiesSet {
    templateContext = new TemplateContext
    val defaultUrls: Map[String, String] = new HashMap[String, String]
    val sharingOptions: SharingOptions = SharingOptions.getDefaultSharingOptions
    defaultUrls.+(("cdn_url", SiteConfiguration.getCdnUrl), ("facebook_url", sharingOptions.getFacebookUrl),
      ("google_url", sharingOptions.getGoogleplusUrl), ("linkedin_url", sharingOptions.getLinkedinUrl),
      ("twitter_url", sharingOptions.getTwitterUrl))
    templateContext.put("defaultUrls", defaultUrls)
  }

  override def putVariable(key: String, value: scala.Any): Unit = templateContext.put(key, value)

  override def generateBodyContent(templateFilePath: String): String = {
    val writer: StringWriter = new StringWriter
    val resourceStream: InputStream = classOf[LocalizationHelper].getClassLoader.getResourceAsStream(templateFilePath)

    var reader: Reader = null
    try {
      reader = new InputStreamReader(resourceStream, "UTF-8")
    }
    catch {
      case e: UnsupportedEncodingException => {
        reader = new InputStreamReader(resourceStream)
      }
    }

    templateEngine.evaluate(templateContext.getVelocityContext, writer, "log task", reader)
    return writer.toString
  }

  override def generateBodyContent(templateFilePath: String, currentLocale: Locale): String = this.generateBodyContent(templateFilePath, currentLocale, null)

  override def generateBodyContent(templateFilePath: String, currentLocale: Locale, defaultLocale: Locale): String = {
    val writer: StringWriter = new StringWriter
    var reader: Reader = LocalizationHelper.templateReader(templateFilePath, currentLocale)
    if (reader == null) {
      if (defaultLocale == null) {
        throw new MyCollabException("Can not find file " + templateFilePath + " in locale " + currentLocale)
      }
      reader = LocalizationHelper.templateReader(templateFilePath, currentLocale)
      if (reader == null) {
        throw new MyCollabException("Can not find file " + templateFilePath + " in locale " + currentLocale + " and default locale " + defaultLocale)
      }
    }

    templateEngine.evaluate(templateContext.getVelocityContext, writer, "log task", reader)
    return writer.toString
  }

  override def generateSubjectContent(subject: String): String = {
    val writer: StringWriter = new StringWriter
    val reader: Reader = new StringReader(subject)
    templateEngine.evaluate(templateContext.getVelocityContext, writer, "log task", reader)
    return writer.toString
  }
}
