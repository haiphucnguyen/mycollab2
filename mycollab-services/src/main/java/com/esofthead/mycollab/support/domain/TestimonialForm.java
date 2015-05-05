package com.esofthead.mycollab.support.domain;

import javax.ws.rs.FormParam;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author MyCollab Ltd
 * @since 5.0.6
 */
public class TestimonialForm {
    ClientBuilder a;
    @FormParam("displayname")
    private String displayname;

    @FormParam("jobrole")
    private String jobrole;

    @FormParam("company")
    private String company;

    @FormParam("website")
    private String website;

    @FormParam("testimonial")
    private String testimonial;

    @FormParam("email")
    private String email;

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
