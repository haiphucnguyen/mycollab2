package com.esofthead.mycollab.core;

import com.esofthead.mycollab.core.utils.FileUtils;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class SystemProperties {
    private static final Logger LOG = LoggerFactory.getLogger(SystemProperties.class);

    private static Properties properties;
    private static String id;

    static {
        try {
            File homeFolder = FileUtils.getHomeFolder();
            File sysFile = new File(homeFolder, ".app.properties");
            properties = new Properties();
            if (sysFile.isFile() && sysFile.exists()) {
                properties.load(new FileInputStream(sysFile));
            } else {
                properties.setProperty("id", UUID.randomUUID().toString() + new LocalDateTime().getMillisOfSecond());
                properties.store(new FileOutputStream(sysFile), "");
            }
        } catch (IOException e) {
            LOG.error("Error", e);
        }
    }

    public static String getId() {
        return properties.getProperty("id", UUID.randomUUID().toString() + new LocalDateTime().getMillisOfSecond());
    }
}
