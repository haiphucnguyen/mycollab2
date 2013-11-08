/**
 * This file is part of mycollab-api.
 *
 * mycollab-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-api.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.rest.server.signup;

import com.esofthead.mycollab.core.UserInvalidInputException;

public class ExistingEmailRegisterException extends UserInvalidInputException {

	public ExistingEmailRegisterException(final String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
