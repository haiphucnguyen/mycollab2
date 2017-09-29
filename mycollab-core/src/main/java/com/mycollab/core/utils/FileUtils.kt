package com.mycollab.core.utils

import com.google.common.base.MoreObjects
import com.mycollab.core.MyCollabException
import com.mycollab.core.UserInvalidInputException
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.StandardCopyOption.REPLACE_EXISTING
import java.util.*
import java.util.regex.Pattern

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
object FileUtils {

    private var _homeFolder: File

    init {
        val userFolder = System.getProperty("user.dir")
        val homeDir = File(userFolder + "/.mycollab")
        val userHomeDir = File(System.getProperty("user.home") + "/.mycollab")
        if (userHomeDir.exists()) {
            _homeFolder = try {
                Files.move(userHomeDir.toPath(), homeDir.toPath(), REPLACE_EXISTING)
                homeDir
            } catch (e: Exception) {
                userHomeDir
            }

        } else {
            _homeFolder = when {
                homeDir.exists() -> homeDir
                else -> {
                    FileUtils.mkdirs(homeDir)
                    homeDir
                }
            }
        }
    }

    @JvmStatic
    val homeFolder
        get() = _homeFolder

    @JvmStatic
    val userFolder: File
        get() {
            val userDir = MoreObjects.firstNonNull(System.getProperty("MYCOLLAB_APP_HOME"), System.getProperty("user.dir"))
            return File(userDir)
        }

    @JvmStatic
    fun readFileAsPlainString(fileName: String): String {
        try {
            val pricingFile = FileUtils.getDesireFile(userFolder, fileName, "src/main/conf/" + fileName)
            return when {
                pricingFile != null -> String(Files.readAllBytes(pricingFile.toPath()), Charset.forName("UTF-8"))
                else -> ""
            }
        } catch (e: IOException) {
            throw MyCollabException(e)
        }

    }

    @JvmStatic
    fun getVolumeDisplay(volume: Long?): String {
        val GB_SIZE = (1024 * 1024 * 1024).toLong()
        val MB_SIZE = (1024 * 1024).toLong()
        val KB_SIZE = 1024F
        return when {
            volume == null -> "0 Kb"
            volume < KB_SIZE -> volume.toString() + " Bytes"
            volume < MB_SIZE -> (Math.floor((volume / KB_SIZE * 100).toDouble()) / 100).toString() + " Kb"
            volume < GB_SIZE -> (Math.floor((volume / MB_SIZE * 100).toDouble()) / 100).toString() + " Mb"
            else -> (Math.floor((volume / GB_SIZE * 100).toDouble()) / 100).toString() + " Gb"
        }
    }

    /**
     * @param baseFolder
     * @param relativePaths
     * @return null if can not get the folder
     */
    @JvmStatic
    fun getDesireFile(baseFolder: File, vararg relativePaths: String): File? {
        var file: File
        relativePaths.forEach {
            file = File(baseFolder, it)
            if (file.exists()) {
                return file
            }
        }
        return null
    }

    @JvmStatic
    fun mkdirs(file: File) {
        if (file.exists()) {
            return
        }
        try {
            Files.createDirectories(file.toPath())
        } catch (e: IOException) {
            throw MyCollabException(e)
        }
    }

    private val INVALID_RESOURCE_BASENAMES = arrayOf("aux", "com1", "com2", "com3", "com4", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "com5", "com6", "com7", "com8", "com9", "con", "lpt1", "lpt2", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
            "lpt3", "lpt4", "lpt5", "lpt6", "lpt7", "lpt8", "lpt9", "nul", "prn") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$

    private val INVALID_RESOURCE_FULLNAMES = arrayOf("clock$") //$NON-NLS-1$;

    @JvmStatic
    fun isValidFileName(name: String): Boolean {
        if (name == "." || name == "..")
        //$NON-NLS-1$ //$NON-NLS-2$
            return false
        //empty names are not valid
        val length = name.length
        if (length == 0)
            return false
        val lastChar = name[length - 1]
        // filenames ending in dot are not valid
        if (lastChar == '.')
            return false
        // file names ending with whitespace are truncated (bug 118997)
        if (Character.isWhitespace(lastChar))
            return false
        val dot = name.indexOf('.')
        //on windows, filename suffixes are not relevant to name validity
        val basename = if (dot == -1) name else name.substring(0, dot)
        return if (Arrays.binarySearch(INVALID_RESOURCE_BASENAMES, basename.toLowerCase()) >= 0) false else Arrays.binarySearch(INVALID_RESOURCE_FULLNAMES, name.toLowerCase()) < 0
    }

    private val ILLEGAL_FOLDER_PATTERN = Pattern.compile("[.<>:&/\\|?*&%()\\+\\-\\[\\]]")

    @JvmStatic
    fun assertValidFolderName(name: String) {
        val matcher = ILLEGAL_FOLDER_PATTERN.matcher(name)
        if (matcher.find()) {
            throw UserInvalidInputException("Please enter valid folder name except any follow characters: " + ILLEGAL_FOLDER_PATTERN.pattern())
        }
    }

    @JvmStatic
    fun escape(fileName: String): String {
        return fileName.replace("[<>:&/\\|?*&%()\\+\\-\\[\\]]".toRegex(), "")
    }
}
