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
package com.mycollab.db.arguments

import java.util.Date

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class RangeDateSearchField : SearchField {

    var from: Date? = null
    var to: Date? = null

    constructor()

    constructor(from: Date, to: Date) : this(SearchField.AND, from, to)

    constructor(oper: String, from: Date, to: Date) {
        this.operation = oper
        this.from = from
        this.to = to
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
