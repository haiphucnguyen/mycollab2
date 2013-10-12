// Copyright (C) 2010 - Will Glozer.  All rights reserved.

package com.esofthead.mycollab.jetty;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

import org.eclipse.jetty.security.authentication.SessionAuthentication;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.UserIdentity;

/**
 * Serializable (by JBoss Marshalling) implementation of Jetty session {@link Authentication}.
 * This requires a serializable implementation of {@link UserIdentity}.
 *
 * @author  Will Glozer
 */
public class SerializableSessionAuthentication implements Authentication.User,
    HttpSessionActivationListener, HttpSessionBindingListener, Serializable {

    private static String SESSION_SECURED = "org.eclipse.jetty.security.secured";

    private String method;
    private UserIdentity identity;

    private transient HttpSession session;

    /**
     * No-arg constructor for JBoss Marshalling.
     */
    private SerializableSessionAuthentication() {
        super();
    }

    /**
     * Create a new instance.
     *
     * @param   method      Authentication method.
     * @param   identity    Authenticated user's identity.
     */
    public SerializableSessionAuthentication(String method, UserIdentity identity) {
        this.method = method;
        this.identity = identity;
    }

    @Override
    public String getAuthMethod() {
        return method;
    }

    @Override
    public UserIdentity getUserIdentity() {
        return identity;
    }

    @Override
    public boolean isUserInRole(UserIdentity.Scope scope, String role) {
        return identity.isUserInRole(role, scope);
    }

    @Override
    public void logout() {
        if (session != null) {
            session.removeAttribute(SessionAuthentication.__J_AUTHENTICATED);
            session.removeAttribute(SESSION_SECURED);
        }
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent event) {
        if (session == null) session = event.getSession();
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent event) {
        // nothing to do here
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        if (session == null) session = event.getSession();
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // nothing to do here
    }
}
