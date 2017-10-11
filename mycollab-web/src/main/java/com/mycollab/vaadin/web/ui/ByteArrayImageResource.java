/**
 * mycollab-web - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui;

import com.vaadin.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ByteArrayImageResource extends StreamResource {
    private static final long serialVersionUID = 1L;

    public ByteArrayImageResource(final byte[] imageData, String mimeType) {
        super(new StreamResource.StreamSource() {
            private static final long serialVersionUID = 1L;

            public InputStream getStream() {
                return new ByteArrayInputStream(imageData);
            }
        }, "avatar");

        this.setMIMEType(mimeType);
    }
}
