/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.resources;

import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class StreamDownloadResourceUtil {

    public static String getDownloadFileName(Collection<Resource> lstRes) {
        if (CollectionUtils.isEmpty(lstRes)) {
            throw new UserInvalidInputException("No selected file to download");
        } else if (lstRes.size() == 1) {
            Resource resource = lstRes.iterator().next();
            return (resource instanceof Folder) ? "out.zip" : resource.getName();
        } else {
            return "out.zip";
        }

    }

    public static StreamResource getStreamResourceSupportExtDrive(List<Resource> lstRes) {
        String filename = getDownloadFileName(lstRes);
        StreamSource streamSource = getStreamSourceSupportExtDrive(lstRes);
        return new StreamResource(streamSource, filename);
    }

    public static StreamSource getStreamSourceSupportExtDrive(Collection<Resource> lstRes) {
        if (CollectionUtils.isEmpty(lstRes)) {
            throw new UserInvalidInputException("You must select at least one file");
        } else {
            return new StreamDownloadResourceSupportExtDrive(lstRes);
        }
    }
}
