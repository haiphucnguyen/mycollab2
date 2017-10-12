/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.configuration.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.FileUtils;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class LogConfig {

    public static void initMyCollabLog() {
        // Optionally remove existing handlers attached to j.u.l root logger
//        SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
//        SLF4JBridgeHandler.install();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        InputStream inputStream = LogConfig.class.getClassLoader().getResourceAsStream("logback-test.xml");
        if (inputStream == null) {
            try {
                File configFile = FileUtils.getDesireFile(FileUtils.getUserFolder(), "config/logback.xml", "src/main/config/logback.xml");
                if (configFile != null) inputStream = new FileInputStream(configFile);
            } catch (FileNotFoundException e) {
                inputStream = LogConfig.class.getClassLoader().getResourceAsStream("logback.xml");
            }
        }

        if (inputStream != null) {
            try {
                configurator.setContext(loggerContext);
                configurator.doConfigure(inputStream); // loads logback file
            } catch (Exception e) {
                throw new MyCollabException(e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new MyCollabException(e);
                }
            }
        }
    }
}
