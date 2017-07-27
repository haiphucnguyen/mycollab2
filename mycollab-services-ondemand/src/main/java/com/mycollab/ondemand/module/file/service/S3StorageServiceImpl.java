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
package com.mycollab.ondemand.module.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.ondemand.configuration.AmazonServiceConfiguration;
import org.springframework.stereotype.Service;

/**
 * Amazon S3 Configuration
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service
public class S3StorageServiceImpl extends AbstractStorageService {

    public final AmazonS3 newS3Client() {
        return new AmazonS3Client(AmazonServiceConfiguration.amazonCredentials());
    }

    public String getBucket() {
        return AmazonServiceConfiguration.getBucket();
    }

    @Override
    public String generateAssetRelativeLink(String resourceId) {
        return SiteConfiguration.getCdnUrl() + resourceId;
    }
}
