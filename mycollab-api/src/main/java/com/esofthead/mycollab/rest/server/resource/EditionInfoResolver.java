package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.core.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class EditionInfoResolver {
    private static EditionInfo editionInfo = new EditionInfo();

    public static void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File versionFile = FileUtils.getDesireFile(System.getProperty("user.dir"), "version", "src/main/conf/version");
                if (versionFile == null || !versionFile.exists()) {
                    throw new MyCollabException("Can not find version file");
                } else {
                    // Create a new Watch Service
                    try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                        loadEditionInfo(versionFile);
                        Path path = Paths.get(versionFile.getParent());
                        // Register events
                        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                        while (true) {
                            // Obtaining watch keys
                            final WatchKey key = watchService.poll(10, TimeUnit.SECONDS);
                            if (key != null) {
                                // key value can be null if no event was triggered
                                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                                    final WatchEvent.Kind<?> kind = watchEvent.kind();
                                    // Overflow event
                                    if (StandardWatchEventKinds.OVERFLOW == kind) {
                                        continue; // loop
                                    } else if (StandardWatchEventKinds.ENTRY_MODIFY == kind) {
                                        // A new Path was created
                                        Path modifiedPath = ((WatchEvent<Path>) watchEvent).context();
                                        // Output
                                        String fileName = modifiedPath.toFile().getName();
                                        if ("version".equals(modifiedPath.toFile().getName())) {
                                            loadEditionInfo(versionFile);
                                        }
                                    }
                                }
                                if (!key.reset()) {
                                    break; //loop
                                }
                            }

                        }
                    } catch (IOException | InterruptedException e) {
                        throw new MyCollabException(e);
                    }
                }
            }
        }).start();
    }

    private static void loadEditionInfo(File versionFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(versionFile));
        editionInfo.setVersion(properties.getProperty("version", "0"));
        editionInfo.setCommunityDownloadLink(properties.getProperty("communityDownloadLink", ""));
        editionInfo.setAltCommunityDownloadLink(properties.getProperty("altCommunityDownloadLink", ""));
        editionInfo.setCommunityUpgradeLink(properties.getProperty("communityUpgradeLink", ""));
        editionInfo.setPremiumDownloadLink(properties.getProperty("premiumDownloadLink", ""));
        editionInfo.setPremiumUpgradeLink(properties.getProperty("premiumUpgradeLink", ""));
    }

    public static EditionInfo getEditionInfo() {
        return editionInfo;
    }

    public static void main(String[] args) {
        init();
    }
}
