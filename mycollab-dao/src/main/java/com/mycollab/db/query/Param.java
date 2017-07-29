package com.mycollab.db.query;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public abstract class Param implements Serializable {
    protected String id;

    public Param() {
        this("");
    }

    public Param(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Param item = (Param) obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}