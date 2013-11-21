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
package com.esofthead.mycollab.rest.server.domain;

import javax.ws.rs.FormParam;

public class ContactForm {

	@FormParam("name")
	private String name;

	@FormParam("email")
	private String email;

	@FormParam("company")
	private String company;

	@FormParam("role")
	private String role;

	@FormParam("industry")
	private String industry;

	@FormParam("budget")
	private String budget;

	@FormParam("subject")
	private String subject;

	@FormParam("message")
	private String message;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(final String company) {
		this.company = company;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(final String industry) {
		this.industry = industry;
	}

	public String getBudget() {
		return this.budget;
	}

	public void setBudget(final String budget) {
		this.budget = budget;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
