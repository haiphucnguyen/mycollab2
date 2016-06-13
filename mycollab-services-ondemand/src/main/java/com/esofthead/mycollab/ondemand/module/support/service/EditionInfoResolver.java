package com.esofthead.mycollab.ondemand.module.support.service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.ondemand.module.support.domain.EditionInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

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
@Service
public class EditionInfoResolver implements InitializingBean {
    private EditionInfo editionInfo = new EditionInfo();

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File versionFile = FileUtils.getDesireFile(FileUtils.getUserFolder(), "version", "src/main/conf/version");
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

    private void loadEditionInfo(File versionFile) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(versionFile));
        editionInfo.setVersion(properties.getProperty("version", "0"));
        editionInfo.setCommunityDownloadLink(properties.getProperty("communityDownloadLink", ""));
        editionInfo.setAltCommunityDownloadLink(properties.getProperty("altCommunityDownloadLink", ""));
        editionInfo.setCommunityUpgradeLink(properties.getProperty("communityUpgradeLink", ""));
        editionInfo.setPremiumDownloadLink(properties.getProperty("premiumDownloadLink", ""));
        editionInfo.setPremiumUpgradeLink(properties.getProperty("premiumUpgradeLink", ""));
    }

    public EditionInfo getEditionInfo() {
        return editionInfo;
    }
}