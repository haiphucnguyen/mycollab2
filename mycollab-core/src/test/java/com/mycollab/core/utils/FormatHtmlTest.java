/**
 * mycollab-core - Parent pom providing dependency and plugin management for applications
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
/**
 * mycollab-core - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
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
package com.mycollab.core.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author MyCollab Ltd
 * @since 5.0.8
 */
public class FormatHtmlTest {
    @Test
    public void testFormatHtml() {
        String mixTextAndHtml = StringUtils.formatRichText("Hello world https://community.mycollab.com <b>Hai Nguyen</b>");
        Assert.assertEquals("Hello world \n" +
                "<a href=\"https://community.mycollab.com\" target=\"_blank\">https://community.mycollab.com</a> \n" +
                "<b>Hai Nguyen</b>", mixTextAndHtml);

        String pureHtml = StringUtils.formatRichText("https://mycollab.com");
        Assert.assertEquals("<a href=\"https://mycollab.com\" target=\"_blank\">https://mycollab.com</a>", pureHtml);
    }
}
