/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.utils.FieldGroupFormatter;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceFieldFormatter extends FieldGroupFormatter {
    private static InvoiceFieldFormatter _instance = new InvoiceFieldFormatter();

    private InvoiceFieldFormatter() {
        super();

        this.generateFieldDisplayHandler(Invoice.Field.contactuserfullname.name(), InvoiceI18nEnum.FORM_CONTACT_PERSON);
    }

    public static InvoiceFieldFormatter instance() {
        return _instance;
    }
}
