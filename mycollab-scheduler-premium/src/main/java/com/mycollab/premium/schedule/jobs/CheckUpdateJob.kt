package com.mycollab.premium.schedule.jobs

import com.mycollab.configuration.EnDecryptHelper
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.core.BroadcastMessage
import com.mycollab.core.Broadcaster
import com.mycollab.core.NewUpdateAvailableNotification
import com.mycollab.core.Version
import com.mycollab.core.utils.JsonDeSerializer
import com.mycollab.license.service.LicenseResolver
import com.mycollab.schedule.jobs.GenericQuartzJobBean
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.zip.ZipFile

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CheckUpdateJob : GenericQuartzJobBean() {

    @Autowired
    private val serverConfiguration: ServerConfiguration? = null

    @Autowired
    private val licenseResolver: LicenseResolver? = null

    @Throws(JobExecutionException::class)
    override fun executeJob(context: JobExecutionContext) {
        val restTemplate = RestTemplate()
        val licenseInfo = licenseResolver!!.licenseInfo
        val customerId = EnDecryptHelper.encryptText(licenseInfo!!.customerId!!)
        val result = restTemplate.getForObject(serverConfiguration!!.getApiUrl("checkpremiumupdate?version=" +
                Version.getVersion() + "&customerId=" + customerId), String::class.java)
        val props = JsonDeSerializer.fromJson(result, Properties::class.java)
        val version = props.getProperty("version")
        if (Version.isEditionNewer(version)) {
            if (!isDownloading) {
                if (latestFileDownloadedPath != null) {
                    val installerFile = File(latestFileDownloadedPath!!)
                    if (installerFile.exists() && installerFile.name.startsWith("mycollab" + version.replace('.', '_'))) {
                        return
                    }
                }
                LOG.info("There is the new version of MyCollab " + version)
                val autoDownloadLink = props.getProperty("autoDownload")
                val manualDownloadLink = props.getProperty("downloadLink")
                if (autoDownloadLink != null) {
                    try {
                        val downloadMyCollabThread = DownloadMyCollabThread(version, autoDownloadLink)
                        isDownloading = true
                        downloadMyCollabThread.start()
                        downloadMyCollabThread.join()
                        val installerFile = downloadMyCollabThread.tmpFile
                        if (installerFile.exists() && installerFile.isFile && installerFile.length() > 0 && isValid(installerFile)) {
                            latestFileDownloadedPath = installerFile.absolutePath
                            Broadcaster.broadcast(BroadcastMessage(NewUpdateAvailableNotification(version,
                                    autoDownloadLink, manualDownloadLink, latestFileDownloadedPath)))
                        } else {
                            Broadcaster.broadcast(BroadcastMessage(NewUpdateAvailableNotification(version, null,
                                    manualDownloadLink, null)))
                        }
                    } catch (e: Exception) {
                        LOG.error("Exception", e)
                    } finally {
                        isDownloading = false
                    }
                } else {
                    Broadcaster.broadcast(BroadcastMessage(NewUpdateAvailableNotification(version, autoDownloadLink,
                            manualDownloadLink, latestFileDownloadedPath)))
                }
            }
        }
    }

    private class DownloadMyCollabThread internal constructor(private val version: String, private val downloadLink: String) : Thread() {
        lateinit var tmpFile: File

        override fun run() {
            try {
                tmpFile = File.createTempFile("mycollab" + version.replace('.', '_'), ".zip")
                val url = URL(downloadLink)
                LOG.info("Start download the new MyCollab version at $downloadLink")
                var httpConn = url.openConnection() as HttpURLConnection
                val responseCode = httpConn.responseCode
                var inputStream: InputStream? = null

                // always check HTTP response code first
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // opens input stream from the HTTP connection
                    inputStream = httpConn.inputStream
                } else {
                    if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                            || responseCode == HttpURLConnection.HTTP_MOVED_PERM
                            || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                        // get redirect url from "location" header field
                        val newUrl = httpConn.getHeaderField("Location")

                        // get the cookie if need, for login
                        val cookies = httpConn.getHeaderField("Set-Cookie")

                        // open the new connection again
                        httpConn = URL(newUrl).openConnection() as HttpURLConnection
                        httpConn.setRequestProperty("Cookie", cookies)
                        httpConn.addRequestProperty("Accept-Language", "en-US,en;q=0.8")
                        httpConn.addRequestProperty("Referer", "google.com")
                        inputStream = httpConn.inputStream
                    }
                }
                if (inputStream != null) {
                    var loadedBytes = 0
                    // opens an output stream to save into file
                    FileOutputStream(tmpFile).use { outputStream ->
                        val buffer = ByteArray(4096)
                        var bytesRead = inputStream!!.read(buffer)
                        while (bytesRead != -1) {
                            outputStream.write(buffer, 0, bytesRead)
                            loadedBytes += bytesRead
                            LOG.info("  Progress: ${loadedBytes / 1024}")
                            bytesRead = inputStream!!.read(buffer)
                        }
                        outputStream.close()
                        inputStream!!.close()
                        httpConn.disconnect()
                        LOG.info("Download MyCollab edition successfully to $tmpFile.absolutePath")
                    }
                } else {
                    LOG.info("Can not download the new MyCollab. Reason is: $responseCode")
                }
            } catch (e: Exception) {
                LOG.error("Error while download $downloadLink", e)
            }

        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(CheckUpdateJob::class.java)

        private var isDownloading = false
        private var latestFileDownloadedPath: String? = null

        internal fun isValid(file: File): Boolean {
            try {
                ZipFile(file).use { _ -> return true }
            } catch (e: IOException) {
                return false
            }
        }
    }
}
