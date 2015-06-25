/**
 * This file is part of mycollab-config.
 *
 * mycollab-config is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-config is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-config.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.configuration;

import com.esofthead.mycollab.core.MyCollabException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class StorageManager {
    private static final Logger LOG = LoggerFactory.getLogger(StorageManager.class);
    private static final String S3_CONF_CLS = "com.esofthead.mycollab.ondemand.configuration.S3StorageConfiguration";
    private static StorageManager instance = new StorageManager();

    private Storage storage;
    private String storageSystem;

    private StorageManager() {
    }

    static {
        // Load storage configuration
        String storageSystem = ApplicationProperties.getString(ApplicationProperties.STORAGE_SYSTEM,
                Storage.FILE_STORAGE_SYSTEM);
        instance.storageSystem = storageSystem;
        if (Storage.FILE_STORAGE_SYSTEM.equals(storageSystem)) {
            instance.storage = new FileStorage();
        } else if (Storage.S3_STORAGE_SYSTEM.equals(storageSystem)) {
            try {
                Class<Storage> s3Conf = (Class<Storage>) Class.forName(S3_CONF_CLS);
                Storage newInstance = s3Conf.newInstance();
                instance.storage = newInstance;
            } catch (Exception e) {
                LOG.error(String.format("Can not load s3 file system with class %s", S3_CONF_CLS), e);
                System.exit(-1);
            }
        } else {
            throw new MyCollabException(String.format("Can not load storage  %s", storageSystem));
        }
    }

    public static String getAvatarLink(String userAvatarId, int size) {
        if (StringUtils.isBlank(userAvatarId)) {
            return MyCollabAssets.newResourceLink(String.format("icons/default_user_avatar_%d.png", size));
        } else {
            return instance.storage.getAvatarPath(userAvatarId, size);
        }
    }

    public static Storage getStorage() {
        return instance.storage;
    }

    public static boolean isFileStorage() {
        return Storage.FILE_STORAGE_SYSTEM.equals(instance.storageSystem);
    }

    public static boolean isS3Storage() {
        return Storage.S3_STORAGE_SYSTEM.equals(instance.storageSystem);
    }
}
